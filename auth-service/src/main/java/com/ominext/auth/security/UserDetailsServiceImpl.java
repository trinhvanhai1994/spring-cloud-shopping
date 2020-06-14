package com.ominext.auth.security;

import com.ominext.auth.feign.customer.CustomerClient;
import com.ominext.common.model.response.CustomerResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final CustomerClient customerClient;

    public UserDetailsServiceImpl(BCryptPasswordEncoder encoder, CustomerClient customerClient) {
        this.encoder = encoder;
        this.customerClient = customerClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CustomerResponse customer = customerClient.findByUsername(username);
            System.out.println(customer);
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + customer.getRole());

            String password = encoder.encode(customer.getPassword());
            return new User(customer.getUsername(), password, grantedAuthorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username: " + username + " not found");
        }
    }
}