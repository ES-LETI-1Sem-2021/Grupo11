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

    public GUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);

    }

    public static void main(String[] args) throws Exception{
        final URI uri = new URI("https://github.com/miguelrato18/ES-LETI-1Sem-2021-Grupo11");

        JFrame frame = new GUI("TrelloHub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        JButton button= new JButton();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(uri);
                    } catch (Exception ex) {
                    }
                } else {
                }
            }
        });
    }

}

