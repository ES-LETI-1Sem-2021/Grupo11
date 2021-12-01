
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class GenerateCSV{

    private static void generateCsvFile()  throws IOException{

        List<String[]> Data = DataForCSV.CreateData();

        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\llord\\Desktop\\test.csv"))) {
            writer.writeAll(Data);
        }

    }

}