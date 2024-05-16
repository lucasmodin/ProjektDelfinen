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

public class TreasureUserinterface extends UserInterface{
    Scanner input = new Scanner(System.in);

    public TreasureUserinterface(Controller controller){
        super(controller);
    }
    @Override
    public void menu() throws Exception {


        getController().loadDatabase();
        getController().sortClubMembers();


        String userChoice = "";
        printLogo();
        while (!userChoice.equals("5")){
            printMenu();
            userChoice = input.nextLine();

            switch (userChoice){
                case "1" -> searchClubMembers();
                case "2" -> getClubMembers();
                case "3" -> showMembersWhoHasntPaid();
                case "4" -> showTotalIncomeForYear();
                case "5" -> System.out.println("\tLukker ned...");
                default -> System.out.println("Ugyldigt input! \nVenligst indtast et tal mellem 1 og 5 for at tilgå et menupunkt \n");
            }
        }
        getController().saveDatabse();
    }
    @Override
    public void printMenu()  {
        System.out.println("\tHoved Menu: (Kasserer)");
        System.out.println("\t1. Søg efter en bestemt bruger");
        System.out.println("\t2. Se liste over alle bruger i systemet");
        System.out.println("\t3. Se restance og indberet betalinger");
        System.out.println("\t4. Få næste års økonomi budget");
        System.out.println("\t5. Luk programmet ned");
    }
}
