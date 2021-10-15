import javax.swing.*;
import java.awt.*;

public class GUI{
    JFrame frame;
    JPanel panel;
    JPasswordField pw;

    public GUI(){
        frame = new JFrame();
        panel = new JPanel();
        pw = new JPasswordField(15);
        pw.setMaximumSize(new Dimension(3,2));
        pw.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
        pw.setEchoChar('*');


        panel.setBorder(BorderFactory.createEmptyBorder(300, 300, 10, 10));
        panel.setLayout(new GridLayout(0,1));
        panel.setBackground(Color.GRAY);
        panel.add(pw);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Project");
        frame.pack();
        frame.setVisible(true);

        frame.add(pw);
    }

    public static void main(String[] args) {
        new GUI();

    }

}