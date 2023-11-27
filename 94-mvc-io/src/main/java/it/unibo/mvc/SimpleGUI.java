package it.unibo.mvc;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private static final String TITLE = "Simple GUI";
    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new SimpleController();

    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField();
        canvas.add(textField, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea();
        canvas.add(textArea, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        canvas.add(bottomPanel, BorderLayout.SOUTH);

        JButton printButton = new JButton("Print");
        bottomPanel.add(printButton, BorderLayout.WEST);

        JButton historyButton = new JButton("Show history");
        bottomPanel.add(historyButton, BorderLayout.EAST);

        printButton.addActionListener(e -> {
            controller.setNextPrintString(textField.getText());
            controller.printCurrentString();
        });

        historyButton.addActionListener(e -> {
            List<String> history = controller.getPrintHistory();
            history.forEach(element -> {
                textArea.append(element + "\n");
            });
        });
    }

    public void Display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SimpleGUI().Display();
    }

}
