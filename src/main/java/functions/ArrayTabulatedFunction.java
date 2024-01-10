package functions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction{

    private double[] xValues;
    private double[] yValues;

    private int count;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if(xValues.length < 2)
            throw new IllegalArgumentException("length less than 2");
        if(xValues.length != yValues.length)
            throw new IllegalArgumentException("length isn't same");
        for(int i=1; i<xValues.length; i++) {
            if(xValues[i-1] > xValues[i]) {
                throw new IllegalArgumentException("x values isn't sorted");
            }
        }
        this.xValues = Arrays.copyOf(xValues,xValues.length);
        this.yValues = Arrays.copyOf(yValues,yValues.length);
        count = xValues.length;
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if(count < 2)
            throw new IllegalArgumentException("length less than 2");
        if(xFrom>xTo) {double temp=xFrom;xFrom=xTo;xTo=temp;}
        this.xValues=new double[count];
        this.yValues=new double[count];
        if(xFrom==xTo){
           for(int i=0;i<count;++i)
           {
               this.xValues[i]=xFrom;
               this.yValues[i]=source.apply(xFrom);
           }
        }else{
        double step = (xTo-xFrom)/(count-1);
        int i=0;
        for(double x=xFrom;x<=(xTo+step/2);x+=step){

            this.xValues[i]=x;
            this.yValues[i]=source.apply(x);
            ++i;
            }
        }
    }

    @Override
    public int GetCount() {
        return this.count;
    }

    @Override
    public double getX(int index) {
        if (index < 0 || index > count - 1)
            throw new IllegalArgumentException("Index out of range");
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        if (index < 0 || index > count - 1)
            throw new IllegalArgumentException("Index out of range");
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index < 0 || index > count - 1)
            throw new IllegalArgumentException("Index out of range");
        yValues[index] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count-1];
    }

    @Override
    public int indexOfX(double x) {
       for(int i=0;i<xValues.length-1;++i){
           if(Double.compare(x,xValues[i])==0){
               return i;
           }
       }
       return -1;
    }

    @Override
    public int indexOfY(double y) {
        for(int i=0;i<yValues.length-1;++i){
            if(Double.compare(y,yValues[i])==0){
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < getX(0))
            throw new IllegalArgumentException("X out of range");
        for(int i=0;i<xValues.length-1;++i){
            if((x>xValues[i])&&(x<xValues[i+1])){
                return i;
            }
        }
        return count-1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (x > getX(0))
            throw new IllegalArgumentException("X out of range");
        return (yValues[0]+((yValues[1]-yValues[0])/(xValues[1]-xValues[0]))*(x-xValues[0]));
    }

    @Override
    protected double extrapolateRight(double x) {
        if (x < getX(count - 1))
            throw new IllegalArgumentException("X out of range");
        return (yValues[count-2]+((yValues[count-1]-yValues[count-2])/(xValues[count-1]-xValues[count-2]))*(x-xValues[count-2]));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex < 1 || floorIndex > count - 1)
            throw new IllegalArgumentException("Index out of range");
        return (yValues[floorIndex-1]+((yValues[floorIndex]-yValues[floorIndex-1])/(xValues[floorIndex]-xValues[floorIndex-1]))*(x-xValues[floorIndex-1]));
    }

    @Override
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return (leftY+((rightY-leftY)/(rightX-leftX))*(x-leftX));
    }

    @Override
    public double apply(double x) {
        double res=0.0;
        if (x < xValues[0]) {
        res=extrapolateLeft(x);
        }else if(x> xValues[count-1]){
            res=extrapolateRight(x);
        }else {
            if(indexOfX(x)!=-1){
             res = getY(indexOfX(x));
            }else{
                res=interpolate(x,floorIndexOfX(x));
            }
        }
        return res;
    }
}
