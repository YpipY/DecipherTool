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

    /**
     * Decipheres the giving text according to the rules of a Vigenere cipher (a=1, b=2, ... )
     * @param plaintext the enciphered text
     * @param key the key used for deciphering
     * @return deciphered text
     */
    public String vigenereDecipher(String plaintext, String key){
        return vigenereTransfrom(plaintext, key, true);
    }

    /**
     * Enciphers the giving text according to the rules of a Vigenere cipher (a=1, b=2, ... )
     * @param plaintext the unenciphered text
     * @param key the key used for enciphering
     * @return enciphered text
     */
    public String vigenereEncipher (String plaintext, String key){
        return vigenereTransfrom(plaintext, key, false);

    }

    /**
     * Enciphers or deciphers the giving text according to the rules of a Vigenere cipher (a=1, b=2, ... )
     * @param plaintext the enciphered or unenciphered text
     * @param key the key used for deciphering or enciphering
     * @param decipher true if it should decipher
     * @return Transformed text
     */
    private String vigenereTransfrom (String plaintext, String key, boolean decipher){
        // setup
        StringBuilder textout = new StringBuilder("");
        plaintext = plaintext.toLowerCase();

        // get numbers of shifts from the key
        ArrayList<Integer> shift = keyShift(key);

        // transform the given text using the given key
        int keypos = 0;
        if (!plaintext.equals("") && !key.equals("")) {
            for (int i = 0; i < plaintext.length(); i++) {
                if (keypos == shift.size()) {  // cycles over the key
                    keypos = 0;
                }
                int temp = plaintext.charAt(i) - 97; // ASCII number
                if (0 <= temp && temp <= 25) {
                    if (decipher){
                        if ((temp - shift.get(keypos)) >= 0){                           // Decipher
                            textout.append((char) (97 + ((temp - shift.get(keypos)))));
                        } else{
                            textout.append((char) (123 + ((temp - shift.get(keypos)))));
                        }
                    } else {                                                            // Encipher
                        textout.append((char) (97 + ((temp + shift.get(keypos)) % 26)));
                    }
                    keypos++;
                } else{
                    textout.append(plaintext.charAt(i));
                }
            }
        }
        return textout.toString();
    }

    /**
     * Gets number if shifts along the alphabet the key indicates (a=1, b=2, ... )
     * @param key the key used for enciphering
     * @return List of the number of shifts for each letter/number in the key
     */
    private ArrayList<Integer> keyShift (String key){
        ArrayList<Integer> shift = new ArrayList<>();
        for (int i = 0; i <  key.length(); i++){
            int newshift = key.charAt(i) - 96;
            if (1 <= newshift && newshift <= 26){ // if letter
                shift.add(newshift);
            }else if (-48 <= newshift && newshift <= -39){ // if number
                shift.add(key.charAt(i) - '0');
            }
        }
        return shift;
    }
}
