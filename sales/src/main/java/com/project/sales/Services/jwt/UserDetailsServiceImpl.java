package com.project.sales.Services.jwt;

import com.project.sales.Entity.User;
import com.project.sales.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findFirstByEmail(username);
        if(optionalUser.isEmpty())throw new UsernameNotFoundException("User Name Not Found",null);
        return  new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(),optionalUser.get().getPassword()
        ,new ArrayList<>());
    }
}
