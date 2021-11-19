import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Trello extends JFrame {
    private JPanel mainPanel;
    private JLabel TrelloImg;
    private JTextArea CaixaTexto_token;
    private JTextArea CaixaTexto_key;
    private JPasswordField password_token;
    private JPasswordField password_key;
    private String password_token_text;
    private String password_key_text;
    private JButton button_login;

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
        button_login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SetPassword();
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

    private void PasswordValidation(){
        while( password_token_text.isEmpty() || password_key_text.isEmpty() ) {
            PasswordValidation_Token();
            PasswordValidation_Key();
        }
    }

    private void SetPassword(){
        password_token_text = String.valueOf(password_token.getPassword());
        System.out.println(password_token_text);
        password_key_text = String.valueOf(password_key.getPassword());
        System.out.println(password_key_text);
        PasswordValidation();
    }


}
