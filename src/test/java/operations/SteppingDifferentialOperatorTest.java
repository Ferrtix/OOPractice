package operations;

import functions.MathFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SteppingDifferentialOperatorTest {
    MathFunction sqr = new SqrFunction();
    @Test
    public void LeftSteppingDifferentialOperator() {
        SteppingDifferentialOperator leftOperation = new LeftSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = leftOperation.derive(sqr);

        assertEquals(2, differentialSqr.apply(1), 0.000001);
    }

    @Test
    public void RightSteppingDifferentialOperator() {
        SteppingDifferentialOperator rightOperation = new RightSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = rightOperation.derive(sqr);

        assertEquals(2, differentialSqr.apply(1), 0.000001);
    }

    @Test
    public void MiddleSteppingDifferentialOperator() {
        SteppingDifferentialOperator middleOperation = new MiddleSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = middleOperation.derive(sqr);

        assertEquals(2, differentialSqr.apply(1), 0.000001);
    }

}