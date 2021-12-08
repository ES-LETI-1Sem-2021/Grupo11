import com.opencsv.CSVWriter;
import lombok.SneakyThrows;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.kohsuke.github.GHTag;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe que cria e executa ações da GUI Application
 * contendo métodos e funções para a sua execução.
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
    private final List<String> project_membersNames = new ArrayList<>();
    private List<String> project_sprintDescription = new ArrayList<>();
    private final List<String> product_backlog_per_sprint = new ArrayList<>();
    private final List<String> project_sprintDates = new ArrayList<>();
    private final List<String> cardsNotOriginatedCommitsNames = new ArrayList<>();
    private final List<String> cardsOriginatedCommitsNames = new ArrayList<>();
    private final List<String> project_githubTagsNames = new ArrayList<>();
    private final List<Double> project_sprintHoursOfWork = new ArrayList<>();
    private final List<Double> project_humanResourcesCost = new ArrayList<>();
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


    /**
     * Contrutor de objectos do tipo GUI_Application, onde é dado como parametro o titulo da GUI.
     *
     * @param title - Objecto do tipo String;
     */
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
            JFrame framePiechartSprint1 = new JFrame("Custo Sprint #1");
            JFrame framePiechartSprint2 = new JFrame("Custo Sprint #2");
            JFrame framePiechartSprint3 = new JFrame("Custo Sprint #3");
            String[] columnNames = new String[trello.numberOfSprints(board)+1];
            for(int i=0; i<=trello.numberOfSprints(board);i++){
                if(i==0)columnNames[i] = "Membros" ;
                else columnNames[i] = "Sprint #" + i;
            }
            JTable table = new JTable(ConvertHumanResourcesToTableData(), columnNames);
            table.setBounds(30, 40, 200, 300);
            JScrollPane scrollBar = new JScrollPane(table);
            frame.add(scrollBar);

            JFreeChart pieChartSprint1 = ChartFactory.createPieChart("", pieChartDataSet(1), true, true, false);
            JFreeChart pieChartSprint2 = ChartFactory.createPieChart("", pieChartDataSet(2), true, true, false);
            JFreeChart pieChartSprint3 = ChartFactory.createPieChart("", pieChartDataSet(3), true, true, false);

            ChartPanel chartPanelSprint1 = new ChartPanel(pieChartSprint1);
            framePiechartSprint1.add(chartPanelSprint1);
            framePiechartSprint1.setSize(500, 250);
            framePiechartSprint1.setVisible(true);

            ChartPanel chartPanelSprint2 = new ChartPanel(pieChartSprint2);
            framePiechartSprint2.add(chartPanelSprint2);
            framePiechartSprint2.setSize(500, 250);
            framePiechartSprint2.setVisible(true);

            ChartPanel chartPanelSprint3 = new ChartPanel(pieChartSprint3);
            framePiechartSprint3.add(chartPanelSprint3);
            framePiechartSprint3.setSize(500, 250);
            framePiechartSprint3.setVisible(true);

            frame.setSize(500, 200);
            frame.setVisible(true);
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
            char charDelimiter = JOptionPane.showInputDialog(null, "Por favor insira Caracter delimitador utilizado pelo EXCEL no seu PC", "",JOptionPane.WARNING_MESSAGE).charAt(0);
            try {
                exportarCSV(board,charDelimiter);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Arial", Font.BOLD, 15)));
            JOptionPane.showMessageDialog(null, "Parametros Exportados para ficheiro Dados.csv"  , "Exportar para .CVS", JOptionPane.INFORMATION_MESSAGE);
        });
    }


    /**
     * Método que executa os seguintes métodos:
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
     * @throws IOException;
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
     * Método que formata a String atas_das_reunioes, subestituindo as seguintes partes da String
     * ": \n", ",  Duração:" e "." por ": \n         ", ".         Duração:" e ".\n         " respetivamente.
     */
    private void formatSprintDescription(){
        atas_das_reunioes = atas_das_reunioes.replace(": \n",": \n         ");
        atas_das_reunioes = atas_das_reunioes.replace(",  Duração:", ".         Duração:");
        atas_das_reunioes = atas_das_reunioes.replace(".", ".\n         ");
    }


    /**
     * Função que coverte as lista project_Members, lista project_humanResourcesCost
     * no formato necessario para criar um PieChart.
     *
     * @param sprintNumber - Numero do Sprint;
     * @return - um DefaultPieDataset;
     */
    private DefaultPieDataset pieChartDataSet(int sprintNumber){
        var dataset = new DefaultPieDataset();
            for (int j = 0; j < project_members.size(); j++) {
                dataset.setValue((project_membersNames.get(j)),project_humanResourcesCost.get(((sprintNumber-1)*project_members.size())+j).intValue());
            }
        return dataset;
    }


    /**
     * Método que atribui valores á lista project_githubTags.
     */
    private void setProject_githubTags() throws IOException {
        project_githubTags = github.getTags();
        for(int i =0; i< project_githubTags.size();i++){
            project_githubTagsNames.add(project_githubTags.get(i).getName());
        }
    }


    /**
     * Método que atribui valores à lista project_cardOriginatedCommits.
     */
    private void setProject_cardOriginatedCommits(){
        project_cardOriginatedCommits = trello.getCardOriginatedCommits(board);
        for(int i=0; i<project_cardOriginatedCommits.size(); i++){
            cardsOriginatedCommitsNames.add(project_cardOriginatedCommits.get(i).getName());
        }
    }


    /**
     * Método que atribui valores à lista project_cardNotOriginatedCommits.
     */
    private void setProject_cardNotOriginatedCommits(){
        project_cardNotOriginatedCommits = trello.getCardNotOriginatedCommits(board);
        for(int i=0; i<project_cardNotOriginatedCommits.size(); i++){
            cardsNotOriginatedCommitsNames.add(project_cardNotOriginatedCommits.get(i).getName());
        }
    }


    /**
     * Método que atribui valores à lista project_humanResourcesCost.
     */
    private void setProject_humanResourcesCost(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_humanResourcesCost.add(trello.HumanResourcesCostBySprint(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }


    /**
     * Método que atribui valores à lista project_sprintHoursOfWork.
     */
    private void setProject_sprintHoursOfWork(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            for(int j = 0; j<project_members.size();j++){
                project_sprintHoursOfWork.add(trello.getHoursOfWork(board, project_members.get(j), "Sprint #" + i));
            }
        }
    }


    /**
     * Método que atribui valores à lista project_sprintDates.
     */
    private void setProject_sprintDates(){
        for (int i = 1; i <= trello.numberOfSprints(board); i++){
            project_sprintDates.add(trello.getSprintDuration(board,"Sprint #"+i));
        }
    }


    /**
     * Método que atribui valores à lista product_backlog_per_sprint.
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
     * Método que define a lista project_lists.
     */
    private void setProject_lists(){
        project_lists = trello.getLists(board);
    }


    /**
     * Método que atribui valores à lista project_name.
     */
    private void setProject_name(){
        List<Board> boards = trello.getTrelloApi().getBoardsByMember(trello_username);
        for(int i=0; i<boards.size(); i++) boardName = boards.get(i).getName();
        project_name.setText(boardName);
    }


    /**
     * Método que atribui valor ao objecto board.
     */
    private void setProject_board(){
        board = trello.getBoard(boardName);
    }


    /**
     * Método que define a lista project_startDate.
     */
    private void setProject_startDate(){
        project_startDate = trello.getSprintStartDate(board,"Sprint #1");
    }


    /**
     * Método que define a lista project_sprintDescription.
     */
    private void setProject_sprintDescription(){
        project_sprintDescription = trello.getSprintDescription(board);
    }


    /**
     * Método que atribui valores à lista project_membersNames.
     */
    private void setProject_members(){
        project_members = trello.getMembers(board);
        for(int i=0; i<project_members.size(); i++){
           project_membersNames.add(project_members.get(i).getFullName());
        }
    }


    /**
     * Exporta a informação pretendida para um ficheiro .csv .
     *
     * @param board - Objecto do tipo Board;
     * @param charDelimiter - Objecto do tipo char;
     * @throws IOException;
     */
    public void exportarCSV(Board board,char charDelimiter) throws IOException {
        FileWriter fw = new FileWriter(new File("Dados.csv"));
        CSVWriter cw = new CSVWriter(fw,charDelimiter, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        List <String[]> data = new ArrayList<String[]>();

        String [] headers ="Membros,Sprint,Horas de Trabalho por Sprint,Custo dos Recursos Humanos por Sprint,Numero de Atividades que deram origem a commits por membro ,Horas trabalhadas que deram origem a commits por membro,Custo dos Recursos Humanos que deram origem a commits por membro,Numero de Atividades que nao deram origem a commits por membro,Horas trabalhadas que nao deram origem a commits por membro,Custo dos Recursos Humanos que nao deram origem a commits por membro".split(",");

        List <Member> members= trello.getMembers(board);
        data.add(headers);
        Double committed=0.0;
        Double notCommitted=0.0;

        for(int i =0; i<members.size(); i++) {
            for (int i2 = 1; i2 <=trello.numberOfSprints(board); i2++) {
                String [] item ={members.get(i).getUsername(), Integer.toString(i2), Double.toString(trello.getHoursOfWork(board,members.get(i),
                        "Sprint #"+Integer.toString(i2) )), Double.toString(trello.HumanResourcesCostBySprint(board, members.get(i),
                        "Sprint #"+Integer.toString(i2) )),Integer.toString(trello.numberOfCardsOriginatedCommitsByMember(board,
                        members.get(i))), Double.toString(trello.getHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i))),
                        Double.toString(trello.costHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i))),
                        Integer.toString(trello.numberOfCardsNotOriginatedCommitsByMember(board, members.get(i))),
                        Double.toString(trello.getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i))),
                        Double.toString(trello.costHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i)))};
                data.add(item);
            }
            committed=committed+trello.getHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i));
            notCommitted=notCommitted+trello.getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i));
        }
        String [] total = {"Total", "","","",Integer.toString(trello.numberOfCardsOriginatedCommits(board)),Double.toString(committed),
                Double.toString(trello.TotalCostHoursWorkedForCardsThatOriginatedCommits(board)),Integer.toString(trello.numberOfCardsNotOriginatedCommits(board)),
                Double.toString(notCommitted),Double.toString(trello.TotalCostHoursWorkedForCardsThatNotOriginatedCommits(board))};
        data.add(total);

        String [] space = {"", "","","","","", "","", "",""};
        data.add(space);
        String [] headersGit ="Author,Data do Commit,Info do Commit".split(",");
        data.add(headersGit);

        cw.writeAll(data);
        cw.close();
        fw.close();
    }


    //Passwords Setters
    /**
     * Método que atribui valores ás variaveis token, key e username.
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
     * Método que atribui valores ás variaveis token, repositoryOwner e repositoryName.
     * Criando tamebem o objecto github.
     *
     * @param token - Token do github;
     * @param repositoryOwner - Nome do dono do Repositório;
     * @param repositoryName - Nome do Repositório;
     * @throws IOException;
     */
    public void setGitHub(String token, String repositoryOwner, String repositoryName) throws IOException {
        github_token = token;
        github_repositoryOwner = repositoryOwner;
        github_repositoryName = repositoryName;
        github = new GithubAPI(github_token,github_repositoryOwner,github_repositoryName);
    }

}
