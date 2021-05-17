package com.vodyanchuk.coursework.security;

import com.vodyanchuk.coursework.model.AuthorizationManager;
import com.vodyanchuk.coursework.repository.AuthorizationManagerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorizationManagerRepository authorizationManagerRepository;

    public UserDetailsServiceImpl(AuthorizationManagerRepository authorizationManagerRepository) {
        this.authorizationManagerRepository = authorizationManagerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthorizationManager user = authorizationManagerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return SecurityUser.fromUser(user);
    }
}
