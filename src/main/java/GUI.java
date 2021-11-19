import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GUI extends JFrame{
    private JPanel mainPanel;
    private JLabel GithubImg;
    private JLabel TrelloImg;
    private JButton iniciarLogInButton;
    private JButton button_GithubRepository;
    private JButton button_github;
    private JButton button_trello;
    private JLabel text;

    public GUI(String title){
        super(title);
        this.setVisible(true);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setSize(500, 500);
        this.setResizable(false);

        button_github.setVisible(false);
        button_trello.setVisible(false);
        iniciarLogInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarLogInButton.setVisible(false);
                button_github.setVisible(true);
                button_trello.setVisible(true);
                }
        });
        button_trello.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new GUI_Trello("Trello").setVisible(true);
               button_trello.setEnabled(false);
                //implementar close window
            }
        });
        button_github.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new GUI_Github("Github");
                button_github.setEnabled(false);
                //implementar close window
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

    }
    //encontrado num forum
    public static boolean openWebpage(URI uri) {
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

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

