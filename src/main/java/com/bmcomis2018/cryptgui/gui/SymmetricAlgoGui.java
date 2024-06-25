package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.SymmetricAlgo;

import javax.swing.*;

public class SymmetricAlgoGui<T> extends AlgoGui {
    private SymmetricAlgo<T> symmetricCipher;

    public SymmetricAlgoGui(SymmetricAlgo<T> cipher, JFrame parent) {
        super(cipher, parent);
        symmetricCipher = cipher;

        setUpKeyGenGui();
        mainPanel.add(centerPanel);
    }

    @Override
    public void setKey() {
        String potentialKey = keyArea0.getText();
        try {
            symmetricCipher.setKey(symmetricCipher.validateKey(potentialKey)); // Skull
        } catch (NumberFormatException e) {
            message("Invalid key (You suck)");
            keyArea0.setText("");
            return;
        }
        
        keyIsSet = true;
    }

    @Override
    public void generateKey() {
        cipher.generateKey();
        keyArea0.setText(symmetricCipher.getKeyString());

        message("Key generated!");
        keyIsSet = true;
    }
}
