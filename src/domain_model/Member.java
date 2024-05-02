package domain_model;

import Sortmethods.InterfaceComparator;

public class Member implements InterfaceComparator {

    // ********************* Attributes *************************//

    private String name;
    private int age;
    private boolean isActive;
    private MemberAccount account;

    // ***************** Constructor *********************************************** ///
    public Member(String name, int age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.account = new MemberAccount(this);
        //method to add dues upon creation of a member
        this.account.addDues();
    }

    /// ************************* Getter methods **********************************////

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public MemberAccount getMemberAccount() {
        return account;
    }

    public boolean isActive() {
        return isActive;
    }

    /// ************************* Setter methods **********************************////

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    /// ******************** Methods to save and toString ***************************////

    // -- Helper methods to get string output -- //
    @Override
    public String toString(){
        String total = "";
        total = "Navn: " + name + "\nAlder: " + age + "\n";
        if(isActive){
            total += "Medlemskab: Aktiv";
        } else {
            total += "Medlemskab: Passiv";
        }
        return total;
    }

    // -- Helper methods to save file -- //
    public String saveFormat() {
        return name + "," + age + "," + isActive;
    }


    /// ************************* Compare methods **********************************////
    @Override
    public int getSortTime() {
        return 0;
    }
    @Override
    public String getSortDiscipline(){
        return "xxxx";
    }
}
