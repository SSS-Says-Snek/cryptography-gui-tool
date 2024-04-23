package com.bmcomis2018.cryptgui.algos;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;

public class RSA {
    public static BigInteger e = new BigInteger("65537");

    private BigInteger p;
    private BigInteger q;

    private BigInteger d;

    private BigInteger n;
    private int keysize;

    private SecureRandom rand;

    public RSA(int keysize) {
        this.keysize = keysize;
        rand = new SecureRandom();
    }

    public void generateKey() {
        p = new BigInteger(keysize, 128, rand);
        q = new BigInteger(keysize, 128, rand);

        BigInteger pmin1 = p.subtract(BigInteger.ONE);
        BigInteger qmin1 = q.subtract(BigInteger.ONE);
        
        n = p.multiply(q);
        BigInteger totn = (pmin1.multiply(qmin1)).divide(gcd(pmin1, qmin1));

        d = multInv(e, totn);
        if (d.compareTo(BigInteger.ZERO) == -1) {
            d = totn.add(d);
        }
    }

    public char[] encrypt(char[] plaintext) {
        BigInteger message = OS2IP(plaintext);
        BigInteger ciphertext = squareAndMult(message, e, n);
        return Base64.getEncoder().encodeToString(ciphertext.toByteArray()).toCharArray();
    }

    public char[] decrypt(char[] ciphertext) {
        BigInteger cipherMessage = new BigInteger(Base64.getDecoder().decode(new String(ciphertext).getBytes()));
        return I2OSP(squareAndMult(cipherMessage, d, n));
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger temp;
        while (b != BigInteger.ZERO) {
            temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    private static BigInteger multInv(BigInteger a, BigInteger b) {
        BigInteger old_r = a;
        BigInteger r = b;
        BigInteger old_s = BigInteger.ONE;
        BigInteger s = BigInteger.ZERO;

        BigInteger temp;
        BigInteger quotient;
        while (r != BigInteger.ZERO) {
            quotient = old_r.divide(r);
            temp = old_r;
            old_r = r;
            r = temp.subtract(quotient.multiply(r));

            temp = old_s;
            old_s = s;
            s = temp.subtract(quotient.multiply(s));

        }
        return old_s;
    }

    private static BigInteger squareAndMult(BigInteger a, BigInteger b, BigInteger n) {
        if (b.compareTo(BigInteger.ZERO) == 0) return BigInteger.ONE;

        BigInteger y = BigInteger.ONE;
        while (b.compareTo(BigInteger.ONE) == 1) {
            if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                y = a.multiply(y).mod(n);
                b = b.subtract(BigInteger.ONE);
            }
            a = a.multiply(a).mod(n);
            b = b.divide(BigInteger.TWO);
        }
        return a.multiply(y).mod(n);
    }

    private static BigInteger OS2IP(char[] os) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < os.length; i++) {
            char c = os[os.length - 1 - i]; // Endianness i forgor
            result = result.add(BigInteger.valueOf(c).multiply(BigInteger.valueOf(256).pow(i)));
        }
        return result;
    }

    private static char[] I2OSP(BigInteger input) {
        ArrayList<Character> result = new ArrayList<>();
        while (input.compareTo(BigInteger.ZERO) == 1) {
            result.add((char)input.mod(BigInteger.valueOf(256)).intValue());
            input = input.shiftRight(8);
        }
        Collections.reverse(result);

        return result.toString().toCharArray();
    }

    public static void main(String[] args) {
        char[] a = "SHGDPSGHSSDGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGPDGSDG".toCharArray();
        RSA thing = new RSA(2048);
        thing.generateKey();

        char[] enc = thing.encrypt(a);
        char[] dec = thing.decrypt(enc);
        System.out.println("Dec " + new String(dec));
    }
}
