import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class GUI extends JFrame{
    private JPanel mainPanel;
    private JLabel img1;
    private JLabel img2;
    private JLabel text;
    private JButton iniciarLogInButton;
    private JButton button;

    public GUI(String title) throws URISyntaxException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(true);

        button.addActionListener(new ActionListener() {
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
    
    
    
    public static void main(String[] args) throws Exception{

        JFrame frame = new GUI("TrelloHub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);
        JButton button= new JButton();

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

