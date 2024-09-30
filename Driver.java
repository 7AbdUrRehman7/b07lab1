public class Driver {
    public static void main(String[] args) {
        // Creating a zero polynomial (default constructor)
        Polynomial p = new Polynomial();
        System.out.println("p(3) = " + p.evaluate(3)); // Evaluating the zero polynomial at x = 3

        // Creating the polynomial 6 + 5x^3 (using two arrays for coefficients and exponents)
        double[] c1 = {6, 5}; // Non-zero coefficients
        int[] e1 = {0, 3}; // Corresponding exponents
        Polynomial p1 = new Polynomial(c1, e1);

        // Creating the polynomial -2x + (-9)x^4
        double[] c2 = {-2, -9}; // Non-zero coefficients
        int[] e2 = {1, 4}; // Corresponding exponents
        Polynomial p2 = new Polynomial(c2, e2);

        // Adding the two polynomials: (6 + 5x^3) + (-2x - 9x^4)
        Polynomial s = p1.add(p2);

        // Evaluating the sum polynomial at x = 0.1
        System.out.println("s(0.1) = " + s.evaluate(0.1));

        // Checking if x = 1 is a root of the sum polynomial
        if (s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
    }
}