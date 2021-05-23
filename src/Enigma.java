import java.util.*;

/**
 * Class for transforming a Enigma cipher
 */

public class Enigma {
    private String scr11;
    private String scr12;
    private String scr21;
    private String scr22;
    private String scr31;
    private String scr32;
    private String ref1;
    private String ref2;
    private HashMap<Character, Character> rewi = new HashMap<>();

    /**
     * Constructor of the Enigma cipher class
     */
    public Enigma() {
        // initial rewiring, a = a, b = b, ...
        for (int i= 97; i < 123; i++){
            rewi.put((char)i, (char)i);
        }
    }

    /**
     * Set the scramblers, the string corresponds to the initial position of the scramblers and the order of its letters.
     * If a scramblers is not present leave empty
     * @param scr11 Scrambler 1 first letter list
     * @param scr12 Scrambler 1 second letter list
     * @param scr21 Scrambler 2 first letter list
     * @param scr22 Scrambler 2 second letter list
     * @param scr31 Scrambler 3 first letter list
     * @param scr32 Scrambler 3 second letter list
     * @param ref1 Reflector 1 first letter list
     * @param ref2 Reflector 1 second letter list
     */
    public void setScrambler (String scr11, String scr12, String scr21, String scr22,String scr31, String scr32, String ref1, String ref2){
        this.scr11 = scr11;
        this.scr12 = scr12;
        this.scr21 = scr21;
        this.scr22 = scr22;
        this.scr31 = scr31;
        this.scr32 = scr32;
        this.ref1 = ref1;
        this.ref2 = ref2;
    }

    /**
     *  Resets rewiring, a = a, b = b, ...
     */
    public void resetRewiring (){
        for (int i= 97; i < 123; i++){
            rewi.replace((char)i, (char)i);
        }
    }

    /**
     * Rewire two letter, swapping them
     * @param letter1 letter to wire together
     * @param letter2 letter to wire together
     */
    public void rewiring (char letter1, char letter2){
        rewi.replace(letter1, letter2);
        rewi.replace(letter2, letter1);
    }

    /**
     * Transforms the plaintext according to the Enigma machine
     * @param plaintext the enciphered or unenciphered text
     * @return transformed plaintext
     */
    public String transform (String plaintext) {
        plaintext = plaintext.toLowerCase();
        StringBuilder textout = new StringBuilder();
        char first;
        int index;
        int scr2 = 0;
        int scr3 = 0;

        for (int i = 0; i < plaintext.length(); i++) {
            if (rewi.containsKey(plaintext.charAt(i))) { // check if it is a letter
                // move scrambler 1
                if (scr11.length() > 0 && scr11.length() < 27 && scr12.length() > 0 && scr12.length() < 27) {
                    first = scr11.charAt(0);
                    scr11 = scr11 + first;
                    scr11 = scr11.substring(1);

                    first = scr12.charAt(0);
                    scr12 = scr12 + first;
                    scr12 = scr12.substring(1);
                }

                scr2++;

                if (scr2 == 25) {
                    // move scrambler 2
                    if (scr21.length() > 0 && scr21.length() < 27 && scr22.length() > 0 && scr22.length() < 27) {
                        first = scr21.charAt(0);
                        scr21 = scr21 + first;
                        scr21 = scr21.substring(1);

                        first = scr22.charAt(0);
                        scr22 = scr22 + first;
                        scr22 = scr22.substring(1);
                    }

                    scr2 = 0;
                    scr3++;

                    if (scr3 == 25) {
                        // move scrambler 3
                        if (scr31.length() > 0 && scr31.length() < 27 && scr32.length() > 0 && scr32.length() < 27) {
                            first = scr31.charAt(0);
                            scr31 = scr31 + first;
                            scr31 = scr31.substring(1);

                            first = scr32.charAt(0);
                            scr32 = scr32 + first;
                            scr32 = scr32.substring(1);
                        }

                        scr3 = 0;
                    }
                }

                // rewiring
                index = ((int) rewi.get(plaintext.charAt(i))) - 97;

                // scrambler 1
                if (scr11.length() == 26 && scr12.length() == 26) {
                    index = scr12.indexOf(scr11.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 1. Check for duplicates in scramblers";
                    }
                }

                // scrambler 2
                if (scr21.length() == 26 && scr22.length() == 26) {
                    index = scr22.indexOf(scr21.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 2. Check for duplicates in scramblers";
                    }
                }

                // scrambler 3
                if (scr31.length() == 26 && scr32.length() == 26) {
                    index = scr32.indexOf(scr31.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 3. Check for duplicates in scramblers";
                    }
                }

                // reflector
                if (ref1.length() == 26 && ref2.length() == 26) {
                    index = ref2.indexOf(ref1.charAt(index));
                    if (index == -1) {
                        return "mapping error in reflector. Check for duplicates in reflector";
                    }
                }

                // scrambler 3 reverse
                if (scr31.length() == 26 && scr32.length() == 26) {
                    index = scr31.indexOf(scr32.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 3. Check for duplicates in scramblers";
                    }
                }

                // scrambler 2 reverse
                if (scr21.length() == 26 && scr22.length() == 26) {
                    index = scr21.indexOf(scr22.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 2. Check for duplicates in scramblers";
                    }
                }

                // scrambler 1 reverse
                if (scr11.length() == 26 && scr12.length() == 26) {
                    index = scr11.indexOf(scr12.charAt(index));
                    if (index == -1) {
                        return "mapping error in scrambler 1. Check for duplicates in scramblers";
                    }
                }

                // rewiring and append
                textout.append(rewi.get((char) (97 + index)));
            }
        }
        return textout.toString();
    }
}
