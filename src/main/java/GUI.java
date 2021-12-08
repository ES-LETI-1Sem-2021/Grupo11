import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

//TODO Verificar mais tarde
/**
 * Classe que cria e executa ações da GUI Principal
 * contendo metudos e funções para a sua execução.
 */
public class GUI extends JFrame {
    private JPanel mainPanel;
    private JLabel GithubImg;
    private JLabel TrelloImg;
    private JButton iniciarLogInButton;
    private JButton button_GithubRepository;
    private JButton button_github;
    private JButton button_trello;
    private JLabel text;
    private JCheckBox checkBox_github_login;
    private JCheckBox checkBox_trello_login;
    private JButton button_validate_logins;
    private JButton button_new_gui;

    public GUI_Trello trello_gui;
    public GUI_Github github_gui;
    public GUI_Application application_gui;


    /**
     * Contrutor de objectos do tipo GUI, onde é dado como parametro o titulo da GUI.
     *
     * @param title - Objecto do tipo String;
     */
    public GUI(String title) {
        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(500, 500);
        this.setResizable(false);
        this.trello_gui = new GUI_Trello("Trello");
        this.trello_gui.setVisible(false);
        this.github_gui = new GUI_Github("GitHub");
        this.github_gui.setVisible(false);

        this.application_gui = new GUI_Application("TrelloHub");
        this.application_gui.setVisible(false);

        button_github.setVisible(false);
        button_trello.setVisible(false);
        checkBox_trello_login.setVisible(false);
        checkBox_github_login.setVisible(false);
        button_new_gui.setVisible(false);
        button_validate_logins.setVisible(false);

        iniciarLogInButton.addActionListener(e -> {
            iniciarLogInButton.setVisible(false);
            checkBox_trello_login.setVisible(true);
            checkBox_github_login.setVisible(true);
            button_github.setVisible(true);
            button_trello.setVisible(true);
            button_validate_logins.setVisible(true);
        });
        button_trello.addActionListener(e -> trello_gui.setVisible(true));
        button_github.addActionListener(e -> github_gui.setVisible(true));
        button_GithubRepository.addActionListener(e -> {
            URI uri;
            try {
                uri = new URI("https://github.com/miguelrato18/ES-LETI-1Sem-2021-Grupo11");
                openWebpage(uri);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            }

        });
        button_validate_logins.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                validLogin_GitHub();
                validLogin_Trello();
                if (checkBox_trello_login.isSelected()) {
                    button_trello.setEnabled(false);
                }
                if (checkBox_github_login.isSelected()) {
                    button_github.setEnabled(false);
                }
                if (checkBox_github_login.isSelected() && checkBox_trello_login.isSelected()) {
                    button_validate_logins.setVisible(false);
                    application_gui.setVisible(true);
                    button_new_gui.setVisible(true);
                    checkBox_trello_login.setVisible(false);
                    checkBox_github_login.setVisible(false);
                    button_trello.setVisible(false);
                    button_github.setVisible(false);
                    application_gui.setTrello(trello_gui.getToken(),trello_gui.getKey(),trello_gui.getUser());
                    application_gui.setGitHub(github_gui.getToken(),github_gui.getRepositoryOwner(),github_gui.getRepositoryName());
                    application_gui.setUpApplication();
                }
            }
        });
        button_new_gui.addActionListener(e -> application_gui.setVisible(true));
    }

        public static boolean openWebpage (URI uri){
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri);
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return false;
        }

        public static boolean openWebpage (URL url){
            try {
                return openWebpage(url.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return false;
        }


    /**
     * Metodo que caso a função trello_gui.validateLogin()==True Passa o valor da checkBox_trello_login para TRUE
     */
    public void validLogin_Trello () {
        if (trello_gui.validateLogin()) checkBox_trello_login.setSelected(true);
    }


    /**
     * Metodo que caso a função trello_gui.validateLogin()==True Passa o valor da checkBox_trello_login para TRUE
     */
    public void validLogin_GitHub () {
        if(github_gui.validateLogin()) checkBox_github_login.setSelected(true);
    }
}
