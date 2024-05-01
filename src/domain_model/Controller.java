package domain_model;

import data_source.FileHandler;

public class Controller {

    private Club club;
    private Payment payment;
    private FileHandler fileHandler;

    public Controller(){
        this.club = new Club();
        this.payment = new Payment(club);
        this.fileHandler = new FileHandler();
    }

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

    public void loadDatabase () {
        fileHandler.loadedDatabase(club);
    }

    public void saveDatabse() {
        fileHandler.saveDatabase(club);
    }

    public String overViewOfCompetitionMembers(){
        return club.overViewOfCompetitionMembers();
    }

    public double calculateTotalExpectedIncome() {
        return payment.calculateTotalExpectedIncome();
    }

    public String displayMembersWhoOwe() {
        return payment.displayMembersWhoOwe();
    }
}
