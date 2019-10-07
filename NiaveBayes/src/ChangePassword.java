import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class ChangePassword extends JFrame
{
    private JPanel contentPane;
    private JTextField NewPassword;
    private JTextField ConfirmPassword;
    private JTextField NewConfirmPassword;
    
    public ChangePassword() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        final JLabel lblNewPassword = new JLabel("Current Password");
        lblNewPassword.setBounds(34, 39, 104, 14);
        this.contentPane.add(lblNewPassword);
        final JLabel lblConfirm = new JLabel("New Password");
        lblConfirm.setBounds(34, 86, 86, 14);
        this.contentPane.add(lblConfirm);
        (this.NewPassword = new JPasswordField()).setBounds(178, 36, 96, 20);
        this.contentPane.add(this.NewPassword);
        this.NewPassword.setColumns(10);
        (this.ConfirmPassword = new JPasswordField()).setBounds(178, 83, 96, 20);
        this.contentPane.add(this.ConfirmPassword);
        this.ConfirmPassword.setColumns(10);
        final JButton updatePassword = new JButton("Update");
        updatePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String CurrentPassword = "";
                final String newPassword = ChangePassword.this.NewPassword.getText();
                final String ConfirmPass = ChangePassword.this.ConfirmPassword.getText();
                final String NewConfirmPass = ChangePassword.this.NewConfirmPassword.getText();
                final String url = "jdbc:mysql://localhost:3306/navybase";
                final String username = "root";
                final String password = "root";
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            final String query1 = "Select password from nlogin";
                            final Statement st1 = connection.createStatement();
                            final ResultSet rs = st1.executeQuery(query1);
                            while (rs.next()) {
                                CurrentPassword = rs.getString("password");
                            }
                            if (CurrentPassword.contentEquals(newPassword)) {
                                if (NewConfirmPass.contentEquals(ConfirmPass)) {
                                    final String query2 = "update nlogin set password='" + ConfirmPass + "'";
                                    final Statement st2 = connection.createStatement();
                                    st2.executeUpdate(query2);
                                    connection.close();
                                    JOptionPane.showMessageDialog(null, "password updated successfully");
                                    ChangePassword.this.NewPassword.setText("");
                                    ChangePassword.this.ConfirmPassword.setText("");
                                    ChangePassword.this.NewConfirmPassword.setText("");
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "password did not match");
                                }
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "you enetered wrong password");
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
        updatePassword.setBounds(178, 188, 96, 23);
        this.contentPane.add(updatePassword);
        final JLabel lblConfirmpassword = new JLabel("Confirm Password");
        lblConfirmpassword.setBounds(34, 127, 134, 14);
        this.contentPane.add(lblConfirmpassword);
        (this.NewConfirmPassword = new JPasswordField()).setBounds(178, 124, 96, 20);
        this.contentPane.add(this.NewConfirmPassword);
        this.NewConfirmPassword.setColumns(10);
        final JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final adminPanel frame1 = new adminPanel();
                frame1.setVisible(true);
                ChangePassword.this.setVisible(false);
            }
        });
        btnLogin.setBounds(304, 188, 89, 23);
        this.contentPane.add(btnLogin);
    }
}
