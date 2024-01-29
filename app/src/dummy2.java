import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class dummy2 extends JFrame implements ActionListener {
  FirstFrame f1;

  JLabel heading = new JLabel("Database Operations");
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

  JPanel tab1 = new JPanel(new GridBagLayout());
  JPanel tab2 = new JPanel(new GridLayout(3, 2));
  JPanel tab3 = new JPanel(new GridLayout(2, 2));
  JPanel tab4 = new JPanel(new GridLayout(2, 0));

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

  JTable dataTable;
  DefaultTableModel tableModel;
  JButton view = new JButton("View Data");

  int i = 0;
  int j = 1;

  private String user;
  private String pass;

  dummy2(String user, String pass) {
    this.user = user;
    this.pass = pass;

    f1 = new FirstFrame();
    f1.closeFrame();
    add(heading, BorderLayout.NORTH);
    heading.setHorizontalAlignment(JLabel.CENTER);
    heading.setFont(font);

    setLocation(420, 100);

    // Create instances of GridBagConstraints for setting component properties
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5); // Set insets for padding

    tab1.add(id, gbc);
    gbc.gridx = 1;
    tab1.add(t4, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    tab1.add(name, gbc);
    gbc.gridx = 1;
    tab1.add(t1, gbc);
    gbc.gridx = 0;
    gbc.gridy = 2;
    tab1.add(age, gbc);
    gbc.gridx = 1;
    tab1.add(t2, gbc);
    gbc.gridx = 0;
    gbc.gridy = 3;
    tab1.add(salary, gbc);
    gbc.gridx = 1;
    tab1.add(t3, gbc);
    gbc.gridx = 0;
    gbc.gridy = 4;
    tab1.add(b1, gbc);
    gbc.gridx = 1;
    tab1.add(b2, gbc);

    tab2.add(emp_id);
    emp_id.setFont(font1);
    tab2.add(emp_id1);
    tab2.add(emp_name);
    emp_name.setFont(font1);
    tab2.add(emp_name1);
    tab2.add(update);
    tab2.add(clear);

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.EAST;
    tab3.add(demp_id, gbc);
    demp_id.setFont(font2);
    demp_id1.setPreferredSize(new Dimension(150, 30));
    tab3.add(demp_id1);

    tableModel = new DefaultTableModel();
    tableModel.addColumn("Employee ID");
    tableModel.addColumn("Name");
    tableModel.addColumn("Salary");
    tableModel.addColumn("Age");

    dataTable = new JTable(tableModel);
    tab4.add(dataTable);
    tab4.add(view);
    view.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        showww();
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Company Information");
    setVisible(true);
    setSize(500, 600);

    tb.add("INSERT", tab1);
    tb.add("UPDATE", tab2);
    tb.add("DELETE", tab3);
    tb.add("VIEW", tab4);
    add(tb);
  }

  public static void main(String[] args) {
    new dummy2(null, null);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == b1) {
      insert();
      i++;
    } else if (e.getSource() == b2) {
      // showww();
    }
  }

  public void insert() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      int id = Integer.parseInt(t4.getText());
      int age = Integer.parseInt(t2.getText());
      int salary = Integer.parseInt(t3.getText());

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";
      Connection con = DriverManager.getConnection(URL, user, pass);
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
        }
      }
    } catch (SQLException e) {
    } catch (ClassNotFoundException e) {
      System.out.println("Is it here " + e.getMessage());
    }
  }

  // ... other methods ...

  public void showww() {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      String URL = "jdbc:oracle:thin:@localhost:1521:XE";
      String user = "system";
      String pass = "system@123";

      Connection con = DriverManager.getConnection(URL, user, pass);
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

        tableModel.addRow(row);
      }

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }
}
