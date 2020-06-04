package lt.codeacademy.blog.services;

import lt.codeacademy.blog.entities.User;
import lt.codeacademy.blog.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public MyUserDetailsService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No user found by name: " + username));
    }

    public User newUser(User user){
        User userNew= new User();
        userNew.setUsername(user.getUsername());
        userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(userNew);
    }

}
