public class Activities {

    int Number;
    double Hours;
    double Cost;
    boolean Artifact;



    public void setNumber(int number) {
        Number = number;
    }
    public void setArtifact(boolean artifact) {
        Artifact = artifact;
    }
    public void setHours(double hours) {
        Hours = hours;
    }
    public void setCost(double cost) {

        Cost = cost;
    }


    public boolean isArtifact() {
        return Artifact;
    }
    public int getNumber() {
        return Number;
    }
    public double getHours() {

        return Hours;
    }
    public double getCost() {

        return Cost;
    }

}
