package concurrent;

import functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    private final TabulatedFunction tabulatedFunction;

    public MultiplyingTask (TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }
    @Override
    public void run() {
        for(int i = 0; i < tabulatedFunction.GetCount(); ++i) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, tabulatedFunction.getY(i) * 2);
            }
        }
        System.out.println(Thread.currentThread().getName() + " has completed");
    }
}
