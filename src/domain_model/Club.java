package domain_model;

import Sortmethods.InterfaceComparator;
import Sortmethods.ParameterComparator;
import data_source.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Club {


    //****************** ATTRIBUTES **************************************************//
    private ArrayList<Member> clubMembers;

    // ***************** Constructor *********************************************** ///
    public Club (){
        this.clubMembers = new ArrayList<>();
    }

    /// *************** Formand - methods to handle member data ********************////

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

    /// ************* Træner - methods to handle get trainer information **********////

    public String overViewOfCompetitionMembers(){
        String output = "";
        for (Member member :clubMembers) {
            if (member instanceof CompetitionMember) {
                output += "\n" + member.toString() + "\n";
            }
        }
        return output;
    }

    public String top5Discipline(String choosenDiscipline){             // sortClubMembers
        sortCompetitionTime();
        ArrayList<CompetitionMember> top5 = new ArrayList<>();
        String output = "";
        for(Member member: clubMembers) {
            if (member instanceof CompetitionMember) {
                if (((CompetitionMember) member).getDiscipline().equalsIgnoreCase(choosenDiscipline)) {
                    top5.add((CompetitionMember) member);
                }
            }
        }

        output = top5Dis(top5);

        return output;
    }



    // *** Helper: to get a list of top 5 competition based on discipline list with bounds *** //
    private String top5Dis(ArrayList<CompetitionMember> comMember){
        int top = 4;
        String output = "";
        if (comMember.size() < top + 1){
            top = comMember.size() - 1;
        }

        for(int i = 0; i <= top; i++){
            output += "\n" + comMember.get(i).toString() + "\n";
        }
        return output;
    }

    /// ************* Sorting methods  ******************************************////


    public void sortDiscipline() {
        Collections.sort(clubMembers, new Comparator<InterfaceComparator>() {
            @Override
            public int compare(InterfaceComparator o1, InterfaceComparator o2) {
                return o1.getSortDiscipline().compareTo(o2.getSortDiscipline());
            }
        });
    }

    public void sortCompetitionTime(){
        Collections.sort(clubMembers, new Comparator<InterfaceComparator>() {
            @Override
            public int compare(InterfaceComparator o1, InterfaceComparator o2) {
                return o1.getSortTime() - o2.getSortTime();
            }
        });
    }

    public void sortClubMembers(){
        clubMembers.sort(Comparator.comparing(Member::getName));
    }

    //****************** testing ************************************* //

}
