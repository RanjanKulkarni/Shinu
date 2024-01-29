import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dialog Box Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton messageDialogButton = new JButton("Show Message Dialog");
        JButton fileChooserButton = new JButton("Show File Chooser");
        JButton colorChooserButton = new JButton("Show Color Chooser");

        messageDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMessageDialog(); 
            }
        });

        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFileChooser(frame);
            }
        });

        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColorChooser(frame);
            }
        });

        panel.add(messageDialogButton);
        panel.add(fileChooserButton);
        panel.add(colorChooserButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private static void showMessageDialog() {
        JOptionPane.showMessageDialog(null, "This is a Message Dialog", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showFileChooser(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFile = fileChooser.getSelectedFile().getPath();
            JOptionPane.showMessageDialog(null, "Selected File: " + selectedFile, "File Chooser", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void showColorChooser(JFrame frame) {
        Color selectedColor = JColorChooser.showDialog(frame, "Choose a Color", Color.RED);

        if (selectedColor != null) {
            JOptionPane.showMessageDialog(null, "Selected Color: " + selectedColor, "Color Chooser", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
