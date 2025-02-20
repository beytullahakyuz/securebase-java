/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tr.beytullahakyuz.securebase;

/**
 *
 * @author beytullahakyuz
 */
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class Keccak {
    private static final int KeccakRounds = 24;
    private static final long[] RoundConstants = {
            0x0000000000000001L, 0x0000000000008082L, 0x800000000000808AL, 0x8000000080008000L,
            0x000000000000808BL, 0x0000000080000001L, 0x8000000080008081L, 0x8000000000008009L,
            0x000000000000008AL, 0x0000000000000088L, 0x0000000080008009L, 0x000000008000000AL,
            0x000000008000808BL, 0x800000000000008BL, 0x8000000000008089L, 0x8000000000008003L,
            0x8000000000008002L, 0x8000000000000080L, 0x000000000000800AL, 0x800000008000000AL,
            0x8000000080008081L, 0x8000000000008080L, 0x0000000080000001L, 0x8000000080008008L
    };

    private static final int[] RhoOffsets = {
            0, 1, 62, 28, 27,
            36, 44, 6, 55, 20,
            3, 10, 43, 25, 39,
            41, 45, 15, 21, 8,
            18, 2, 61, 56, 14
    };

    private long[] state = new long[25];
    private boolean disposed = false;

    public byte[] hash(byte[] input, int outputLengthBits) {
        if (disposed) throw new IllegalStateException("Object is disposed");

        int rateInBytes = (1600 - 2 * outputLengthBits) / 8;
        byte[] paddedMessage = pad(input, rateInBytes);
        absorb(paddedMessage, rateInBytes);
        return squeeze(outputLengthBits / 8);
    }

    private void absorb(byte[] message, int rateInBytes) {
        int blockSize = rateInBytes;
        for (int offset = 0; offset < message.length; offset += blockSize) {
            for (int i = 0; i < blockSize / 8; i++) {
                state[i] ^= toLong(Arrays.copyOfRange(message, offset + i * 8, offset + (i + 1) * 8));
            }
            keccakF();
        }
    }

    private byte[] squeeze(int outputLength) {
        byte[] output = new byte[outputLength];
        int offset = 0;

        while (outputLength > 0) {
            int bytesToOutput = Math.min(outputLength, 200);
            for (int i = 0; i < bytesToOutput; i++) {
                output[offset + i] = (byte) (state[i / 8] >>> (8 * (i % 8)));
            }
            offset += bytesToOutput;
            outputLength -= bytesToOutput;

            if (outputLength > 0) keccakF();
        }
        return output;
    }

    private void keccakF() {
        for (int round = 0; round < KeccakRounds; round++) {
            long[] C = new long[5];
            long[] D = new long[5];

            for (int i = 0; i < 5; i++) {
                C[i] = state[i] ^ state[i + 5] ^ state[i + 10] ^ state[i + 15] ^ state[i + 20];
            }

            for (int i = 0; i < 5; i++) {
                D[i] = C[(i + 4) % 5] ^ rol(C[(i + 1) % 5], 1);
            }

            for (int i = 0; i < 25; i += 5) {
                for (int j = 0; j < 5; j++) {
                    state[i + j] ^= D[j];
                }
            }

            long[] B = new long[25];
            for (int i = 0; i < 25; i++) {
                B[i] = rol(state[i], RhoOffsets[i]);
            }

            for (int i = 0; i < 25; i += 5) {
                for (int j = 0; j < 5; j++) {
                    state[i + j] = B[i + j] ^ (~B[i + ((j + 1) % 5)] & B[i + ((j + 2) % 5)]);
                }
            }

            state[0] ^= RoundConstants[round];
        }
    }

    private long rol(long x, int n) {
        return (x << n) | (x >>> (64 - n));
    }

    private byte[] pad(byte[] input, int rateInBytes) {
        int paddingLength = rateInBytes - (input.length % rateInBytes);
        byte[] padded = Arrays.copyOf(input, input.length + paddingLength);
        padded[input.length] = 0x06;
        padded[padded.length - 1] |= 0x80;
        return padded;
    }

    private long toLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        return buffer.getLong();
    }

    public void dispose() {
        if (!disposed) {
            Arrays.fill(state, 0);
            disposed = true;
        }
    }
}

