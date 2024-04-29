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
}
