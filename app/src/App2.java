import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class App2 extends JFrame implements ActionListener {
  FirstFrame f1;

  JLabel heading = new JLabel("DataBase Operations");
  Font font = new Font("Times New Roman", Font.BOLD, 24);

  JTabbedPane tb = new JTabbedPane();
  JButton b1 = new JButton("Submit");
  JButton b2 = new JButton("Clear");
  JTextField t1 = new JTextField(10);
  JTextField t2 = new JTextField(10);
  JTextField t3 = new JTextField(10);
  JTextField t4 = new JTextField(10);
  Font font1 = new Font("Times New Roman", Font.BOLD, 16);
  Font font2 = new Font("Times New Roman", Font.BOLD, 14);
  JLabel id = new JLabel("Employee ID");
  JLabel name = new JLabel("Name");
  JLabel age = new JLabel("Age");
  JLabel salary = new JLabel("Salary");
  JLabel l1 = new JLabel();

  JPanel tab1 = new JPanel(new GridLayout(5, 2));
  JPanel tab2 = new JPanel(new GridLayout(2, 2));
  JPanel tab3 = new JPanel(new GridLayout(1, 2));
  JPanel tab4 = new JPanel(new GridLayout(1, 0));

  JLabel emp_id = new JLabel("Enter Employee ID ");
  JLabel emp_name = new JLabel("Enter Updated name ");
  JTextField emp_id1 = new JTextField(10);
  JTextField emp_name1 = new JTextField(10);
  JButton update = new JButton("Update");
  JButton clear = new JButton("Clear");

  JLabel demp_id = new JLabel("Enter Employee ID to be Deleted: ");
  JTextField demp_id1 = new JTextField(10);
  JButton delete = new JButton("Delete");
  JButton dclear = new JButton("Clear");

  String title[] = { "Id", "Name", "Salary", "Age" };
  JTable dataTable;
  DefaultTableModel tableModel;
  JButton view = new JButton("View Data");

  int i = 0;
  int j = 1;

  private String user;
  private String pass;

  App2(String user, String pass) {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.user = user;
    this.pass = pass;

    f1 = new FirstFrame();
    f1.closeFrame();
    add(heading, BorderLayout.NORTH);
    heading.setHorizontalAlignment(JLabel.CENTER);
    heading.setFont(font);

    setLocation(420, 100);
    // setLayout(new FlowLayout());
    tab1.add(id);
    id.setFont(font1);
    tab1.add(t4);
    tab1.add(name);
    name.setFont(font1);
    tab1.add(t1);
    tab1.add(age);
    age.setFont(font1);
    tab1.add(t2);
    tab1.add(salary);
    salary.setFont(font1);
    tab1.add(t3);
    tab1.add(b1);
    tab1.add(b2);
    b1.setMnemonic('S');
    b2.setMnemonic('C');
    b1.addActionListener(this);
    b2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clear1();
      }
    });
    // tab1.add(l1);
    // add(tab1, BorderLayout.CENTER);
    getContentPane().add(tab1);

    tab2.add(emp_id);
    emp_id.setFont(font1);
    tab2.add(emp_id1);
    tab2.add(emp_name);
    emp_name.setFont(font1);
    tab2.add(emp_name1);
    tab2.add(update);
    tab2.add(clear);
    update.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        update();
      }
    });
    clear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clear2();
      }
    });

    tab3.add(demp_id);
    demp_id.setFont(font2);
    tab3.add(demp_id1);
    tab3.add(delete);
    tab3.add(dclear);
    delete.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        delete();
      }
    });
    dclear.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        clear3();
      }
    });

    tableModel = new DefaultTableModel(
        new Object[] { "Employee ID", "Name", "Salary", "Age" }, 0);
    // tableModel = new DefaultTableModel();
    // tableModel.addColumn("Employee ID");
    // tableModel.addColumn("Name");
    // tableModel.addColumn("Salary");
    // tableModel.addColumn("Age");

    dataTable = new JTable(tableModel);
    JScrollPane scrollPane = new JScrollPane(dataTable);
    tab4.add(scrollPane);
    tab4.add(view);

    view.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showww();
      }
    });

    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(500, 600);

    tb.add("INSERT", tab1);
    tb.add("UPDATE", tab2);
    tb.add("DELETE", tab3);
    tb.add("VIEW", tab4);
    add(tb);

    customizeButton(b1);
    customizeButton(b2);
    add(createButtonPanel(b1, b2), BorderLayout.SOUTH);
    customizeButton(update);
    customizeButton(clear);
    add(createButtonPanel(update, clear), BorderLayout.SOUTH);
    customizeButton(delete);
    customizeButton(dclear);
    add(createButtonPanel(delete, dclear), BorderLayout.SOUTH);
    customizeButton(view);
    add(createButtonPanel(view, null), BorderLayout.SOUTH);


  }

  public static void main(String[] args) {
    new App2("system", "system@123");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

    if (e.getSource() == b1) {
      insert();
      i++;
    }
  }

  public void insert() {
    try {
      // String name = new String(t1.getText());
      Class.forName("oracle.jdbc.driver.OracleDriver");
      int id = Integer.parseInt(t4.getText());
      int age = Integer.parseInt(t2.getText());
      int salary = Integer.parseInt(t3.getText());

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";

      Connection con = DriverManager.getConnection(URL, user, pass);
      System.out.println("Connection established");
      Statement st = con.createStatement();

      try {
        st.execute("CREATE TABLE Employee (id INT PRIMARY KEY, name VARCHAR(30) , salary INT, age INT ) ");
      } catch (Exception eq) {
        while (i >= 0) {
          System.out.println("Go on king " + eq.getMessage());
          st.executeUpdate(
              "insert into Employee values (" + id + "," + " '" + t1.getText() + "'," + salary + "," + age + ")");
          System.out.println("Data inserted");
          break;
          // st.executeUpdate("insert into emp values ('xxx',23000,20)");
        }
      }
    }

    catch (SQLException e) {
      System.out.println("SQL ");
    } catch (ClassNotFoundException e) {
      System.out.println("Is it here in" + e.getMessage());
    }

  }

  public void clear1() {
    t1.setText("");
    t2.setText("");
    t3.setText("");
    t4.setText("");
  }

  public void update() {
    try {
      // String name = new String(t1.getText());
      Class.forName("oracle.jdbc.driver.OracleDriver");

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";
      String user = "system";
      String pass = "system@123";

      Connection con = DriverManager.getConnection(URL, user, pass);
      System.out.println("Connection established");
      Statement st = con.createStatement();

      int emp_id = Integer.parseInt(emp_id1.getText());

      try {
        st.executeUpdate("UPDATE Employee SET name= '" + emp_name1.getText() + "' WHERE id= " + emp_id);
        System.out.println("Data Updated");
      } catch (Exception eq) {
        System.out.println("Update Exception: " + eq);
      }

    } catch (SQLException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Is it here in update " + e.getMessage());
    }
  }

  public void clear2() {
    emp_id1.setText("");
    emp_name1.setText("");
  }

  public void delete() {
    try {
      // String name = new String(t1.getText());
      Class.forName("oracle.jdbc.driver.OracleDriver");

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";

      Connection con = DriverManager.getConnection(URL, user, pass);
      System.out.println("Connection established");
      Statement st = con.createStatement();

      int emp_id = Integer.parseInt(demp_id1.getText());

      try {
        st.executeUpdate("DELETE FROM Employee WHERE id= " + emp_id);
        System.out.println("Employee Deleted");
      } catch (Exception eq) {
        System.out.println("Update Exception: " + eq);
      }

    } catch (SQLException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Is it here delete" + e.getMessage());
    }
  }

  public void clear3() {
    demp_id1.setText("");
  }

  public void showww() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";
      // String user = "system";
      // String pass = "system@123";

      Connection con = DriverManager.getConnection(URL, user, pass);
      System.out.println("Connection established");
      Statement st = con.createStatement();

      ResultSet rs = st.executeQuery("SELECT * FROM Employee");

      tableModel.setRowCount(0);

      while (rs.next()) {
        Object[] row = {
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("salary"),
            rs.getInt("age")
        };

        // System.out.println("in your Facee");
        tableModel.addRow(row);
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  private void customizeButton(JButton button) {
    button.setPreferredSize(new Dimension(100, 30));
    button.setBackground(new Color(0, 153, 204)); // Custom button color
    button.setForeground(Color.WHITE);
    button.setFont(new Font("Arial", Font.BOLD, 14));
  }

  private JPanel createButtonPanel(JButton submitButton, JButton clearButton) {
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(submitButton);
    buttonPanel.add(clearButton);
    return buttonPanel;
  }
}