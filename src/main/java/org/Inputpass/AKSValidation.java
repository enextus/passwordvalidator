package org.Inputpass;

import java.math.BigInteger;

public class AKSValidation {
    private static BigInteger[] coefficients;

    public static boolean isPrime(BigInteger n) {
        if (n.compareTo(BigInteger.valueOf(2)) < 0) {
            return false;
        }

        int r = 1;
        while (orderOf(n, r) <= Math.sqrt(totient(r)) * Math.log(n.longValue())) {
            r++;
        }

        BigInteger[] aksCoefficients = getCoefficients(r);
        aksCoefficients[0] = aksCoefficients[0].subtract(BigInteger.ONE);
        aksCoefficients[r] = aksCoefficients[r].subtract(n);

        for (BigInteger coef : aksCoefficients) {
            if (coef.mod(n).compareTo(BigInteger.ZERO) != 0) {
                return false;
            }
        }

        return true;
    }

    private static BigInteger[] getCoefficients(int r) {
        coefficients = new BigInteger[r + 1];
        coefficients[0] = BigInteger.ONE;

        for (int i = 0; i < r; i++) {
            coefficients[i + 1] = coefficients[i].multiply(BigInteger.valueOf(r - i)).divide(BigInteger.valueOf(i + 1));
        }

        return coefficients;
    }

    private static double totient(int r) {
        return r * (1.0 - (1.0 / (double) r));
    }

    private static int orderOf(BigInteger n, int r) {
        int k = 1;

        while (n.modPow(BigInteger.valueOf(k), BigInteger.valueOf(r)).compareTo(BigInteger.ONE) != 0) {
            k++;
        }

        return k;
    }

    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String[] args) {
        BigInteger prime = BigInteger.valueOf(89);
        BigInteger composite = BigInteger.valueOf(91);

        System.out.println(prime + " is prime? " + isPrime(prime));
        System.out.println(composite + " is prime? " + isPrime(composite));
    }

}
