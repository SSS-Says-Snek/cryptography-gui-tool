package com.bmcomis2018.cryptgui;

import com.bmcomis2018.cryptgui.algos.AESAlgo;
import com.bmcomis2018.cryptgui.algos.AESImpl;
import com.bmcomis2018.cryptgui.algos.CaesarCipher;
import com.bmcomis2018.cryptgui.algos.RSA;
import com.bmcomis2018.cryptgui.gui.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.math.BigInteger;

import javax.swing.*;

public class Main
{
    public static void main( String[] args )
    {
        // Actually good UI
        FlatLightLaf.setup();

        JFrame frame = new JFrame();
        JTabbedPane cryptAlgorithmOptions = new JTabbedPane();

        SymmetricAlgoGui<Integer> caesar = new SymmetricAlgoGui<>(new CaesarCipher(), frame);
        AsymmetricAlgoGui<BigInteger> rsa = new AsymmetricAlgoGui<>(new RSA(2048), frame);
        SymmetricAlgoGui<char[]> aes128 =  new SymmetricAlgoGui<>(new AESAlgo(AESImpl.AES_128_KEYLEN), frame);
        SymmetricAlgoGui<char[]> aes192 =  new SymmetricAlgoGui<>(new AESAlgo(AESImpl.AES_192_KEYLEN), frame);
        SymmetricAlgoGui<char[]> aes256 =  new SymmetricAlgoGui<>(new AESAlgo(AESImpl.AES_256_KEYLEN), frame);

        cryptAlgorithmOptions.addTab("Caesar Cipher", caesar.getMainPanel());
        cryptAlgorithmOptions.addTab("RSA 2048", rsa.getMainPanel());
        cryptAlgorithmOptions.addTab("AES 128", aes128.getMainPanel());
        cryptAlgorithmOptions.addTab("AES 192", aes192.getMainPanel());
        cryptAlgorithmOptions.addTab("AES 256", aes256.getMainPanel());


        // Boilerplate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(cryptAlgorithmOptions);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
