import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;
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
        String[] simoptions1 = {"Monoalphabetic cipher", "Vigenere decipher", "Vigenere encipher", "Enigma", "Binary with key"};
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
    private void runCipher(String cipherselected) {
        JFrame frame = new JFrame(cipherselected);
        frame.setSize(800, 600);
        Container con = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        switch (cipherselected) {
            case "Vigenere decipher":
            case "Vigenere encipher": {                                                                                 // Run Vigenere cipher
                con.setLayout(new GridLayout(1, 1));

                JPanel mainpane = new JPanel();
                mainpane.setLayout(new GridLayout(3, 1, 0, 10));
                mainpane.setBorder(new EmptyBorder(10, 10, 10, 10));
                con.add(mainpane);

                // Cipher key text field
                JPanel pane0 = new JPanel();
                pane0.setLayout(new GridLayout(5, 2));
                pane0.add(new JLabel("key: "));
                JTextField key = new JTextField("");
                key.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(key);
                JButton obot = new JButton("Output text");

                JTextArea ciphertext = new JTextArea("");
                obot.addActionListener(e -> outputText(ciphertext));
                pane0.add(obot);
                mainpane.add(pane0);

                // Cipher plain text
                JTextArea plaintext = new JTextArea("");
                plaintext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                mainpane.add(plaintext);

                // Deciphered/enciphered text
                ciphertext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                ciphertext.setEditable(false);
                mainpane.add(ciphertext);

                // Draw frame
                frame.setVisible(true);

                VigenereCipher vc = new VigenereCipher();

                // Loops the program until closed
                while (true) {
                    if (cipherselected.equals("Vigenere decipher")) {
                        ciphertext.setText(vc.vigenereDecipher(plaintext.getText(), key.getText()));
                    } else {
                        ciphertext.setText(vc.vigenereEncipher(plaintext.getText(), key.getText()));
                    }

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
            case "Monoalphabetic cipher": {                                                                             // Run monoalphabetic cipher
                con.setLayout(new GridLayout(1, 1));

                JPanel mainpane = new JPanel();
                mainpane.setLayout(new GridLayout(3, 1, 0, 10));
                mainpane.setBorder(new EmptyBorder(10, 10, 10, 10));
                con.add(mainpane);

                // Cipher key text field
                JPanel pane0 = new JPanel();
                pane0.setLayout(new GridLayout(0, 8));

                // Adding all the letter encodings
                ArrayList<JTextField> jtexts = new ArrayList<>();
                for (int i = 97; i < 123; i++) {
                    pane0.add(new JLabel("                          " + (char) i + ": "));
                    JTextField newt = new JTextField("");
                    newt.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                    jtexts.add(newt);
                    pane0.add(newt);
                }

                JTextArea ciphertext = new JTextArea("");
                JButton obot = new JButton("Output text");
                obot.addActionListener(event -> outputText(ciphertext));
                pane0.add(obot);
                mainpane.add(pane0);

                // Cipher plain text
                JTextArea plaintext = new JTextArea("");
                plaintext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                mainpane.add(plaintext);

                // Deciphered/enciphered text
                ciphertext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                ciphertext.setEditable(false);
                mainpane.add(ciphertext);

                // Draw frame
                frame.setVisible(true);

                MonoAlphabeticCipher mac = new MonoAlphabeticCipher();

                // Loops the program until closed
                while (true) {
                    for (int i = 97; i < 123; i++) {
                        if (jtexts.get(i - 97).getText().length() == 0) {
                            mac.addEncoding((char) i, '-');
                        } else {
                            mac.addEncoding((char) i, jtexts.get(i - 97).getText().charAt(0));
                        }
                    }

                    ciphertext.setText(mac.transform(plaintext.getText()));

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
            case "Enigma": {                                                                                            // Run Enigma cipher
                frame.setSize(800, 800);
                con.setLayout(new GridLayout(1, 1));

                JPanel mainpane = new JPanel();
                mainpane.setLayout(new GridLayout(3, 1, 0, 10));
                mainpane.setBorder(new EmptyBorder(10, 10, 10, 10));
                con.add(mainpane);

                // Cipher key text field
                JPanel pane0 = new JPanel();
                pane0.setLayout(new GridLayout(0, 2));

                pane0.add(new JLabel("Scrambler 1 top: "));
                JTextField scr11 = new JTextField("abcdefghijklmnopqrstuvwxyz");
                scr11.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr11);

                pane0.add(new JLabel("Scrambler 1 bottom: "));
                JTextField scr12 = new JTextField("");
                scr12.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr12);

                pane0.add(new JLabel("Scrambler 2 top: "));
                JTextField scr21 = new JTextField("abcdefghijklmnopqrstuvwxyz");
                scr21.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr21);

                pane0.add(new JLabel("Scrambler 2 bottom: "));
                JTextField scr22 = new JTextField("");
                scr22.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr22);

                pane0.add(new JLabel("Scrambler 3 top: "));
                JTextField scr31 = new JTextField("abcdefghijklmnopqrstuvwxyz");
                scr31.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr31);

                pane0.add(new JLabel("Scrambler 3 bottom: "));
                JTextField scr32 = new JTextField("");
                scr32.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(scr32);

                pane0.add(new JLabel("Reflector 1 top: "));
                JTextField ref1 = new JTextField("abcdefghijklmnopqrstuvwxyz");
                ref1.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(ref1);

                pane0.add(new JLabel("Reflector 2 top: "));
                JTextField ref2 = new JTextField("");
                ref2.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(ref2);

                pane0.add(new JLabel("Rewiring/plugboard eg. (ab de cd uv ...): "));
                JTextField rew = new JTextField("");
                rew.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(rew);

                JTextArea ciphertext = new JTextArea("");
                JButton obot = new JButton("Output text");
                obot.addActionListener(e -> outputText(ciphertext));
                pane0.add(obot);
                mainpane.add(pane0);

                // Cipher plain text
                JTextArea plaintext = new JTextArea("");
                plaintext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                mainpane.add(plaintext);

                // Deciphered/enciphered text
                ciphertext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                ciphertext.setEditable(false);
                mainpane.add(ciphertext);

                // Draw frame
                frame.setVisible(true);

                Enigma eni = new Enigma();

                // Loops the program until closed
                while (true) {
                    eni.setScrambler(scr11.getText(), scr12.getText(), scr21.getText(), scr22.getText(), scr31.getText(), scr32.getText(), ref1.getText(), ref2.getText());

                    eni.resetRewiring();
                    String rewt = rew.getText().toLowerCase();
                    for (int i = 1; i < rewt.length(); i = i + 3) {
                        eni.rewiring(rewt.charAt(i - 1), rewt.charAt(i));
                    }

                    ciphertext.setText(eni.transform(plaintext.getText()));

                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                        System.exit(-1);
                    }
                }
            }
            case "Binary with key": {                                                                                   // Run binary cipher
                con.setLayout(new GridLayout(1, 1));

                JPanel mainpane = new JPanel();
                mainpane.setLayout(new GridLayout(3, 1, 0, 10));
                mainpane.setBorder(new EmptyBorder(10, 10, 10, 10));
                con.add(mainpane);

                // Cipher key text field
                JPanel pane0 = new JPanel();
                pane0.setLayout(new GridLayout(0, 2));
                pane0.add(new JLabel("key: "));
                JTextField key = new JTextField("");
                key.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(key);

                pane0.add(new JLabel("binary: "));
                JTextField binary = new JTextField("");
                binary.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                pane0.add(binary);

                // up here since it is need for the buttons
                JTextArea plaintext = new JTextArea("");
                BinaryCipher bc = new BinaryCipher();
                JTextArea ciphertext = new JTextArea("");

                JButton addbin = new JButton("Add binary");
                addbin.addActionListener(e -> plaintext.setText(plaintext.getText() + ((char) (Integer.parseInt(binary.getText().replaceAll("\\s+", ""), 2)))));
                JButton obot = new JButton("Output text");
                obot.addActionListener(e -> outputText(ciphertext));
                JButton xorgate = new JButton("XOR gate");
                xorgate.addActionListener(e -> ciphertext.setText(bc.xorTransform(key.getText(), plaintext.getText())));
                JButton ver = new JButton("Vertical shift");
                ver.addActionListener(e -> ciphertext.setText(bc.vShiftTransform(key.getText(), plaintext.getText())));
                pane0.add(obot);
                pane0.add(addbin);
                pane0.add(xorgate);
                pane0.add(ver);
                mainpane.add(pane0);

                // Cipher plain text
                plaintext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                mainpane.add(plaintext);

                // Deciphered/enciphered text
                ciphertext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
                ciphertext.setEditable(false);
                mainpane.add(ciphertext);

                // Draw frame
                frame.setVisible(true);

                // Loops the program until closed
                while (true) {
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

    /**
     * Output a frame containing the the deciphered/enciphered text for easy copying and editing
     */
    private void outputText(JTextArea ciphertext){
        JFrame outframe = new JFrame("Output text");
        outframe.setSize(800, 200);

        JPanel outpane = new JPanel();
        outpane.setLayout(new GridLayout(1, 1));

        JTextArea outtext = new JTextArea("");
        outtext.setFont(new Font("Lucida Console", Font.PLAIN, 14));
        outtext.setText(ciphertext.getText());

        outpane.add(outtext);
        outframe.add(outpane);
        outframe.setVisible(true);
    }
}
