package domain_model;

public class CompetitionMember extends Member {

    // ********************* Attributes *************************//

    private double time;
    private String discipline;
    private String date;



    public CompetitionMember(String name, int age, boolean isActive, double time, String discipline, String date) {
        super(name, age, isActive);
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }


    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString(){
        String total = "";
        total = "Navn: " + getName() + "\nAlder: " + getAge() + "\n";
        if(isActive()){
            total += "Medlemskab: Aktiv";
        } else {
            total += "Medlemskab: Passiv";
        }
        total += "\nBedste tid: " + time + "\nSvømme Diciplin: " + discipline + "\nDato for resultat: " + date;
        return total;
    }

    public String saveFormat() {
        return getName() + "," + getAge() + isActive() + "," + time + "," + discipline + "," + date;
    }

}
