import org.trello4j.Trello;
import javax.swing.*;


/**
 * Classe que cria e executa ações da GUI de Login do Trello
 * contendo métodos e funções para a sua execução.
 */
public class GUI_Trello extends JFrame {
    private JPanel mainPanel;
    private JLabel TrelloImg;
    private JTextArea CaixaTexto_token;
    private JTextArea CaixaTexto_key;
    private JTextArea CaixaTexto_user;
    private JPasswordField password_token;
    private JPasswordField password_key;
    private JPasswordField password_user;
    public String password_token_text = "";
    public String password_key_text = "";
    public String password_user_text = "";
    private JButton button_login;
    private Trello trello;


    /**
     * Contrutor de objectos do tipo GUI_Trello, onde é dado como parametro o titulo da GUI.
     *
     * @param title - Objecto do tipo String;
     */
    public GUI_Trello(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        password_token.addActionListener(e -> SetPassword());
        password_key.addActionListener(e -> SetPassword());
        password_user.addActionListener(e -> SetPassword());
        button_login.addActionListener(e -> {
            SetPassword();
            setVisible(false);
        });
    }


    /**
     * Método que verifica se a password_token_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_Token(){
        if(password_token_text.isEmpty()){
            password_token_text = JOptionPane.showInputDialog(null, "Por favor insira o seu token do Trello", "Token Trello",JOptionPane.WARNING_MESSAGE);
            password_token.setText(password_token_text);
        }
    }


    /**
     * Método que verifica se a password_key_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_Key(){
        if(password_key_text.isEmpty()){
            password_key_text = JOptionPane.showInputDialog(null, "Por favor insira a sua key do Trello", "Key Trello",JOptionPane.WARNING_MESSAGE);
            password_key.setText(password_key_text);
        }
    }


    /**
     * Método que verifica se a password_user_text está vazia.
     * Caso esteja pede ao utilizador para inserir um valor para o mesmo.
     */
    private void PasswordValidation_User(){
        if(password_user_text.isEmpty()){
            password_user_text = JOptionPane.showInputDialog(null, "Por favor insira o User do Trello", "User Trello",JOptionPane.WARNING_MESSAGE);
            password_user.setText(password_user_text);
        }

    }


    /**
     * Método que enquanto os valores das variaveis password_token_text, password_key_text
     * e password_user_text forem vazios, executa os seguintes métodos:
     *  -> PasswordValidation_Token();
     *  -> PasswordValidation_Key();
     *  -> PasswordValidation_User().
     */
    private void PasswordValidation(){
        while( password_token_text.isEmpty() || password_key_text.isEmpty() || password_user_text.isEmpty()) {
            PasswordValidation_Token();
            PasswordValidation_Key();
            PasswordValidation_User();
        }
    }


    /**
     * Método que atribui valores ás variaveis password_token_text, password_key_text
     * e password_user_text.
     * Executa tambem o metudo: PasswordValidation().
     */
    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        password_key_text = String.valueOf(password_key.getPassword());
        password_user_text = String.valueOf(password_user.getPassword());
        PasswordValidation();
    }


    /**
     * Função que verifica se as ariaveis password_token_text, password_key_text
     * e password_user_text não são vazias.
     *
     * @return - um boolean
     */
    public boolean validateLogin(){
        return !password_token_text.isEmpty() && !password_key_text.isEmpty() && !password_user_text.isEmpty();
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
     * Retorna o valor da String password_key_text.
     *
     * @return - uma String
     */
    public String getKey() {
        return password_key_text;
    }


    /**
     * Retorna o valor da String password_user_text.
     *
     * @return - uma String
     */
    public String getUser() {
        return password_user_text;
    }
}
