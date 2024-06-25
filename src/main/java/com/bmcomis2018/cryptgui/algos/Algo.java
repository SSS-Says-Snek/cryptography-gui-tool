package com.bmcomis2018.cryptgui.algos;

public interface Algo { // T is the type of the key (int, char[])
    char[] encrypt(char[] plaintext);
    char[] decrypt(char[] ciphertext);

    void generateKey();
}
