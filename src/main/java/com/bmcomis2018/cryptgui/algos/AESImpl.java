package com.bmcomis2018.cryptgui.algos;

import java.util.Arrays;

public class AESImpl {
    private static final char[] S_BOX = {
        0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
        0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
        0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
        0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
        0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
        0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
        0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
        0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
        0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
        0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
        0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
        0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
        0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
        0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
        0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
        0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
    };

    private static final char[] INV_S_BOX = {
        0x52, 0x09, 0x6A, 0xD5, 0x30, 0x36, 0xA5, 0x38, 0xBF, 0x40, 0xA3, 0x9E, 0x81, 0xF3, 0xD7, 0xFB,
        0x7C, 0xE3, 0x39, 0x82, 0x9B, 0x2F, 0xFF, 0x87, 0x34, 0x8E, 0x43, 0x44, 0xC4, 0xDE, 0xE9, 0xCB,
        0x54, 0x7B, 0x94, 0x32, 0xA6, 0xC2, 0x23, 0x3D, 0xEE, 0x4C, 0x95, 0x0B, 0x42, 0xFA, 0xC3, 0x4E,
        0x08, 0x2E, 0xA1, 0x66, 0x28, 0xD9, 0x24, 0xB2, 0x76, 0x5B, 0xA2, 0x49, 0x6D, 0x8B, 0xD1, 0x25,
        0x72, 0xF8, 0xF6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xD4, 0xA4, 0x5C, 0xCC, 0x5D, 0x65, 0xB6, 0x92,
        0x6C, 0x70, 0x48, 0x50, 0xFD, 0xED, 0xB9, 0xDA, 0x5E, 0x15, 0x46, 0x57, 0xA7, 0x8D, 0x9D, 0x84,
        0x90, 0xD8, 0xAB, 0x00, 0x8C, 0xBC, 0xD3, 0x0A, 0xF7, 0xE4, 0x58, 0x05, 0xB8, 0xB3, 0x45, 0x06,
        0xD0, 0x2C, 0x1E, 0x8F, 0xCA, 0x3F, 0x0F, 0x02, 0xC1, 0xAF, 0xBD, 0x03, 0x01, 0x13, 0x8A, 0x6B,
        0x3A, 0x91, 0x11, 0x41, 0x4F, 0x67, 0xDC, 0xEA, 0x97, 0xF2, 0xCF, 0xCE, 0xF0, 0xB4, 0xE6, 0x73,
        0x96, 0xAC, 0x74, 0x22, 0xE7, 0xAD, 0x35, 0x85, 0xE2, 0xF9, 0x37, 0xE8, 0x1C, 0x75, 0xDF, 0x6E,
        0x47, 0xF1, 0x1A, 0x71, 0x1D, 0x29, 0xC5, 0x89, 0x6F, 0xB7, 0x62, 0x0E, 0xAA, 0x18, 0xBE, 0x1B,
        0xFC, 0x56, 0x3E, 0x4B, 0xC6, 0xD2, 0x79, 0x20, 0x9A, 0xDB, 0xC0, 0xFE, 0x78, 0xCD, 0x5A, 0xF4,
        0x1F, 0xDD, 0xA8, 0x33, 0x88, 0x07, 0xC7, 0x31, 0xB1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xEC, 0x5F,
        0x60, 0x51, 0x7F, 0xA9, 0x19, 0xB5, 0x4A, 0x0D, 0x2D, 0xE5, 0x7A, 0x9F, 0x93, 0xC9, 0x9C, 0xEF,
        0xA0, 0xE0, 0x3B, 0x4D, 0xAE, 0x2A, 0xF5, 0xB0, 0xC8, 0xEB, 0xBB, 0x3C, 0x83, 0x53, 0x99, 0x61,
        0x17, 0x2B, 0x04, 0x7E, 0xBA, 0x77, 0xD6, 0x26, 0xE1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0C, 0x7D
    };

    private static final int NB = 4;
    private static final int BLOCK_SIZE = 16;

    public static final int AES_128_ROUNDS = 10;
    public static final int AES_192_ROUNDS = 12;
    public static final int AES_256_ROUNDS = 14;

    public static final int AES_128_KEYLEN = 4;
    public static final int AES_192_KEYLEN = 6;
    public static final int AES_256_KEYLEN = 8;

    private static char gfMultBytes(char p1, char p2) {
        char product = ((p2 & 1) == 1) ? p1 : 0;
        char temp = p1;
        for (int i = 0; i < 8; i++) {
            char carry = (char)(temp & 0x80);
            temp <<= 1;
            temp &= 0xff;
            if (carry != 0) {
                temp ^= 0x1b;
            }
            p2 >>= 1;
            if ((p2 & 1) == 1) {
                product ^= temp;
            }
        }
        return product;
    }

    private static long gfMultWords(long w1, long w2) {
        assert (0 <= w1 && w1 <= 0xffffffffL);
        assert (0 <= w2 && w2 <= 0xffffffffL);

        char a0 = (char)(w1 & 0xff);
        char a1 = (char)((w1 & 0xff00) >> 8);
        char a2 = (char)((w1 & 0xff0000) >> 16);
        char a3 = (char)((w1 & 0xff000000L) >> 24);
        char b0 = (char)(w2 & 0xff);
        char b1 = (char)((w2 & 0xff00) >> 8);
        char b2 = (char)((w2 & 0xff0000) >> 16);
        char b3 = (char)((w2 & 0xff000000L) >> 24);

        long product = gfMultBytes(a0, b0) ^ gfMultBytes(a3, b1) ^ gfMultBytes(a2, b2) ^ gfMultBytes(a1, b3);
        product |= (gfMultBytes(a1, b0) ^ gfMultBytes(a0, b1) ^ gfMultBytes(a3, b2) ^ gfMultBytes(a2, b3)) << 8;
        product |= (long)(gfMultBytes(a2, b0) ^ gfMultBytes(a1, b1) ^ gfMultBytes(a0, b2) ^ gfMultBytes(a3, b3)) << 16;
        product |= (long)(gfMultBytes(a3, b0) ^ gfMultBytes(a2, b1) ^ gfMultBytes(a1, b2) ^ gfMultBytes(a0, b3)) << 24;
        return product;
    }

    private static long rotWord(long word) {
        assert (0 <= word && word <= 0xffffffffL);
        return ((word << 8) | (word >> 24)) & 0xffffffffL;
    }

    private static void shiftRows(char[] state) {
        for (int i = 1; i < NB; i++) {
            long row = (state[i]) | (state[i + NB] << 8) | ((long)state[i + 2*NB] << 16) | ((long)state[i + 3*NB] << 24);
            long wrappingPart = row & ((1L << 8*i) - 1);
            long newRow = (row >> 8*i) | (wrappingPart << 32-8*i);

            for (int j = 0; j < 4; j++) {
                state[i + 4*j] = (char)((newRow & 0xffL << 8*j) >> 8*j);
            }
        }
    }

    private static void mixColumns(char[] state) {
        for (int i = 0; i < NB; i++) {
            long column = (state[4*i]) | (state[4*i + 1] << 8) | ((long)state[4*i + 2] << 16) | ((long)state[4*i + 3] << 24);
            long newColumn = gfMultWords(column, 0x03010102L);
            for (int j = 0; j < 4; j++) {
                state[4*i + j] = (char)((newColumn & 0xff << 8*j) >> 8*j);
            }
        }
    }

    private static void addRoundKey(char[] state, long[] roundKey) {
        for (int i = 0; i < NB; i++) {
            for (int j = 0; j < 4; j++) {
                state[4*i + j] ^= (char)((roundKey[i] & 0xff << 24-8*j) >> 24-8*j);
            }
        }
    }

    private static void subBytes(char[] state) {
        for (int i = 0; i < NB * 4; i++) {
            state[i] = S_BOX[state[i]];
        }
    }

    private static long subWord(long word) {
        return (long)S_BOX[(char)(word & 0xff)] | ((long)S_BOX[(char)((word & 0xff00L) >> 8)] << 8) | ((long)S_BOX[(char)((word & 0xff0000L) >> 16)] << 16) | ((long)S_BOX[(char)((word & 0xff000000L) >> 24)] << 24);
    }

    private static long[] keyExpansion(char[] key, int keylen, int numRounds) {
        long temp;
        long[] expandedKey = new long[4 * (numRounds + 1)];
        for (int i = 0; i < keylen; i++) {
            expandedKey[i] = ((long)key[4*i] << 24) | ((long)key[4 * i + 1] << 16) | (key[4*i + 2] << 8) | (key[4*i + 3]);
        }

        long rcon = 0x01000000;
        for (int i = keylen; i < 4 * (numRounds + 1); i++) {
            temp = expandedKey[i - 1];
            if (i % keylen == 0) {
                temp = subWord(rotWord(temp)) ^ rcon;
                long carry = rcon & 0x80000000L;

                rcon <<= 1;
                if (carry > 0) {
                    rcon &= 0xffffffffL;
                    rcon ^= 0x1b000000L;
                }
            }
            else if (keylen == 8 && i % 8 == 4) {
                temp = subWord(temp);
            }
            expandedKey[i] = expandedKey[i - keylen] ^ temp;
        }

        return expandedKey;
    }

    private static char[] cipher(char[] bytes, long[] roundKeys, int numRounds) {
        char[] state = Arrays.copyOf(bytes, bytes.length);
        addRoundKey(state, Arrays.copyOfRange(roundKeys, 0, NB));

        for (int i = 0; i < numRounds - 1; i++) {
            subBytes(state);
            shiftRows(state);
            mixColumns(state);
            addRoundKey(state, Arrays.copyOfRange(roundKeys, (i+1)*NB, (i+2)*NB));
        }

        // Last round; no mix columns
        subBytes(state);
        shiftRows(state);
        addRoundKey(state, Arrays.copyOfRange(roundKeys, roundKeys.length - NB, roundKeys.length));

        return state;
    }

    /////////////////////////////////////
    // INVERSE                         //
    /////////////////////////////////////
    
    private static void invShiftRows(char[] state) {
        for (int i = 1; i < NB; i++) {
            long row = (state[i]) | (state[i + NB] << 8) | ((long)state[i + 2*NB] << 16) | ((long)state[i + 3*NB] << 24);
            long partToMove = row & ((1L << 32-8*i) - 1);
            long wrappingPart = row >> 32-8*i;
            long newRow = (wrappingPart) | (partToMove << 8*i);

            for (int j = 0; j < 4; j++) {
                state[i + 4*j] = (char)((newRow & 0xffL << 8*j) >> 8*j);
            }
        }
    }

    private static void invMixColumns(char[] state) {
        for (int i = 0; i < NB; i++) {
            long column = (state[4*i]) | (state[4*i + 1] << 8) | ((long)state[4*i + 2] << 16) | ((long)state[4*i + 3] << 24);
            long newColumn = gfMultWords(column, 0x0b0d090eL);
            for (int j = 0; j < 4; j++) {
                state[4*i + j] = (char)((newColumn & 0xff << 8*j) >> 8*j);
            }
        }
    }
    
    private static void invSubBytes(char[] state) {
        for (int i = 0; i < NB * 4; i++) {
            state[i] = INV_S_BOX[state[i]];
        }
    }

    private static char[] invCipher(char[] bytes, long[] roundKeys, int numRounds) {
        char[] state = Arrays.copyOf(bytes, bytes.length);
        addRoundKey(state, Arrays.copyOfRange(roundKeys, roundKeys.length - NB, roundKeys.length));

        for (int i = numRounds - 1; i > 0; i--) {
            invShiftRows(state);
            invSubBytes(state);
            addRoundKey(state, Arrays.copyOfRange(roundKeys, i*NB, (i+1)*NB));
            invMixColumns(state);
        }

        // Last round; no mix columns
        invShiftRows(state);
        invSubBytes(state);
        addRoundKey(state, Arrays.copyOfRange(roundKeys, 0, NB));

        return state;
    }

    //////////////////////////////////
    // API                          //
    //////////////////////////////////


    private static void stupidPrint(char[] stupidArray) {
        for (char c : stupidArray) {
            System.out.print(Integer.toHexString(c) + "_");
        }
        System.out.println();
    }
    
    private static char[] encryptGeneric(char[] plaintext, char[] key, int keylen, int rounds) {
        long[] expandedKey = keyExpansion(key, keylen, rounds);

        int i;
        int roundedCipherLength = (int)Math.ceil((double)plaintext.length / BLOCK_SIZE) * BLOCK_SIZE;
        char[] ciphertext = new char[roundedCipherLength];
        for (i = 0; i < plaintext.length / BLOCK_SIZE; i++) {
            char[] block = Arrays.copyOfRange(plaintext, i * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
            char[] cipherBlock = cipher(block, expandedKey, rounds);
            System.arraycopy(cipherBlock, 0, ciphertext, i * BLOCK_SIZE, BLOCK_SIZE);
        }

        if (plaintext.length % BLOCK_SIZE != 0) {
            char[] finalBlock = new char[BLOCK_SIZE];
            char[] finalPlaintext = Arrays.copyOfRange(plaintext, i * BLOCK_SIZE, plaintext.length);
            Arrays.fill(finalBlock, (char)0); // Basically ljusts \x00
            System.arraycopy(finalPlaintext, 0, finalBlock, 0, finalPlaintext.length);

            char[] cipherBlock = cipher(finalBlock, expandedKey, rounds);
            System.arraycopy(cipherBlock, 0, ciphertext, i * BLOCK_SIZE, BLOCK_SIZE);
        }

        return ciphertext;
    }

    private static char[] decryptGeneric(char[] ciphertext, char[] key, int keylen, int rounds) {
        assert ciphertext.length % BLOCK_SIZE != 0;

        long[] expandedKey = keyExpansion(key, keylen, rounds);
        char[] plaintext = new char[ciphertext.length];
        for (int i = 0; i < ciphertext.length / BLOCK_SIZE; i++) {
            char[] block = Arrays.copyOfRange(ciphertext, i * BLOCK_SIZE, (i + 1) * BLOCK_SIZE);
            char[] invCipherBlock = invCipher(block, expandedKey, rounds);
            System.arraycopy(invCipherBlock, 0, plaintext, i * BLOCK_SIZE, BLOCK_SIZE);
        }

        return plaintext;
    }

    public static char[] encrypt(char[] plaintext, char[] key) {
        if (key.length / 4 == AES_128_KEYLEN) return encryptGeneric(plaintext, key, AES_128_KEYLEN, AES_128_ROUNDS);
        else if (key.length / 4 == AES_192_KEYLEN) return encryptGeneric(plaintext, key, AES_192_KEYLEN, AES_192_ROUNDS);
        else if (key.length / 4 == AES_256_KEYLEN) return encryptGeneric(plaintext, key, AES_256_KEYLEN, AES_256_ROUNDS);
        else {
            System.err.println("!!!!!");
            return new char[0];
        }
    }

    public static char[] decrypt(char[] ciphertext, char[] key) {
        if (key.length / 4 == AES_128_KEYLEN) return decryptGeneric(ciphertext, key, AES_128_KEYLEN, AES_128_ROUNDS);
        else if (key.length / 4 == AES_192_KEYLEN) return decryptGeneric(ciphertext, key, AES_192_KEYLEN, AES_192_ROUNDS);
        else if (key.length / 4 == AES_256_KEYLEN) return decryptGeneric(ciphertext, key, AES_256_KEYLEN, AES_256_ROUNDS);
        else {
            System.err.println("!!!!!");
            return new char[0];
        }
    }

    public static void main(String[] args) {
        char[] e = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        char[] plain = {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2'};
        char[] ciphere = encrypt(plain, e);
        stupidPrint(ciphere);

//        char[] plainHopefully = invCipher(Arrays.copyOfRange(ciphere, BLOCK_SIZE, ciphere.length), g, 10);
        char[] plainHope = decrypt(ciphere, e);
        stupidPrint(plainHope);
    }
}
