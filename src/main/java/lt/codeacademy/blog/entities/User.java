package lt.codeacademy.blog.entities;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Collection;
// kai bus roles, turi atspausdinti userio role frontende
@Data
@Entity
@Table(name = "Users")

public class User implements UserDetails {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

//naudot custom validatoriu// try catch exception
    @Column(name = "username", unique = true)
    @NotBlank
    private String username;
//naudot custom validatoriu slapt patikrinimui//NotBlank nepraleidzia tarpu
    @NotBlank
    @Column(name = "password")
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
