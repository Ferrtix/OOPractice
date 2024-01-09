package functions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements TabulatedFunction{

    private double[] xValues;
    private double[] yValues;

    private int count;

    ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues=Arrays.copyOf(xValues,xValues.length);
        this.yValues=Arrays.copyOf(yValues,yValues.length);
        count=xValues.length;
    }

    ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
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
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index]=value;
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
        if(x<xValues[0]) return 0;
        for(int i=0;i<xValues.length-2;++i){
            if((x>xValues[i])&&(x<xValues[i+1])){
                return i;
            }
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return (yValues[0]+((yValues[1]-yValues[0])/(xValues[1]-xValues[0]))*(x-xValues[0]));
    }

    @Override
    protected double extrapolateRight(double x) {
        return (yValues[count-2]+((yValues[count-1]-yValues[count-2])/(xValues[count-1]-xValues[count-2]))*(x-xValues[count-2]));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return (yValues[floorIndex]+((yValues[floorIndex]-yValues[floorIndex-1])/(xValues[floorIndex]-xValues[floorIndex-1]))*(x-xValues[floorIndex-1]));
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
