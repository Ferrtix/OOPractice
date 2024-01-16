package concurrent;

import functions.LinkedListTabulatedFunction;
import functions.TabulatedFunction;
import functions.UnitFunction;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class MultiplyingTaskExecutor {
    public static void main(String[] args)  throws InterruptedException {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            Runnable multiplyingTask = new MultiplyingTask(tabulatedFunction);
            executorService.execute(multiplyingTask);
        }

        Thread.sleep(1000);
        executorService.shutdown();
        System.out.println(tabulatedFunction);
    }
}
