import javax.swing.*;


/**
 * Classe que cria e executa ações da GUI de Login do GitHub
 * contendo metudos e funções para a sua execução.
 */
public class GUI_Github extends JFrame {
    private JPanel mainPanel;
    private JLabel GithubImg;
    private JTextArea caixaTexto_token;
    private JTextArea caixaTexto_repositoryOwner;
    private JTextArea caixaTexto_repositoryName;
    private JPasswordField password_token;
    private JPasswordField password_repositoryOwner;
    private JPasswordField password_repositoryName;
    private String password_token_text = "";
    private String password_repositoryOwner_text = "";
    private String password_repositoryName_text = "";
    private JButton button_login;

    public GUI_Github(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        password_token.addActionListener(e -> SetPassword());
        password_repositoryOwner.addActionListener(e -> SetPassword());
        password_repositoryName.addActionListener(e -> SetPassword());
        button_login.addActionListener(e -> {
            SetPassword();
            setVisible(false);
        });
    }


    /**
     * Metudo que verifica se a password_token_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_Token(){
        if(password_token_text.isEmpty()){
            password_token_text = JOptionPane.showInputDialog(null, "Por favor insira o seu token do GitHub", "Token GitHub",JOptionPane.WARNING_MESSAGE);
            password_token.setText(password_token_text);
        }
    }


    /**
     * Metudo que verifica se a password_repositoryOwner_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_RepositoryOwner() {
        if(password_repositoryOwner_text.isEmpty()){
            password_repositoryOwner_text = JOptionPane.showInputDialog(null, "Por favor insira o Repository Owner", "Repository Owner",JOptionPane.WARNING_MESSAGE);
            password_repositoryOwner.setText(password_repositoryOwner_text);
        }
    }


    /**
     * Metudo que verifica se a password_repositoryName_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_RepositoryName() {
        if(password_repositoryName_text.isEmpty()){
            password_repositoryName_text = JOptionPane.showInputDialog(null, "Por favor insira o Repository Name", "Repository Name",JOptionPane.WARNING_MESSAGE);
            password_repositoryName.setText(password_repositoryName_text);
        }
    }


    /**
     * Metudo que enquanto os valores das variaveis password_token_text, password_repositoryOwner_text
     * e password_repositoryName_text forem vazios, executa os seguintes metudos:
     *  -> PasswordValidation_Token();
     *  -> PasswordValidation_RepositoryOwner();
     *  -> PasswordValidation_RepositoryName().
     */
    private void PasswordValidation(){
        while( password_token_text.isEmpty() || password_repositoryOwner_text.isEmpty() || password_repositoryName_text.isEmpty()) {
            PasswordValidation_Token();
            PasswordValidation_RepositoryOwner();
            PasswordValidation_RepositoryName();
        }
    }


    /**
     * Metudo que atribui valores ás variaveis password_token_text, password_repositoryOwner_text
     * e password_repositoryName_text.
     * Executa tambem o metudo: PasswordValidation().
     */
    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        password_repositoryOwner_text = String.valueOf(password_repositoryOwner.getPassword());
        password_repositoryName_text = String.valueOf(password_repositoryName.getPassword());
        PasswordValidation();
    }


    /**
     * Função que verifica se as variaveis password_token_text, password_repositoryOwner_text
     * e password_repositoryName_text não são vazias.
     *
     * @return - um boolean
     */
    public boolean validateLogin(){
        return !password_token_text.isEmpty() && !password_repositoryOwner_text.isEmpty() && !password_repositoryName_text.isEmpty();
    }


    //Passwords Getters
    /**
     * Retorna o valor da String password_token_text.
     *
     * @return - uma String
     */
    public String getToken() {
        return password_token_text;
    }


    /**
     * Retorna o valor da String password_repositoryName_text.
     *
     * @return - uma String
     */
    public String getRepositoryName() {
        return password_repositoryName_text;
    }


    /**
     * Retorna o valor da String password_repositoryOwner_text.
     *
     * @return - uma String
     */
    public String getRepositoryOwner() {
        return password_repositoryOwner_text;
    }

}
