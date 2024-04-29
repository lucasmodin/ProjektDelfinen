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


    public String searchMembers(String name){
        String total = "";
        for(Member mem : clubMembers){
            if(mem.getName().toLowerCase().contains(name.toLowerCase())){
                total += mem.toString();
            }
        }
        return total;
    }

    @Override
    public String toString(){
        String total = "";
        for(Member mem : clubMembers){
            total += "\n" + mem.toString() + "\n";
        }
        return total;
    }
}
