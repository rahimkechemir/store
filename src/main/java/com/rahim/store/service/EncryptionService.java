package com.rahim.store.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    @Value("${encryption.salt.rounds}")
    private int saltRounds;

    public String encryptpassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(saltRounds));
    }

    public boolean verifypassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

}