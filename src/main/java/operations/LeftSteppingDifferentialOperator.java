package operations;

import functions.MathFunction;

public class LeftSteppingDifferentialOperator  extends SteppingDifferentialOperator{
    public LeftSteppingDifferentialOperator(double step) {
        super(step);
    }
    @Override
    public MathFunction derive(MathFunction function) {
        return (x) -> (function.apply(x) - function.apply(x - step)) / step;
    }
}
