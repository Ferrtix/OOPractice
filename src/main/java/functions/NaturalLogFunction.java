package functions;

public class NaturalLogFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return java.lang.Math.log(x);
    }
}
