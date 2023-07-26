import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import GUI.Useful_functions.Calculator;
public class CalculatorTests {


    private static Calculator calcObj;
    @Test
    void testAdd() {
        assertEquals(3, calcObj.add(1, 2));
    }
    @BeforeAll
    public static void calcCreation(){
        calcObj = new Calculator();
    }

    @Test
    void testAddBoundary() {
        assertThrows(,()->{
            calcObj.add(null,null);
        });
    }

    @Test
    void testDivideNormal() {
        assertEquals(2, calcObj.div(2, 1));
    }

    @Test
    void testDivideBoundary() {
        assertEquals(0, calcObj.div(2, 0));
    }

    @Test
    void testIsPosNormal() {
        assertEquals(1, calcObj.isPositiveNumber(1));
    }

    @Test
    void testIsPosBoundary() {
        assertEquals(-1, calcObj.isPositiveNumber(-1));
        assertEquals(0, calcObj.isPositiveNumber(0));
    }


}
