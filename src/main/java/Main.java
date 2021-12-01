import org.trello4j.model.Board;
import org.trello4j.model.Card;
import org.trello4j.model.Member;

import java.util.Date;
import java.util.List;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new GUI("TrelloHub");
        TrelloAPI trello = new TrelloAPI("a68d6a52bb47c46fb8e95a23af622976", "83e8a25eed6608419b70207e4a9aa57c9891771366f3e12b500184e39a611dc5\n", "filipegoncalves79");
        Board board = trello.getBoard(trello.getUsername(), "Projecto de Engenharia de Software");
        List<org.trello4j.model.List> lista = trello.getLists(board);
        List<Member> membros = trello.getMembers(board);
        String s = trello.getSprintDuration(board,"Sprint #2");
        String endDate = trello.getSprintEndDate(board, "Sprint #2");
        String startDate = trello.getSprintStartDate(board,"Sprint #2");
        System.out.println(s);
        System.out.println(endDate);
        System.out.println(startDate);
        List<String> list = trello.getSprintDesc(board);
        trello.getHoursOfWork(membros.get(1));
      //  trello.getSprintDates(board);

       for(int p =0; p<list.size(); p++) System.out.println(list.get(p));


/*
        for (int i = 0; i < lista.size(); i++) {

            for (int i2 = 1; i2 <= trello.numberOfSprints(board); i2++) {
                String p = Integer.toString(i2 );
                List<Card> completed = trello.getItemsCompletedBySprint(lista.get(i), "Sprint #".concat(p));


                for (int n = 0; n < completed.size(); n++)
                    System.out.println(completed.get(n).getName());

            }
        }
        }*/

    }
}



