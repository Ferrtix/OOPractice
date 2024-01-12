package operations;

import functions.MathFunction;

import static java.lang.Float.NaN;
import static java.lang.Float.POSITIVE_INFINITY;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step) {
        this.step = step;
        if ((step < 1|| step == POSITIVE_INFINITY|| step == NaN))
            throw new IllegalArgumentException();
    }
    public double getStep() {
        return step;
    }
    public void setStep(double step) {
        this.step = step;
    }
}
