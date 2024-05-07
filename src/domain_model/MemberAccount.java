package domain_model;

public class MemberAccount {

    //****************** ATTRIBUTES **************************************************//
    private Member member;
    private double balance; //Need to clarify if balance represents the amount owed or paid?
    //Consider if this gives problems for saving/loading to/from CSV file. Look up solutions


    // ***************** Constructor *********************************************** ///
    public MemberAccount(Member member) {
        this.member = member;
        this.balance = 0.0;
    }

    /// ************************* Getter methods **********************************////
    public double getBalance() {
        return balance;
    }

    /// ************************* Setter methods **********************************////

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /// ******************** Methods to management Account ***************************////

    public void addPayment(double amount){
        balance -= amount; //substract payment from the balance
    }

    public void addDues() {
        int age = member.getAge();
        boolean isActive = member.isActive();

        if (isActive) {
            if (age < 18) {
                balance += 1000;
            } else if (age >= 60) {
                balance += 1600 * 0.75;
            } else {
                balance += 1600;
            }
        } else {
            balance += 500;
        }
    }

    public boolean isInGoodStanding() {
        return balance <= 0; //if the balance is zero or negative, member is in good standing
    }



    //****************** testing ************************************* //


}
