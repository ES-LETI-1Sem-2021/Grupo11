import javax.swing.*;

public class GUI_Aplication extends JFrame{

    private JButton Button_1;
    private JButton Button_2;
    private JButton Button_3;
    private JButton Button_4;
    private JButton Button_5;
    private JButton Button_6;
    private JButton Button_7;
    private JButton Button_8;
    private JButton Button_9;
    private JLabel project_name;
    private JPanel mainPanel;


    public GUI_Aplication(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
