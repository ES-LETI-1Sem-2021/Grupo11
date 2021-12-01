

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.*;


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
     * @param username
     * @param boardname
     * @return
     */

    public Board getBoard(String username, String boardname) {
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
        for (int i = 0; i < list.size(); i++) {
            //   System.out.println(list.get(i).getFullName());
            // System.out.println(list.get(i).getId());
        }
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
     * Retorna a lista do Board que tiver o nome igual ao parametro listna,e
     *
     * @param board
     * @param listname
     * @return
     */
    public org.trello4j.model.List getList(Board board, String listname) {
        org.trello4j.model.List list;
        List<org.trello4j.model.List> lists = trelloApi.getListByBoard(board.getId());
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getName().equals(listname)) {
                list = lists.get(i);
                return list;
            }

        }
        System.out.println("A lista não existe");
        return null;
    }

    /**
     * Retorna todos os Cards que pertencem ao Board
     *
     * @param list
     * @return
     */
    public List<Card> getCards(org.trello4j.model.List list) {
        return trelloApi.getCardsByList(list.getId());
    }

    /**
     * Retorna o Card do Board que tiver o nome igual ao parametro cardname
     *
     * @param list
     * @param cardname
     * @return
     */
    public Card getCard(org.trello4j.model.List list, String cardname) {
        Card card;
        List<Card> cards = getCards(list);
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardname)) {
                card = cards.get(i);

                return card;
            }
        }
        System.out.println("O card não existe");
        return null;
    }

    /**
     * Retorna todos os cardes completados que contenham uma label com o nome igual ao parametro labelname
     *
     * @param list
     * @param labelname
     * @return
     */
    public List<Card> getItemsCompletedBySprint(org.trello4j.model.List list, String labelname) {
        List<Card> cards = new ArrayList<>();


        if (list.getName().equals("Completed")) {

            List<Card> listc = trelloApi.getCardsByList(list.getId());
            for (int i = 0; i < listc.size(); i++) {

                List<Card.Label> labelList = listc.get(i).getLabels();
                for (int n = 0; n < labelList.size(); n++) {

                    if (labelList.get(n).getName().equals(labelname)) {
                        cards.add(listc.get(i));
                    }
                }


            }

        }

        return cards;
    }


  /*  public Date compareDates(Date date1, Date date2) {
        if (date1.getYear() < date2.getYear()) {
            return date1;
        } else {
            if (date1.getYear() == date2.getYear()) {
                if (date1.getMonth() < date2.getMonth()) {
                    return date1;
                } else {
                    if (date1.getMonth() == date2.getMonth()) {
                        if (date1.getDate() < date2.getDate()) {
                            return date1;
                        }
                    }
                }
            }
        }

        return date2;
    } */

    /**
     * Retorna a datas da duração do Sprint sprint
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
            if (lists.get(i3).getName().equals("Completed") || lists.get(i3).getName().equals("Sprint Meetings")) {
                id = lists.get(i3).getId();

                List<Card> cards = trelloApi.getCardsByMember(member.getId());
                for (int i2 = 0; i2 < cards.size(); i2++) {

                    if (cards.get(i2).getIdList().equals(id)) {
                        List<Card.Label> labels = cards.get(i2).getLabels();
                        for (int i5 = 0; i5 < labels.size(); i5++) {

                            if (labels.get(i5).getName().equals(sprint)) {
                                System.out.println(cards.get(i2).getName());
                                String desc = cards.get(i2).getDesc();
                                String[] hours = desc.split("; ");

                                for (int i4 = 0; i4 < hours.length; i4++) {
                                    if (hours[i4].contains(member.getUsername()) || hours[i4].contains("global")) {
                                        String[] hour = hours[i4].split(" ");
                                        for (int i = 0; i < hour.length; i++) {
                                            if (hour[i].equals("global") || hour[i].equals(member.getUsername())) {

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

        }
        return hoursWorked;
    }

    public double HumanResorcesCost(Board board,Member member,String sprint){
        double hours=getHoursOfWork(board,member,sprint);
        return hours*20;
    }



}








































