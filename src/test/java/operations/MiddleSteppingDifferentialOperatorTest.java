package operations;
import functions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SteppingDifferentialOperatorTest {
    MathFunction sqr = new SqrFunction();
    @Test
    public void LeftSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator leftOperation = new LeftSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = leftOperation.derive(sqr);

        assertEquals(1.999999899027216, differentialSqr.apply(1), 0.9);
    }

    @Test
    public void RightSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator rightOperation = new RightSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = rightOperation.derive(sqr);

        assertEquals(2.0000001010878066, differentialSqr.apply(1), 0.5);
    }

    @Test
    public void MiddleSteppingDifferentialOperatorTest() {
        SteppingDifferentialOperator middleOperation = new MiddleSteppingDifferentialOperator(0.0000001);
        MathFunction differentialSqr = middleOperation.derive(sqr);

        assertEquals(2.0, differentialSqr.apply(1), 0.2);
    }

}