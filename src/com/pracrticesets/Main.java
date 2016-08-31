package com.pracrticesets;

import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            System.err.println("Cannot load Look and Feel");
        }

	    Window window = new Window();
        window.setVisible(true);
    }
}
