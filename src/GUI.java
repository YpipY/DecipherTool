import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Class for the GUI
 */

public class GUI {
    /**
     * Constructor of the GUI class
     */
    public GUI() {
        cipherOptions();
        }

    /**
     * Setup of choosing cipher type
     */
    private void cipherOptions() {
        JFrame frame = new JFrame();
        JPanel pane = new JPanel();
        // drop down menu
        pane.add(new JLabel("Chose cipher:"));
        String[] simoptions1 = {"Vigenere decipher", "Vigenere encipher"};
        JComboBox<String> combobox1 = new JComboBox<>(simoptions1);
        pane.add(combobox1);

        // drawing the choosing cipher type menu
        int options = JOptionPane.showConfirmDialog(frame, pane, "Select cipher", JOptionPane.OK_CANCEL_OPTION);

        // getting selection
        String cipherselected = Objects.requireNonNull(combobox1.getSelectedItem()).toString();

        // Check if cancel or the exit bottom what clicked, if so close the program
        if (options != 0) {
            System.exit(0);
        } else {
                runCipher(cipherselected);
        }
    }

    /**
     * Run cipher programs
     * @param cipherselected chosen cipher
     */
    private void runCipher(String cipherselected){
            JFrame frame = new JFrame(cipherselected);
            frame.setSize(800, 600);
            Container con = frame.getContentPane();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Run Vigenere cipher programs
            if (cipherselected.equals("Vigenere decipher") || cipherselected.equals("Vigenere encipher") ){
                con.setLayout(new GridLayout(1, 1));

                JPanel mainpane = new JPanel();
                mainpane.setLayout(new GridLayout(3, 1, 0,10));
                mainpane.setBorder(new EmptyBorder(10, 10, 10, 10));
                con.add(mainpane);

                // Cipher key text field
                JPanel pane0 = new JPanel();
                pane0.setLayout(new GridLayout(5, 2));
                pane0.add(new JLabel("key: "));
                JTextField key = new JTextField("");
                key.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(key);
                mainpane.add(pane0);

                // Cipher plain text
                JTextArea plaintext = new JTextArea("");
                plaintext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                mainpane.add(plaintext);

                // Deciphered text
                JTextArea deciphered = new JTextArea("");
                deciphered.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                deciphered.setEditable(false);
                mainpane.add(deciphered);

                // Draw frame
                frame.setVisible(true);

                String dctextold;
                String dctextnew;

                VigenereCipher vc = new VigenereCipher();

                while (true){
                    dctextold = deciphered.getText();
                    if (cipherselected.equals("Vigenere decipher")) {
                        dctextnew = vc.vigenereDecipher(plaintext.getText(), key.getText());
                    }else {
                        dctextnew = vc.vigenereEncipher(plaintext.getText(), key.getText());
                    }

                    deciphered.replaceRange(dctextnew,0, dctextold.length());

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
    }
}
