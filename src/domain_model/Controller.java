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

    public Club getClubMembers(){
        return club;
    }

    public String searchClubMembers(String member){
        return club.searchMembers(member);
    }

    public Member findMember(String member){
        return club.findMember(member);
    }

    public void removeMember(Member member){
        club.removeMember(member);
    }
}
