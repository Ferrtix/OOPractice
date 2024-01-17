package ui;

import javax.swing.*;

public class ExceptionMessage {
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

}