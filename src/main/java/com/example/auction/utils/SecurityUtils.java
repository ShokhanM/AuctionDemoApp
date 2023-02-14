package com.example.auction.utils;


import com.example.auction.model.UserEntity;
import com.example.auction.repository.UserRepository;
import com.example.auction.service.impl.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityUtils {


    public static UserDetailsImpl getAuthUser() {
        return  (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
