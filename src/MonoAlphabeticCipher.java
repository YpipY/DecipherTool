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

        public void addLetter(char key, char letter){
                encodings.replace(key, letter);
        }

        public String outputText(String plaintext){
                plaintext = plaintext.toLowerCase();
                for (int i= 97; i < 123; i++){
                        if (encodings.get((char) i) != '-') {
                                plaintext = plaintext.replace((char) i, Character.toUpperCase(encodings.get((char) i)));
                        }
                }

                return plaintext;
        }

}
