import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;

// 
// Decompiled by Procyon v0.5.36
// 

public class choice extends JFrame
{
    private JPanel contentPane;
    
    public choice() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 450, 300);
        (this.contentPane = new JPanel()).setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        final JButton btnEnterTrainingData = new JButton("Enter Training Data");
        btnEnterTrainingData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final TrainingDataSet frame = new TrainingDataSet();
                            frame.setVisible(true);
                            choice.this.setVisible(false);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        btnEnterTrainingData.setBounds(27, 90, 150, 44);
        this.contentPane.add(btnEnterTrainingData);
        final JButton btnStartPrediction = new JButton("Start Prediction");
        btnStartPrediction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final Predictionoptions frame = new Predictionoptions();
                frame.setVisible(true);
                choice.this.setVisible(false);
            }
        });
        btnStartPrediction.setBounds(246, 90, 136, 44);
        this.contentPane.add(btnStartPrediction);
        final JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final adminPanel ad = new adminPanel();
                ad.setVisible(true);
                choice.this.setVisible(false);
            }
        });
        btnLogout.setBounds(170, 178, 89, 23);
        this.contentPane.add(btnLogout);
    }
}
