
import com.mashape.unirest.http.exceptions.UnirestException;
import org.trello4j.Trello;
import org.trello4j.TrelloImpl;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import java.io.IOException;
import java.util.List;

public class TrelloAPI {


    private static String trelloKey;
    private static String trelloAccessToken;
    private static Trello trelloApi ;
    private static String username ;



    public TrelloAPI(String Key_utilizado, String Token_utilizador, String username) {
        this.trelloKey = Key_utilizado;
        this.trelloAccessToken = Token_utilizador;
        this.username = username;
        trelloApi=  new TrelloImpl(trelloKey, trelloAccessToken);

    }

    public String getUsername(){
        return this.username;
    }


    public static Board getBoard(String username, String boardname){
         Board board;
        List <Board> boards = trelloApi.getBoardsByMember(username);
        for ( int i = 0; i<boards.size(); i++){
            if( boards.get(i).getName().equals(boardname)) {
                board=boards.get(i);
                return   board;
            }
    }

        System.out.println("O boardname introduzido não existe");

        return null;
    }


    public static List <org.trello4j.model.List> getLists(Board board){
        List<org.trello4j.model.List> lists = trelloApi.getListByBoard(board.getId());

        return lists;

    }


    public static org.trello4j.model.List getList(Board board ,String listname){
        org.trello4j.model.List list;
        List<org.trello4j.model.List> lists = getLists(board);
        for(int i =0; i< lists.size(); i++){
            if (lists.get(i).getName().equals(listname)){
                list= lists.get(i);
                return list;
            }

        }
        System.out.println("A lista não existe");
        return null;
    }


    public static List <Card> getCards(org.trello4j.model.List list){
        List <Card> cards = trelloApi.getCardsByList(list.getId());

       return cards;
    }


    public static Card getCard(org.trello4j.model.List list, String cardname){
        Card card;
        List <Card> cards = getCards(list);
        for (int i =0; i< cards.size();  i++){
            if(cards.get(i).getName().equals(cardname)){
                card = cards.get(i);
                return card;
            }
        }
        System.out.println("O card não existe");
        return null;
    }



    public static void main(String[] args) throws IOException, UnirestException {
                TrelloAPI trello = new TrelloAPI("a68d6a52bb47c46fb8e95a23af622976","23b97a3bc463f6411d30d44a61e0d35b30c33a12c3cff5cbf73c62dff6ef6696\n","filipegoncalves79");
                 Board board = getBoard(trello.getUsername(), "Projecto de Engenharia de Software");
                 List <org.trello4j.model.List> lista = getLists(board);

                 for (int i = 0; i< lista.size(); i++){
                     System.out.println(lista.get(i).getName());

                     List <Card> cartoes = getCards(lista.get(i));

                     for (int n = 0; n< cartoes.size(); n++){
                         System.out.println(cartoes.get(n).getName());
                     }
                 }

                }
        }




















