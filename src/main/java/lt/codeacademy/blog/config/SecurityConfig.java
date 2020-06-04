package lt.codeacademy.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2/**", "/signIn/**", "/", "/public/**", "/styles/**", "/img/**").permitAll()
                    .antMatchers("/private/**").authenticated()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/signIn")
                    .loginProcessingUrl("/signInSubmit")
                    .failureUrl("/signIn?error")
                    .and()
                .logout()
                .permitAll()
                .logoutUrl("/signOut")
                .deleteCookies();

        http.csrf()
                .ignoringAntMatchers("/h2/**");
        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
   public PasswordEncoder encoder(){ return PasswordEncoderFactories.createDelegatingPasswordEncoder();}
}
