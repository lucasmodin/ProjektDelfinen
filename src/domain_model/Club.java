package domain_model;

import Sortmethods.ParameterComparator;
import data_source.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        ArrayList<CompetitionMember> listsort = new ArrayList<>();
        for (Member member :clubMembers) {
            if (member instanceof CompetitionMember) {
                listsort.add((CompetitionMember) member);

                // output += "\n" + member.toString() + "\n";
            }
        }
        listsort.sort(Comparator.comparing(CompetitionMember::getTime));
        return output;
    }

}
