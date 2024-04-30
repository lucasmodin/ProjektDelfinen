package domain_model;

public class Member {

    // ********************* Attributes *************************//

    private String name;
    private int age;
    private boolean isActive;

    public Member(String name, int age, boolean isActive) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //changed!!!
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

    public String saveFormat() {
        return name + "," + age + "," + isActive;
    }
}
