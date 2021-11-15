import javax.swing.*;

public class GUI_Trello extends JFrame {
    private JPanel mainPanel;
    private JTextArea CaixaTexto_token;
    private JPasswordField password_token;
    private JTextArea CaixaTexto_key;
    private JPasswordField password_key;
    private JLabel TrelloImg;

    public GUI_Trello(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
    }
}
