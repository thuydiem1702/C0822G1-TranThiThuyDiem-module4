package com.ss7;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class demo {
    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123"));

    }
}