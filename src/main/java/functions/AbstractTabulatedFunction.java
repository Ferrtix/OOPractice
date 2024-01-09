package functions;

public abstract class AbstractTabulatedFunction {
    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    abstract protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    abstract protected double apply(double x);
}
