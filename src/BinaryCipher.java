import java.util.ArrayList;

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
    public String xorTransform (String key, String plaintext) {
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

    public String vShiftTransform (String key, String plaintext){
        // check input, bad characters are not checked be nice
        if (key.length() != 8){
            return "Key is not 8 numbers";
        }

        // transform the key into shift numbers
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < key.length(); i++){
             keys.add(key.charAt(i) - '0');
        }

        // sets up a matrix of int with 8 rows
        ArrayList<StringBuilder> plainbin = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            plainbin.add(new StringBuilder());
        }

        // insert the characters of the main text into the matrix
        for (int i = 0; i < plaintext.length(); i++){
            String tempbin = String.format("%8s", Integer.toBinaryString(plaintext.charAt(i))).replace(' ', '0');
            for (int x = 0; x < tempbin.length(); x++){
                plainbin.get(x).append(tempbin.charAt(x));
            }
        }

        // shift the matrix according to the key
        ArrayList<String> plainbinout = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++){
            String tempbin = plainbin.get(i).toString();
            String end = tempbin.substring(tempbin.length() - (keys.get(i) % tempbin.length()));
            tempbin = end + tempbin;
            tempbin = tempbin.substring(0, tempbin.length() - (keys.get(i) % tempbin.length()));
            plainbinout.add(tempbin);
        }

        // Convert back to text
        StringBuilder plainout = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++){
            StringBuilder tempbin = new StringBuilder();
            for (int x = 0; x < keys.size(); x++){
                tempbin.append(plainbinout.get(x).charAt(i));
            }
            plainout.append((char) Integer.parseInt(tempbin.toString(), 2));
        }

        return plainout.toString();
    }
}
