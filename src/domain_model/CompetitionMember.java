package domain_model;

import Sortmethods.InterfaceComparator;

public class CompetitionMember extends Member implements InterfaceComparator {

    //****************** ATTRIBUTES **************************************************//

    private double time;
    private String discipline;
    private String date;
    private String competitionName;
    private int placement;
    private double competitionTime;

    // ***************** Constructor *********************************************** ///
    public CompetitionMember(String name, int age, boolean isActive, double time, String discipline, String date) {
        super(name, age, isActive);
        this.time = time;
        this.discipline = discipline;
        this.date = date;
    }

    /// ************************* Getter methods **********************************////
    public double getTime() {
        return time;
    }

    public String getDiscipline() {
        return discipline;
    }

    public String getDate() {
        return date;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public int getPlacement() {
        return placement;
    }

    public double getCompetitionTime() {
        return competitionTime;
    }
/// ************************* Setter methods **********************************////

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setCompetitionTime(double competitionTime) {
        this.competitionTime = competitionTime;
    }

    /// ******************** Methods to save and toString ***************************////

    // -- Helper methods to get string output -- //
    @Override
    public String toString() {
        String total = "";
        total = "Navn: " + getName() + "\nAlder: " + getAge() + "\n";

        if (isActive()) {
            total += "Medlemskab: Aktiv";
        } else {
            total += "Medlemskab: Passiv";
        }
        total += "\nBedste trænings tid: " + time + "\nSvømme Diciplin: " + discipline + "\nDato for resultat: " + date;

        if (competitionTime > 0) {
            total += "\nKonkurrence Navn: " + getCompetitionName() + "\n Konkurrence placering: " + getPlacement() + "\nKonkurrence tid: " + getCompetitionTime();
        }
        return total;
    }

    // -- Helper methods to save file -- //
    public String saveFormat() {
        return getName() + "," + getAge() + "," + isActive() + "," + time + "," + discipline + "," + date + "," + super.getMemberAccount().getBalance() + "," + getCompetitionName() + "," + getPlacement() + "," + getCompetitionTime();
    }

    /// ************************* Compare methods **********************************////

    @Override
    public int getSortTime() {
        int results = 0;
        if (getTime() > getCompetitionTime()){
            results = (int) getCompetitionTime();
        } else {
            results = (int) getTime();
        }
        return results;
    }

    @Override
    public String getSortDiscipline(){
        String results = getDiscipline();
        return results;
    }

    //****************** testing ************************************* //
}
