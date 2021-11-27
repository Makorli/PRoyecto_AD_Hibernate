package com.Views;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class HintTextField extends JTextField implements FocusListener {

    private String hintText = "";
    private boolean showingHint = false;
    private boolean enableHint;

    public HintTextField() {
    }

    public HintTextField(String text) {
        super(text);
    }

    public HintTextField(int columns) {
        super(columns);
    }

    public HintTextField(String text, int columns) {
        super(text, columns);
    }

    public HintTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }

    /*
    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }*

     */

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getHintText() {
        return hintText;
    }

    public boolean isEnableHint() {
        return enableHint;
    }

    public void setEnableHint(boolean enableHint) {
        this.enableHint = enableHint;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (isEnableHint()) {
            if (this.getText().isEmpty()) {
                super.setText("");
                showingHint = false;
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (isEnableHint()) {
            if (this.getText().isEmpty()) {
                super.setText(hintText);
                showingHint = true;
            }
        }
    }

    @Override
    public String getText() {
        return showingHint ? "" : super.getText();
    }
}