package functions;

public class NaturalLogFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.log(x);
    }
}
