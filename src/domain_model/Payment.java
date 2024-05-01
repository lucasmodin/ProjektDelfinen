package domain_model;

public class Payment {

    private Club club;


    public Payment(Club club) {
        this.club = club;

    }

    public double calculateTotalExpectedIncome() {
        double totalExpectedIncome = 0.0;
        for (Member member : club.getClubMembers()){
            MemberAccount account = member.getMemberAccount();

                if (member.isActive()) {
                    if (member.getAge() < 18) {
                        totalExpectedIncome += 1000;
                    } else if (member.getAge() >= 60) {
                        totalExpectedIncome += 1600 * 0.75;
                    } else {
                        totalExpectedIncome += 1600;
                    }
                } else {
                    totalExpectedIncome += 500;
                }
            } return totalExpectedIncome;
        }


    public String displayMembersWhoOwe() {
        String result = "";
        for (Member member : club.getClubMembers()) {
            MemberAccount account = member.getMemberAccount();
            if (!account.isInGoodStanding()) {
                double dues = account.getBalance();
                result += (member.getName() + " skylder " + dues + " kr." + "\n");
            }
        } return result;
    }







}
