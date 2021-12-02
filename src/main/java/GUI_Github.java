import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


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
                try {
                    GithubLogin();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
    private void GithubLogin() throws IOException {
        System.out.println(password_token_text);
        System.out.println(password_repositoryOwner_text);
        System.out.println(password_repositoryName_text);
        GithubAPI GAPI = new GithubAPI(password_token_text);
        //GAPI.getProjectDiscription(); //mudar
        GAPI.getCommitInfo();  //mudar

    }

    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        password_repositoryOwner_text = String.valueOf(password_repositoryOwner.getPassword());
        password_repositoryName_text = String.valueOf(password_repositoryName.getPassword());
        PasswordValidation();
    }

    //Passwords Getters
    public String getToken() {
        return password_token_text;
    }

    public String getRepositoryName() {
        return password_repositoryName_text;
    }

    public String getRepositoryOwner() {
        return password_repositoryOwner_text;
    }

}
