package com.bmcomis2018.cryptgui.algos;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESAlgo implements SymmetricAlgo<char[]> {
    private char[] key;

    public AESAlgo(int keylenBytes) {
        key = new char[keylenBytes * 4];
    }

    @Override
    public char[] getKey() {
        return key;
    }

    @Override
    public void setKey(char[] key) {
        // TODO
    }

    @Override
    public char[] validateKey(String key) {
        // TODO
        return new char[0];
    }

    @Override
    public char[] encrypt(char[] plaintext) {
        char[] ciphertext = AESImpl.encrypt(plaintext, key);
        return Base64.getEncoder().encodeToString(new String(ciphertext).getBytes()).toCharArray();
    }

    @Override
    public char[] decrypt(char[] ciphertext) {
        byte[] bytes = Base64.getDecoder().decode(new String(ciphertext).getBytes());
        return AESImpl.decrypt(new String(bytes).toCharArray(), key);
    }

    @Override
    public void generateKey() {
        SecureRandom rng = new SecureRandom();
        for (int i = 0; i < key.length; i++) {
            key[i] = (char)rng.nextInt(0, 256);
        }
    }

    @Override
    public String getKeyString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            result.append(Integer.toHexString(key[i]));
            if (i != key.length - 1) {
                result.append(' ');
            }
        }
        return result.toString();
    }
}
