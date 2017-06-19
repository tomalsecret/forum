package com.Forum.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Tomal on 2017-05-26.
 */
public class PasswordEncoderGenerator {

    public static String generate(String pass) {

        int i = 0;
        String hashedPassword = "";
        while (i < 10) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            hashedPassword = passwordEncoder.encode(pass);
            i++;
        }
        return hashedPassword;
    }

}
