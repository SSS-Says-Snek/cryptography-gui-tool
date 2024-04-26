package com.bmcomis2018.cryptgui.algos;

import java.util.concurrent.ThreadLocalRandom;

public class CaesarCipher implements SymmetricAlgo<Integer> {
    private int key;

    public CaesarCipher() {
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getKey() {
        return key;
    }

    public char[] encrypt(char[] plaintext) {
        char[] result = new char[plaintext.length];
        
        for (int i = 0; i < plaintext.length; i++) {
            char inputByte = plaintext[i];
            if (65 <= inputByte && inputByte <= 90) {
                inputByte = (char)((inputByte - 65 + key) % 26 + 65);
            } else if (97 <= inputByte && inputByte <= 122) {
                inputByte = (char)((inputByte - 97 + key) % 26 + 97);
            }
            result[i] = inputByte;
        }
        return result;
    }

    // Bro it's almost literally the same
    public char[] decrypt(char[] ciphertext) {
        char[] result = new char[ciphertext.length];
        
        for (int i = 0; i < ciphertext.length; i++) {
            char inputByte = ciphertext[i];
            if (65 <= inputByte && inputByte <= 90) {
                inputByte = (char)(Math.floorMod(inputByte - 65 - key, 26) + 65);
            } else if (97 <= inputByte && inputByte <= 122) {
                inputByte = (char)(Math.floorMod(inputByte - 97 - key, 26) + 97);
            }
            result[i] = inputByte;
        }
        return result;
    }

    public Integer validateKey(String potentialKey) {
        int key = Integer.parseInt(potentialKey); // Propagate err

        if (0 <= key && key <= 25) {
            return key;
        }
        throw new NumberFormatException("You suck");
    }

    public void generateKey() {
        key = ThreadLocalRandom.current().nextInt(0, 26);
    }

}
