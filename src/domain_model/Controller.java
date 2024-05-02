package domain_model;

import data_source.FileHandler;

public class Controller {

    //****************** ATTRIBUTES **************************************************//
    private Club club;
    private Payment payment;
    private FileHandler fileHandler;

    // ***************** Constructor *********************************************** ///
    public Controller(){
        this.club = new Club();
        this.payment = new Payment(club);
        this.fileHandler = new FileHandler();
    }

    /// *************** Formand - methods to handle member data ********************////
    public void addMember(Member member){
        club.addMember(member);
    }

    //changes!!!
    public Club getClubMembers(){
        return club;
    }
    //changes!!!
    public String searchClubMembers(String member){
        return club.searchMembers(member);
    }

    //changes!!!
    public Member findMember(String member){
        return club.findMember(member);
    }

    //changes!!!
    public void removeMember(Member member){
        club.removeMember(member);
    }

    /// *************** Kassere - methods to handle payment balance ********************////


    public double calculateTotalExpectedIncome() {
        return payment.calculateTotalExpectedIncome();
    }

    public String displayMembersWhoOwe() {
        return payment.displayMembersWhoOwe();
    }

    /// ************* Træner - methods to handle get trainer information **********////

    public String overViewOfCompetitionMembers(){
        return club.overViewOfCompetitionMembers();
    }
    public String top5Discipline(){
        return club.top5Discipline();
    }


    /// ************* Sorting methods  ******************************************////
    public void sortClubMembers(){
        club.sortClubMembers();
    }

    public void sortingCompetitionMemberOnDiscipline(){
        club.sortingCompetitionMemberOnDiscipline();
    }

    public void sortingCompetitionMember(){
        club.sortingCompetitionMember();
    }


    /// *************** Database Management - methods to handle data ********************////
    public void loadDatabase () {
        fileHandler.loadedDatabase(club);
    }

    public void saveDatabse() {
        fileHandler.saveDatabase(club);
    }

    //****************** testing ************************************* //
}
