package com.jwt.security.service;

import com.jwt.security.model.User;
import com.jwt.security.repository.UserRepository;
import com.jwt.security.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(Long.parseLong(id));
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(id.toString());
        }
        return new UserDetailsImpl(user.get());
    }
}
