import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GUI_Application extends JFrame{

    //GUI Objects
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton button_4;
    private JButton button_5;
    private JButton Button_6;
    private JButton Button_8;
    private JButton Button_9;
    private JButton Button_7;
    private JLabel project_name;
    private JPanel mainPanel;
    private JButton Button_10;
    private JButton Button_11;
    private JButton Button_12;

    //Aplication Variables
    private static Card card;
    private String trello_token ;
    private String trello_key;
    private String trello_username;
    private String github_token;
    private String github_repositoryOwner;
    private String github_repositoryName;
    private String boardName;
    public TrelloAPI trello;
    public Board board;
    private List<String> project_membersNames = new ArrayList<String>();
    private List<String> project_sprintDescription = new ArrayList<String>();
    private List<String> product_backlog_per_sprint = new ArrayList<String>();
    private List<String> project_sprintDates = new ArrayList<String>();
    private List<Member> project_members;
    private List<org.trello4j.model.List> project_lists;
    private List<Card> project_productBacklog;
    private String datas_dos_sprints;
    private String product_backlog;
    private String membros_do_projecto_Names;
    private String atas_das_reunioes;
    private Date project_startDate;

    public GUI_Application(String title){
        super(title);
        this.setSize(500, 500);
        this.setVisible(true);
        this.setContentPane(mainPanel);
        this.pack();
        this.setResizable(false);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    membros_do_projecto_Names = project_membersNames.toString().replace("[","")
                            .replace("]","").replace(", ","\n");
                    UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                    JOptionPane.showMessageDialog(null, membros_do_projecto_Names, "Membros do Projecto", 1);
            }
        });
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
                JOptionPane.showMessageDialog(null, project_startDate.getDate() + "/" + project_startDate.getMonth() + "/" + project_startDate.getYear() , "Data de inicio", 1);
            }
        });
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < project_sprintDates.size(); i++){
                    if(i>0){
                        datas_dos_sprints+="\nSprint #" + (i+1) ;
                        datas_dos_sprints+= " -> Inicio: " + project_sprintDates.get(i).split(" - ")[0];
                        datas_dos_sprints+= " -> Fim: " + project_sprintDates.get(i).split(" - ")[1];
                    }
                    else{
                        datas_dos_sprints = "Sprint #" +(i+1);
                        datas_dos_sprints+= " -> Inicio: " + project_sprintDates.get(i).split(" - ")[0];
                        datas_dos_sprints+= " -> Fim: " + project_sprintDates.get(i).split(" - ")[1];
                    }
                }
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, datas_dos_sprints , "Datas dos Sprints", 1);
            }
        });
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               product_backlog = product_backlog_per_sprint.toString().replace("[","").replace("]","").replace(", ","\n");
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, product_backlog , "Product Backlog dos Sprints", 1);
            }
        });
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < project_sprintDescription.size(); i++) {
                    if(i>0) atas_das_reunioes = atas_das_reunioes + project_sprintDescription.get(i) + "\n";
                    else atas_das_reunioes = project_sprintDescription.get(i) + "\n";
                }
                formatSprintDesc();
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 10)));
                JOptionPane.showMessageDialog(null, atas_das_reunioes, "Atas das Reuniões", 1);
            }
        });
    }

    public void setUpApplication(){
        setProject_name();
        setProject_board();
        setProject_members();
        setProject_startDate();
        setProject_sprintDescription();
        setProject_lists();
        setProject_productBacklog();
        setProject_sprintDates();
    }

    private void formatSprintDesc(){
        atas_das_reunioes = atas_das_reunioes.replace(": \n",": \n         ");
        atas_das_reunioes = atas_das_reunioes.replace(",  Duração:", ".         Duração:");
        atas_das_reunioes = atas_das_reunioes.replace(".", ".\n         ");
    }

    private void setProject_sprintDates(){
        for (int i = 0; i < project_sprintDescription.size(); i++){
            if(project_sprintDescription.get(i).startsWith("Sprint Planning:")) project_sprintDates.add( project_sprintDescription.get(i).substring(
                    project_sprintDescription.get(i).lastIndexOf("Duração:") + 9).replace("\n"," "));
        }
    }

    private void setProject_productBacklog(){
        for (int j = 1; j < 4; j++) {
            product_backlog_per_sprint.add("Sprint #"+j+":");
            for (int i = 0; i < project_lists.size(); i++) {
                project_productBacklog = trello.getItemsCompletedBySprint(project_lists.get(i), "Sprint #".concat((String.valueOf(j))));
                for (int n = 0; n < project_productBacklog.size(); n++) {
                    product_backlog_per_sprint.add("  ->"+project_productBacklog.get(n).getName());
                }
            }
        }
    }

    private void setProject_lists(){
        project_lists = trello.getLists(board);
    }

    private void setProject_name(){
        List<Board> boards = trello.getTrello().getBoardsByMember(trello_username);
        for(int i=0; i<boards.size(); i++) boardName = boards.get(i).getName();
        project_name.setText(boardName);
    }

    private void setProject_board(){
        board = trello.getBoard(trello_username, boardName);
    }

    private void setProject_startDate(){
        project_startDate = trello.getStartDate(board);
    }

    private void setProject_sprintDescription(){
        project_sprintDescription = trello.getSprintDesc(board);
    }

    private void setProject_members(){
        project_members = trello.getMembers(board);
        for(int i=0; i<project_members.size(); i++){
           project_membersNames.add(project_members.get(i).getFullName());
        }
    }

    //Passwords Setters
    public void setTrello(String token, String key, String username) {
        trello_token = token;
        trello_key = key;
        trello_username = username;
        trello = new TrelloAPI(trello_key,trello_token,trello_username);
    }

    public void setGitHub(String token, String repositoryOwner, String repositoryName) {
        github_token = token;
        github_repositoryOwner = repositoryOwner;
        github_repositoryName = repositoryName;
    }

}
