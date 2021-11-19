import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Github extends JFrame {
    private JPanel mainPanel;
    private JLabel GithubImg;
    private JTextArea caixaTexto_token;
    private JTextArea caixaTexto_repositoryOwner;
    private JTextArea caixaTexto_repositoryName;
    private JPasswordField password_token;
    private JPasswordField password_repositoryOwner;
    private JPasswordField password_repositoryName;
    private String password_token_text;
    private String password_repositoryOwner_text;
    private String password_repositoryName_text;
    private JButton button_login;

    public GUI_Github(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        password_token.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { SetPassword(); }
        });
        password_repositoryOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { SetPassword(); }
        });
        password_repositoryName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { SetPassword(); }
        });
        button_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPassword();
            }
        });
    }

    //os InputDialog estão a dar erro ao carregar no botão cancel
    private void PasswordValidation_Token(){
        if(password_token_text.isEmpty()){
            password_token_text = JOptionPane.showInputDialog(null, "Por favor insira o seu token do GitHub", "Token GitHub",2);
            password_token.setText(password_token_text);
        }
    }

    private void PasswordValidation_RepositoryOwner() {
        if(password_repositoryOwner_text.isEmpty()){
            password_repositoryOwner_text = JOptionPane.showInputDialog(null, "Por favor insira o Repository Owner", "Repository Owner",2);
            password_repositoryOwner.setText(password_repositoryOwner_text);
        }
    }

    private void PasswordValidation_RepositoryName() {
        if(password_repositoryName_text.isEmpty()){
            password_repositoryName_text = JOptionPane.showInputDialog(null, "Por favor insira o Repository Name", "Repository Name",2);
            password_repositoryName.setText(password_repositoryName_text);
        }
    }

    private void PasswordValidation(){
        while( password_token_text.isEmpty() || password_repositoryOwner_text.isEmpty() || password_repositoryName_text.isEmpty()) {
            PasswordValidation_Token();
            PasswordValidation_RepositoryOwner();
            PasswordValidation_RepositoryName();
        }
    }

    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        System.out.println(password_token_text);
        password_repositoryOwner_text = String.valueOf(password_repositoryOwner.getPassword());
        System.out.println(password_repositoryOwner_text);
        password_repositoryName_text = String.valueOf(password_repositoryName.getPassword());
        System.out.println(password_repositoryName_text);
        PasswordValidation();
    }
}
