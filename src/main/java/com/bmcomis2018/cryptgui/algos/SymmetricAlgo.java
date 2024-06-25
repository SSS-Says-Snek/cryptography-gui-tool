package com.bmcomis2018.cryptgui.algos;

public interface SymmetricAlgo<T> extends Algo { // T is the type of the key (int, char[])
    T getKey();
    void setKey(T key);
    T validateKey(String key);

    default String getKeyString() {
        return getKey().toString();
    };
}
