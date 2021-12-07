import lombok.SneakyThrows;
import org.kohsuke.github.GHTag;
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
import java.util.List;

/**
 * Classe que cria e executa ações da GUI Application
 * contendo metudos e funções para a sua execução.
 */

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
    private JButton button_13;
    private JButton button_12;

    //Aplication Variables
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
    private final List<String> project_membersNames = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private List<String> project_sprintDescription = new ArrayList<>();
    private final List<String> product_backlog_per_sprint = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<String> project_sprintDates = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<String> cardsNotOriginatedCommitsNames = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<String> cardsOriginatedCommitsNames = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<String> project_githubTagsNames = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<Double> project_sprintHoursOfWork = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private final List<Double> project_humanResourcesCost = new ArrayList<>(); //TODO VERIFICAR SE FUNCIONA COM FINAL
    private List<Card> project_cardNotOriginatedCommits;
    private List<Card> project_cardOriginatedCommits;
    private List<Member> project_members;
    private List<org.trello4j.model.List> project_lists;
    private List<Card> project_productBacklog;
    private List<GHTag> project_githubTags;

    private String total_de_horas_por_Sprint;
    private String datas_dos_sprints;
    private String product_backlog;
    private String githubTagsNames;
    private String notOriginatedCommits;
    private String originatedCommits;
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
        button_1.addActionListener(e -> {
                membros_do_projecto_Names = project_membersNames.toString().replace("[","")
                        .replace("]","").replace(", ","\n");
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, membros_do_projecto_Names, "Membros do Projecto", JOptionPane.INFORMATION_MESSAGE);
        });
        button_2.addActionListener(e -> {
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
            JOptionPane.showMessageDialog(null, project_startDate , "Data de inicio", JOptionPane.INFORMATION_MESSAGE);
        });
        button_3.addActionListener(e -> {
            datas_dos_sprints = "";
            for(int i = 0; i < project_sprintDates.size(); i++){
                datas_dos_sprints+=("\nSprint #" + (i+1)
                                    + " -> Inicio: " + project_sprintDates.get(i).split(" - ")[0]
                                    + " -> Fim: " + project_sprintDates.get(i).split(" - ")[1]);
            }
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, datas_dos_sprints , "Datas dos Sprints", JOptionPane.INFORMATION_MESSAGE);
        });
        button_4.addActionListener(e -> {
           product_backlog = product_backlog_per_sprint.toString().replace("[","").replace("]","").replace(", ","\n");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, product_backlog , "Product Backlog dos Sprints", JOptionPane.INFORMATION_MESSAGE);
        });
        button_5.addActionListener(e -> {
            for (int i = 0; i < project_sprintDescription.size(); i++) {
                if(i>0) atas_das_reunioes = atas_das_reunioes + project_sprintDescription.get(i) + "\n";
                else atas_das_reunioes = project_sprintDescription.get(i) + "\n";
            }
            formatSprintDescription();
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 10)));
            JOptionPane.showMessageDialog(null, atas_das_reunioes, "Atas das Reuniões", JOptionPane.INFORMATION_MESSAGE);
        });
        button_6.addActionListener(e -> {
            JFrame frame = new JFrame("Custo por Sprint");
            String[] columnNames = new String[trello.numberOfSprints(board)+1];
            for(int i=0; i<=trello.numberOfSprints(board);i++){
                if(i==0)columnNames[i] = "Membros" ;
                else columnNames[i] = "Sprint #" + i;
            }
            JTable table = new JTable(ConvertHumanResourcesToTableData(), columnNames);
            table.setBounds(30, 40, 200, 300);
            JScrollPane scrollBar = new JScrollPane(table);
            frame.add(scrollBar);
            frame.setSize(500, 200);
            frame.setVisible(true);

            //TODO VER PIE CHART

        });
        button_7.addActionListener(e -> {
            for( int i = 1; i<= trello.numberOfSprints(board); i++){
                if(i==1) total_de_horas_por_Sprint = "Sprint #" + (i) + ":\n";
                else total_de_horas_por_Sprint += "Sprint #" + (i) + "\n";
                for (int j = 0; j < project_membersNames.size(); j++) {
                    if(i==1)total_de_horas_por_Sprint += "  ->" + project_membersNames.get(j) + ": " + project_sprintHoursOfWork.get(j).toString() + "\n";
                    if (i>1) total_de_horas_por_Sprint += "  ->" + project_membersNames.get(j) + ": " + project_sprintHoursOfWork.get((project_membersNames.size()*(i-1))+j).toString() + "\n";
                }
            }
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 20)));
            JOptionPane.showMessageDialog(null, total_de_horas_por_Sprint , "Total de horas por Sprint", JOptionPane.INFORMATION_MESSAGE);
        });
        button_8.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Commits Info");
                JTextArea commitInfo= new JTextArea(github.getCommitInfo());
                JScrollPane scrollBar= new JScrollPane(commitInfo);
                scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                commitInfo.setLineWrap(true);
                commitInfo.setEditable(false);
                frame.setSize(500, 200);
                frame.setVisible(true);
                frame.add(scrollBar);
            }
        });
        button_9.addActionListener(new ActionListener() {
            @SneakyThrows
            public void actionPerformed(ActionEvent e) {
                UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
                JOptionPane.showMessageDialog(null, github.getREADME() , "Descrição do Projecto", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        button_10.addActionListener(e -> {
            originatedCommits = cardsOriginatedCommitsNames.toString().replace("[","").replace("]","").replace(", ","\n");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, originatedCommits , "Atividades que Originaram Artefactos", JOptionPane.INFORMATION_MESSAGE);
        });
        button_11.addActionListener(e -> {
            notOriginatedCommits = cardsNotOriginatedCommitsNames.toString().replace("[","").replace("]","").replace(", ","\n");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, notOriginatedCommits  , "Atividades que não Originaram Artefactos", JOptionPane.INFORMATION_MESSAGE);
        });
        button_12.addActionListener(e -> {
            githubTagsNames = project_githubTagsNames.toString().replace("[","").replace("]","").replace(", ","\n");
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, githubTagsNames  , "Etiquetas do Repositório", JOptionPane.INFORMATION_MESSAGE);
        });
        button_13.addActionListener(e -> {
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, "A Exportar para .CVS"  , "Exportar para .CVS", JOptionPane.INFORMATION_MESSAGE);
        });
    }


    /**
     * Metudo que executa os seguintes metudos:
     * -> setProject_name();
     * -> setProject_board();
     * -> setProject_members();
     * -> setProject_startDate();
     * -> setProject_sprintDescription();
     * -> setProject_lists();
     * -> setProject_productBacklog();
     * -> setProject_sprintDates();
     * -> setProject_sprintHoursOfWork();
     * -> setProject_humanResourcesCost();
     * -> setProject_cardOriginatedCommits();
     * ->  setProject_cardNotOriginatedCommits();
     * -> setProject_githubTags();
     */
    public void setUpApplication() throws IOException {
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
        setProject_cardOriginatedCommits();
        setProject_cardNotOriginatedCommits();
        setProject_githubTags();
    }


    /**
     * Função que coverte as lista project_Members, lista project_humanResourcesCost
     * no formato necessario para criar uma tabela JTable.
     *
     * @return - uma matrix de String
     */
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


    /**
     * Metudo que formata a String atas_das_reunioes, subestituindo as seguintes partes da String
     * ": \n", ",  Duração:" e "." por ": \n         ", ".         Duração:" e ".\n         " respetivamente.
     */
    private void formatSprintDescription(){
        atas_das_reunioes = atas_das_reunioes.replace(": \n",": \n         ");
        atas_das_reunioes = atas_das_reunioes.replace(",  Duração:", ".         Duração:");
        atas_das_reunioes = atas_das_reunioes.replace(".", ".\n         ");
    }


    /**
     * Metudo que atribui valores á lista project_githubTags.
     */
    private void setProject_githubTags() throws IOException {
        project_githubTags = github.getTags();
        for(int i =0; i< project_githubTags.size();i++){
            project_githubTagsNames.add(project_githubTags.get(i).getName());
        }
    }


    /**
     * Metudo que atribui valores à lista project_cardOriginatedCommits.
     */
    private void setProject_cardOriginatedCommits(){
        project_cardOriginatedCommits = trello.getCardOriginatedCommits(board);
        for(int i=0; i<project_cardOriginatedCommits.size(); i++){
            cardsOriginatedCommitsNames.add(project_cardOriginatedCommits.get(i).getName());
        }
    }


    /**
     * Metudo que atribui valores à lista project_cardNotOriginatedCommits.
     */
    private void setProject_cardNotOriginatedCommits(){
        project_cardNotOriginatedCommits = trello.getCardNotOriginatedCommits(board);
        for(int i=0; i<project_cardNotOriginatedCommits.size(); i++){
            cardsNotOriginatedCommitsNames.add(project_cardNotOriginatedCommits.get(i).getName());
        }
    }


    /**
     * Metudo que atribui valores à lista project_humanResourcesCost.
     */
    private void setProject_humanResourcesCost(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_humanResourcesCost.add(trello.HumanResourcesCostBySprint(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }


    /**
     * Metudo que atribui valores à lista project_sprintHoursOfWork.
     */
    private void setProject_sprintHoursOfWork(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_sprintHoursOfWork.add(trello.getHoursOfWork(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }


    /**
     * Metudo que atribui valores à lista project_sprintDates.
     */
    private void setProject_sprintDates(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            project_sprintDates.add(trello.getSprintDuration(board,"Sprint #"+i));
        }
    }


    /**
     * Metudo que atribui valores à lista product_backlog_per_sprint.
     */
    private void setProject_productBacklog(){
        for (int j = 1; j <= trello.numberOfSprints(board); j++) {
            product_backlog_per_sprint.add("Sprint #"+j+":");
            project_productBacklog = trello.getItemsCompletedBySprint(board, "Sprint #".concat((String.valueOf(j))));
                for (int n = 0; n < project_productBacklog.size(); n++) {
                    product_backlog_per_sprint.add("  ->"+project_productBacklog.get(n).getName());
                }
        }
    }


    /**
     * Metudo que define a lista project_lists.
     */
    private void setProject_lists(){
        project_lists = trello.getLists(board);
    }


    /**
     * Metudo que atribui valores à lista project_name.
     */
    private void setProject_name(){
        List<Board> boards = trello.getTrelloApi().getBoardsByMember(trello_username);
        for(int i=0; i<boards.size(); i++) boardName = boards.get(i).getName();
        project_name.setText(boardName);
    }


    /**
     * Metudo que atribui valor ao objecto board.
     */
    private void setProject_board(){
        board = trello.getBoard(boardName);
    }


    /**
     * Metudo que define a lista project_startDate.
     */
    private void setProject_startDate(){
        project_startDate = trello.getSprintStartDate(board,"Sprint #1");
    }


    /**
     * Metudo que define a lista project_sprintDescription.
     */
    private void setProject_sprintDescription(){
        project_sprintDescription = trello.getSprintDesc(board);
    }


    /**
     * Metudo que atribui valores à lista project_membersNames.
     */
    private void setProject_members(){
        project_members = trello.getMembers(board);
        for(int i=0; i<project_members.size(); i++){
           project_membersNames.add(project_members.get(i).getFullName());
        }
    }


    //Passwords Setters
    /**
     * Metudo que atribui valores ás variaveis token, key e username.
     * Criando tamebem o objecto trello.
     *
     * @param token - Token do Trello;
     * @param key - Key do Trello;
     * @param username - Nome de usuario do Trello;
     */
    public void setTrello(String token, String key, String username) {
        trello_token = token;
        trello_key = key;
        trello_username = username;
        trello = new TrelloAPI(trello_key,trello_token,trello_username);
    }


    /**
     * Metudo que atribui valores ás variaveis token, repositoryOwner e repositoryName.
     * Criando tamebem o objecto github.
     *
     * @param token - Token do github;
     * @param repositoryOwner - Nome do dono do Repositório;
     * @param repositoryName - Nome do Repositório;
     */
    public void setGitHub(String token, String repositoryOwner, String repositoryName) throws IOException {
        github_token = token;
        github_repositoryOwner = repositoryOwner;
        github_repositoryName = repositoryName;
        github = new GithubAPI(github_token,github_repositoryOwner,github_repositoryName);
    }

}
