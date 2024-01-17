package ui;

import javax.swing.*;
import functions.TabulatedFunction;
import functions.factory.TabulatedFunctionFactory;
import io.FunctionsIO;
import operations.DifferentialOperator;
import operations.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import static ui.ExceptionMessage.showError;

public class DifferentialWindow extends JDialog {
    private JLabel resultLabel;
    private TabulatedDifferentialOperator tabulatedDifferentialOperator;
    double[] xValues;
    double[] yValues;
    public JTable tableFunc;
    public JTable tableResFunc;
    private TabulatedDifferentialOperator functionDifferentialOperator = new TabulatedDifferentialOperator();
    private TabulatedFunction tabulatedFunc;
    private TabulatedFunction tabulatedResFunc;

    public DifferentialWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {
        super(mainWindow, "Производная", Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new Dimension(800, 400));
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int borderMargin = 10;

        EmptyBorder border = new EmptyBorder(borderMargin, borderMargin, borderMargin, borderMargin);

        JPanel gridPanel = new JPanel(new GridLayout(0, 2));

        JPanel funcPanel = new JPanel(new BorderLayout());
        funcPanel.setBorder(border);

        JPanel resFuncPanel = new JPanel(new BorderLayout());
        resFuncPanel.setBorder(border);

        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        DefaultTableModel tableResModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);

        tableFunc = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column == 1);
            }
        };
        tableResFunc = new JTable(tableResModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JScrollPane scrollPane1 = new JScrollPane(tableFunc);
        JScrollPane scrollPane3 = new JScrollPane(tableResFunc);

        funcPanel.add(new JLabel("Функция: "), BorderLayout.NORTH);
        funcPanel.add(scrollPane1);

        resFuncPanel.add(new JLabel("Результат дифференцирования: "), BorderLayout.NORTH);
        resFuncPanel.add(scrollPane3);

        JButton create1ButtonArr = new JButton("Создать массив");
        create1ButtonArr.addActionListener(e -> {
            TabulatedFunctionUI tabulatedFunctionUI = new TabulatedFunctionUI(this, factory);

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton create1ButtonMath = new JButton("Создать функцию");
        create1ButtonMath.addActionListener(e -> {
            CreateTabulatedFunctionSource tabulatedFunctionSourceWindow = new CreateTabulatedFunctionSource(this, factory);

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton load1Button = new JButton("Загрузить");
        load1Button.addActionListener(e -> {
            loadFunctionFromFile();

            tableModel.setRowCount(0);
            for (int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableModel.addRow(rowData);
            }
            setTabulatedFunc();
        });
        JButton save1Button = new JButton("Сохранить");
        save1Button.addActionListener(e -> {
            if (tabulatedFunc != null && tabulatedFunc.GetCount() > 0) {
                for (int i = 0; i < tabulatedFunc.GetCount(); i++) {
                    try {
                        tabulatedFunc.setY(i, Double.parseDouble(tableModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        return;
                    }
                }

                saveFunctionInFile(tabulatedFunc);
            } else {
                showError("Создайте функцию перед сохранением");
            }
        });

        JPanel gridButton1Panel = new JPanel(new GridLayout(2, 2, 5, 5));
        gridButton1Panel.add(create1ButtonArr);
        gridButton1Panel.add(create1ButtonMath);
        gridButton1Panel.add(load1Button);
        gridButton1Panel.add(save1Button);


        JButton difButton = new JButton("Продифференцировать");
        difButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFunc.GetCount(); i++) {
                    try {
                        tabulatedFunc.setY(i, Double.parseDouble(tableModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }

                tabulatedFunc = functionDifferentialOperator.deriveSynchronously(tabulatedFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количество точек должно быть положительным и одинаковым");
            }
        });

        JButton save3Button = new JButton("Сохранить");
        save3Button.addActionListener(e -> {
            saveFunctionInFile(tabulatedResFunc);
        });

        JPanel gridButton3Panel = new JPanel(new GridLayout(0, 2, 5, 5) );
        gridButton3Panel.add(difButton);
        gridButton3Panel.add(save3Button);

        funcPanel.add(gridButton1Panel,BorderLayout.SOUTH);
        resFuncPanel.add(gridButton3Panel,BorderLayout.SOUTH);

        gridPanel.add(funcPanel);
        gridPanel.add(resFuncPanel);
        add(gridPanel);

        pack();
        setVisible(true);
    }
    public void setTabulatedFuncA(TabulatedFunction func){
        this.tabulatedFunc = func;
    }
    public void setTabulatedFunc(){
        this.tabulatedFunc = this.tabulatedFunc;
    }
    public void setTabulatedResFunc(){
        this.tabulatedResFunc = this.tabulatedFunc;
    }


    private void saveFunctionInFile(TabulatedFunction func) {
        if (func == null) {
            showError("Создайте функцию");
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
            fileChooser.setFileFilter(filter);

            int result = fileChooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File file = fileChooser.getSelectedFile();
                try {
                    BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
                    FunctionsIO.serialize(outputStream, func);
                } catch (IOException ex) {
                    showError("Файл не записан");
                }
            }
        }

    }


    private void loadFunctionFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized Files (*.ser)", "ser");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File file = fileChooser.getSelectedFile();
            try {
                BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                TabulatedFunction tabulatedFunction = FunctionsIO.deserialize(inputStream);
                setTabulatedFuncA(tabulatedFunction);
                System.out.println(tabulatedFunction);
                JOptionPane.showMessageDialog(this, "Функция загружена");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ошибка при загрузке");
            }
        }
    }
}