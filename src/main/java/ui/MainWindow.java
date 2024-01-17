package ui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class MainWindow extends JFrame {

    public MainWindow mainWindow = this;
    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    public TabulatedFunctionFactory functionFactory = new ArrayTabulatedFunctionFactory();

    public MainWindow() {
        setTitle("Меню");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 20));

        JButton operations = new JButton("Операции над функциями");
        JButton differential = new JButton("Производная функции");
        JButton settings = new JButton("Настройки");

        operations.setBackground(Color.LIGHT_GRAY);
        differential.setBackground(Color.LIGHT_GRAY);
        settings.setBackground(Color.LIGHT_GRAY);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        operations.setFont(buttonFont);
        differential.setFont(buttonFont);
        settings.setFont(buttonFont);

        int borderMargin = 10;
        operations.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        differential.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));
        settings.setMargin(new Insets(borderMargin, borderMargin, borderMargin, borderMargin));

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        operations.addActionListener(e -> new OperationsWindow(mainWindow, factory));
        differential.addActionListener(e -> new DifferentialWindow(mainWindow, factory));
        settings.addActionListener(e -> new SettingsWindow(mainWindow, factory));

        buttonPanel.add(operations);
        buttonPanel.add(differential);
        buttonPanel.add(settings);

        add(buttonPanel, BorderLayout.CENTER);
    }

    public void setFactory(TabulatedFunctionFactory fact){
        this.factory = fact;
    }

    public static void main(String[] args) {
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}