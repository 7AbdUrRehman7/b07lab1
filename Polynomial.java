public class Polynomial {
    // Field to store coefficients of the polynomial
    private double[] coefficients;

    // No-argument constructor (sets the polynomial to zero)
    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    // Constructor that takes an array of coefficients
    public Polynomial(double[] coefficients) {
        this.coefficients = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            this.coefficients[i] = coefficients[i];
        }
    }

    // Method to add two polynomials
    public Polynomial add(Polynomial other) {
        int maxDegree = Math.max(this.coefficients.length, other.coefficients.length);
        double[] result = new double[maxDegree];

        // Add the coefficients of both polynomials
        for (int i = 0; i < maxDegree; i++) {
            double thisCoeff = 0;
            double otherCoeff = 0;

            if (i < this.coefficients.length) {
                thisCoeff = this.coefficients[i];
            }

            if (i < other.coefficients.length) {
                otherCoeff = other.coefficients[i];
            }

            result[i] = thisCoeff + otherCoeff;
        }

        return new Polynomial(result);
    }

    // Method to evaluate the polynomial for a given value of x
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    // Method to check if a given value is a root of the polynomial
    public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }
}