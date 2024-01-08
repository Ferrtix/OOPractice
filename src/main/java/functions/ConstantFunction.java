package functions;

public class ConstantFunction implements MathFunction {
    private final double Cons;
    public ConstantFunction(double cons) {
        this.Cons = cons;
    }



    public double GetCons(){
        return Cons;
    }
    @Override
    public double apply(double x) {
        return Cons;
    }
}
