import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class FirstFrame extends JFrame {
  static JTextField login1 = new JTextField(4);
  static JPasswordField password1 = new JPasswordField(4);
  JLabel login = new JLabel("Username");
  JLabel password = new JLabel("Password");
  Font font1 = new Font("Times New Roman", Font.BOLD, 16);

  // FirstFrame f1 = new FirstFrame();

  public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> FirstFrame());
    // SwingUtilities.invokeLater(() -> {
    // FirstFrame f2 = new FirstFrame();
    // });
    new FirstFrame();
  }

  FirstFrame() {
    setLocation(512, 200);
    setTitle("Login");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);


    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(15, 10, 15, 10);

    JLabel heading = new JLabel("Login");
    Font font = new Font("Times New Roman", Font.BOLD, 24);
    JButton submit = new JButton("Submit");
    JButton clear = new JButton("Clear");

    add(heading, BorderLayout.NORTH);
    heading.setHorizontalAlignment(JLabel.CENTER);
    heading.setFont(font);

    gbc.gridx = 0;
    gbc.gridy = 1;
    panel.add(login, gbc);

    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.gridwidth = 10;
    panel.add(login1, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    panel.add(password, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2;
    panel.add(password1, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(submit, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    panel.add(clear, gbc);

    getContentPane().add(panel);
    pack(); // Automatically sizes the frame based on its contents

    submit.setBackground(Color.RED);
    submit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == submit) {
          // submitButton();
          try {
            App a1 = new App(login1.getText(), new String(password1.getPassword()));
          } catch (Exception eq) {
            System.out.println("Error! This User does not Exist. " + eq);
          }
        }

      }
    });

    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == clear) {
          login1.setText("");
          password1.setText("");
        }
      }
    });
  }

  public void closeFrame() {
    setVisible(false);
    dispose();
  }

}
