import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

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

        iniciarLogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarLogInButton.setVisible(false);
                checkBox_trello_login.setVisible(true);
                checkBox_github_login.setVisible(true);
                button_github.setVisible(true);
                button_trello.setVisible(true);
                button_validate_logins.setVisible(true);
            }
        });
        button_trello.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                trello_gui.setVisible(true);
                }
        });
        button_github.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                github_gui.setVisible(true);
               }
        });
        button_GithubRepository.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                URI uri;
                try {
                    uri = new URI("https://github.com/miguelrato18/ES-LETI-1Sem-2021-Grupo11");
                    openWebpage(uri);
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

            }
        });
        button_validate_logins.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validLogin_GitHub();
                validLogin_Trello();
                if (checkBox_trello_login.isSelected()) {
                    button_trello.setEnabled(false);
                    checkBox_trello_login.setVisible(false);
                    trello_gui.setVisible(false);
                }
                if (checkBox_github_login.isSelected()) {
                    button_github.setEnabled(false);
                    checkBox_github_login.setVisible(false);
                    github_gui.setVisible(false);
                }
                if (checkBox_github_login.isSelected() || checkBox_trello_login.isSelected()) {
                    button_validate_logins.setVisible(false);
                    application_gui.setVisible(true);
                    button_new_gui.setVisible(true);
                    button_trello.setVisible(false);
                    button_github.setVisible(false);
                    application_gui.setTrello(trello_gui.getToken(),trello_gui.getKey(),trello_gui.getUser());
                    //falta setGitHub
                    application_gui.setUpApplication();
                }
            }
        });

        button_new_gui.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                application_gui.setVisible(true);
            }
        });
    }
        //encontrado num forum
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

        private void validLogin_Trello () {
            if (trello_gui.validateLogin()) checkBox_trello_login.setSelected(true);
        }

        //VER MAIS TARDE
        private void validLogin_GitHub () {
            checkBox_github_login.setSelected(true);
        }
    }
