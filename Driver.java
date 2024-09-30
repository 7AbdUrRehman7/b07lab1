import java.io.File;

public class Driver {
    public static void main(String[] args) {
        // Test 1: Evaluating default polynomial (0 polynomial) at x = 3
        Polynomial defaultPoly = new Polynomial();
        System.out.println("Default polynomial evaluated at x = 3: " + defaultPoly.evaluate(3));

        // Test 2: Creating and displaying first polynomial (e.g., 6x^3 + 5)
        double[] coeffs1 = {6, 0, 0, 5};  // Coefficients for 6x^3 + 5
        int[] exponents1 = {3, 2, 1, 0};  // Exponents corresponding to terms
        Polynomial poly1 = new Polynomial(coeffs1, exponents1);
        System.out.println("Polynomial 1: " + poly1);

        // Test 3: Creating and displaying second polynomial (e.g., -2x^3 - 9)
        double[] coeffs2 = {0, -2, 0, 0, -9};  // Coefficients for -2x^3 - 9
        int[] exponents2 = {4, 3, 2, 1, 0};    // Exponents corresponding to terms
        Polynomial poly2 = new Polynomial(coeffs2, exponents2);
        System.out.println("Polynomial 2: " + poly2);

        // Test 4: Adding two polynomials and displaying the sum
        Polynomial polySum = poly1.add(poly2);
        System.out.println("Sum of Polynomial 1 and Polynomial 2: " + polySum);

        // Test 5: Evaluating the sum polynomial at x = 0.1
        double evalSumAtPoint = polySum.evaluate(0.1);
        System.out.println("Sum polynomial evaluated at x = 0.1: " + evalSumAtPoint);

        // Test 6: Checking if x = 1 is a root of the sum polynomial
        boolean isRoot = polySum.hasRoot(1);
        if (isRoot) {
            System.out.println("x = 1 is a root of the sum polynomial.");
        } else {
            System.out.println("x = 1 is NOT a root of the sum polynomial.");
        }

        // Test 7: Multiplying the two polynomials and displaying the product
        Polynomial polyProduct = poly1.multiply(poly2);
        System.out.println("Product of Polynomial 1 and Polynomial 2: " + polyProduct);

        // Test 8: Saving the sum polynomial to an output file
        polySum.saveToFile("sum_polynomial.txt");
        System.out.println("Sum polynomial saved to sum_polynomial.txt");

        // File I/O Testing
        try {
            // Test 9: Creating a polynomial from a file (e.g., poly.txt)
            File inputFile = new File("poly.txt");
            Polynomial polyFromFile = new Polynomial(inputFile);

            // Test 10: Displaying the polynomial read from the file
            System.out.println("Polynomial from poly.txt: " + polyFromFile);

            // Test 11: Evaluating the polynomial from the file at x = 1
            double evalPolyFromFile = polyFromFile.evaluate(1);
            System.out.println("Polynomial from file evaluated at x = 1: " + evalPolyFromFile);

            // Test 12: Checking if x = 0 is a root of the polynomial read from the file
            if (polyFromFile.hasRoot(0)) {
                System.out.println("x = 0 is a root of the polynomial from the file.");
            } else {
                System.out.println("x = 0 is NOT a root of the polynomial from the file.");
            }

            // Test 13: Saving the polynomial from the file to a new file
            polyFromFile.saveToFile("file_output_polynomial.txt");
            System.out.println("Polynomial from file saved to file_output_polynomial.txt");

        } catch (Exception e) {
            System.out.println("Error while processing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}