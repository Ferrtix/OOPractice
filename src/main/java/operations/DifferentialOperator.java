package operations;

import functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    public T derive(T function);
}
