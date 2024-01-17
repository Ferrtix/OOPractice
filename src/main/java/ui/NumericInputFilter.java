package ui;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericInputFilter extends DocumentFilter {
    private boolean showWarning = true;

    public NumericInputFilter(boolean showWarning) {
        this.showWarning = showWarning;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {
        if (!string.isEmpty() && (isNumeric(string) || (string.equals("-") && offset == 0))) {
            super.insertString(fb, offset, string, attr);
        } else if (showWarning) {
            showErrorDialog();
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {
        if (!text.isEmpty() && (isNumeric(text) || (text.equals("-") && offset == 0))) {
            super.replace(fb, offset, length, text, attrs);
        } else if (showWarning) {
            showErrorDialog();
        }
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d*\\.?\\d*");
    }

    private void showErrorDialog() {
        JOptionPane.showMessageDialog(null, "Некорректный ввод. Введите корректное число.",
                "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}