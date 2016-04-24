package RichardGui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class RichardTetris {

    public RichardTetris() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	// the UIManager allows
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("LEVIIIIIIIII");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new RichardTetrisPanel());
                // pack sizes the frame so that everything is at or above their preferred size.
                frame.setSize(400, 800);
                //frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }



    public static void main(String[] args) {
        new RichardTetris();
    }

}