import java.sql.ResultSet;
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
import javax.swing.JPanel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class Predictionoptions extends JFrame
{
    private JPanel contentPane;
    
    public Predictionoptions() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        final JLabel lblRunnyNose = new JLabel("Runny Nose");
        lblRunnyNose.setBounds(10, 49, 74, 14);
        this.contentPane.add(lblRunnyNose);
        final JRadioButton rdbtnYesNose = new JRadioButton("Yes");
        rdbtnYesNose.setBounds(105, 45, 53, 23);
        this.contentPane.add(rdbtnYesNose);
        rdbtnYesNose.setActionCommand("yes");
        final JRadioButton rdbtnNoNose = new JRadioButton("No");
        rdbtnNoNose.setBounds(178, 45, 109, 23);
        this.contentPane.add(rdbtnNoNose);
        rdbtnNoNose.setActionCommand("no");
        final JLabel lblHeadache = new JLabel("Headache");
        lblHeadache.setBounds(10, 110, 66, 14);
        this.contentPane.add(lblHeadache);
        final JRadioButton rdbtnYesHead = new JRadioButton("Yes");
        rdbtnYesHead.setBounds(105, 106, 53, 23);
        this.contentPane.add(rdbtnYesHead);
        rdbtnYesHead.setActionCommand("yes");
        final JRadioButton rdbtnNoHead = new JRadioButton("No");
        rdbtnNoHead.setBounds(178, 106, 109, 23);
        this.contentPane.add(rdbtnNoHead);
        rdbtnNoHead.setActionCommand("no");
        final JLabel lblFever = new JLabel("Fever");
        lblFever.setBounds(10, 168, 48, 14);
        this.contentPane.add(lblFever);
        final JRadioButton rdbtnYesFever = new JRadioButton("Yes");
        rdbtnYesFever.setBounds(105, 164, 53, 23);
        this.contentPane.add(rdbtnYesFever);
        rdbtnYesFever.setActionCommand("yes");
        final JRadioButton rdbtnNoFever = new JRadioButton("No");
        rdbtnNoFever.setBounds(178, 164, 109, 23);
        this.contentPane.add(rdbtnNoFever);
        rdbtnNoFever.setActionCommand("no");
        final ButtonGroup G2 = new ButtonGroup();
        G2.add(rdbtnNoFever);
        G2.add(rdbtnYesFever);
        final ButtonGroup G3 = new ButtonGroup();
        G3.add(rdbtnNoHead);
        G3.add(rdbtnYesHead);
        final ButtonGroup G4 = new ButtonGroup();
        G4.add(rdbtnNoNose);
        G4.add(rdbtnYesNose);
        final JButton btnShowResult = new JButton("Show Result");
        btnShowResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final String url = "jdbc:mysql://localhost:3306/navybase";
                final String username = "root";
                final String password = "root";
                int count = 0;
                int countNoseYes = 0;
                int countHeadYes = 0;
                int countFeverYes = 0;
                int countFluYes = 0;
                int countNoseNo = 0;
                int countHeadNo = 0;
                int countFeverNo = 0;
                int countFluNo = 0;
                int countNoseYesGivenFluYes = 0;
                int countHeadYesGivenFluYes = 0;
                int countFeverYesGivenFluYes = 0;
                int countNoseYesGivenFluNo = 0;
                int countHeadYesGivenFluNo = 0;
                int countFeverYesGivenFluNo = 0;
                int countNoseNoGivenFluYes = 0;
                int countHeadNoGivenFluYes = 0;
                int countFeverNoGivenFluYes = 0;
                int countNoseNoGivenFluNo = 0;
                int countHeadNoGivenFluNo = 0;
                int countFeverNoGivenFluNo = 0;
                float pFluYes = 0.0f;
                float pFluNo = 0.0f;
                float pRunnyNoseYesGivenFluYes = 0.0f;
                float pRunnyNoseNoGivenFluYes = 0.0f;
                float pHeadacheYesGivenFluYes = 0.0f;
                float pHeadacheNoGivenFluYes = 0.0f;
                float pFeverYesGivenFluYes = 0.0f;
                float pFeverNoGivenFluYes = 0.0f;
                float pRunnyNoseYesGivenFluNo = 0.0f;
                float pRunnyNoseNoGivenFluNo = 0.0f;
                float pHeadacheYesGivenFluNo = 0.0f;
                float pHeadacheNoGivenFluNo = 0.0f;
                float pFeverYesGivenFluNo = 0.0f;
                float pFeverNoGivenFluNo = 0.0f;
                try {
                    Throwable t = null;
                    try {
                        final Connection connection = DriverManager.getConnection(url, username, password);
                        try {
                            if ((rdbtnYesNose.isSelected() || rdbtnNoNose.isSelected()) && (rdbtnYesHead.isSelected() || rdbtnNoHead.isSelected()) && (rdbtnYesFever.isSelected() || rdbtnNoFever.isSelected())) {
                                final String ny = G4.getSelection().getActionCommand();
                                final String hy = G3.getSelection().getActionCommand();
                                final String fy = G2.getSelection().getActionCommand();
                                final String query = "SELECT runnyNose, headache, fever, flue FROM ntrainingdata ";
                                final Statement st = connection.createStatement();
                                final ResultSet rs = st.executeQuery(query);
                                while (rs.next()) {
                                    final String runny = rs.getString("runnyNose");
                                    final String head = rs.getString("headache");
                                    final String fev = rs.getString("fever");
                                    final String flu = rs.getString("flue");
                                    ++count;
                                    if (runny.equals("yes")) {
                                        ++countNoseYes;
                                        if (runny.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("yes")) {
                                            ++countNoseYesGivenFluYes;
                                        }
                                        else if (runny.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("no")) {
                                            ++countNoseYesGivenFluNo;
                                        }
                                    }
                                    else {
                                        ++countNoseNo;
                                        if (runny.equalsIgnoreCase("no") && flu.equalsIgnoreCase("yes")) {
                                            ++countNoseNoGivenFluYes;
                                        }
                                        else if (runny.equalsIgnoreCase("no") && flu.equalsIgnoreCase("no")) {
                                            ++countNoseNoGivenFluNo;
                                        }
                                    }
                                    if (head.equals("yes")) {
                                        ++countHeadYes;
                                        if (head.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("yes")) {
                                            ++countHeadYesGivenFluYes;
                                        }
                                        else if (head.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("no")) {
                                            ++countHeadYesGivenFluNo;
                                        }
                                    }
                                    else {
                                        ++countHeadNo;
                                        if (head.equalsIgnoreCase("no") && flu.equalsIgnoreCase("yes")) {
                                            ++countHeadNoGivenFluYes;
                                        }
                                        else if (head.equalsIgnoreCase("no") && flu.equalsIgnoreCase("no")) {
                                            ++countHeadNoGivenFluNo;
                                        }
                                    }
                                    if (fev.equals("yes")) {
                                        ++countFeverYes;
                                        if (fev.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("yes")) {
                                            ++countFeverYesGivenFluYes;
                                        }
                                        else if (fev.equalsIgnoreCase("yes") && flu.equalsIgnoreCase("no")) {
                                            ++countFeverYesGivenFluNo;
                                        }
                                    }
                                    else {
                                        ++countFeverNo;
                                        if (fev.equalsIgnoreCase("no") && flu.equalsIgnoreCase("yes")) {
                                            ++countFeverNoGivenFluYes;
                                        }
                                        else if (fev.equalsIgnoreCase("no") && flu.equalsIgnoreCase("no")) {
                                            ++countFeverNoGivenFluNo;
                                        }
                                    }
                                    if (flu.equals("yes")) {
                                        ++countFluYes;
                                    }
                                    else {
                                        ++countFluNo;
                                    }
                                }
                                pFluYes = countFluYes / (float)count;
                                pFluNo = countFluNo / (float)count;
                                pRunnyNoseYesGivenFluYes = countNoseYesGivenFluYes / (float)countFluYes;
                                pRunnyNoseNoGivenFluYes = countNoseNoGivenFluYes / (float)countFluYes;
                                pRunnyNoseYesGivenFluNo = countNoseYesGivenFluNo / (float)countFluNo;
                                pRunnyNoseNoGivenFluNo = countNoseNoGivenFluNo / (float)countFluNo;
                                pHeadacheYesGivenFluYes = countHeadYesGivenFluYes / (float)countFluYes;
                                pHeadacheNoGivenFluYes = countHeadNoGivenFluYes / (float)countFluYes;
                                pHeadacheYesGivenFluNo = countHeadYesGivenFluNo / (float)countFluNo;
                                pHeadacheNoGivenFluNo = countHeadNoGivenFluNo / (float)countFluNo;
                                pFeverYesGivenFluYes = countFeverYesGivenFluYes / (float)countFluYes;
                                pFeverNoGivenFluYes = countFeverNoGivenFluYes / (float)countFluYes;
                                pFeverYesGivenFluNo = countFeverYesGivenFluNo / (float)countFluNo;
                                pFeverNoGivenFluNo = countFeverNoGivenFluNo / (float)countFluNo;
                                float resultYes = 0.0f;
                                float resultNo = 0.0f;
                                if ((rdbtnYesNose.isSelected() || rdbtnNoNose.isSelected()) && (rdbtnYesHead.isSelected() || rdbtnNoHead.isSelected()) && (rdbtnYesFever.isSelected() || rdbtnNoFever.isSelected())) {
                                    final String UserChoiceNose = G4.getSelection().getActionCommand();
                                    final String UserChoiceHead = G3.getSelection().getActionCommand();
                                    final String UserChoiceFever = G2.getSelection().getActionCommand();
                                    if (UserChoiceNose.equalsIgnoreCase("yes")) {
                                        if (UserChoiceHead.equalsIgnoreCase("yes")) {
                                            if (UserChoiceFever.equalsIgnoreCase("yes")) {
                                                resultYes = pFluYes * pRunnyNoseYesGivenFluYes * pHeadacheYesGivenFluYes * pFeverYesGivenFluYes;
                                                resultNo = pFluNo * pRunnyNoseYesGivenFluNo * pHeadacheYesGivenFluNo * pFeverYesGivenFluNo;
                                                if (resultYes > resultNo) {
                                                    JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                                }
                                                else {
                                                    JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                                }
                                            }
                                            else {
                                                resultYes = pFluYes * pRunnyNoseYesGivenFluYes * pHeadacheYesGivenFluYes * pFeverNoGivenFluYes;
                                                resultNo = pFluNo * pRunnyNoseYesGivenFluNo * pHeadacheYesGivenFluNo * pFeverNoGivenFluNo;
                                                if (resultYes > resultNo) {
                                                    JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                                }
                                                else {
                                                    JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                                }
                                            }
                                        }
                                        else if (UserChoiceFever.equalsIgnoreCase("yes")) {
                                            resultYes = pFluYes * pRunnyNoseYesGivenFluYes * pHeadacheNoGivenFluYes * pFeverYesGivenFluYes;
                                            resultNo = pFluNo * pRunnyNoseYesGivenFluNo * pHeadacheNoGivenFluNo * pFeverYesGivenFluNo;
                                            if (resultYes > resultNo) {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                            }
                                            else {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                            }
                                        }
                                        else {
                                            resultYes = pFluYes * pRunnyNoseYesGivenFluYes * pHeadacheNoGivenFluYes * pFeverNoGivenFluYes;
                                            resultNo = pFluNo * pRunnyNoseYesGivenFluNo * pHeadacheNoGivenFluNo * pFeverNoGivenFluNo;
                                            if (resultYes > resultNo) {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                            }
                                            else {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                            }
                                        }
                                    }
                                    else if (UserChoiceHead.equalsIgnoreCase("yes")) {
                                        if (UserChoiceFever.equalsIgnoreCase("yes")) {
                                            resultYes = pFluYes * pRunnyNoseNoGivenFluYes * pHeadacheYesGivenFluYes * pFeverYesGivenFluYes;
                                            resultNo = pFluNo * pRunnyNoseNoGivenFluNo * pHeadacheYesGivenFluNo * pFeverYesGivenFluNo;
                                            if (resultYes > resultNo) {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                            }
                                            else {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                            }
                                        }
                                        else {
                                            resultYes = pFluYes * pRunnyNoseNoGivenFluYes * pHeadacheYesGivenFluYes * pFeverNoGivenFluYes;
                                            resultNo = pFluNo * pRunnyNoseNoGivenFluNo * pHeadacheYesGivenFluNo * pFeverNoGivenFluNo;
                                            if (resultYes > resultNo) {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                            }
                                            else {
                                                JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                            }
                                        }
                                    }
                                    else if (UserChoiceFever.equalsIgnoreCase("yes")) {
                                        resultYes = pFluYes * pRunnyNoseNoGivenFluYes * pHeadacheNoGivenFluYes * pFeverYesGivenFluYes;
                                        resultNo = pFluNo * pRunnyNoseNoGivenFluNo * pHeadacheNoGivenFluNo * pFeverYesGivenFluNo;
                                        if (resultYes > resultNo) {
                                            JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                        }
                                    }
                                    else {
                                        resultYes = pFluYes * pRunnyNoseNoGivenFluYes * pHeadacheNoGivenFluYes * pFeverNoGivenFluYes;
                                        resultNo = pFluNo * pRunnyNoseNoGivenFluNo * pHeadacheNoGivenFluNo * pFeverNoGivenFluNo;
                                        if (resultYes > resultNo) {
                                            JOptionPane.showMessageDialog(null, "Patient likely to have flu \n With Probability" + resultYes);
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(null, "Patient likely to have No flu \n With Probability" + resultNo);
                                        }
                                    }
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "please fill all data");
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
        btnShowResult.setBounds(10, 208, 109, 23);
        this.contentPane.add(btnShowResult);
        final JButton btnClearAll = new JButton("clear all");
        btnClearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                G2.clearSelection();
                G3.clearSelection();
                G4.clearSelection();
            }
        });
        btnClearAll.setBounds(152, 208, 95, 23);
        this.contentPane.add(btnClearAll);
        final JButton btnGoBack = new JButton("Go back");
        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final choice frame = new choice();
                frame.setVisible(true);
                Predictionoptions.this.setVisible(false);
            }
        });
        btnGoBack.setBounds(276, 208, 89, 23);
        this.contentPane.add(btnGoBack);
    }
}
