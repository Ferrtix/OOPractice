package io;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.factory.LinkedListTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\serialized linked list functions.bin"))) {
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {1, 4, 9, 16, 25};

            TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

            TabulatedFunction Derive1 = operator.derive(function);
            TabulatedFunction Derive2 = operator.derive(Derive1);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, Derive1);
            FunctionsIO.serialize(out, Derive2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\serialized linked list functions.bin"))) {
            System.out.println("Function - " + FunctionsIO.deserialize(in));
            System.out.println("First derivative - " + FunctionsIO.deserialize(in));
            System.out.println("Second derivative - " + FunctionsIO.deserialize(in));
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
