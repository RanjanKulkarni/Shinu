import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 

public class Main1 extends JFrame {
    static JTextField login1 = new JTextField(10);
    static JPasswordField password1 = new JPasswordField(10);
    JLabel login = new JLabel("Username");
    JLabel password = new JLabel("Password");
    Font font1 = new Font("Times New Roman", Font.BOLD, 16);

    public static void main(String[] args) {
        new Main1();
    }
    
    Main1() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLocation(800, 400);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel heading = new JLabel("Login");
        Font font = new Font("Times New Roman", Font.BOLD, 24);
        JButton submit = new JButton("Submit");
        JButton clear = new JButton("Clear");

        add(heading, BorderLayout.NORTH);
        heading.setHorizontalAlignment(JLabel.CENTER);
        heading.setFont(font);
        heading.setForeground(new Color(5, 181, 224));
        // login.setForeground(new Color(0, 204, 255));
        // password.setForeground(new Color(0, 204, 255));



        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(login, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(login1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(password, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(password1, gbc);

        // gbc.gridx = 0;
        // gbc.gridy = 3;
        panel.add(submit);

        // gbc.gridx = 1;
        // gbc.gridy = 3;
        panel.add(clear);

        customizeButton(submit);
        customizeButton(clear);
        add(createButtonPanel(submit, clear), BorderLayout.SOUTH);


        getContentPane().add(panel);
        pack(); 

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submit) {
                    // submitButton();
                    try {
                        app3 a1 = new app3(login1.getText(), new String(password1.getPassword()));
                    } catch (Exception eq) {
                        System.out.println("Error! This User does not Exist. " + eq);
                        closeFrame();
                    }
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clear) {
                    login1.setText("");
                    password1.setText("");
                }
            }
        });

        setVisible(true);
    }

    public void closeFrame() {
        setVisible(false);
        dispose();
    }

    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(100, 30));
        button.setBackground(new Color(0, 153, 204)); // Custom button color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Times New Roman", Font.BOLD, 14));
    }
    private JPanel createButtonPanel(JButton submitButton, JButton clearButton) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        return buttonPanel;
    }
}
