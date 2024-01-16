package concurrent;

import functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private final TabulatedFunction function;
    private final double value;
    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.function = tabulatedFunction;
        this.value = value;
    }

    public void run() {
        for (int i = 0; i < function.GetCount(); i++) {
            synchronized (function) {
                function.setY(i, value);
                System.out.println(String.format("Writing for index %d complete ", i));
            }
        }
    }
}
