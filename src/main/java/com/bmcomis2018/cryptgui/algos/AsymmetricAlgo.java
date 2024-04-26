package com.bmcomis2018.cryptgui.algos;

public interface AsymmetricAlgo<T> extends Algo { // T is the type of the key (int, char[])
    public void setKey(T e, T d);
    // public T validateKey(String encryptKey, String decryptKey); TODO

    public T getEncryptionKey();
    public T getDecryptionKey();
}
