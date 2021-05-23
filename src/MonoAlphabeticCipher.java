import java.util.HashMap;

/**
 * Class for transforming a mono alphabetic cipher
 */

public class MonoAlphabeticCipher {
        HashMap<Character,Character> encodings = new HashMap<>();

        /**
         * Constructor of the mono alphabetic cipher class
         */
        public MonoAlphabeticCipher() {
                // initial encodings, all '-' = empty
                for (int i= 97; i < 123; i++){
                        encodings.put((char)i, '-');
                }
        }

        /**
         * Add the encoding for the key
         * @param key the letter in the plain text
         * @param letter the letter in the deciphered/enciphered text
         */
        public void addEncoding(char key, char letter){
                encodings.replace(key, letter);
        }

        /**
         * Output the entire text according to the encoding that have been given so far
         * @param plaintext the enciphered or unenciphered text
         * @return Transformed text
         */
        public String transform(String plaintext){
                plaintext = plaintext.toLowerCase();
                for (int i= 97; i < 123; i++){
                        if (encodings.get((char) i) != '-') {
                                plaintext = plaintext.replace((char) i, Character.toUpperCase(encodings.get((char) i)));
                        }
                }
                return plaintext;
        }

}
