package mk.ukim.finki.mk.lab.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    private final PasswordEncoder passwordEncoder;
    //private final CustomAuthenticationProvider authenticationProvider;

//    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomAuthenticationProvider authenticationProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationProvider = authenticationProvider;
//    }

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /*
         .headers is used for the h2 db console because the menu is made of frames, and they are normally
         blocked due to security reason .Now I enabled  it to only allow frames that have common attributes
         to the current URL that I am located.

         .csrf disables cross site forgery attacks commands are transmitted from a user that is trusted.

         .anyRequest and .authenticated means that any request not covered by the rules above will require
         authentication.

         .httpBasic(Customizer.withDefaults()) enables the basic http authentication protocol


         */

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/","/events","/book","/register")
                        .permitAll()
                        .requestMatchers("/events/details/**","/eventBooking").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/events/**","/rest/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                //.httpBasic(Customizer.withDefaults())
                .formLogin( form -> form
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/login?error=badCredentials")
                        .defaultSuccessUrl("/events",true)
                )
                .logout( logout -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/login")
                )
                .exceptionHandling((ex) -> ex
                .accessDeniedPage("/access_denied")
                );

        return http.build();
    }

    //.failureHandler(new AuthenticationErrorHandler())

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1= User.builder()
                .username("l")
                .password(passwordEncoder.encode("l"))
                .roles("USER")
                .build();
        UserDetails user2= User.builder()
                .username("bojana")
                .password(passwordEncoder.encode("bo"))
                .roles("USER")
                .build();
        UserDetails admin= User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,user2,admin);
    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder managerBuilder= http
//                .getSharedObject(AuthenticationManagerBuilder.class);
//        managerBuilder.authenticationProvider(authenticationProvider);
//        return managerBuilder.build();
//    }

}
