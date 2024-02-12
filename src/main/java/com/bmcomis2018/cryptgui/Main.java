package com.bmcomis2018.cryptgui;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;

public class Main
{
    public static void main( String[] args )
    {
        // Actually good UI
        FlatLightLaf.setup();

        JFrame frame = new JFrame();
        JTabbedPane cryptAlgorithmOptions = new JTabbedPane();

        JPanel caesarCipherPanel = new JPanel(new BorderLayout());
        JPanel encryptPanel = new JPanel();
        JPanel decryptPanel = new JPanel();

        caesarCipherPanel.add(encryptPanel, BorderLayout.WEST);
        caesarCipherPanel.add(decryptPanel, BorderLayout.EAST);
        cryptAlgorithmOptions.addTab("Sussy", caesarCipherPanel);

        // Boilerplate
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(cryptAlgorithmOptions);
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
