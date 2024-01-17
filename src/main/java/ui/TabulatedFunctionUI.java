package ui;
import exceptions.ArrayIsNotSortedException;
import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.ArrayTabulatedFunctionFactory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import static ui.ExceptionMessage.showError;
public class TabulatedFunctionUI extends JDialog {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField pointsTextField;
    private JButton createButton;
    private TabulatedFunctionFactory factory;
    public TabulatedFunctionUI(OperationsWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции из массива", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 300);
        setLocationRelativeTo(operationsWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        JButton inputVal = new JButton("Количество точек");

        inputPanel.add(new JLabel("Создать функцию"));
        inputPanel.add(pointsTextField);
        inputPanel.add(inputVal);

        tableModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        inputVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputValues();
            }
        });
        JPanel resultPanel = new JPanel();
        JButton createFunc = new JButton("Создать функцию");
        resultPanel.add(createFunc);
        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(operationsWindow, factory);
            }
        });

        ((AbstractDocument) pointsTextField.getDocument()).setDocumentFilter(new NumberFilter());
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);;
        setVisible(true);
    }

    private class NumberFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (isNumber(string)) {
                super.insertString(fb, offset, string, attr);
            } else {
                showError("Введите числа");
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (isNumber(text)) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                showError("Введите числа");
            }
        }

        private boolean isNumber(String text) {
            try {
                double value = Double.parseDouble(text);
                return value != 0.0;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public TabulatedFunctionUI(DifferentialWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции из массива", Dialog.ModalityType.APPLICATION_MODAL);
        setSize(400, 300);
        setLocationRelativeTo(operationsWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        pointsTextField = new JTextField(10);
        JButton inputVal = new JButton("Создать функцию");

        inputPanel.add(new JLabel("Количество точек:"));
        inputPanel.add(pointsTextField);
        inputPanel.add(inputVal);

        tableModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        inputVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputValues();
            }
        });
        JPanel resultPanel = new JPanel();
        JButton createFunc = new JButton("Создать функцию");
        resultPanel.add(createFunc);
        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(operationsWindow, factory);
            }
        });
        ((AbstractDocument) pointsTextField.getDocument()).setDocumentFilter(new NumberFilter());
        add(inputPanel, BorderLayout.NORTH);
        add(resultPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);;
        setVisible(true);
    }
    private void inputValues() {
        try {
            int numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Введите положительное число");
                return;
            }

            NumericInputFilter numericFilter = new NumericInputFilter(true);

            for (int i = 0; i < numberOfPoints; i++) {
                Object[] rowData = new Object[2];

                JTextField xField = new JTextField();
                JTextField yField = new JTextField();

                ((AbstractDocument) xField.getDocument()).setDocumentFilter(numericFilter);
                ((AbstractDocument) yField.getDocument()).setDocumentFilter(numericFilter);

                xField.setColumns(10);
                yField.setColumns(10);

                int resultX = JOptionPane.showConfirmDialog(null, xField, "Введите X" + (i + 1), JOptionPane.OK_CANCEL_OPTION);
                int resultY = JOptionPane.showConfirmDialog(null, yField, "Введите Y" + (i + 1), JOptionPane.OK_CANCEL_OPTION);

                if (resultX == JOptionPane.OK_OPTION && resultY == JOptionPane.OK_OPTION) {
                    rowData[0] = Double.parseDouble(xField.getText());
                    rowData[1] = Double.parseDouble(yField.getText());
                    tableModel.addRow(rowData);
                } else {
                    showError("Введите числа");
                    return;
                }
            }
        } catch (NumberFormatException ex) {
            showError("Введите числа");
        }
    }

    private void createTabulatedFunction(OperationsWindow operationsWindow, TabulatedFunctionFactory newFactory) {
        this.factory = newFactory;
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = new double[tableModel.getRowCount()];
        double[] yValues = new double[tableModel.getRowCount()];
        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                try {
                    xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                } catch (NumberFormatException ex) {
                    showError("Неверный Ввод. Пожалуйста введите в " + (i+1) + " строке числами с плавающей запятой");
                }
            }
            TabulatedFunction func = factory.create(xValues, yValues);
            operationsWindow.setTabulatedFunc(func);
            System.out.println("Tabulated Function was created - " + func.toString());
            dispose();
        } catch (ArrayIsNotSortedException ex) {
            showError("X не по возрастанию");
        }
    }
    private void createTabulatedFunction(DifferentialWindow operationsWindow, TabulatedFunctionFactory newFactory) {
        this.factory = newFactory;
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = new double[tableModel.getRowCount()];
        double[] yValues = new double[tableModel.getRowCount()];

        try {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                try {
                    xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                    yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
                } catch (NumberFormatException ex) {
                    showError("Неверный Ввод. Пожалуйста введите в " + (i+1) + " строке числами с плавающей запятой");
                }
            }
            TabulatedFunction func = factory.create(xValues, yValues);
            operationsWindow.setTabulatedFuncA(func);
            System.out.println("Tabulated Function created: " + func);
            dispose();
        } catch (ArrayIsNotSortedException ex) {
            showError("X не по возрастанию");
        }
    }
}