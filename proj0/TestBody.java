/**
 *  TESTS body
 */
 public class TestBody {
   /**
    *  Test Body.
    */
    public static void main(String[] args) {
      checkBody();
    }

    private static void checkEquals(double expected, double actual, String label, double eps) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    private static void checkBody {
      Body a = new Body(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
      Body b = new Body(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");


      checkEquals(133.4, a.calcForceExertedBy(b), "Body()", 0.01);
    }

 }
