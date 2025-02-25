package tr.beytullahakyuz.securebase;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author beytullahakyuz
 */
public final class SecureBase implements AutoCloseable {
    
    public enum SBEncoding
    {
	UNICODE,
	UTF8;

	public static final int SIZE = java.lang.Integer.SIZE;

	public int getValue()
	{
            return this.ordinal();
	}

	public static SBEncoding forValue(int value)
	{
            return values()[value];
	}
    }
    
    private final String defcharset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"#&'()*,-.:;<>?@[]\\^_{}|~/+=";
    private final String base64standart = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private String globalcharset = "";
    private char padding;
    private boolean disposed = false;
    SBEncoding GEncoding;

    public SecureBase(SBEncoding encoding) {
        globalcharset = base64standart;
        padding = '=';
        GEncoding = encoding;
    }

    public SecureBase(SBEncoding encoding, String secretkey) {
        SetSecretKey(secretkey);
        GEncoding = encoding;
    }
    
    public void SetSecretKey(String secretkey) {
        if (secretkey.length() != 0) {
            globalcharset = defcharset;
            pr_SuffleCharset(secretkey);
            padding = globalcharset.charAt(64);
            globalcharset = globalcharset.substring(0, 64);
        } else {
            globalcharset = base64standart;
            padding = '=';
        }
    }
    
    public String encode(String input) throws Exception {
        if (GEncoding == SBEncoding.UNICODE){
            byte[] utf16bytes = removeBOM(processEncoding(input.getBytes(StandardCharsets.UTF_16LE)));
            String utf8string = new String(utf16bytes, "UTF-8");
            return utf8string;
        }else
            return new String(new String(processEncoding(input.getBytes(StandardCharsets.UTF_8))).getBytes(StandardCharsets.UTF_8));
    }
    
    public String decode(String input) throws Exception {
        byte[] processedBytes = processDecoding(input);
        if (GEncoding == SBEncoding.UNICODE)
            return new String(processedBytes, StandardCharsets.UTF_16LE);
        else
            return new String(processedBytes, StandardCharsets.UTF_8);
    }
    
    private byte[] processEncoding(byte[] input) throws Exception{
        try {
            char[] baseArray = globalcharset.toCharArray();
            byte[] pdata = input;
            int[] positiveData = new int[pdata.length];
            for (int i = 0; i < pdata.length; i++) {
                positiveData[i] = (pdata[i] & 0xFF);
            }
            char[] encodedData = new char[0];
            if (positiveData.length > 0) {
                int length = positiveData.length;
                int lengthDiv3 = length / 3;
                int remainder = length % 3;
                int encodedLength = (lengthDiv3 * 4) + (remainder == 0 ? 0 : 4);
                encodedData = new char[encodedLength];
                int dataIndex = 0;
                int encodedIndex = 0;
                for (int i = 0; i < lengthDiv3; i++) {
                    int chunk = (positiveData[dataIndex++] << 16) | (positiveData[dataIndex++] << 8) | positiveData[dataIndex++];
                    encodedData[encodedIndex++] = baseArray[(chunk >> 18) & 63];
                    encodedData[encodedIndex++] = baseArray[(chunk >> 12) & 63];
                    encodedData[encodedIndex++] = baseArray[(chunk >> 6) & 63];
                    encodedData[encodedIndex++] = baseArray[chunk & 63];
                }
                if (remainder == 1) {
                    int lastByte = positiveData[dataIndex];
                    encodedData[encodedIndex++] = baseArray[lastByte >> 2];
                    encodedData[encodedIndex++] = baseArray[((lastByte & 3) << 4)];
                    encodedData[encodedIndex++] = padding;
                    encodedData[encodedIndex++] = padding;
                } else if (remainder == 2) {
                    int secondLastByte = positiveData[dataIndex++];
                    int lastByte = positiveData[dataIndex];
                    encodedData[encodedIndex++] = baseArray[secondLastByte >> 2];
                    encodedData[encodedIndex++] = baseArray[((secondLastByte & 3) << 4) | (lastByte >> 4)];
                    encodedData[encodedIndex++] = baseArray[(lastByte & 15) << 2];
                    encodedData[encodedIndex++] = padding;
                }
            }
            if (GEncoding == SBEncoding.UNICODE)
                return new String(encodedData).getBytes(StandardCharsets.UTF_16LE);
            else
                return new String(encodedData).getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("Invalid data or secret key!", e);
        }
    }
    
    private byte[] processDecoding(String input) throws Exception{
        try {
            char[] baseArray = globalcharset.toCharArray();
            byte[] decodedData = new byte[0];
            if (input.length() > 0) {
                byte[] base64Values = new byte[256];
                for (int i = 0; i < 64; i++) {
                    base64Values[baseArray[i]] = (byte) i;
                }
                int length = input.length();
                int paddingCount = 0;
                if (length > 0 && input.charAt(length - 1) == padding) {
                    paddingCount++;
                }
                if (length > 1 && input.charAt(length - 2) == padding) {
                    paddingCount++;
                }
                int decodedLength = (length * 3) / 4 - paddingCount;
                decodedData = new byte[decodedLength];
                int encodedIndex = 0;
                int decodedIndex = 0;
                while (encodedIndex < length) {
                    int chunk = (base64Values[input.charAt(encodedIndex++)] << 18) |
                        (base64Values[input.charAt(encodedIndex++)] << 12) |
                        (base64Values[input.charAt(encodedIndex++)] << 6) |
                        base64Values[input.charAt(encodedIndex++)];

                    decodedData[decodedIndex++] = (byte) ((chunk >> 16) & 0xFF);
                    if (decodedIndex < decodedLength) {
                        decodedData[decodedIndex++] = (byte) ((chunk >> 8) & 0xFF);
                    }
                    if (decodedIndex < decodedLength) {
                        decodedData[decodedIndex++] = (byte) (chunk & 0xFF);
                    }  
                }   
            }
            return decodedData;
        } catch (Exception e) {
            throw new Exception("Invalid data or secret key!", e);
        }
    }
    
    private byte[] removeBOM(byte[] bytes){
        int len = bytes.length;
        byte[] newbytes = new byte[len/2];
        int nindex = 0;
        for (int i=0; i < len; i+=2){
            if (bytes[i+1] == 0){
                newbytes[nindex] = bytes[i];
                nindex++;
            }
        }
        return newbytes;
    }
    
    private void pr_SuffleCharset(String secretkey) {
        String secrethash = computeHash(secretkey, 512);
        globalcharset = fn_SuffleCharset(globalcharset, fn_CharacterSetSecretKey(secrethash));
    }

    private String computeHash(String s, int key) {
        Keccak keccak = new Keccak();
        byte[] input = s.getBytes(StandardCharsets.UTF_8);
        byte[] hash = keccak.hash(input, key);
        keccak.dispose();

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String fn_SuffleCharset(String data, int[] keys) {
        char[] karakterler = data.toCharArray();
        int keylen = keys.length;
        for (int j = 0; j < keylen - 1; j++) {
            for (int i = karakterler.length - 1; i > 0; i--) {
                int x = (i * keys[j]) % karakterler.length;
                char temp = karakterler[i];
                karakterler[i] = karakterler[x];
                karakterler[x] = temp;
            }
        }
        return new String(karakterler);
    }

    private int[] fn_CharacterSetSecretKey(String anahtar) {
        int[] arr = new int[anahtar.length()];
        for (int i = 0; i < anahtar.length() - 1; i++) {
            char c = anahtar.charAt(i);
            int hs = 0;
            hs = (hs * 31 + c) % Integer.MAX_VALUE;
            arr[i] = hs;
        }
        return arr;
    }

    @Override
    public void close() {
        dispose(true);
    }

    protected void dispose(boolean disposing) {
        if (!disposed) {
            disposed = true;
        }
    }

    @Override
    protected void finalize() throws Throwable {
        dispose(false);
    }
}