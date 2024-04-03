package com.bmcomis2018.cryptgui.algos;

public interface SymmetricAlgo<T> { // T is the type of the key (int, char[])
    public void setKey(T key); 
    public T getKey();

    public char[] encrypt(char[] plaintext);
    public char[] decrypt(char[] ciphertext);

    public T validateKey(String key);
    public void generateKey();
}
