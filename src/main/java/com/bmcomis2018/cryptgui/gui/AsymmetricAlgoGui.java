package com.bmcomis2018.cryptgui.gui;

import com.bmcomis2018.cryptgui.algos.Algo;

import java.awt.*;
import javax.swing.*;

public class AsymmetricAlgoGui<T> extends AlgoGui<T> {
    public AsymmetricAlgoGui(Algo<T> cipher, JFrame parent) {
        super(cipher, parent);

        setUpEncryptionGui();
        setUpKeyGenGui();
        setUpDecryptionGui();

        mainPanel.add(leftPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(rightPanel);
    }

    @Override
    public void setUpKeyGenGui() {
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
}
