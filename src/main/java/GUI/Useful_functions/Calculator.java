package GUI.Useful_functions;

public class Calculator {
    public int add (int a, int b) {
        return a + b;
    }
    public int div (int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by 0");
        } else {
            return a / b;
        }
    }
    public int isPositiveNumber (int a) {
        if (a > 0) {
            return 1;
        } else if (a < 0) {
            return -1;
        } else {
            return 0;
        }
    }
}
