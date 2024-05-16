package ui;

import Sortmethods.SwimmingDiscipline;
import domain_model.CompetitionMember;
import domain_model.Controller;
import domain_model.Member;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TrainerUserinterface extends UserInterface{
    Scanner input = new Scanner(System.in);

    public TrainerUserinterface(Controller controller){
        super(controller);
    }
    @Override
    public void menu() throws Exception {


        getController().loadDatabase();
        getController().sortClubMembers();


        String userChoice = "";
        printLogo();
        while (!userChoice.equals("6")){
            printMenu();
            userChoice = input.nextLine();

            switch (userChoice){
                case "1" -> editClubMembers();
                case "2" -> overViewofAllCompetitors();
                case "3" -> updateCompetitionResults();
                case "4" -> top5Discipline();
                case "5" -> showCompetitorsWhoHasCompeteted();
                case "6" -> System.out.println("\tLukker ned...");
                default -> System.out.println("Ugyldigt input! \nVenligst indtast et tal mellem 1 og 6 for at tilgå et menupunkt \n");
            }
        }
        getController().saveDatabse();
    }
    @Override
    public void printMenu()  {
        System.out.println("\tHoved Menu: (Træner)");
        System.out.println("\t1. redigere i bruger data");
        System.out.println("\t2. Få en oversigt af konkurrence svømmer");
        System.out.println("\t3. Opdater konkurrence informationer for svømmer");
        System.out.println("\t4. Se top 5 bedste svømmer i klubben");
        System.out.println("\t5. Vis svømmer som har været til konkurrence");
        System.out.println("\t6. Luk programmet ned");
    }
}
