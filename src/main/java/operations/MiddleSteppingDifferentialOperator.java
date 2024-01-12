package operations;

import functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator{
    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }
    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> (function.apply(x + step) - function.apply(x - step)) / (2 * step);
    }
}
