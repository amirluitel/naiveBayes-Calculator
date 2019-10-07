import javax.swing.AbstractAction;
import java.sql.Statement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class TrainingDataSet extends JFrame
{
    private JPanel contentPane;
    private final Action action;
    
    public TrainingDataSet() {
        this.action = new SwingAction();
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        final JLabel lblRunnyNose = new JLabel("Runny Nose");
        lblRunnyNose.setBounds(10, 33, 76, 14);
        this.contentPane.add(lblRunnyNose);
        final JLabel lblHeadache = new JLabel("Headache");
        lblHeadache.setBounds(10, 84, 76, 14);
        this.contentPane.add(lblHeadache);
        final JLabel lblFever = new JLabel("Fever");
        lblFever.setBounds(10, 133, 48, 14);
        this.contentPane.add(lblFever);
        final JRadioButton rdbtnYesNose = new JRadioButton("Yes");
        rdbtnYesNose.setBounds(117, 29, 48, 23);
        this.contentPane.add(rdbtnYesNose);
        rdbtnYesNose.setActionCommand("yes");
        final JRadioButton rdbtnNoNose = new JRadioButton("No");
        rdbtnNoNose.setBounds(184, 29, 109, 23);
        this.contentPane.add(rdbtnNoNose);
        rdbtnNoNose.setActionCommand("no");
        final JRadioButton rdbtnYesHead = new JRadioButton("Yes");
        rdbtnYesHead.setBounds(117, 80, 53, 23);
        this.contentPane.add(rdbtnYesHead);
        rdbtnYesHead.setActionCommand("yes");
        final JRadioButton rdbtnNoHead = new JRadioButton("No");
        rdbtnNoHead.setBounds(184, 80, 109, 23);
        this.contentPane.add(rdbtnNoHead);
        rdbtnNoHead.setActionCommand("no");
        final JRadioButton rdbtnYesFever = new JRadioButton("Yes");
        rdbtnYesFever.setBounds(117, 124, 53, 23);
        this.contentPane.add(rdbtnYesFever);
        rdbtnYesFever.setActionCommand("yes");
        final JRadioButton rdbtnNoFever = new JRadioButton("No");
        rdbtnNoFever.setBounds(185, 124, 48, 23);
        this.contentPane.add(rdbtnNoFever);
        rdbtnNoFever.setActionCommand("no");
        final JLabel lblFlu = new JLabel("Flu");
        lblFlu.setBounds(10, 166, 48, 14);
        this.contentPane.add(lblFlu);
        final JRadioButton rdbtnYesFlu = new JRadioButton("Yes");
        rdbtnYesFlu.setBounds(117, 163, 58, 23);
        this.contentPane.add(rdbtnYesFlu);
        rdbtnYesFlu.setActionCommand("yes");
        final JRadioButton rdbtnNoFlu = new JRadioButton("No");
        rdbtnNoFlu.setBounds(184, 163, 49, 23);
        this.contentPane.add(rdbtnNoFlu);
        rdbtnNoFlu.setActionCommand("no");
        final ButtonGroup G1 = new ButtonGroup();
        G1.add(rdbtnYesFlu);
        G1.add(rdbtnNoFlu);
        final ButtonGroup G2 = new ButtonGroup();
        G2.add(rdbtnNoFever);
        G2.add(rdbtnYesFever);
        final ButtonGroup G3 = new ButtonGroup();
        G3.add(rdbtnNoHead);
        G3.add(rdbtnYesHead);
        final ButtonGroup G4 = new ButtonGroup();
        G4.add(rdbtnNoNose);
        G4.add(rdbtnYesNose);
        final JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String url = "jdbc:mysql://localhost:3306/navybase";
                final String username = "root";
                final String password = "root";
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            if ((rdbtnYesNose.isSelected() || rdbtnNoNose.isSelected()) && (rdbtnYesHead.isSelected() || rdbtnNoHead.isSelected()) && (rdbtnYesFever.isSelected() || rdbtnNoFever.isSelected()) && (rdbtnYesFlu.isSelected() || rdbtnNoFlu.isSelected())) {
                                final String ny = G4.getSelection().getActionCommand();
                                final String hy = G3.getSelection().getActionCommand();
                                final String fy = G2.getSelection().getActionCommand();
                                final String fluy = G1.getSelection().getActionCommand();
                                final String query = "INSERT INTO ntrainingdata VALUES ('" + ny + "', '" + hy + "','" + fy + "','" + fluy + "')";
                                final Statement st = connection.createStatement();
                                st.executeUpdate(query);
                                JOptionPane.showMessageDialog(null, "data is successfully inserted into database!");
                                G4.clearSelection();
                                G3.clearSelection();
                                G2.clearSelection();
                                G1.clearSelection();
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "please fill all data");
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
        btnAdd.setBounds(129, 203, 89, 23);
        this.contentPane.add(btnAdd);
        final JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final choice ch = new choice();
                ch.setVisible(true);
                TrainingDataSet.this.setVisible(false);
            }
        });
        btnClose.setBounds(229, 203, 89, 23);
        this.contentPane.add(btnClose);
    }
    
    private class SwingAction extends AbstractAction
    {
        public SwingAction() {
            this.putValue("Name", "SwingAction");
            this.putValue("ShortDescription", "Some short description");
        }
        
        @Override
        public void actionPerformed(final ActionEvent e) {
        }
    }
}
