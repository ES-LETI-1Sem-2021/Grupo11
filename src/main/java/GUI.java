import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame{
    private JPasswordField passwordField1;
    private JPanel panel1;

    public GUI(){
        add(panel1);
        validate();
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(300, 300);
        gui.setVisible(true);
        gui.pack();
        gui.setTitle("TrelloHub");
    }

}

