package com.example.demo.domain.user;

import com.example.demo.domain.requests.SignupData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repo.findByLogin(login);
    }

    public User registerNewUser(SignupData data) throws Exception {
        boolean alreadyExists = repo.existsByLogin(data.login());
        if(alreadyExists){ throw new Exception("Email already registered"); }

        var hashedPass = passwordEncoder.encode(data.password());
        return repo.save(new User(data.login(), hashedPass));
    }

    public UserDetails findAuthenticatedUser(String subject) {
        return repo.findByLogin(subject);
    }
}
