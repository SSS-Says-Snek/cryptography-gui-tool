package com.bmcomis2018.cryptgui.algos;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

public class RSA implements AsymmetricAlgo<BigInteger> {
    private BigInteger n;
    private int keysize;

    private BigInteger e = BigInteger.valueOf(65537);
    private BigInteger d;

    private SecureRandom rand;

    public RSA(int keysize) {
        this.keysize = keysize;
        rand = new SecureRandom();
    }

    public void generateKey() {
        BigInteger p = new BigInteger(keysize, 128, rand);
        BigInteger q = new BigInteger(keysize, 128, rand);

        BigInteger pmin1 = p.subtract(BigInteger.ONE);
        BigInteger qmin1 = q.subtract(BigInteger.ONE);
        
        n = p.multiply(q);
        BigInteger totn = (pmin1.multiply(qmin1)).divide(gcd(pmin1, qmin1));

        d = multInv(e, totn);
        if (d.compareTo(BigInteger.ZERO) < 0) {
            d = totn.add(d);
        }

    }

    public char[] encrypt(char[] plaintext) {
        BigInteger message = PrimitiveEnc.OS2IP(plaintext);
        BigInteger ciphertext = squareAndMult(message, e, n);
        return Base64.getEncoder().encodeToString(ciphertext.toByteArray()).toCharArray();
    }

    public char[] decrypt(char[] ciphertext) {
        BigInteger cipherMessage = new BigInteger(Base64.getDecoder().decode(new String(ciphertext).getBytes()));
        return PrimitiveEnc.I2OSP(squareAndMult(cipherMessage, d, n));
    }

    // public AsymmetricKey<BigInteger> validateKey(String e, String d) { // Yeah no no validation plz
    //     return new AsymmetricKey<BigInteger>();
    // }

    public void setKey(BigInteger e, BigInteger d) {
        this.e = e;
        this.d = d;
    }

    public BigInteger getEncryptionKey() {
        return e;
    }

    public BigInteger getDecryptionKey() {
        return d;
    }

    // RSA AND CRYPTOGRAPHIC STUFF
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
        while (b.compareTo(BigInteger.ONE) > 0) {
            if (b.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                y = a.multiply(y).mod(n);
                b = b.subtract(BigInteger.ONE);
            }
            a = a.multiply(a).mod(n);
            b = b.divide(BigInteger.TWO);
        }
        return a.multiply(y).mod(n);
    }
}
