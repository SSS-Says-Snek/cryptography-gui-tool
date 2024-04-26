package com.bmcomis2018.cryptgui.algos;

public interface Algo { // T is the type of the key (int, char[])
    public char[] encrypt(char[] plaintext);
    public char[] decrypt(char[] ciphertext);

    public void generateKey();
}
