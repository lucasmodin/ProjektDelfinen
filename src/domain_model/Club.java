package domain_model;

import data_source.FileHandler;

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

    //changes!!!
    public String searchMembers(String name){
        String total = "";
        for(Member mem : clubMembers){
            if(mem.getName().toLowerCase().contains(name.toLowerCase())){
                total += "\n" + mem.toString() + "\n";
            }
        }
        return total;
    }

    //changes!!!
    public Member findMember(String name){
        for (Member mem : clubMembers){
            if(mem.getName().equalsIgnoreCase(name.toLowerCase())){
                return mem;
            }
        } return null;
    }

    //changes!!!
    @Override
    public String toString(){
        String total = "";
        for(Member mem : clubMembers){
            total += "\n" + mem.toString() + "\n";
        }
        return total;
    }


    public String overViewOfCompetitionMembers(){
        String output = "";
        for (Member member :clubMembers) {
            if (member instanceof CompetitionMember) {
                output += "\n" + member.toString() + "\n";
            }
        }
        return output;
    }

}
