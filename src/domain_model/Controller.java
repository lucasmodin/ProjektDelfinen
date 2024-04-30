package domain_model;

public class Controller {

    private Club club;
    private Payment payment;

    public Controller(){
        this.club = new Club();
        this.payment = new Payment(club);
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
}
