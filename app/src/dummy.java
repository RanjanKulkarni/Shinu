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
    // setLocation(512, 200);
    setLocationRelativeTo(null);
    setTitle("Login");
    // setSize(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    JPanel panel = new JPanel(new GridLayout(3, 2));
    panel.setBackground(Color.GRAY);
    
    JLabel heading = new JLabel("Login");
    Font font = new Font("Times New Roman", Font.BOLD, 24);
    JButton submit = new JButton("Submit");
    JButton clear = new JButton("Clear");
    
    heading.setPreferredSize(new Dimension(100, 30));
    login.setPreferredSize(new Dimension(100, 30));
    login1.setPreferredSize(new Dimension(100, 30));
    password.setPreferredSize(new Dimension(150, 30));
    password1.setPreferredSize(new Dimension(150, 30));
    submit.setPreferredSize(new Dimension(100, 30));
    clear.setPreferredSize(new Dimension(100, 30));
    pack();
    
    add(heading, BorderLayout.NORTH);
    heading.setHorizontalAlignment(JLabel.CENTER);
    heading.setFont(font);
    panel.add(login);
    login.setFont(font1);
    login.setForeground(Color.BLUE);
    panel.add(login1);
    panel.add(password);
    password.setFont(font1);
    panel.add(password1);
    panel.add(submit);
    panel.add(clear);

    getContentPane().add(panel);

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
