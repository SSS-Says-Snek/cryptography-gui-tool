package com.bmcomis2018.cryptgui.algos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

public class PrimitiveEnc {
    public static BigInteger OS2IP(char[] os) {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < os.length; i++) {
            char c = os[os.length - 1 - i]; // Endianness i forgor
            result = result.add(BigInteger.valueOf(c).multiply(BigInteger.valueOf(256).pow(i)));
        }
        return result;
    }

    public static char[] I2OSP(BigInteger input) {
        ArrayList<Character> result = new ArrayList<>();
        while (input.compareTo(BigInteger.ZERO) == 1) {
            result.add((char)input.mod(BigInteger.valueOf(256)).intValue());
            input = input.shiftRight(8);
        }
        Collections.reverse(result);

        return arrayListCharToPrimitive(result);
    }

    private static char[] arrayListCharToPrimitive(ArrayList<Character> array) {
        char[] result = new char[array.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = array.get(i);
        }
        return result;
    }

}
