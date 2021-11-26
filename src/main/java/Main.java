
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.trello4j.Trello;
import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.util.Date;
import java.util.List;

import javax.swing.*;

public class Main {

    private static Card card;

    public static void main(String[] args) {

        JFrame frame = new GUI("TrelloHub");
        TrelloAPI trello = new TrelloAPI("a68d6a52bb47c46fb8e95a23af622976", "23b97a3bc463f6411d30d44a61e0d35b30c33a12c3cff5cbf73c62dff6ef6696\n", "filipegoncalves79");
        Board board = trello.getBoard(trello.getUsername(), "Projecto de Engenharia de Software");
        List<org.trello4j.model.List> lista = trello.getLists(board);
        List<Member> membros = trello.getMembers(board);
        Date startDate = trello.getStartDate(board);
        System.out.println(startDate.getDate() + "/" + startDate.getMonth() + "/" + startDate.getYear());
        List <String> list = trello.getSprintDesc(board);

         for(int p =0; p<list.size(); p++) System.out.println(list.get(p));


    for (int j=1; j<4;j++) {
        for (int i = 0; i < lista.size(); i++) {

            List<Card> completed = trello.getItemsCompletedBySprint(lista.get(i), "Sprint #".concat((String.valueOf(j))));


            for (int n = 0; n < completed.size(); n++) {
                System.out.println(completed.get(n).getName());


            }
        }
    }
    }
}


