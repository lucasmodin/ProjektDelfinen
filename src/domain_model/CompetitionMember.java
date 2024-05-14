package domain_model;

import Sortmethods.InterfaceComparator;

public class CompetitionMember extends Member implements InterfaceComparator {

    //****************** ATTRIBUTES **************************************************//

    private double time;
    private String discipline;
    private String date;

    private String competitionName;
    private int competetionPlacement;
    private double competitionTime;

    // ***************** Constructor *********************************************** ///
    public CompetitionMember(String name, int age, boolean isActive, double time, String discipline, String date,
                             String competitionName, int competetionPlacement, double competitionTime) {
        super(name, age, isActive);
        this.time = time;
        this.discipline = discipline;
        this.date = date;

        this.competitionName = competitionName;
        this.competetionPlacement = competetionPlacement;
        this.competitionTime = competitionTime;


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



    public double getCompetitionTime() {
        return competitionTime;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public int getCompetetionPlacement() {
        return competetionPlacement;
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

    /// ******************** Methods to save and toString ***************************////

    // -- Helper methods to get string output -- //
    @Override
    public String toString(){
        String total = "";
        total = "Navn: " + getName() + "\nAlder: " + getAge() + "\n";
        if(isActive()){
            total += "Medlemskab: Aktiv";
        } else {
            total += "Medlemskab: Passiv";
        }
        total += "\nBedste trænings tid: " + time + "\nSvømme Diciplin: " + discipline + "\nDato for resultat: " + date;
        total += "\nSidst deltaget ved konkurrencen: " + competitionName + "\nBedste konkurrence tid: " + competitionTime + "\nBedste placering: " + competetionPlacement + ". pladsen";
        return total;
    }

    // -- Helper methods to save file -- //
    public String saveFormat() {
        return getName() + "," + getAge() + "," + isActive() + "," + time + "," + discipline + "," + date + "," + super.getMemberAccount().getBalance();
    }

    /// ************************* Compare methods **********************************////

    @Override
    public int getSortTime() {
        int results = (int) getTime();
        return results;
    }

    @Override
    public String getSortDiscipline(){
        String results = getDiscipline();
        return results;
    }

    //****************** testing ************************************* //
}
