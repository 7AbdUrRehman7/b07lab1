import java.io.*;
import java.util.ArrayList;

public class Polynomial {
    private double[] coefficients; // Non-zero coefficients
    private int[] exponents; // Corresponding exponents

    // No-argument constructor (sets the polynomial to zero)
    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    // Constructor that takes two arrays: coefficients and exponents
    public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = new double[coefficients.length];
        this.exponents = new int[exponents.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
            this.exponents[i] = exponents[i];
        }
    }

    // Constructor that takes a file and initializes the polynomial based on file contents
    public Polynomial(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        // Parse the polynomial string and extract coefficients and exponents
        ArrayList<Double> coeffsList = new ArrayList<>();
        ArrayList<Integer> expsList = new ArrayList<>();

        String[] terms = line.split("(?=[+-])");
        for (String term : terms) {
            if (term.contains("x")) {
                String[] parts = term.split("x\\^?");
                double coeff = parts[0].isEmpty() || parts[0].equals("+") ? 1 : Double.parseDouble(parts[0]);
                if (parts[0].equals("-")) coeff = -1; // Handle case of negative coefficient
                int exp = parts.length > 1 ? Integer.parseInt(parts[1]) : 1; // Default exponent for 'x' is 1
                coeffsList.add(coeff);
                expsList.add(exp);
            } else {
                coeffsList.add(Double.parseDouble(term)); // Constant term
                expsList.add(0);
            }
        }

        // Convert ArrayList to arrays
        this.coefficients = coeffsList.stream().mapToDouble(Double::doubleValue).toArray();
        this.exponents = expsList.stream().mapToInt(Integer::intValue).toArray();
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial other) {
        ArrayList<Double> resultCoeffs = new ArrayList<>();
        ArrayList<Integer> resultExps = new ArrayList<>();

        int i = 0, j = 0;
        while (i < this.coefficients.length || j < other.coefficients.length) {
            if (i >= this.coefficients.length) {
                resultCoeffs.add(other.coefficients[j]);
                resultExps.add(other.exponents[j]);
                j++;
            } else if (j >= other.coefficients.length) {
                resultCoeffs.add(this.coefficients[i]);
                resultExps.add(this.exponents[i]);
                i++;
            } else if (this.exponents[i] == other.exponents[j]) {
                resultCoeffs.add(this.coefficients[i] + other.coefficients[j]);
                resultExps.add(this.exponents[i]);
                i++;
                j++;
            } else if (this.exponents[i] > other.exponents[j]) {
                resultCoeffs.add(this.coefficients[i]);
                resultExps.add(this.exponents[i]);
                i++;
            } else {
                resultCoeffs.add(other.coefficients[j]);
                resultExps.add(other.exponents[j]);
                j++;
            }
        }

        return new Polynomial(resultCoeffs.stream().mapToDouble(Double::doubleValue).toArray(),
                              resultExps.stream().mapToInt(Integer::intValue).toArray());
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    // Method to check if a given value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

    // Method to multiply two polynomials
    public Polynomial multiply(Polynomial other) {
        ArrayList<Double> resultCoeffs = new ArrayList<>();
        ArrayList<Integer> resultExps = new ArrayList<>();

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                double newCoeff = this.coefficients[i] * other.coefficients[j];
                int newExp = this.exponents[i] + other.exponents[j];

                // Combine like terms (i.e., same exponent) if necessary
                int index = resultExps.indexOf(newExp);
                if (index != -1) {
                    resultCoeffs.set(index, resultCoeffs.get(index) + newCoeff);
                } else {
                    resultCoeffs.add(newCoeff);
                    resultExps.add(newExp);
                }
            }
        }

        return new Polynomial(resultCoeffs.stream().mapToDouble(Double::doubleValue).toArray(),
                              resultExps.stream().mapToInt(Integer::intValue).toArray());
    }

    // Method to save the polynomial to a file in textual format
    public void saveToFile(String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        StringBuilder polynomialString = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
            if (i != 0 && coefficients[i] >= 0) {
                polynomialString.append("+");
            }
            polynomialString.append(coefficients[i]);
            if (exponents[i] != 0) {
                polynomialString.append("x");
                if (exponents[i] != 1) {
                    polynomialString.append("^").append(exponents[i]);
                }
            }
        }

        writer.write(polynomialString.toString());
        writer.close();
    }
}