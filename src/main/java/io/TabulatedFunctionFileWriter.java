package io;

import functions.ArrayTabulatedFunction;
import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        try {
            try (BufferedWriter writerArray = new BufferedWriter(new FileWriter("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\array function.txt"));
                 BufferedWriter writerLinked = new BufferedWriter(new FileWriter("C:\\Users\\joyfa\\IdeaProjects\\OOPractice\\output\\linked list function.txt"))) {

                double[] xValue = {1, 2, 3, 5, 7};
                double[] yValue = {3, 4, 6, 11, 14};
                TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValue, yValue);
                TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(xValue,yValue);

                io.FunctionsIO.writeTabulatedFunction(writerArray,arrayTabulatedFunction);
                io.FunctionsIO.writeTabulatedFunction(writerLinked,linkedListTabulatedFunction);
            }
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
