package ui;

import functions.*;
import functions.factory.TabulatedFunctionFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import static ui.ExceptionMessage.showError;
public class CreateTabulatedFunctionSource extends JDialog {
    private TabulatedFunctionFactory factory;
    private JTextField pointsTextField;
    private JTextField interval_1TextField;
    private JTextField interval_2TextField;
    private JComboBox<String> funcBox;
    private HashMap<String, Object> functionsMap;
    private OperationsWindow operationsWindow;
    private DifferentialWindow differentialWindow;

    public CreateTabulatedFunctionSource(OperationsWindow operationsWindow, TabulatedFunctionFactory factory) {
        super(operationsWindow, "Создание функции на основе другой функции", Dialog.ModalityType.APPLICATION_MODAL);
        this.operationsWindow = operationsWindow;
        pointsTextField = new JTextField(10);
        initialize(factory);
    }

    public CreateTabulatedFunctionSource(DifferentialWindow differentialWindow, TabulatedFunctionFactory factory) {
        super(differentialWindow, "Создание функции на основе другой функции", Dialog.ModalityType.APPLICATION_MODAL);
        this.differentialWindow = differentialWindow;
        pointsTextField = new JTextField(10);
        initialize(factory);
    }

    private void initialize(TabulatedFunctionFactory factory) {
        interval_1TextField = new JTextField(10);
        interval_2TextField = new JTextField(10);

        setSize(400, 300);
        setLocationRelativeTo(operationsWindow != null ? operationsWindow : differentialWindow);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        String[] functions = {
                "Единичная функция",
                "Квадратичная функция",
                "Котангенс функция",
                "Натуральный логарифм функция",
                "Нулевая функция",
                "Синус функция",
                "Тождественная функция"
        };
        funcBox = new JComboBox<>(functions);

        JPanel funcPanel = new JPanel();
        funcPanel.add(new JLabel("Вид функции: "));
        funcPanel.add(funcBox);
        funcPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));


        JPanel pointsPanel = new JPanel();
        pointsPanel.add(new JLabel("Кол-во точек разбиения: "));
        pointsPanel.add(pointsTextField);
        pointsPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JPanel interval_1Panel = new JPanel();
        interval_1Panel.add(new JLabel("Начало разбиения: "));
        interval_1Panel.add(interval_1TextField);
        interval_1Panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JPanel interval_2Panel = new JPanel();
        interval_2Panel.add(new JLabel("Конец разбиения: "));
        interval_2Panel.add(interval_2TextField);

        JPanel gridPanel = new JPanel(new GridLayout(4, 0, 30, 0));
        gridPanel.add(funcPanel);
        gridPanel.add(pointsPanel);
        gridPanel.add(interval_1Panel);
        gridPanel.add(interval_2Panel);

        JButton createFunc = new JButton("Создать функцию");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createFunc);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));

        functionsMap = new HashMap<>();
        functionsMap.put("Единичная функция", new UnitFunction());
        functionsMap.put("Квадратичная функция", new SqrFunction());
        functionsMap.put("Котангенс функция", new CtgFunction());
        functionsMap.put("Натуральный логарифм функция", new NaturalLogFunction());
        functionsMap.put("Нулевая функция", new ZeroFunction());
        functionsMap.put("Синус функция", new SinFunction());
        functionsMap.put("Тождественная функция", new IdentityFunction());

        createFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTabulatedFunction(factory);
            }
        });
        add(gridPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void createTabulatedFunction(TabulatedFunctionFactory newFactory) {
        this.factory = newFactory;

        int numberOfPoints = -1;
        double xFrom;
        double xTo;

        try {
            numberOfPoints = Integer.parseInt(pointsTextField.getText());
            if (numberOfPoints <= 0) {
                showError("Количество точек должно быть положительным и одинаковым");
                return;
            }
            xFrom = Double.parseDouble(interval_1TextField.getText());
            xTo = Double.parseDouble(interval_2TextField.getText());
            String mathFunc = (String) funcBox.getSelectedItem();

            TabulatedFunction func = factory.create((MathFunction) functionsMap.get(mathFunc), xFrom, xTo, numberOfPoints);
            //System.out.println(numberOfPoints + " " + xFrom + " " + xTo);
            //TabulatedFunction func = factory.create((MathFunction)new SqrFunction(), xFrom, xTo, numberOfPoints);

            if (operationsWindow != null) {
                operationsWindow.setTabulatedFunc(func);
            } else if (differentialWindow != null) {
                differentialWindow.setTabulatedFuncA(func);
            }

            System.out.println("Tabulated Function was created - " + func.toString());
            dispose();
        } catch (NumberFormatException ex) {
            showError("Введите числа");
        }
    }
}