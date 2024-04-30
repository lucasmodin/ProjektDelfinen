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
    String filePath = "DelfyDatabse.csv";
    Scanner sc;

    public FileHandler(){
    }

    public void saveDatabase(MemberCollection collection){

        try{
            FileWriter fileWriter = new FileWriter(filePath);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Member member : XXX){
                printWriter.println("");
                printWriter.print(member.saveFormat()):
            }
            printWriter.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

    }


    public ArrayList<Member> loadDatabase() {
        ArrayList<Member> loadedDatabase = new ArrayList<>();
        sc = null;
        try {
            sc = new Scanner(new File (filePath), StandardCharsets.UTF_8);
            sc.nextLine();
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] attributes = line.split(",");
            Member memberData = null;
            CompetitionMember competitionMemberData = null;

            if (attributes[?] != null){
                competitionMemberData = new CompetitionMember(
                        attributes[0],
                        Integer.parseInt(attributes[1]),
                        Boolean.parseBoolean(attributes[2]),
                        Double.parseDouble(attributes[3]),
                        attributes[4],
                        attributes[5]);
                loadedDatabase.add(memberData);
            } else {
                memberData = new Member(
                attributes[0],
                Integer.parseInt(attributes[1]),
                Boolean.parseBoolean(attributes[2]));
                loadedDatabase.add(memberData);
            }

        }
        sc.close();
        return loadedDatabase;
    }

}
