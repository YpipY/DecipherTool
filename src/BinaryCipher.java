import java.util.Locale;

/**
 * Class for transforming a binary text according to a key and XOR gate
 */

public class BinaryCipher {

    /**
     * Constructor of the Binary cipher class
     */
    public BinaryCipher(){

    }

    /**
     * Transforms the plaintext according to the Enigma machine
     * @param key the key
     * @param plaintext the enciphered or unenciphered text
     * @return transformed plaintext
     */
    public String transform (String key, String plaintext) {
        StringBuilder textout = new StringBuilder();
        int keypos = 0;
        if (key.length() > 0) {
            for (int i = 0; i < plaintext.length(); i++) {
                if (keypos == key.length()) {  // cycles over the key
                    keypos = 0;
                }
                int p = plaintext.charAt(i);
                int k = key.charAt(keypos);
                int trans = p ^ k;

                textout.append((char) trans);
                keypos++;
            }
        }
        return textout.toString();
    }
}
