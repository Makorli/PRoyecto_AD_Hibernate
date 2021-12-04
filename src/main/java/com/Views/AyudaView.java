package com.Views;

import javax.swing.*;

public class AyudaView {
    private JPanel JPGeneral;
    private JTextArea textArea1;

    public AyudaView() {
        textArea1.setEditable(false);
        textArea1.setText(
                String.format("%s\n%s\n%s",
                        "Fran Orlando",
                        "149FDAM..o algo asi",
                        "Diciembre 2021")
        );
    }

    public JPanel getJPGeneral() {
        return JPGeneral;
    }
}
