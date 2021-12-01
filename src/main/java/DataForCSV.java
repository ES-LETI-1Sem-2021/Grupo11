import jdk.dynalink.beans.StaticClass;

import java.util.ArrayList;
import java.util.List;

public class DataForCSV {

    static double Hours;
    static double Cost;
    static Activities Activities_Artifact;


    public static List<String[]> CreateData() {
        List<String[]> data = new ArrayList<>();

        /*
        Activities_Artifact.setArtifact();
        Activities_Artifact.setCost();
        Activities_Artifact.setHours();
        Activities_Artifact.setNumber();
        */

        //criar sting com info
        String[] line = {String.valueOf(Hours),String.valueOf(Cost),String.valueOf(Activities_Artifact.getNumber()),String.valueOf(Activities_Artifact.getCost()), String.valueOf(Activities_Artifact.getHours())};
        //introduzir info em data
        data.add(line);
    return data;
    }
}
