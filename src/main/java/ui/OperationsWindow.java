package ui;
import functions.factory.TabulatedFunctionFactory;
import io.FunctionsIO;
import operations.TabulatedFunctionOperationService;
import functions.TabulatedFunction;
import java.io.*;
import javax.swing.JTable;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import static ui.ExceptionMessage.showError;
public class OperationsWindow extends JDialog {
    private TabulatedFunctionOperationService functionOperationService = new TabulatedFunctionOperationService();
    private TabulatedFunctionOperationService operationService;
    private TabulatedFunction tabulatedFunc;
    private TabulatedFunction tabulatedFstFunc;
    private TabulatedFunction tabulatedSecFunc;
    private TabulatedFunction tabulatedResFunc;
    public JTable tableFstFunc;
    public JTable tableSecFunc;
    public JTable tableResFunc;

    public OperationsWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {
        super(mainWindow, "Операции над функциями", Dialog.ModalityType.APPLICATION_MODAL);

        setPreferredSize(new Dimension(800, 400));
        setLocationRelativeTo(mainWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        int borderMargin = 10;
        EmptyBorder border = new EmptyBorder(borderMargin, borderMargin, borderMargin, borderMargin);

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 0, 0) );

        JPanel fstFuncPanel = new JPanel(new BorderLayout());
        fstFuncPanel.setBorder(border);
        JPanel secFuncPanel = new JPanel(new BorderLayout());
        secFuncPanel.setBorder(border);
        JPanel resFuncPanel = new JPanel(new BorderLayout());
        resFuncPanel.setBorder(border);

        DefaultTableModel tableFstModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        DefaultTableModel tableSecModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        DefaultTableModel tableResModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);

        tableFstFunc = new JTable(tableFstModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return (column==1);
            }
        };
        tableSecFunc = new JTable(tableSecModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return (column==1);
            }
        };
        tableResFunc = new JTable(tableResModel){
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };

        JScrollPane scrollPane1 = new JScrollPane(tableFstFunc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(tableSecFunc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane3 = new JScrollPane(tableResFunc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane1, scrollPane2);
        splitPane.setResizeWeight(0.5);

        fstFuncPanel.add(new JLabel("Первая функция"), BorderLayout.NORTH);
        secFuncPanel.add(new JLabel("Вторая функция"), BorderLayout.NORTH);
        resFuncPanel.add(new JLabel("Результат"), BorderLayout.NORTH);

        fstFuncPanel.add(scrollPane1);
        secFuncPanel.add(scrollPane2);
        resFuncPanel.add(scrollPane3);

        JButton create1ButtonArr = new JButton("Создать массив");
        create1ButtonArr.addActionListener(e -> {
            TabulatedFunctionUI tabulatedFunctionUI = new TabulatedFunctionUI(this, factory);

            tableFstModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableFstModel.addRow(rowData);
            }
            setTabulatedFstFunc();
        });

        JButton create1ButtonMath = new JButton("Создать функцию");
        create1ButtonMath.addActionListener(e -> {
            CreateTabulatedFunctionSource tabulatedFunctionSourceWindow = new CreateTabulatedFunctionSource(this, factory);

            tableFstModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableFstModel.addRow(rowData);
            }
            setTabulatedFstFunc();
        });
        JButton load1Button = new JButton("Загрузить");
        load1Button.addActionListener(e -> {
            loadFunctionFromFile();

            tableFstModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableFstModel.addRow(rowData);
            }
            setTabulatedFstFunc();
        });
        JButton save1Button = new JButton("Сохранить");
        save1Button.addActionListener(e -> {
            if (tabulatedFstFunc != null && tabulatedFstFunc.GetCount() > 0) {
                for (int i = 0; i < tabulatedFstFunc.GetCount(); i++) {
                    try {
                        tabulatedFstFunc.setY(i, Double.parseDouble(tableFstModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        return;
                    }
                }

                saveFunctionInFile(tabulatedFstFunc);
            } else {
                showError("Создайте функцию перед сохранением");
            }
        });

        JPanel gridButton1Panel = new JPanel(new GridLayout(2, 2, 5, 5));
        gridButton1Panel.add(create1ButtonArr);
        gridButton1Panel.add(create1ButtonMath);
        gridButton1Panel.add(load1Button);
        gridButton1Panel.add(save1Button);

        JButton create2ButtonArr = new JButton("Создать массив");
        create2ButtonArr.addActionListener(e -> {
            TabulatedFunctionUI tabulatedFunctionUI = new TabulatedFunctionUI(this, factory);

            tableSecModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableSecModel.addRow(rowData);
            }
            setTabulatedSecFunc();
        });
        JButton create2ButtonMath = new JButton("Создать функцию");
        create2ButtonMath.addActionListener(e -> {
            CreateTabulatedFunctionSource tabulatedFunctionSourceWindow = new CreateTabulatedFunctionSource(this, factory);

            tableSecModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableSecModel.addRow(rowData);
            }
            setTabulatedSecFunc();
        });
        JButton load2Button = new JButton("Загрузить");
        load2Button.addActionListener(e -> {
            loadFunctionFromFile();

            tableSecModel.setRowCount(0);
            for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                Object[] rowData = new Object[2];
                rowData[0] = tabulatedFunc.getX(i);
                rowData[1] = tabulatedFunc.getY(i);
                tableSecModel.addRow(rowData);
            }
            setTabulatedSecFunc();
        });

        JButton save2Button = new JButton("Сохранить");
        save2Button.addActionListener(e -> {
            if (tabulatedSecFunc != null && tabulatedSecFunc.GetCount() > 0) {
                for (int i = 0; i < tabulatedSecFunc.GetCount(); i++) {
                    try {
                        tabulatedSecFunc.setY(i, Double.parseDouble(tableSecModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        return;
                    }
                }

                saveFunctionInFile(tabulatedFstFunc);
            } else {
                showError("Создайте функцию перед сохранением");
            }
        });

        JPanel gridButton2Panel = new JPanel(new GridLayout(2, 2, 5, 5) );
        gridButton2Panel.add(create2ButtonArr);
        gridButton2Panel.add(create2ButtonMath);
        gridButton2Panel.add(load2Button);
        gridButton2Panel.add(save2Button);

        JButton addButton = new JButton("Сумма");
        addButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFstFunc.GetCount(); i++) {
                    try {
                        tabulatedFstFunc.setY(i, Double.parseDouble(tableFstModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }
                for (int i = 0; i < tabulatedSecFunc.GetCount(); i++) {
                    try {
                        tabulatedSecFunc.setY(i, Double.parseDouble(tableSecModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }

                tabulatedFunc = functionOperationService.Add(tabulatedFstFunc, tabulatedSecFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количесвто точек должно быть положительным и одинаковым");
            }
        });
        JButton subtractButton = new JButton("Разность");
        subtractButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFstFunc.GetCount(); i++) {
                    try {
                        tabulatedFstFunc.setY(i, Double.parseDouble(tableFstModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }
                for (int i = 0; i < tabulatedSecFunc.GetCount(); i++) {
                    try {
                        tabulatedSecFunc.setY(i, Double.parseDouble(tableSecModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }

                tabulatedFunc = functionOperationService.Sub(tabulatedFstFunc, tabulatedSecFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количесвто точек должно быть положительным и одинаковым");
            }
        });
        JButton multiplyButton = new JButton("Умножение");
        multiplyButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFstFunc.GetCount(); i++) {
                    try {
                        tabulatedFstFunc.setY(i, Double.parseDouble(tableFstModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }
                for (int i = 0; i < tabulatedSecFunc.GetCount(); i++) {
                    try {
                        tabulatedSecFunc.setY(i, Double.parseDouble(tableSecModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }

                tabulatedFunc = functionOperationService.Mult(tabulatedFstFunc, tabulatedSecFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количесвто точек должно быть положительным и одинаковым");
            }
        });
        JButton divideButton = new JButton("Деление");
        divideButton.addActionListener(e -> {
            try {
                for (int i = 0; i < tabulatedFstFunc.GetCount(); i++) {
                    try {
                        tabulatedFstFunc.setY(i, Double.parseDouble(tableFstModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }
                for (int i = 0; i < tabulatedSecFunc.GetCount(); i++) {
                    try {
                        tabulatedSecFunc.setY(i, Double.parseDouble(tableSecModel.getValueAt(i, 1).toString()));
                    } catch (NumberFormatException ex) {
                        showError("Только числа с точкой");
                        break;
                    }
                }

                tabulatedFunc = functionOperationService.Div(tabulatedFstFunc, tabulatedSecFunc);
                tableResModel.setRowCount(0);
                for(int i = 0; i < tabulatedFunc.GetCount(); i++) {

                    Object[] rowData = new Object[2];
                    rowData[0] = tabulatedFunc.getX(i);
                    rowData[1] = tabulatedFunc.getY(i);
                    tableResModel.addRow(rowData);
                }
                setTabulatedResFunc();
            } catch (RuntimeException ex) {
                showError("Количесвто точек должно быть положительным и одинаковым");
            }
        });
        JButton save3Button = new JButton("Сохранить");
        save3Button.addActionListener(e -> {
            saveFunctionInFile(tabulatedResFunc);
        });

        JPanel gridButton3Panel = new JPanel(new GridLayout(2, 3, 5, 5) );
        gridButton3Panel.add(addButton);
        gridButton3Panel.add(subtractButton);
        gridButton3Panel.add(save3Button);
        gridButton3Panel.add(multiplyButton);
        gridButton3Panel.add(divideButton);

        fstFuncPanel.add(gridButton1Panel,BorderLayout.SOUTH);
        secFuncPanel.add(gridButton2Panel,BorderLayout.SOUTH);
        resFuncPanel.add(gridButton3Panel,BorderLayout.SOUTH);

        gridPanel.add(fstFuncPanel);
        gridPanel.add(secFuncPanel);
        gridPanel.add(resFuncPanel);
        add(gridPanel);

        pack();
        setVisible(true);
    }

    public void setTabulatedFunc(TabulatedFunction func){
        this.tabulatedFunc = func;
    }
    public void setTabulatedFstFunc(){
        this.tabulatedFstFunc = this.tabulatedFunc;
    }
    public void setTabulatedSecFunc(){
        this.tabulatedSecFunc = this.tabulatedFunc;
    }
    public void setTabulatedResFunc(){
        this.tabulatedResFunc = this.tabulatedFunc;
    }

    private void saveFunctionInFile(TabulatedFunction func) {
        if (func == null){
            showError("Создайте функцию");
        }
        else {
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
                    showError("Функция не записана");
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
                setTabulatedFunc(tabulatedFunction);
                System.out.println(tabulatedFunction);
                JOptionPane.showMessageDialog(this, "Функция загружена");
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ошибка при загрузке");
            }
        }
    }
}