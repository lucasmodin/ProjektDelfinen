import domain_model.Club;
import domain_model.Member;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;




class ClubTest {



    @Test
    void addMember() {

        //Act
        Club delfinen = new Club();
        Member member1 = new Member("Bruce", 50, true);

        //Arrange
        delfinen.addMember(member1);

        int actualSize = 1;
        int expectedSize = delfinen.getClubMembers().size();


        //Assert
        assertEquals(actualSize, expectedSize);

    }

    @Test
    void removeMember() {

        //Act
        Club delfinen = new Club();
        Member member1 = new Member("Bruce", 50, true);
        Member member2 = new Member("Hans", 40, true);

        //Arrange
        delfinen.addMember(member1);
        delfinen.addMember(member2);

        delfinen.removeMember(member2);

        int actualSize = delfinen.getClubMembers().size();
        int expectedSize = 1;

        //Assert
        assertEquals(actualSize, expectedSize);

    }










}
