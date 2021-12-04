

import com.opencsv.CSVWriter;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.*;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Classe que que contém os métodos para obter objetos do Trello usando a API trello4j
 */
public class TrelloAPI {


    private static String trelloKey;
    private static String trelloAccessToken;
    public static Trello trelloApi;
    public static String username;


    public TrelloAPI(String Key_utilizado, String Token_utilizador, String username) {
        this.trelloKey = Key_utilizado;
        this.trelloAccessToken = Token_utilizador;
        this.username = username;
        this.trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);

    }

    /**
     * Retorna o nome de utilizador usado no Trello
     *
     * @return
     */

    public String getUsername() {

        return this.username;
    }

    public static Trello getTrelloApi() {
        return trelloApi;
    }

    /**
     * Retorna o Board do User que tiver o nome igual ao parametro boardname
     *
     * @param boardname
     * @return
     */

    public Board getBoard(String boardname) {
        Board board;
        List<Board> boards = trelloApi.getBoardsByMember(username);
        for (int i = 0; i < boards.size(); i++) {
            if (boards.get(i).getName().equals(boardname)) {
                board = boards.get(i);
                return board;
            }
        }

        System.out.println("O boardname introduzido não existe");

        return null;
    }

    /**
     * Retorna os membros que participam do Board
     *
     * @param board
     * @return
     */
    public List<Member> getMembers(Board board) {
        List<Member> list = trelloApi.getMembersByBoard(board.getId());

        return list;
    }

    /**
     * Retorna todas as listas pertencentes ao Board
     *
     * @param board
     * @return
     */
    public List<org.trello4j.model.List> getLists(Board board) {
        return trelloApi.getListByBoard(board.getId());
    }


    /**
     * Retorna todos os Cards que pertencem a Lista list
     *
     * @param list
     * @return
     */
    public List<Card> getCards(org.trello4j.model.List list) {
        return trelloApi.getCardsByList(list.getId());
    }


    /**
     * Retorna todos os cards completados que contenham uma label com o nome igual ao parametro labelname
     *
     * @param board
     * @param labelname
     * @return
     */
    public List<Card> getItemsCompletedBySprint(Board board, String labelname) {
        List<Card> cards = new ArrayList<>();
        List <org.trello4j.model.List> lists= getLists(board);
          for(int i2=0; i2<lists.size(); i2++) {
              if (lists.get(i2).getName().equals("Completed")) {

                  List<Card> listc = getCards(lists.get(i2));
                  for (int i = 0; i < listc.size(); i++) {

                      List<Card.Label> labelList = listc.get(i).getLabels();
                      for (int n = 0; n < labelList.size(); n++) {

                          if (labelList.get(n).getName().equals(labelname)) {
                              cards.add(listc.get(i));

                          }
                      }


                  }

              }
          }

        return cards;
    }


    /**
     * Retorna duração do Sprint sprint
     *
     * @param board
     * @param sprint
     * @return
     */
    public String getSprintDuration(Board board, String sprint) {

        String duration = "";

        List<org.trello4j.model.List> lists = getLists(board);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getName().equals("Sprint Meetings")) {
                List<Card> cards = getCards(lists.get(i));
                for (int i2 = 0; i2 < cards.size(); i2++) {
                    if (cards.get(i2).getName().equals("Sprint Planning")) {

                        List<Card.Label> labels = cards.get(i2).getLabels();
                        for (int i3 = 0; i3 < labels.size(); i3++) {
                            if (labels.get(i3).getName().equals(sprint)) {
                                String desc = cards.get(i2).getDesc();
                                String[] arg = desc.split(":");
                                String[] arg2 = arg[1].split(";");
                                duration = arg2[0];

                            }

                        }
                    }
                }
            }
        }


        return duration;

    }

    /**
     * Retorna a data do fim do Sprint sprint
     *
     * @param board
     * @param sprint
     * @return
     */
    public String getSprintEndDate(Board board, String sprint) {
        String endDate = "";
        String duration = getSprintDuration(board, sprint);
        String[] args = duration.split(" - ");
        endDate = args[1];

        return endDate;
    }

    /**
     * Retrona a data do inicio do Sprint sprint
     *
     * @param board
     * @param sprint
     * @return
     */
    public String getSprintStartDate(Board board, String sprint) {
        String startDate = "";
        String duration = getSprintDuration(board, sprint);
        String[] args = duration.split(" - ");
        startDate = args[0];

        return startDate;
    }

    /**
     * Retorna as descrições de todas as reuniões feitas em cada sprint
     *
     * @param board
     * @return
     */
    public List<String> getSprintDesc(Board board) {
        List<Card> cards = null;
        List<String> descs = new ArrayList<>();
        List<org.trello4j.model.List> lists = getLists(board);
        cards = getCards(cards, lists);
        for (int i2 = 0; i2 < cards.size(); i2++) {
            descs.add(cards.get(i2).getName() + ": " + cards.get(i2).getDesc());
        }
        return descs;
    }

    private List<Card> getCards(List<Card> cards, List<org.trello4j.model.List> lists) {
        for (int i = 0; i < lists.size(); i++) {
            cards = getCards(cards, lists, i);
        }
        return cards;
    }

    private List<Card> getCards(List<Card> cards, List<org.trello4j.model.List> lists, int i) {
        if (lists.get(i).getName().equals("Sprint Meetings")) {
            cards = getCards(lists.get(i));
        }
        return cards;
    }


    /**
     * Retorna o número  de Sprints do Board
     *
     * @param board
     * @return
     */
    public int numberOfSprints(Board board) {
        int n = 0;
        List<org.trello4j.model.List> lists = getLists(board);
        for (int i = 0; i < lists.size(); i++) {

            if (lists.get(i).getName().equals("Sprint Meetings")) {
                List<Card> cards = getCards(lists.get(i));
                for (int i2 = 0; i2 < cards.size(); i2++) {
                    if (cards.get(i2).getName().equals("Sprint Planning")) {
                        n++;
                    }
                }
            }

        }

        return n;
    }

    /**
     * Retorna o número de horas que um membro trabalhou durante o Sprint sprint
     *
     * @param board
     * @param member
     * @param sprint
     * @return
     */

    public double getHoursOfWork(Board board, Member member, String sprint) {
        double hoursWorked = 0;
        String id = "";
        List<org.trello4j.model.List> lists = getLists(board);
         System.out.println(member.getUsername());
        for (int i3 = 0; i3 < lists.size(); i3++) {
            hoursWorked = getHoursWorked(member, sprint, hoursWorked, lists, i3);

        }
        return hoursWorked;
    }

    private double getHoursWorked(Member member, String sprint, double hoursWorked, List<org.trello4j.model.List> lists, int i3) {
        String id;
        if (lists.get(i3).getName().equals("Completed") || lists.get(i3).getName().equals("Sprint Meetings") || lists.get(i3).getName().equals("In Progress")) {
            id = lists.get(i3).getId();


            List<Card> cards = trelloApi.getCardsByMember(member.getId());
            for (int i2 = 0; i2 < cards.size(); i2++) {

                if (cards.get(i2).getIdList().equals(id)) {

                    List<Card.Label> labels = cards.get(i2).getLabels();
                    for (int i5 = 0; i5 < labels.size(); i5++) {

                        if (labels.get(i5).getName().equals(sprint)) {

                            String desc = cards.get(i2).getDesc();
                            String[] hours = desc.split("; ");

                            for (int i4 = 0; i4 < hours.length; i4++) {
                                if (hours[i4].contains(member.getUsername()) || hours[i4].contains("global")) {
                                    String[] hour = hours[i4].split(" ");
                                    for (int i = 0; i < hour.length; i++) {
                                        if (hour[i].equals("global") || hour[i].equals(member.getUsername())) {
                                                System.out.println(hour[i]);
                                            hoursWorked = hoursWorked + Double.valueOf(hour[i + 1]);
                                            System.out.println(hoursWorked);

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return hoursWorked;
    }

    /**
     * Retorna o montante a pagar pelas horas trabalhadas no Sprint sprint
     *
     * @param board
     * @param member
     * @param sprint
     * @return
     */
    public double HumanResourcesCostBySprint(Board board, Member member, String sprint) {
        double hours = getHoursOfWork(board, member, sprint);
        return hours * 20;
    }

    /**
     * Retorna os cards que originaram commits no GITHUB
     * @param board
     * @return
     */
    public List<Card> getCardOriginatedCommits(Board board) {
        List<Card> commited = new ArrayList<Card>();
        List<org.trello4j.model.List> list = getLists(board);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals("Completed")) {
                List<Card> cards = getCards(list.get(i));
                for (int i2 = 0; i2 < cards.size(); i2++) {
                    List<Card.Label> labels = cards.get(i2).getLabels();
                    for (int i3 = 0; i3 < labels.size(); i3++) {
                        if (labels.get(i3).getName().equals("GitHub Commits")) {

                            commited.add(cards.get(i2));
                        }
                    }
                }
            }
        }
        return commited;
    }

    /**
     * Devolve a lista com os cards em que o membro participa que  originaram commits
     * @param board
     * @param member
     * @return Lista de Card
     */
    public List<Card> getCardOriginatedCommitsByMember(Board board,Member member) {
        org.trello4j.model.List list = getList(board);

        List<Card> commited = new ArrayList<Card>();
        List<Card> cards = trelloApi.getCardsByMember(member.getId());
        for (int i = 0; i < cards.size(); i++) {
            List<Card.Label> labels = cards.get(i).getLabels();

            for(int i4=0; i4<labels.size();i4++) {
                if (labels.get(i4).getName().equals("GitHub Commits") && cards.get(i).getIdList().equals(list.getId())) {

                    commited.add(cards.get(i));
                }
            }

                    }






        return commited;
    }

    /**
     * Retorna os cards que não originaram commits no GITHUB
     * @param board
     * @return
     */
    public List <Card> getCardNotOriginatedCommits(Board board) {
        Card.Label label = null;

        List<Card> Notcommited = new ArrayList<Card>();

        List<org.trello4j.model.List> list = getLists(board);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals("Completed")) {
                List<Card> cards = getCards(list.get(i));
                for (int i2 = 0; i2 < cards.size(); i2++) {
                    List<Card.Label> labels = cards.get(i2).getLabels();
                    label = getLabel(label, labels);
                    if(labels.contains(label)==false){
                        Notcommited.add(cards.get(i2));
                    }
                }
            }
        }

       return Notcommited;


    }

    private Card.Label getLabel(Card.Label label, List<Card.Label> labels) {
        for (int i3 = 0; i3 < labels.size(); i3++) {
            if (labels.get(i3).getName().equals("GitHub Commits")) {
                label = labels.get(i3);
            }
        }
        return label;
    }


    /**
     * Devolve a lista com os cards em que o membro participa que não originaram commits
     * @param board
     * @param member
     * @return Lista de Card
     */
    public List<Card> getCardNotOriginatedCommitsByMember(Board board,Member member) {
        org.trello4j.model.List list = getList(board);

        Card.Label labelg=null;
        List<Card> Notcommited = new ArrayList<Card>();
        List<Card> cards = trelloApi.getCardsByMember(member.getId());
        for (int i = 0; i < cards.size(); i++) {
            List<Card.Label> labels = cards.get(i).getLabels();

            labelg = getLabel(labelg, labels);

            if( labels.contains(labelg)==false && cards.get(i).getIdList().equals(list.getId())){

                Notcommited.add(cards.get(i));
            }
        }






        return Notcommited;
    }

    private org.trello4j.model.List getList(Board board) {
        List<org.trello4j.model.List> lists = getLists(board);
        org.trello4j.model.List list = null;
        for (int i5 = 0; i5 < lists.size(); i5++) {
            if (lists.get(i5).getName().equals("Completed"))
                list = lists.get(i5);
        }
        return list;
    }

    /**
     * Número de cards em que o membro member participa que  originaram commits
     * @param board
     * @param member
     * @return int
     */
    public int numberOfCardsOriginatedCommitsByMember(Board board,Member member){
        int count=0;
        List <Card> commited = getCardOriginatedCommitsByMember(board,member);
        for(int i6 = 0;i6<commited.size();i6++)
            count++;

        return count;

    }

    /**
     * Número de cards em que o membro member participa que não originaram commits
     * @param board
     * @param member
     * @return int
     */
    public int numberOfCardsNotOriginatedCommitsByMember(Board board, Member member){
        int count=0;
        List <Card> Notcommited = getCardNotOriginatedCommitsByMember(board,member);
        for(int i6 = 0;i6<Notcommited.size();i6++)
            count++;

        return count;

    }
    /**
     * Retorna o número de cards que originaram commits nno GITHUB
     * @param board
     * @return
     */
    public int numberOfCardsOriginatedCommits(Board board){
        int count=0;
        List <Card> commited = getCardOriginatedCommits(board);
        for(int i6 = 0;i6<commited.size();i6++)
            count++;

        return count;

    }

    /**
     * Retorna o número de cards que não originaram commits nno GITHUB
     * @param board
     * @return
     */
   public int numberOfCardsNotOriginatedCommits(Board board){
        int count=0;
        List <Card> Notcommited = getCardNotOriginatedCommits(board);
        for(int i6 = 0;i6<Notcommited.size();i6++)
            count++;

        return count;

    }

    /**
     * Retorna o número de horas gastas pelo Membro member para realizar as tarefas que originaram Commits no GITHUB
     * @param board
     * @param member
     * @return
     */
    public double getHoursWorkedForCardsThatOriginatedCommitsByMember(Board board, Member member){
        List<Card> cards = getCardOriginatedCommitsByMember(board,member);
        double hoursWorked=0.0;
        for (int i2 = 0; i2 < cards.size(); i2++) {


                        String desc = cards.get(i2).getDesc();
                        String[] hours = desc.split("; ");

                        for (int i4 = 0; i4 < hours.length; i4++) {
                            if (hours[i4].contains(member.getUsername()) || hours[i4].contains("global")) {
                                String[] hour = hours[i4].split(" ");
                                for (int i = 0; i < hour.length; i++) {
                                    if (hour[i].equals("global") || hour[i].equals(member.getUsername())) {

                                        hoursWorked = hoursWorked + Double.valueOf(hour[i + 1]);


                                    }
                                }
                            }
                        }

                }
        return hoursWorked;
            }

    /**
     * Retorna o número de horas gastas pelo Membro member para realizar as tarefas que não originaram Commits no GITHUB
     * @param board
     * @param member
     * @return
     */
    public double getHoursWorkedForCardsThatNotOriginatedCommitsByMember(Board board, Member member){
        List<Card> cards = getCardNotOriginatedCommitsByMember(board,member);
        double hoursWorked=0.0;
        for (int i2 = 0; i2 < cards.size(); i2++) {





            String desc = cards.get(i2).getDesc();
            String[] hours = desc.split("; ");

            for (int i4 = 0; i4 < hours.length; i4++) {
                if (hours[i4].contains(member.getUsername()) || hours[i4].contains("global")) {
                    String[] hour = hours[i4].split(" ");
                    for (int i = 0; i < hour.length; i++) {
                        if (hour[i].equals("global") || hour[i].equals(member.getUsername())) {

                            hoursWorked = hoursWorked + Double.valueOf(hour[i + 1]);

                        }
                    }
                }
            }

        }
        return hoursWorked;


    }

    /**
     * Retorna o custo monetário a pagar ao Membro member associado ao trabalho que gerou Commits no GITHUB
     * @param board
     * @param member
     * @return
     */
    public double costHoursWorkedForCardsThatOriginatedCommitsByMember(Board board, Member member){

        return  getHoursWorkedForCardsThatOriginatedCommitsByMember(board,member)*20;
    }

    /**
     * Retorna o custo monetário a pagar ao Membro member associado ao trabalho que não gerou Commits no GITHUB
     * @param board
     * @param member
     * @return
     */
    public double costHoursWorkedForCardsThatNotOriginatedCommitsByMember(Board board, Member member){
        return  getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board,member)*20;
    }

    /**
     * Retorna o custo monetário total a pagar pela realização das tarefas que geraram commits no GITHUB
     * @param board
     * @return
     */
    public double TotalCostHoursWorkedForCardsThatOriginatedCommits(Board board){
        double cost=0;
        List <Member> members = getMembers(board);
        for(int i=0; i< members.size(); i++){

            cost = cost + costHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i));
        }
        return cost;
    }

    /**
     * Retorna o custo monetário total a pagar pela realização das tarefas que geraram não commits no GITHUB
     * @param board
     * @return
     */
    public double TotalCostHoursWorkedForCardsThatNotOriginatedCommits(Board board){
        double cost=0;
        List <Member> members = getMembers(board);
        for(int i=0; i< members.size(); i++){
            cost = cost + costHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i));
        }
        return cost;
    }

    public void exportarCSV(Board board) throws IOException {
        FileWriter fw = new FileWriter(new File("Dados.csv"));
        CSVWriter cw = new CSVWriter(fw,';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        List <String[]> data = new ArrayList<String[]>();
        String [] headers ="Membros,Sprint,Horas de Trabalho por Sprint,Custo dos Recursos Humanos por Sprint,Numero de Atividades que deram origem a commits por membro ,Horas trabalhadas que deram origem a commits por membro,Custo dos Recursos Humanos que deram origem a commits por membro,Numero de Atividades que nao deram origem a commits por membro,Horas trabalhadas que nao deram origem a commits por membro,Custo dos Recursos Humanos que nao deram origem a commits por membro".split(",");

        List <Member> members= getMembers(board);
        data.add(headers);
        Double commited=0.0;
        Double Notcommited=0.0;


        for(int i =0; i<members.size(); i++) {
            for (int i2 = 1; i2 <= numberOfSprints(board); i2++) {
             String [] item ={members.get(i).getUsername(), Integer.toString(i2), Double.toString(getHoursOfWork(board,members.get(i),
                     "Sprint #"+Integer.toString(i2) )), Double.toString(HumanResourcesCostBySprint(board, members.get(i),
                     "Sprint #"+Integer.toString(i2) )),Integer.toString(numberOfCardsOriginatedCommitsByMember(board,
                     members.get(i))), Double.toString(getHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i))),
                     Double.toString(costHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i))),
                     Integer.toString(numberOfCardsNotOriginatedCommitsByMember(board, members.get(i))),
                     Double.toString(getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i))),
                     Double.toString(costHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i)))};
             data.add(item);
            }
            commited=commited+getHoursWorkedForCardsThatOriginatedCommitsByMember(board, members.get(i));
            Notcommited=Notcommited+getHoursWorkedForCardsThatNotOriginatedCommitsByMember(board, members.get(i));

        }
        String [] total = {"Total", "","","",Integer.toString(numberOfCardsOriginatedCommits(board)),Double.toString(commited),
                Double.toString(TotalCostHoursWorkedForCardsThatOriginatedCommits(board)),Integer.toString(numberOfCardsNotOriginatedCommits(board)),
        Double.toString(Notcommited),Double.toString(TotalCostHoursWorkedForCardsThatNotOriginatedCommits(board))};
          data.add(total);
        cw.writeAll(data);
        cw.close();
        fw.close();
        System.out.println("WOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");


    }





}







































