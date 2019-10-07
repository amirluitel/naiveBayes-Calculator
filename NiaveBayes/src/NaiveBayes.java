import java.awt.EventQueue;

// 
// Decompiled by Procyon v0.5.36
// 

public class NaiveBayes
{
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final adminPanel frame = new adminPanel();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
