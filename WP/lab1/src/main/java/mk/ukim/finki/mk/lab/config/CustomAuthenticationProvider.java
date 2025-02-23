package mk.ukim.finki.mk.lab.config;

import mk.ukim.finki.mk.lab.model.exceptions.NonExistingUserException;
import mk.ukim.finki.mk.lab.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String pass=authentication.getCredentials().toString();

        if(username.isEmpty() || pass.isEmpty()){
            throw new BadCredentialsException("Empty credentials");
        }

        UserDetails userDetails=null;
        try {
            userDetails=this.userService.loadUserByUsername(username);
        }catch (UsernameNotFoundException e){
            throw new BadCredentialsException("User not found");
        }

        if(!passwordEncoder.matches(pass,userDetails.getPassword())){
            throw new BadCredentialsException("Password is incorrect");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

 */
