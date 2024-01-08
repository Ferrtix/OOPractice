package functions;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return java.lang.Math.pow(x,2);
    }
}
