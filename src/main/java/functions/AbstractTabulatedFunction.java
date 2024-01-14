package functions;
import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction {
    protected int count;
    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    abstract protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY);

    abstract protected double apply(double x);

    static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if(xValues.length!=yValues.length)throw new DifferentLengthOfArraysException();
    }
    static void checkSorted(double[] xValues){
        for(int i=0;i<xValues.length-1;++i){
            if(xValues[i]>xValues[i+1])throw new ArrayIsNotSortedException();
        }
    }
}