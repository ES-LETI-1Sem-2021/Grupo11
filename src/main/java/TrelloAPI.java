

import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Action;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.io.IOException;
import java.util.*;

public class TrelloAPI {


    private static String trelloKey;
    private static String trelloAccessToken;
    private static Trello trelloApi;
    private static String username;


    public TrelloAPI(String Key_utilizado, String Token_utilizador, String username) {
        this.trelloKey = Key_utilizado;
        this.trelloAccessToken = Token_utilizador;
        this.username = username;
        trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);

    }

    public String getUsername() {
        return this.username;
    }


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

    public List<Member> getMembers(Board board) {
        List<Member> list = trelloApi.getMembersByBoard(board.getId());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getFullName());
        }
        return list;
    }


    public List<org.trello4j.model.List> getLists(Board board) {
        List<org.trello4j.model.List> lists = trelloApi.getListByBoard(board.getId());

        return lists;

    }


    public org.trello4j.model.List getList(Board board, String listname) {
        org.trello4j.model.List list;
        List<org.trello4j.model.List> lists = getLists(board);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getName().equals(listname)) {
                list = lists.get(i);
                return list;
            }

        }
        System.out.println("A lista não existe");
        return null;
    }


    public List<Card> getCards(org.trello4j.model.List list) {
        List<Card> cards = trelloApi.getCardsByList(list.getId());


        return cards;
    }


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

    public Date compareDates(Date date1, Date date2){
        if(date1.getYear()<date2.getYear()) {
            return date1;
        }
        else {
            if(date1.getYear() == date2.getYear()){
                if(date1.getMonth()< date2.getMonth()){
                    return date1;
                }
                else {
                    if(date1.getMonth() == date2.getMonth()){
                        if(date1.getDate() < date2.getDate()){
                            return  date1;
                        }
                    }
                }
                }
            }

        return  date2;
        }


    public Date getStartDate(Board board) {
        Date startDate = null;
        int d=0;
        int m=0;
        int y=0;
        List<org.trello4j.model.List> lists = getLists(board);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).getName().equals("Sprint Meetings")) {
                List<Card> cards = getCards(lists.get(i));
                for (int i2 = 0; i2 < cards.size(); i2++) {
                    if (cards.get(i2).getName().equals("Sprint Planning")) {
                        List<Card.Label> labels = cards.get(i2).getLabels();
                        for (int i3 = 0; i3 < labels.size(); i3++) {
                            if (labels.get(i3).getName().equals("Sprint #1")) {
                                List<Action> actions = trelloApi.getActionsByCard(cards.get(i2).getId());
                                for (int i4 = 0; i4 < actions.size(); i4++) {
                                    for(int i5 = i4+1 ; i5< actions.size(); i5++) {
                                        startDate = compareDates(actions.get(i4).getDate(), actions.get(i5).getDate());
                                    }

                                        }
                                    }

                                }
                            }
                        }
                    }
                }

        startDate.setMonth(startDate.getMonth()+1);
        startDate.setYear(startDate.getYear()+1900);

        return  startDate;

            }

    public List <String> getSprintDesc(Board board){
        List<Card> cards = null;
        List <String> descs = new ArrayList<>();


        List <org.trello4j.model.List> lists = getLists(board);
        for (int i =0; i<lists.size(); i++){
            if(lists.get(i).getName().equals("Sprint Meetings")){
                cards=getCards(lists.get(i));
            }
        }
        for (int i2=0; i2< cards.size(); i2++ ){
            descs.add(cards.get(i2).getName() + ":" + cards.get(i2).getDesc());

        }

        return descs;
    }

 /*   public void getSprintDates(Board board){
        List <org.trello4j.model.List> lists = getLists(board);
        for(int i=0; i< lists.size(); i++){

            if (lists.get(i).getName().equals("Sprint Meetings")){
                List <Card> cards = getCards(lists.get(i));
                for(int i2=0; i2<cards.size(); i2++){

                    if(cards.get(i2).getName().equals("Sprint Planning")){
                        List <Card.Label> labels = cards.get(i2).getLabels();
                        System.out.println(cards.get(i2).getName());
                        for(int i3=0; i3< labels.size(); i3++){
                            if(labels.get(i3).getName().equals("Sprint #1") ){
                                List<Action> actions=  trelloApi.getActionsByCard(cards.get(i2).getId());
                                System.out.println(labels.get(i3).getName());
                                for(int i4=0; i4< actions.size(); i4++){
                                   System.out.println(labels.get(i3).getName() + ":"+ actions.get(i4).getDate());

                                }

                            }

                        }
                    }
                }
            }
        }
    }*/
        }








































