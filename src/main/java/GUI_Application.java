import lombok.SneakyThrows;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JButton button_10;
    private JButton button_8;
    private JButton button_6;
    private JButton button_9;
    private JLabel project_name;
    private JPanel mainPanel;
    private JButton button_7;
    private JButton button_11;
    private JButton button_12;

    //Aplication Variables
    private static Card card;
    private String trello_token;
    private String trello_key;
    private String trello_username;
    private String github_token;
    private String github_repositoryOwner;
    private String github_repositoryName;
    private String boardName;
    public TrelloAPI trello;
    public GithubAPI github;
    public Board board;
    private List<String> project_membersNames = new ArrayList<String>();
    private List<String> project_sprintDescription = new ArrayList<String>();
    private List<String> product_backlog_per_sprint = new ArrayList<String>();
    private List<String> project_sprintDates = new ArrayList<String>();
    private List<Double> project_sprintHoursOfWork = new ArrayList<Double>();
    private List<Double> project_humanResourcesCost = new ArrayList<Double>();
    private List<Member> project_members;
    private List<org.trello4j.model.List> project_lists;
    private List<Card> project_productBacklog;
    private String total_de_horas_por_Sprint;
    private String datas_dos_sprints;
    private String product_backlog;
    private String membros_do_projecto_Names;
    private String atas_das_reunioes;
    private String project_startDate;

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
                JOptionPane.showMessageDialog(null, project_startDate , "Data de inicio", 1);
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
                formatSprintDescription();
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 10)));
                JOptionPane.showMessageDialog(null, atas_das_reunioes, "Atas das Reuniões", 1);
            }
        });
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Custo por Sprint");
                String[] columnNames = new String[trello.numberOfSprints(board)+1];
                for(int i=0; i<=trello.numberOfSprints(board);i++){
                    if(i==0)columnNames[i] = "Membros" ;
                    else columnNames[i] = "Sprint #" + i;
                }
                JTable j = new JTable(ConvertHumanResourcesToTableData(), columnNames);
                j.setBounds(30, 40, 200, 300);

                JScrollPane sp = new JScrollPane(j);
                f.add(sp);
                f.setSize(500, 200);
                f.setVisible(true);

                //TODO VER PIE CHART

            }
        });
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for( int i = 1; i<= trello.numberOfSprints(board); i++){
                    if(i==1) total_de_horas_por_Sprint = "Sprint #" + (i) + ":\n";
                    else total_de_horas_por_Sprint += "Sprint #" + (i) + "\n";
                    for (int j = 0; j < project_membersNames.size(); j++) {
                        if(i==1)total_de_horas_por_Sprint += "  ->" + project_membersNames.get(j) + ": " + project_sprintHoursOfWork.get(j).toString() + "\n";
                        if (i>1) total_de_horas_por_Sprint += "  ->" + project_membersNames.get(j) + ": " + project_sprintHoursOfWork.get((project_membersNames.size()*(i-1))+j).toString() + "\n";
                    }
                }
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
                JOptionPane.showMessageDialog(null, total_de_horas_por_Sprint , "Total de horas por Sprint", 1);
            }
        });
        button_8.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Commits Info");
                JPanel panel = new JPanel();
                JTextArea commitInfo= new JTextArea(github.getCommitInfo());
                JScrollPane scrollBar= new JScrollPane(commitInfo);
                scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                commitInfo.setLineWrap(true);
                commitInfo.setEditable(false);

                frame.setSize(500, 200);
                frame.setVisible(true);

                frame.add(scrollBar);
              //  UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
               // JOptionPane.showMessageDialog(null, github.getCommitInfo() , "Commits Info", 1);
            }
        });
        button_9.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, github.getREADME() , "Descrição do Projecto", 1);
            }
        });
        button_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, "A" , "Atividades que Originaram Artefactos", 1);
            }
        });
        button_11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, "A"  , "Atividades que não Originaram Artefactos", 1);
            }
        });
        button_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, "A"  , "Exportar para .CVS", 1);
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
        setProject_sprintHoursOfWork();
        setProject_humanResourcesCost();
    }
    private String[][] ConvertHumanResourcesToTableData(){
        String[][] tableData = new String[project_members.size()][trello.numberOfSprints(board)+1];
        for (int i = 0; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j <project_members.size(); j++){
                if(i==0) tableData[j][i]=project_membersNames.get(j);
                if(i>0) tableData[j][i]=project_humanResourcesCost.get(((i-1)*project_members.size())+j).toString();
            }
        }
        return tableData;
    }

    private void formatSprintDescription(){
        atas_das_reunioes = atas_das_reunioes.replace(": \n",": \n         ");
        atas_das_reunioes = atas_das_reunioes.replace(",  Duração:", ".         Duração:");
        atas_das_reunioes = atas_das_reunioes.replace(".", ".\n         ");
    }

    private void setProject_humanResourcesCost(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_humanResourcesCost.add(trello.HumanResourcesCostBySprint(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }
    private void setProject_sprintHoursOfWork(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_sprintHoursOfWork.add(trello.getHoursOfWork(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }

    private void setProject_sprintDates(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            project_sprintDates.add(trello.getSprintDuration(board,"Sprint #"+i));
        }
    }

    private void setProject_productBacklog(){
        for (int j = 1; j <= trello.numberOfSprints(board); j++) {
            product_backlog_per_sprint.add("Sprint #"+j+":");
            project_productBacklog = trello.getItemsCompletedBySprint(board, "Sprint #".concat((String.valueOf(j))));
                for (int n = 0; n < project_productBacklog.size(); n++) {
                    product_backlog_per_sprint.add("  ->"+project_productBacklog.get(n).getName());
                }
        }
    }

    private void setProject_lists(){
        project_lists = trello.getLists(board);
    }

    private void setProject_name(){
        List<Board> boards = trello.getTrelloApi().getBoardsByMember(trello_username);
        for(int i=0; i<boards.size(); i++) boardName = boards.get(i).getName();
        project_name.setText(boardName);
    }

    private void setProject_board(){
        board = trello.getBoard(boardName);
    }

    private void setProject_startDate(){
        project_startDate = trello.getSprintStartDate(board,"Sprint #1");
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

    public void setGitHub(String token, String repositoryOwner, String repositoryName) throws IOException {
        github_token = token;
        github_repositoryOwner = repositoryOwner;
        github_repositoryName = repositoryName;
        github = new GithubAPI(github_token,github_repositoryOwner,github_repositoryName);
    }

}
