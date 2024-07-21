package com.srimani7.elibrary;

import com.srimani7.elibrary.Entity.MyUser;
import com.srimani7.elibrary.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

    public Optional<MyUser> registerUser(String name, String password, String role) {
        var exists = userRepository.existsByUsername(name);
        if (exists) return Optional.empty();
        else {
            var myuser = new MyUser();
            myuser.setUsername(name);
            myuser.setPassword(passwordEncoder.encode(password));
            myuser.setRole(role);
            var user = userRepository.save(myuser);
            return Optional.of(user);
        }
    }
}