package Sortmethods;

import domain_model.CompetitionMember;
import domain_model.Member;
import domain_model.MemberAccount;

import java.util.Comparator;


public enum ParameterComparator {

    NAME(new Comparator<CompetitionMember>() {
        public int compare(CompetitionMember o1, CompetitionMember o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }),

    AGE(new Comparator<CompetitionMember>() {
        public int compare(CompetitionMember o1, CompetitionMember o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    }),

    TIME(new Comparator<CompetitionMember>() {
        public int compare(CompetitionMember o1, CompetitionMember o2) {
            return Double.compare(o1.getTime(), o2.getTime());
        }
    }),

    DISCIPLINE(new Comparator<CompetitionMember>() {
        public int compare(CompetitionMember o1, CompetitionMember o2) {
            return o1.getDiscipline().compareTo(o2.getDiscipline());
        }
    }),
;


    private Comparator<CompetitionMember> comp;



    ParameterComparator(Comparator<CompetitionMember> comp){
        this.comp = comp;
    }



    public Comparator<CompetitionMember> getComparator() {
        return comp;
    }



}
