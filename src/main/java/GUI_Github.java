import javax.swing.*;

public class GUI_Github extends JFrame {
    private JPanel mainPanel;
    private JLabel GithubImg;
    private JTextArea caixaTexto_token;
    private JPasswordField password_token;
    private JTextArea caixaTexto_repositoryOwner;
    private JPasswordField password_repositoryOwner;
    private JPasswordField password_repositoryName;
    private JTextArea caixaTexto_repositoryName;

    public GUI_Github(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
    }
}
