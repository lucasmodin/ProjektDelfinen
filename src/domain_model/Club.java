package domain_model;

import java.util.ArrayList;

public class Club {

    private ArrayList<Member> clubMembers;

    public Club (){
        this.clubMembers = new ArrayList<>();
    }

    public void addMember (Member member){
        clubMembers.add(member);
    }

    public void removeMember(Member member){
        clubMembers.remove(member);
    }

    public ArrayList<Member> getClubMembers(){
        return clubMembers;
    }


}
