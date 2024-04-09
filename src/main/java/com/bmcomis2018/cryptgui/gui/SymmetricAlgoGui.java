package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.SymmetricAlgo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SymmetricAlgoGui<T> {
    private JPanel mainPanel = new JPanel();

    private JPanel leftPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JTextArea plaintext = new JTextArea();
    private JButton encryptGoButton = new JButton("Encrypt!");

    private JTextArea keyArea = new JTextArea(1, 10);

    private JTextArea ciphertext = new JTextArea(10, 10);
    private JButton decryptGoButton = new JButton("Decrypt!");

    private JFrame parent;

    // Actual encryption/decryption algorithm
    private SymmetricAlgo<T> cipher;

    public SymmetricAlgoGui(SymmetricAlgo<T> cipher, JFrame parent) {
        this.cipher = cipher;
        this.parent = parent;
        mainPanel.setLayout(new GridLayout());

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        setUpEncryptionGui();
        setUpKeyGenGui();
        setUpDecryptionGui();

        mainPanel.add(leftPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(rightPanel);
    }

    private JLabel titleLabel(String text) {
        JLabel label = new JLabel(text);
        addPadding(label);
        label.setFont(new Font("", Font.PLAIN, 30));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        return label;
    }

    private void addPadding(JComponent c) {
        c.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    private void message(String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    private void setUpEncryptionGui() {
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

    private void setUpKeyGenGui() {
        JLabel keyGenLabel = titleLabel("Key Generation");
        centerPanel.add(keyGenLabel);

        keyArea.setLineWrap(true);
        JScrollPane keyScroll = new JScrollPane(keyArea);
        addPadding(keyScroll);
        centerPanel.add(keyScroll);

        JPanel buttons = new JPanel();
        BoxLayout buttonsLayout = new BoxLayout(buttons, BoxLayout.X_AXIS);
        buttons.setLayout(buttonsLayout);

        JButton goButton = new JButton("Set Key");
        goButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        goButton.addActionListener(event -> setKey());
        buttons.add(goButton);

        buttons.add(Box.createRigidArea(new Dimension(10, 0)));
        JButton generateRandomButton = new JButton("Generate Random!");
        generateRandomButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        generateRandomButton.addActionListener(event -> generateKey());
        buttons.add(generateRandomButton);

        centerPanel.add(buttons);
    }

    private void setUpDecryptionGui() {
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

    private void encrypt() {
        char[] result = cipher.encrypt(plaintext.getText().toCharArray());
        ciphertext.setText(new String(result));

        message("Encrypted message!");
    }

    private void decrypt() {
        char[] result = cipher.decrypt(ciphertext.getText().toCharArray());
        plaintext.setText(new String(result));

        message("Decrypted message!");
    }

    private void setKey() {
        String potentialKey = keyArea.getText();
        try {
            cipher.setKey(cipher.validateKey(potentialKey));
        } catch (NumberFormatException e) {
            message("Invalid key (You suck)");
            keyArea.setText("");
            return;
        }
    }

    private void generateKey() {
        cipher.generateKey();
        keyArea.setText(String.valueOf(cipher.getKey()));
    }
}
