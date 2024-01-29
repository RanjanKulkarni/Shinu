import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttractiveFrame extends JFrame {

    private DefaultTableModel tableModel;
    private JTable dataTable;

    public AttractiveFrame() {
        // Set the Nimbus look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Attractive Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // setIconImage(new ImageIcon("path/to/your/icon.png").getImage());

        // Create components
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Salary");
        tableModel.addColumn("Age");

        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);

        // Customize buttons
        customizeButton(submitButton);
        customizeButton(clearButton);

        // Add components to the frame
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createFormPanel(submitButton, clearButton), BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Set frame properties
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void customizeButton(JButton button) {
        button.setPreferredSize(new Dimension(100, 30));
        button.setBackground(new Color(0, 153, 204)); // Custom button color
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));

        // You can add more customizations as needed
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        JLabel headingLabel = new JLabel("Database Operations");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headingLabel);
        return headerPanel;
    }

    private JPanel createFormPanel(JButton submitButton, JButton clearButton) {
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Add form components (text fields, labels, etc.) to formPanel

        formPanel.add(submitButton);
        formPanel.add(clearButton);

        return formPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AttractiveFrame());
    }
}
