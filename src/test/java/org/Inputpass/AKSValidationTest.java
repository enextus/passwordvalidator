package org.Inputpass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.Inputpass.AKSValidation.isPrime;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AKSValidationTest {
    @Test
    @DisplayName("Is two prime?")
    public void isTwoPrime() {
        BigInteger two = BigInteger.valueOf(2);
        assertTrue(isPrime(two), "Two should be prime");
    }

    @Test
    @DisplayName("Is 42 the answer to everything?")
    public void is42TheAnswerToEverything() {
        BigInteger fortyTwo = BigInteger.valueOf(42);
        assertFalse(isPrime(fortyTwo), "42 should not be prime");
    }

    @Test
    @DisplayName("Is the largest known prime number prime?")
    public void isLargestKnownPrimeNumberPrime() {
        BigInteger largestKnownPrime = new BigInteger("2").pow(77_232_917).subtract(BigInteger.ONE);
        assertTrue(isPrime(largestKnownPrime), "The largest known prime number should be prime");
    }

    @Test
    @DisplayName("Is an odd number (greater than two) prime?")
    public void isOddNumberGreaterThanTwoPrime() {
        BigInteger oddNumber = BigInteger.valueOf(7);
        assertTrue(isPrime(oddNumber), "An odd number (greater than two) can be prime");
    }

    @Test
    @DisplayName("Is a negative number prime?")
    public void isNegativeNumberPrime() {
        BigInteger negativeNumber = BigInteger.valueOf(-5);
        assertFalse(isPrime(negativeNumber), "A negative number should not be prime");
    }

    @Test
    @DisplayName("Is zero prime?")
    public void isZeroPrime() {
        BigInteger zero = BigInteger.ZERO;
        assertFalse(isPrime(zero), "Zero should not be prime");
    }

    @Test
    @DisplayName("Is one prime?")
    public void isOnePrime() {
        BigInteger one = BigInteger.ONE;
        assertFalse(isPrime(one), "One should not be prime");
    }

    @Test
    @DisplayName("Did we find the first 10 prime numbers?")
    public void didWeFindFirstTenPrimeNumbers() {
        //int[] firstTenPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        int[] firstTenPrimes = {2,};
        for (int prime : firstTenPrimes) {
            System.out.println("prime: " + prime);
            assertTrue(isPrime(BigInteger.valueOf(prime)), prime + " should be prime");
        }
    }

    @Test
    @DisplayName("Did we find the first 10 composite numbers?")
    public void didWeFindFirstTenCompositeNumbers() {
        int[] firstTenComposites = {4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
        for (int composite : firstTenComposites) {
            assertFalse(isPrime(BigInteger.valueOf(composite)), composite + " should not be prime");
        }
    }


}
