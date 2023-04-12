package br.com.lucas.Container.Store.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    public static String encoder(String senha){
        BCryptPasswordEncoder enconderPassword = new BCryptPasswordEncoder();
        return enconderPassword.encode(senha);
    }
}
