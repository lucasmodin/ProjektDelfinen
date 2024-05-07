import domain_model.Club;
import domain_model.Member;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClubTest {

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








}
