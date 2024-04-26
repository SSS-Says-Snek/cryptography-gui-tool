package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.Algo;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public abstract class AlgoGui<T> {
    protected JPanel mainPanel = new JPanel();

    protected JPanel leftPanel = new JPanel();
    protected JPanel centerPanel = new JPanel();
    protected JPanel rightPanel = new JPanel();

    protected JTextArea plaintext = new JTextArea();
    protected JTextArea ciphertext = new JTextArea(10, 10);

    protected JButton goButton = new JButton("Set Key");

    public JFrame parent;

    protected JTextArea keyArea0 = new JTextArea();

    // Actual encryption/decryption algorithm
    public Algo cipher;

    public AlgoGui(Algo cipher, JFrame parent) {
        this.parent = parent;
        this.cipher = cipher;
        mainPanel.setLayout(new GridLayout());

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        setUpEncryptionGui();
        setUpDecryptionGui();

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);
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

    public abstract void setKey();
    public abstract void generateKey();

    public void setUpEncryptionGui() {
        JLabel encryptLabel = titleLabel("Plaintext");
        leftPanel.add(encryptLabel);


        plaintext.setLineWrap(true);
        JScrollPane inputScroll = new JScrollPane(plaintext);
        inputScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        addPadding(inputScroll);
        leftPanel.add(inputScroll);

        JButton encryptGoButton = new JButton("Encrypt!");
        encryptGoButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        encryptGoButton.addActionListener(event -> encrypt());

        leftPanel.add(encryptGoButton);
    }

    public void setUpKeyGenGui() {
        JLabel keyGenLabel = titleLabel("Key Generation");
        centerPanel.add(keyGenLabel);

        // Setup encryption
        keyArea0.setLineWrap(true);
        JScrollPane keyScroll = new JScrollPane(keyArea0);
        addPadding(keyScroll);
        centerPanel.add(keyScroll);

        JPanel buttons = new JPanel();
        BoxLayout buttonsLayout = new BoxLayout(buttons, BoxLayout.X_AXIS);
        buttons.setLayout(buttonsLayout);

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

    public void setUpDecryptionGui() {
        JLabel decryptLabel = titleLabel("Ciphertext");
        rightPanel.add(decryptLabel);

        ciphertext.setLineWrap(true);
        JScrollPane outputScroll = new JScrollPane(ciphertext);
        outputScroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        addPadding(outputScroll);
        rightPanel.add(outputScroll);

        JButton decryptGoButton = new JButton("Decrypt!");
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

}
