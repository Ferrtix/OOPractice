package functions;

public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }

    public String toString(){
        return "IdentityFunction returns what you passed to it";
    }

    public boolean equals(Object o){
        return(o.getClass()==this.getClass());
    }

    public int hashCode(){
        return getClass().hashCode();
    }

    public Object clone(){
        return new IdentityFunction();
    }
}
