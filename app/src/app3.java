import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class app3 extends JFrame implements ActionListener {

//    FirstFrame f1;

    JLabel heading = new JLabel("Database Operations");
    Font font = new Font("Times New Roman", Font.BOLD, 24);

    JTabbedPane tb = new JTabbedPane();

    // Panel for INSERT tab
    JPanel tab1 = new JPanel();
    JButton b1 = new JButton("Submit");
    JButton b2 = new JButton("Clear");
    JTextField t1 = new JTextField(10);
    JTextField t2 = new JTextField(10);
    JTextField t3 = new JTextField(10);
    JTextField t4 = new JTextField(10);

    // Panel for UPDATE tab
    JPanel tab2 = new JPanel();
    JLabel emp_id = new JLabel("Enter Employee ID ");
    JLabel emp_name = new JLabel("Enter Updated name ");
    JTextField emp_id1 = new JTextField(10);
    JTextField emp_name1 = new JTextField(10);
    JButton update = new JButton("Update");
    JButton clear = new JButton("Clear");

    // Panel for DELETE tab
    JPanel tab3 = new JPanel();
    JLabel demp_id = new JLabel("Enter Employee ID to be Deleted: ");
    JTextField demp_id1 = new JTextField(10);
    JButton delete = new JButton("Delete");
    JButton dclear = new JButton("Clear");

    // Panel for VIEW tab
    JPanel tab4 = new JPanel();
    String title[] = { "Id", "Name", "Salary", "Age" };
    JTable dataTable;
    DefaultTableModel tableModel;
    JButton view = new JButton("View Data");

    private String user;
    private String pass;

    app3(String user, String pass) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.user = user;
        this.pass = pass;

//        f1 = new FirstFrame();
//        f1.closeFrame();

        heading.setFont(font);

        // Setting layout for INSERT tab
        tab1.setLayout(new GridLayout(5, 2, 10, 10));
        tab1.add(new JLabel("Employee ID"));
        tab1.add(t4);
        tab1.add(new JLabel("Name"));
        tab1.add(t1);
        tab1.add(new JLabel("Age"));
        tab1.add(t2);
        tab1.add(new JLabel("Salary"));
        tab1.add(t3);
        tab1.add(b1);
        tab1.add(b2);

        // Setting layout for UPDATE tab
        tab2.setLayout(new GridLayout(4, 2, 10, 10));
        tab2.add(new JLabel("Enter Employee ID"));
        tab2.add(emp_id1);
        tab2.add(new JLabel("Enter Updated name"));
        tab2.add(emp_name1);
        tab2.add(update);
        tab2.add(clear);

        // Setting layout for DELETE tab
        tab3.setLayout(new GridLayout(3, 2, 10, 10));
        tab3.add(new JLabel("Enter Employee ID to be Deleted: "));
        tab3.add(demp_id1);
        tab3.add(delete);
        tab3.add(dclear);

        // Setting layout for VIEW tab
        tab4.setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[] { "Employee ID", "Name", "Salary", "Age" }, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        tab4.add(scrollPane, BorderLayout.CENTER);
        tab4.add(view, BorderLayout.SOUTH);

        // Adding action listeners
        b1.addActionListener(this);
        b2.addActionListener(e -> clear1());
        update.addActionListener(e -> update());
        clear.addActionListener(e -> clear2());
        delete.addActionListener(e -> delete());
        dclear.addActionListener(e -> clear3());
        view.addActionListener(e -> showww());

        // Adding tabs to JTabbedPane
        tb.add("INSERT", tab1);
        tb.add("UPDATE", tab2);
        tb.add("DELETE", tab3);
        tb.add("VIEW", tab4);

        // Setting layout for the main frame
        setLayout(new BorderLayout());
        add(heading, BorderLayout.NORTH);
        add(tb, BorderLayout.CENTER);

        setLocation(650,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 400);
    }

//    public static void main(String[] args) {
//        new app3("system", "system@123");
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            insert();
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
            System.out.println("Connection established");
            Statement st = con.createStatement();

            try {
                st.execute("CREATE TABLE Employee (id INT PRIMARY KEY, name VARCHAR(30), salary INT, age INT)");
            } catch (Exception eq) {
                st.executeUpdate("insert into Employee values (" + id + ",'" + t1.getText() + "'," + salary + "," + age + ")");
                System.out.println("Data inserted");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear2() {
        emp_id1.setText("");
        emp_name1.setText("");
    }

    public void delete() {
        try {
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clear3() {
        demp_id1.setText("");
    }

    public void showww() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String URL = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection con = DriverManager.getConnection(URL, user, pass);
            System.out.println("Connection established");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Employee");

            tableModel.setRowCount(0);

            while (rs.next()) {
                Object[] row = { rs.getInt("id"), rs.getString("name"), rs.getInt("salary"), rs.getInt("age") };
                tableModel.addRow(row);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
