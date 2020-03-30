package com.example.do_an_cnpm.passwordencoder;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordEncoderImpl extends BCryptPasswordEncoder {
}
