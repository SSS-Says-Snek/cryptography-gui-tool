package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.AsymmetricAlgo;

import javax.swing.*;

public class AsymmetricAlgoGui<T> extends AlgoGui<T> {
    private JTextArea keyArea1 = new JTextArea();

    private AsymmetricAlgo<T> asymmetricCipher;
    public AsymmetricAlgoGui(AsymmetricAlgo<T> cipher, JFrame parent) {
        super(cipher, parent);
        asymmetricCipher = cipher;

        setUpKeyGenGui();
        mainPanel.add(centerPanel);
    }

    @Override
    public void setKey() { // TODO
        keyIsSet = true;
        return;
    }

    @Override
    public void generateKey() {
        cipher.generateKey();

        T encryptKey = asymmetricCipher.getEncryptionKey();
        T decryptKey = asymmetricCipher.getDecryptionKey();

        keyArea0.setText(encryptKey.toString());
        keyArea1.setText(decryptKey.toString());

        message("Key generated!");
        keyIsSet = true;
    }

    @Override
    public void setUpKeyGenGui() {
        super.setUpKeyGenGui();

        keyArea0.setEditable(false);
        keyArea1.setEditable(false);
        goButton.setEnabled(false); // TODO

        // Setup decrypt key
        keyArea1.setLineWrap(true);
        JScrollPane decryptKeyScroll = new JScrollPane(keyArea1);
        addPadding(decryptKeyScroll);
        centerPanel.add(decryptKeyScroll, 2);
    }
}
