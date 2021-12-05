import com.opencsv.CSVWriter;
import org.trello4j.model.Board;
import org.trello4j.model.Member;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        JFrame frame = new GUI("TrelloHub");
        TrelloAPI trello = new TrelloAPI("a68d6a52bb47c46fb8e95a23af622976", "83e8a25eed6608419b70207e4a9aa57c9891771366f3e12b500184e39a611dc5\n", "filipegoncalves79");
       Board board = trello.getBoard( "Projecto de Engenharia de Software");
      trello.exportarCSV(board);
      //  GithubAPI git= new GithubAPI("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");






    }




}


