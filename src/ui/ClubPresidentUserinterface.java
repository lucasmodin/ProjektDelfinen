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

public class ClubPresidentUserinterface extends UserInterface{
    Scanner input = new Scanner(System.in);

        public ClubPresidentUserinterface(Controller controller){
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
                    case "1" -> searchClubMembers();
                    case "2" -> createMember();
                    case "3" -> getClubMembers();
                    case "4" -> removeClubMembers();
                    case "5" -> editClubMembers();
                    case "6" -> System.out.println("\tLukker ned...");
                    default -> System.out.println("Ugyldigt input! \nVenligst indtast et tal mellem 1 og 6 for at tilgå et menupunkt \n");
                }
            }
            getController().saveDatabse();
        }
        @Override
        public void printMenu()  {
            System.out.println("Ⓒ" + "\tHoved Menu: (Club Præsident)");
            System.out.println("\uD83D\uDD0D" + "\t1. Søg efter en bestemt bruger");
            System.out.println("\uD83D\uDC64" + "\t2. Opret bruger i systemet");
            System.out.println("\uD83D\uDC65" + "\t3. Se liste over alle bruger i systemet");
            System.out.println("✀" + "\t4. Slet bruger i systemet");
            System.out.println("✎" + "\t5. redigere i bruger data");
            System.out.println("🚪" + "\t6. Luk programmet ned");
        }
    }

