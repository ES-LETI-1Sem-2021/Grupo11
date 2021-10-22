import javax.swing.*;

public class GUI_Github extends JFrame {
    private JPanel mainPanel;
    private JLabel img2;
    private JButton iniciarLogInButton;
    private JLabel img1;
    private JButton button;
    private JButton button_github;
    private JButton button_trello;

    public GUI_Github(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
    }
}
