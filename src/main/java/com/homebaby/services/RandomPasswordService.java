package com.homebaby.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RandomPasswordService {
    private final int amountCharacter;

    private RandomPasswordService(){
        this.amountCharacter = 6;
    }

    public String generate(){
        Random random = new Random();

        char[] caracteres =  "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        char[] generatePassword = new char[this.amountCharacter];

        for(int i = 0; i < amountCharacter; i++){
            generatePassword[i] = caracteres[random.nextInt(caracteres.length)];
        }

        return new String(generatePassword);
    }
}
