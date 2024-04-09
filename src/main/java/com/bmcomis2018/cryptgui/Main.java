package com.bmcomis2018.cryptgui;

import com.bmcomis2018.cryptgui.algos.CaesarCipher;
import com.bmcomis2018.cryptgui.gui.*;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class Main
{
    public static void main( String[] args )
    {
        // Actually good UI
        FlatLightLaf.setup();

        JFrame frame = new JFrame();
        JTabbedPane cryptAlgorithmOptions = new JTabbedPane();

        SymmetricAlgoGui<Integer> caesar = new SymmetricAlgoGui<Integer>(new CaesarCipher(), frame);
        cryptAlgorithmOptions.addTab("Caesar Cipher", caesar.getMainPanel());

        // Boilerplate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(cryptAlgorithmOptions);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
