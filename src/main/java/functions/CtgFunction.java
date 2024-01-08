package functions;

public class CtgFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return 1/Math.tan(x);
    }
}
