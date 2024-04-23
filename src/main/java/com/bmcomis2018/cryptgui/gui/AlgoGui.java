package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.Algo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class AlgoGui<T> {
    public JPanel mainPanel = new JPanel();

    public JPanel leftPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel rightPanel = new JPanel();

    public JTextArea plaintext = new JTextArea();
    public JButton encryptGoButton = new JButton("Encrypt!");

    public JTextArea keyArea = new JTextArea(1, 10);

    public JTextArea ciphertext = new JTextArea(10, 10);
    public JButton decryptGoButton = new JButton("Decrypt!");

    public JFrame parent;

    // Actual encryption/decryption algorithm
    public Algo<T> cipher;

    public AlgoGui(Algo<T> cipher, JFrame parent) {
        this.cipher = cipher;
        this.parent = parent;
        mainPanel.setLayout(new GridLayout());

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    }

    public JLabel titleLabel(String text) {
        JLabel label = new JLabel(text);
        addPadding(label);
        label.setFont(new Font("", Font.PLAIN, 30));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        return label;
    }

    public void addPadding(JComponent c) {
        c.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void message(String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    public void setUpEncryptionGui() {
        JLabel encryptLabel = titleLabel("Plaintext");
        leftPanel.add(encryptLabel);

        JScrollPane inputScroll = new JScrollPane(plaintext);
        inputScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        addPadding(inputScroll);
        leftPanel.add(inputScroll);

        encryptGoButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        encryptGoButton.addActionListener(event -> encrypt());

        leftPanel.add(encryptGoButton);
    }

    public abstract void setUpKeyGenGui();

    public void setUpDecryptionGui() {
        JLabel decryptLabel = titleLabel("Ciphertext");
        rightPanel.add(decryptLabel);

        JScrollPane outputScroll = new JScrollPane(ciphertext);
        outputScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        addPadding(outputScroll);
        rightPanel.add(outputScroll);

        decryptGoButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        decryptGoButton.addActionListener(event -> decrypt());

        rightPanel.add(decryptGoButton);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void encrypt() {
        char[] result = cipher.encrypt(plaintext.getText().toCharArray());
        ciphertext.setText(new String(result));

        message("Encrypted message!");
    }

    public void decrypt() {
        char[] result = cipher.decrypt(ciphertext.getText().toCharArray());
        plaintext.setText(new String(result));

        message("Decrypted message!");
    }

    public void setKey() {
        String potentialKey = keyArea.getText();
        try {
            cipher.setKey(cipher.validateKey(potentialKey));
        } catch (NumberFormatException e) {
            message("Invalid key (You suck)");
            keyArea.setText("");
            return;
        }
    }

    public void generateKey() {
        cipher.generateKey();
        keyArea.setText(String.valueOf(cipher.getKey()));
    }
}
