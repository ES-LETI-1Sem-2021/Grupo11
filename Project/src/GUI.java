import javax.swing.JFrame;

public class GUI{
    public GUI(){
        JFrame frame = new JFrame();

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(new GridLayout());
    }

    public static void main(String[] args){
        new GUI();
    }

}
