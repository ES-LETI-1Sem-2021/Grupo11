import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GUI extends JFrame{
    private JPanel mainPanel;
    private JLabel img1;
    private JLabel img2;
    private JLabel text;
    private JButton iniciarLogInButton;
    private JButton button;
    private JButton button_github;
    private JButton button_trello;

    public GUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        button_github.setVisible(false);
        button_trello.setVisible(false);
        iniciarLogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarLogInButton.setVisible(false);
                button_github.setVisible(true);
                button_trello.setVisible(true);
                }
        });
        button_trello.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        button_github.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

    }

    public static void main(String[] args){
        JFrame frame = new GUI("TrelloHub");
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

}

