package com.tfe.fournil.service;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/** * The type Generate password. */
@Slf4j
public class GeneratePassword {
    /**     * Generate password string.     *
     * * @param length the length
     * * @return the string     */
    public static String generatePassword(int length)
    {
        log.info("Generate psw length {}", length);
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String spl = "@!@#?^$*";
        String pwd = letters + numbers + spl;
        Random random = new Random();
        char[] newPassword = new char[length];
        for(int i=0; i<length; i++){
            newPassword[i] = pwd.charAt(random.nextInt(pwd.length()));        }
        log.info("Return password : {}", String.valueOf(newPassword));
        return String.valueOf(newPassword);
    }
}
