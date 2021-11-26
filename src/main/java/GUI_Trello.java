import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI_Trello extends JFrame {
    private JPanel mainPanel;
    private JLabel TrelloImg;
    private JTextArea CaixaTexto_token;
    private JTextArea CaixaTexto_key;
    private JTextArea CaixaTexto_user;
    private JPasswordField password_token;
    private JPasswordField password_key;
    private JPasswordField password_user;
    public String password_token_text;
    public String password_key_text;
    public String password_user_text;
    private JButton button_login;
    private Trello trello;

    public GUI_Trello(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        password_token.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPassword();
            }
        });
        password_key.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { SetPassword(); }
        });
        password_user.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPassword();
            }
        });
        button_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPassword();
                TrelloLogin();
            }
        });
    }

    //os InputDialog estão a dar erro ao carregar no botão cancel
    private void PasswordValidation_Token(){
        if(password_token_text.isEmpty()){
            password_token_text = JOptionPane.showInputDialog(null, "Por favor insira o seu token do Trello", "Token Trello",2);
            password_token.setText(password_token_text);
        }
    }

    private void PasswordValidation_Key(){
        if(password_key_text.isEmpty()){
            password_key_text = JOptionPane.showInputDialog(null, "Por favor insira a sua key do Trello", "Key Trello",2);
            password_key.setText(password_key_text);
        }
    }
    private void PasswordValidation_User(){
        if(password_user_text.isEmpty()){
            password_user_text = JOptionPane.showInputDialog(null, "Por favor insira o User do Trello", "User Trello",2);
            password_user.setText(password_user_text);
        }

    }

    private void PasswordValidation(){
        while( password_token_text.isEmpty() || password_key_text.isEmpty() || password_user_text.isEmpty()) {
            PasswordValidation_Token();
            PasswordValidation_Key();
            PasswordValidation_User();
        }
    }

    private void TrelloLogin() {
        System.out.println(password_token_text);
        System.out.println(password_key_text);
        System.out.println(password_user_text);

        Trello trello = new TrelloImpl(password_key_text,password_token_text);
        List <Board> boards = trello.getBoardsByMember(password_user_text);
        for(int i=0; i<boards.size(); i++)
        System.out.println(boards.get(i).getName());
    }

    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        password_key_text = String.valueOf(password_key.getPassword());
        password_user_text = String.valueOf(password_user.getPassword());
        PasswordValidation();
    }

    //Passwords Getters
    public String getToken() {
        return password_token_text;
    }

    public String getKey() {
        return password_key_text;
    }

    public String getUser() {
        return password_user_text;
    }

}
