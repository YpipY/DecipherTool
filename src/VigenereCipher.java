import java.util.*;

/**
 * Class for transforming a Vigenere cipher
 */

public class VigenereCipher {

    /**
     * Constructor of the Vigenere Cipher class
     */
    public VigenereCipher() {
    }

    public String vigenereTransfrom (String plaintext, String key){
        StringBuilder textout = new StringBuilder("");
        ArrayList<Integer> shift = new ArrayList<>();

        // get numbers of shift from the key
        for (int i = 0; i <  key.length(); i++){
            int newshift = key.charAt(i) - 96;
            if (1 <= newshift && newshift <= 26){
                shift.add(newshift);
            }else if (-48 <= newshift && newshift <= -39){
                shift.add(key.charAt(i) - '0');
            }
        }

        // transform the given text using the given key
        int keypos = 0;
        if (!plaintext.equals("") && !key.equals("")) {
            for (int i = 0; i < plaintext.length(); i++) {
                if (keypos == shift.size()) {
                    keypos = 0;
                }
                int temp = plaintext.charAt(i) - 97;
                if (0 <= temp && temp <= 25) {
                    if ((temp - shift.get(keypos)) >= 0){
                        textout.append((char) (97 + ((temp - shift.get(keypos)))));
                    } else{
                        textout.append((char) (123 + ((temp - shift.get(keypos)))));
                    }
                    keypos++;
                } else{
                    textout.append(plaintext.charAt(i));
                }
            }
        }
        return textout.toString();
    }
}
