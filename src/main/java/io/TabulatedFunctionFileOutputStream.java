package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try(
             BufferedOutputStream arrFileOut = new BufferedOutputStream(new FileOutputStream("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\array functions.bin"));
             BufferedOutputStream listFileOut = new BufferedOutputStream(new FileOutputStream("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\linked list functions.bin"))) {
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {22, 34, 45, 453, 567};
            ArrayTabulatedFunction arr = new ArrayTabulatedFunction(xValues, yValues);
            LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(xValues, yValues);

            FunctionsIO.writeTabulatedFunction(arrFileOut, arr);
            FunctionsIO.writeTabulatedFunction(listFileOut, list);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
