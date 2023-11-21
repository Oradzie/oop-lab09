package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "My second Java graphical interface";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel upperPanel = new JPanel();
        canvas.add(upperPanel, BorderLayout.NORTH);
        final JTextField textField = new JTextField(
                System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");
        upperPanel.add(textField, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse");
        upperPanel.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final var choice = chooser.showSaveDialog(browse);
                switch (choice) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setFile(new File(chooser.getSelectedFile().getAbsolutePath()));
                        textField.setText(chooser.getSelectedFile().getAbsolutePath());
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        break;
                    default:
                        JOptionPane.showMessageDialog(browse, "Error while selecting file", "Error occurred",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
        });
        final JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        canvas.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.writeOnFile(textArea.getText());
                } catch (IOException error) {
                    error.printStackTrace();
                }
            }

        });
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUIWithFileChooser().display();
    }
}
