package com.bmcomis2018.cryptgui.algos;

public interface SymmetricAlgo<T> extends Algo { // T is the type of the key (int, char[])
    public T getKey();
    public void setKey(T key); 
    public T validateKey(String key);
}
