package data_source;

import domain_model.*;

import java.io.File;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;


public class FileHandler {

    //****************** ATTRIBUTES **************************************************//
    String filePath = "DelfyDatabse.csv";
    Scanner sc;

    // ***************** Constructor *********************************************** ///
    public FileHandler(){
    }

    /// ******************** Methods to save and toString ***************************////

    // -- Helper methods to get loaded database into system -- //
    public void loadedDatabase(Club club) {
        club.getClubMembers().addAll(loadDatabase());
    }

    public void saveDatabase(Club collection) {

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Member member : collection.getClubMembers()) {
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

    public ArrayList<Member> loadDatabase() {
        ArrayList<Member> loadedDatabase = new ArrayList<>();
        sc = null;
        try {
            sc = new Scanner(new File (filePath), StandardCharsets.UTF_8);
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

                        competitionMemberData.setCompetitionName(attributes[6]);
                        competitionMemberData.setPlacement(Integer.parseInt(attributes[7]));
                        competitionMemberData.setCompetitionTime(Double.parseDouble(attributes[8]));

                        competitionMemberData.getMemberAccount().setBalance(Double.parseDouble(attributes[9]));
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


    //****************** testing ************************************* //

}
