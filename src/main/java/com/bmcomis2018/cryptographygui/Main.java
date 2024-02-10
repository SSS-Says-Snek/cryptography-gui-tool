package com.bmcomis2018.cryptographygui;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        JTabbedPane cryptAlgorithmOptions = new JTabbedPane();

        cryptAlgorithmOptions.addTab("Sussy", new JButton("Heyy"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(cryptAlgorithmOptions);
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
