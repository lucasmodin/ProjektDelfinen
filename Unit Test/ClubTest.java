import domain_model.Club;
import domain_model.CompetitionMember;
import domain_model.Controller;
import domain_model.Member;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;


class ClubTest {

    // mock file

    private Controller controller;

    public ClubTest(){
        this.controller = new Controller();
    }


    ///********** Test af Formandens funktionaliter *********************************///
    @Test
    void addMember() {

        //Arrange
        Club delfinen = new Club();
        Member member1 = new Member("Bruce", 50, true);

        delfinen.addMember(member1);

        //Act
        int actualSize = 1;
        int expectedSize = delfinen.getClubMembers().size();


        //Assert
        assertEquals(actualSize, expectedSize);

    }

    @Test
    void removeMember() {

        //Arrange
        Club delfinen = new Club();
        Member member1 = new Member("Bruce", 50, true);
        Member member2 = new Member("Hans", 40, true);

        //Act
        delfinen.addMember(member1);
        delfinen.addMember(member2);

        delfinen.removeMember(member2);

        int actualSize = delfinen.getClubMembers().size();
        int expectedSize = 1;

        //Assert
        assertEquals(actualSize, expectedSize);

    }

    @Test
    void searchMember(){
        //Arrange
        Club club = new Club();
        Member member1 = new Member("Hans", 25, true);
        club.addMember(member1);

        //Act
        String actualValue = club.searchMembers("Hans");

        //Assert
        Member expectedObject = new Member("Hans", 25, true);
        String expectedValue = "\n" + expectedObject.toString() + "\n";

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    void editMember(){
        //Arrange
        Club club = new Club();
        Member member1 = new Member("Bob", 90, false);
        club.addMember(member1);

        //Act
        club.findMember("bob").setName("gert");
        String actualValue = club.findMember("gert").getName();

        //Assert
        Member expectedObject = new Member("gert", 25, true);
        String expectedValue = expectedObject.getName();
        Assertions.assertEquals(expectedValue, actualValue);

    }

    ///********** test af file handler *********************************///

    @Test
    void saveMembers(){

        String filePath = "DatabaseTest.csv";

        File file = new File(filePath);


        // Arrange
        ArrayList<Member> memberSaveTest = new ArrayList<>();

        Member member1 = new Member("Branco",30,true);
        Member member2 = new CompetitionMember("Hillary Clinton",78,true,31.7,"Butterfly","03-05-2024");
        Member member3 = new CompetitionMember("Hans Jensen",19,true,92.5,"Crawl","12-05-2023");
        Member member4 = new Member("Luna",32,true);

        memberSaveTest.add(member1);
        memberSaveTest.add(member2);
        memberSaveTest.add(member3);
        memberSaveTest.add(member4);

        // Act
        saveDatabase(filePath, memberSaveTest);

        assert(file.exists());

        controller.addMember(member1);
        controller.addMember(member2);
        controller.addMember(member3);
        controller.addMember(member4);

        saveDatabase(filePath,controller.getClubMembers().getClubMembers());

        ArrayList<Member> liveMethod = loadDatabase(filePath);


        // Assert
        for (int i = 0; i < memberSaveTest.size(); i++){
            assertEquals(0, memberSaveTest.get(i).getName().compareTo(liveMethod.get(i).getName()));
        }

        // delete and close test.

        file.delete();
    }

    /// *** Helper to test database ***///
    public void saveDatabase(String filePath, ArrayList<Member> memberSaveTest) {

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Member member : memberSaveTest) {
                printWriter.println("");
                if (member instanceof CompetitionMember) {
                    printWriter.print(member.saveFormat());
                } else {
                    printWriter.print(member.saveFormat());
                }
            }

            printWriter.close();

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Member> loadDatabase(String filePath) {
        ArrayList<Member> loadedDatabase = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(filePath), StandardCharsets.UTF_8);
            sc.nextLine();                                                  //Den her gør man springer først linje over.
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] attributes = line.split(",");
            Member memberData = null;
            CompetitionMember competitionMemberData = null;

            if (attributes.length < 3) {
                System.err.println("Malformed line");
                continue;
            }

            if (attributes.length > 4){
                competitionMemberData = new CompetitionMember(
                        attributes[0],
                        Integer.parseInt(attributes[1]),
                        Boolean.parseBoolean(attributes[2]),
                        Double.parseDouble(attributes[3]),
                        attributes[4],
                        attributes[5]);

                competitionMemberData.getMemberAccount().setBalance(Double.parseDouble(attributes[6]));
                //This is the method for setting the balance (change the balance attribute)

                loadedDatabase.add(competitionMemberData);
                //set balance for member on attribute 6
            } else {
                memberData = new Member(
                        attributes[0],
                        Integer.parseInt(attributes[1]),
                        Boolean.parseBoolean(attributes[2]));
                memberData.getMemberAccount().setBalance(Double.parseDouble(attributes[3]));

                loadedDatabase.add(memberData);
            }

        }
        sc.close();
        return loadedDatabase;
    }


    ///********** test af sort Members *********************************///

    @Test
    void sortMember(){

        // Arrange

        Member member1 = new Member("Branco",30,true);
        Member member2 = new CompetitionMember("Hillary Clinton",78,true,31.7,"Crawl","03-05-2024");
        Member member3 = new CompetitionMember("Hans Jensen",19,true,92.5,"Crawl","12-05-2023");
        Member member4 = new Member("Luna",32,true);

        controller.addMember(member1);
        controller.addMember(member2);
        controller.addMember(member3);
        controller.addMember(member4);

        // based on time and discipline
        controller.sortingCompetitionMember();

        Member checkMember = controller.getClubMembers().findMember("Hillary Clinton");

        CompetitionMember checkMember2 = (CompetitionMember) checkMember;

        int timeCheck = (int) checkMember2.getTime();

        assertEquals(31,timeCheck);

        String timeSortCheck = controller.getClubMembers().getClubMembers().get(2).getName();

        assertEquals("Hillary Clinton", timeSortCheck);

    }

    @Test
    void addDues() {
        // Arrange
        //No need to add dues, as the member constructor automatically adds dues
        // based on the member age, and if they're active or not
        Member member = new Member("John Malkovich", 67, true);
        // Act
        Member member2 = new Member("John Travolta", 68, true);

        double memberBalance = member.getMemberAccount().getBalance();
        double expectedBalance = 1600 * 0.75;

        double memberTwoBalance = member2.getMemberAccount().getBalance();

        //Testing the addDues method by substracting the dues added in the constructor, then using the method to add them again
        double memberTwoBalanceSubstracted = memberTwoBalance - 1200;
        member2.getMemberAccount().addDues();

        // Assert
        assertEquals(memberBalance,expectedBalance);
        assertEquals(memberTwoBalance, expectedBalance);
        assertNotEquals(memberTwoBalance, memberTwoBalanceSubstracted);

    }

    @Test
    void addPayment() {
        //Arrange
        Member member = new Member("John Malkovich", 67, true);
        //Act
        double memberBalanceBeforePayment = member.getMemberAccount().getBalance();
        member.getMemberAccount().addPayment(1200);

        double memberBalanceAfterPayment = member.getMemberAccount().getBalance();


        //Assert
        assertNotEquals(memberBalanceBeforePayment, memberBalanceAfterPayment);




    }












}
