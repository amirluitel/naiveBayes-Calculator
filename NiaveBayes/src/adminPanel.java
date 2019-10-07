import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class adminPanel extends JFrame
{
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    
    public adminPanel() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.setTitle("Naive Bayes Predictor");
        final JLabel lblUserName = new JLabel("user name");
        lblUserName.setFont(new Font("Tahoma", 0, 18));
        lblUserName.setBounds(74, 74, 95, 24);
        this.contentPane.add(lblUserName);
        final JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", 0, 18));
        lblPassword.setBounds(74, 122, 84, 23);
        this.contentPane.add(lblPassword);
        (this.textField = new JTextField()).setBounds(191, 74, 156, 25);
        this.contentPane.add(this.textField);
        this.textField.setColumns(10);
        (this.textField_1 = new JPasswordField()).setBounds(191, 120, 156, 25);
        this.contentPane.add(this.textField_1);
        this.textField_1.setColumns(10);
        final JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String url = "jdbc:mysql://localhost:3306/navybase";
                final String username = "root";
                final String password = "root";
                final String naam = adminPanel.this.textField.getText();
                final String pa = adminPanel.this.textField_1.getText();
                boolean flag = false;
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            final String query = "SELECT * FROM nlogin ";
                            final Statement st = connection.createStatement();
                            final ResultSet rs = st.executeQuery(query);
                            while (true) {
                                if (!rs.next()) {
                                    if (!flag) {
                                        break;
                                    }
                                    continue;
                                }
                                else {
                                    final String name = rs.getString("userName");
                                    final String pass = rs.getString("password");
                                    if (name.equals(naam) && pass.equals(pa)) {
                                        flag = true;
                                        adminPanel.this.setVisible(false);
                                        EventQueue.invokeLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    final choice frame = new choice();
                                                    frame.setVisible(true);
                                                }
                                                catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                        connection.close();
                                    }
                                    else {
                                        JOptionPane.showMessageDialog(null, "username and password you've entered doesn't match");
                                    }
                                }
                            }
                        }
                        finally {
                            if (connection != null) {
                                connection.close();
                            }
                        }
                    }
                    finally {
                        if (t == null) {
                            final Throwable exception = null;
                            t = exception;
                        }
                        else {
                            final Throwable exception = null;
                            if (t != exception) {
                                t.addSuppressed(exception);
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    System.out.println("error" + ex);
                }
            }
        });
        btnLogin.setBounds(191, 164, 156, 23);
        this.contentPane.add(btnLogin);
        final JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final ChangePassword frame = new ChangePassword();
                frame.setVisible(true);
                adminPanel.this.setVisible(false);
            }
        });
        btnChangePassword.setBounds(191, 198, 156, 23);
        this.contentPane.add(btnChangePassword);
        final JLabel label = new JLabel("");
        label.setBounds(74, 11, 48, 14);
        this.contentPane.add(label);
    }
}
