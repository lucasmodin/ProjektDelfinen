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
//changes!!!
public class UserInterface {

    ///********** Generate instance of club *********************************///
    Scanner input = new Scanner(System.in);
    private Controller controller;

    public UserInterface(){
        this.controller = new Controller();
    }


    ///********** program master this keeps program in a loop and calls methods *************************///
    //changes!!!
    public void menu() throws Exception {

        controller.loadDatabase();
        controller.sortClubMembers();


        String userChoice = "";
        printLogo();
        while (!userChoice.equals("11")){
            printMenu();
            userChoice = input.nextLine();

            switch (userChoice){
                case "1" -> searchClubMembers();
                case "2" -> createMember();
                // TODO (3) we need to expand to be able to sort by junior and senior members
                case "3" -> getClubMembers();
                case "4" -> removeClubMembers();
                case "5" -> editClubMembers();
                case "6" -> showMembersWhoHasntPaid(); //implement payment for members who haven't paid
                case "7" -> showTotalIncomeForYear();
                // TODO  (8) we need to sort after Junior/Senior members and by discipline
                case "8" -> overViewofAllCompetitors();
                // TODO (9) we need the trainer to be able to pick out top 5 competive swimmers for competitions
                // TODO (9) we need to sort after junior and senior because they are in seperated competitions
                case "9" -> top5Discipline();
                // TODO (10) we need to show competitive members who have been to a competition and see there results
                case "10" -> System.out.println("udvid til at vise stævne, placering og tid til konkurrence svømmer");
                case "11" -> System.out.println("\tLukker ned...");
                default -> System.out.println("Ugyldigt input! \nVenligst indtast et tal mellem 1 og 10 for at tilgå et menupunkt \n");
            }
        }
        controller.saveDatabse();
    }

    ///********** Helper methods and print out practical information  *******************************///
    //changes!!!
    public void printLogo() throws Exception{
        String logo = "\n***--------------------------------------------------------------------------------------------------***" +
                "\n" +
                "|   _____                                     _           _       _____       _  __ _                  |\n" +
                "|  / ____|                                   | |         | |     |  __ \\     | |/ _(_)                 |\n" +
                "| | (_____   ______  _ __ ___  _ __ ___   ___| |__   __ _| |     | |  | | ___| | |_ _ _ __   ___ _ __  |\n" +
                "|  \\___ \\ \\ / / _//\\| '_ ` _ \\| '_ ` _ \\ / _ \\ '_ \\ / _` | |     | |  | |/ _ \\ |  _| | '_ \\ / _ \\ '_ \\ |\n" +
                "|  ____) \\ V / (//) | | | | | | | | | | |  __/ | | | (_| | |     | |__| |  __/ | | | | | | |  __/ | | ||\n" +
                "| |_____/ \\_/ \\//__/|_| |_| |_|_| |_| |_|\\___|_| |_|\\__,_|_|     |_____/ \\___|_|_| |_|_| |_|\\___|_| |_||\n" +
                "|                                                                                                      |\n" +
                "***--------------------------------------------------------------------------------------------------***\n"
                + "\t\t\t\t\t\t\tVelkommen til programmet: StamDelfy\n\t\t\t\t\tFind relevante info omkring bruger stamdata og økonomi.\n";
        for(int i = 0; i < logo.length(); i++){
            System.out.print(logo.charAt(i));
            Thread.sleep(1);
        }
        System.out.println("\t\t\t\t\t\t\tTryk enter for at forsætte...");
        input.nextLine();
    }

    //changes!!!
    public void printMenu()  {
        System.out.println("\tHoved Menu:");
        System.out.println("\t1. Søg efter en bestemt bruger");
        System.out.println("\t2. Opret bruger i systemet");
        System.out.println("\t3. Se liste over alle bruger i systemet");
        System.out.println("\t4. Slet bruger i systemet");
        System.out.println("\t5. redigere i bruger data");
        System.out.println("\t6. Se restance og indberet betalinger");
        System.out.println("\t7. Få næste års økonomi budget");
        System.out.println("\t8. Få en oversigt af konkurrence svømmer");
        System.out.println("\t9. Se top 5 bedste svømmer i klubben");
        System.out.println("\t10. Vis svømmer som har været til konkurrence");
        System.out.println("\t11. Luk programmet ned");
    }


    ///********** Formanden - Methods to creat Member ****************************************************///
    public void createMember(){
        System.out.println("Vil du oprette en motionist(m) eller en konkurrence svømmer(k)?");
        String userChoice = input.nextLine();
        if(userChoice.equals("m")){
            System.out.print("Indtast navn på bruger: ");
            String name = input.nextLine();
            int age = readIntWithValidation("Indtast alder på bruger: ", 0, 100);
            System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
            boolean isActive = input.next().equalsIgnoreCase("a");
            controller.addMember(new Member(name, age, isActive));
            input.nextLine();
        } else if (userChoice.equals("k")){
            System.out.print("Indtast navn på bruger: ");
            String name = input.nextLine();
            int age = readIntWithValidation("Indtast alder på bruger: ", 0, 100);
            System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
            boolean isActive = input.next().equalsIgnoreCase("a");
            double tid = readDoubleWithValidation("Bedste svømme resultat: ", 0, 1000);
            System.out.print("Vælg svømmerens disciplin:");
            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning ");
            int index = readIntWithValidation("Indtast et hel-tal mellem 1 og 4: ", 1, 4);
            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
            String disciplin = enumDis.getDiscipline();
            input.nextLine(); // For at fjerne Scannerbug
            System.out.print("Datoen for resultatet(ddMMyyyy): ");
            String dato = dateValidation(input.nextLine());
            CompetitionMember newMember = new CompetitionMember(name, age,isActive, tid, disciplin, dato);
            System.out.println("Har " + name + " deltaget i en konkurrence ?");
            System.out.println("1. Ja \n 2. Nej");
            int choice = readIntWithValidation("Indtast et hel-tal mellem 1 og 2", 1, 2);
            setCompetitionChoice(choice, newMember);
            controller.addMember(newMember);
            hasMemberPaid(newMember);
            input.nextLine();
        } else {
            System.out.println("Ugyldigt input! indtast 'm' eller 'k' for at oprette en af de to typer medlemmer");
        }
    }

    ///********** Formanden - Methods to handle members ****************************************************///
    //changes!!!
    public void getClubMembers(){
        if(controller.getClubMembers().getClubMembers().isEmpty()){
            System.out.println("Listen er tom. Der er ingen bruger i systemet.");
        } else {
            System.out.print("Her er liste over bruger i systemet:");
            System.out.print(controller.getClubMembers());
        }
        input.nextLine();
    }

    //Method to format date/time to LocalDate object
    public LocalDate parseDate(String date){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            return LocalDate.parse(date, formatter);
        } catch (Exception e){
            return null;
        }
    }

    //Method to format date to dd-MM-yyyy and for secure proper userinput for correct format.
    public String dateValidation(String userChoice) {
        boolean flagDown = false;
        String dateToParse = "";
        while (!flagDown) {
            LocalDate date = parseDate(userChoice);
            if (date != null) {
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dateToParse = date.format(outputFormatter);
                flagDown = true;
            } else {
                System.out.println("invalid input");
                System.out.print("Indtast en valid dato for resultatet(ddMMyyyy): ");
                userChoice = input.nextLine();
            }
        }
        return dateToParse;
    }

    //changes!!!
    public void searchClubMembers(){
        System.out.println("Indtast navnet på bruger du ønsker at se");
        String userCoice = input.nextLine();
        if(controller.searchClubMembers(userCoice).isEmpty()){
            System.out.println("Der findes ingen bruger med det navn");
        } else {
            System.out.println(controller.searchClubMembers(userCoice));
        }
        input.nextLine();
    }

    //changes!!!
    public void removeClubMembers(){
        System.out.println("Angiv hvilken bruger du vil fjerne i systemet:");
        String userChoice = input.nextLine();
        if (controller.searchClubMembers(userChoice).isEmpty()){
            System.out.println("Der findes ingen bruger med det navn");
        } else {
            controller.removeMember(controller.findMember(userChoice));
        }
        input.nextLine();
    }

    //changes!!!
    public void editClubMembers(){
        System.out.println("Indtast navnet på bruger du vil redigere:");
        String userChoice = input.nextLine();
        if(controller.searchClubMembers(userChoice).isEmpty()){
            System.out.println("Der findes ingen bruger med det navn");
        } else {
            Member member = controller.findMember(userChoice);
            System.out.println("Du har valgt: " + member.getName());
            if(member instanceof CompetitionMember){
                System.out.println("Vælg hvad du vil ændre");
                System.out.println("1. Navn");
                System.out.println("2. Alder");
                System.out.println("3. Aktiv/Passiv");
                System.out.println("4. Tid");
                System.out.println("5. Diciplin");
                System.out.println("6. Dato");
                System.out.println("7. Ændre alt");
                System.out.println("8. Ændre til motionist");
                System.out.println("9. Gå tilbage til hovedmenuen");
                userChoice = input.nextLine();
                switch (userChoice) {
                    case "1" -> member.setName(input.nextLine());
                    case "2" -> member.setAge(readIntWithValidation("indtast alder ", 0, 100));
                    case "3" -> member.setActive(input.next().equalsIgnoreCase("a"));
                    case "4" -> ((CompetitionMember) member).setTime(readDoubleWithValidation("Bedste svømme resultat: ",0,1000));
                    case "5" -> ((CompetitionMember) member).setDiscipline(input.nextLine());
                    case "6" -> ((CompetitionMember) member).setDate(input.nextLine());
                    case "7" -> {
                        System.out.print("Indtast navn på bruger: ");
                        member.setName(input.nextLine());
                        System.out.print("Indtast alder på bruger: ");
                        member.setAge(readIntWithValidation("Indtast alder på bruger: ",0, 100));
                        System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
                        member.setActive(input.next().equalsIgnoreCase("a"));
                        ((CompetitionMember) member).setTime(readDoubleWithValidation("Bedste svømme resultat: ", 0, 1000));
                        System.out.print("Svømmerens diciplin: ");
                        input.nextLine();
                        ((CompetitionMember) member).setDiscipline(input.nextLine());
                        System.out.print("Datoen for resultatet: ");
                        ((CompetitionMember) member).setDate(input.nextLine());
                    }
                    case "8" -> {
                        System.out.println("Vil du gerne ændre bruger til motionist. Tryk 1");
                        userChoice = input.nextLine();
                        if(userChoice.equals("1")){
                            Member conversionToCreative = new Member(member.getName(), member.getAge(), member.isActive());
                            controller.addMember(conversionToCreative);
                            controller.removeMember(member);

                        } else {
                            System.out.println("Ugyldig input");
                        }
                    }
                    case "9" -> System.out.println("vil du tilbage til hovedmenuen");
                    default -> System.out.println("ugyldigt input - indtast venligst et hel-tal mellem 1 og 6");
                }
            } else {
                System.out.println("Vælg hvad du vil ændre");
                System.out.println("1. Navn");
                System.out.println("2. Alder");
                System.out.println("3. Aktiv/Passiv");
                System.out.println("4. Ændre alt");
                System.out.println("5. Ændre til kompetance svømmer");
                System.out.println("6. Gå tilbage til hovedmenuen");
                userChoice = input.nextLine();
                switch (userChoice) {
                    case "1" -> member.setName(input.nextLine());
                    case "2" -> member.setAge(readIntWithValidation("indtast alder ", 0, 100));
                    case "3" -> member.setActive(input.next().equalsIgnoreCase("a"));
                    case "4" -> {
                        System.out.print("Indtast navn på bruger:");
                        member.setName(input.nextLine());
                        member.setAge(readIntWithValidation("indtast alder ", 0, 100));
                        System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p):");
                        member.setActive(input.next().equalsIgnoreCase("a"));
                    }
                    case "5" -> {
                        System.out.println("Vil du gerne ændre til kompetance svømmer. Tryk 1");
                        userChoice = input.nextLine();
                        if(userChoice.equals("1")){
                            double tid = readDoubleWithValidation("Bedste svømme resultat: ", 0, 1000);
                            System.out.print("Vælg svømmerens disciplin:");
                            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning ");
                            int index = readIntWithValidation("Indtast et hel-tal mellem 1 og 4: ", 1, 4);
                            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
                            String disciplin = enumDis.getDiscipline();
                            input.nextLine(); // For at fjerne Scannerbug
                            System.out.print("Datoen for resultatet(ddMMyyyy): ");
                            String dato = dateValidation(input.nextLine());
                            CompetitionMember conversionToCompetitive = new CompetitionMember(member.getName(), member.getAge(),
                                    member.isActive(), tid, disciplin, dato);
                            controller.addMember(conversionToCompetitive);
                            controller.removeMember(member);
                        } else {
                            System.out.println("Ugyldig input");
                        }
                    }
                    case "6" -> System.out.println("vil du tilbage til hovedmenuen");
                    default -> System.out.println("ugyldigt input - indtast venligst et hel-tal mellem 1 og 6");

                }
            }
        }

    }

    // *** Helper metode til at sætte konkurrence tider under formanden *** //
    public void setCompetitionChoice(int input, CompetitionMember newMember){
        if (input == 1){
            setCompetitionInformation(newMember);
        } else {
            setDefaultCompetitionInformation(newMember);
        }
    }

    ///********** Kasseren - Methods to handle MemberPayment account **************************************///

    public void showMembersWhoHasntPaid() {
        System.out.println("1. Vis oversigt over medlemmer der er i restance");
        System.out.println("2. Indberet indbetaling for medlem");
        int index = readIntWithValidation("Indtast et hel-tal mellem 1 og 2", 1, 2);

        switch (index) {
            case 1 ->
                    System.out.println("Oversigt over medlemmer der ikke har betalt:  \n" + controller.displayMembersWhoOwe());
            case 2 -> {
                System.out.println("Indtast navnet på brugeren der har indbetalt: ");
                String userChoice = input.nextLine();
                if (controller.searchClubMembers(userChoice).isEmpty()) {
                    System.out.println("Der findes ingen bruger med det navn");
                } else {
                    Member member = controller.findMember(userChoice);
                    System.out.println("Du har valgt: " + member.getName());
                    System.out.println("Indtast beløbet, som brugeren har indbetalt: ");
                    double amount = readDoubleWithValidation("(tal mellem 1 og 100.000)",1, 100000);
                    member.getMemberAccount().addPayment(amount);
                    System.out.println("Du har nu indberettet en indbetaling på " + amount + " for medlemmet " + member.getName());
                    input.nextLine();
                }

            }

        }
    }

    public void showTotalIncomeForYear() {
        System.out.println("Oversigt over forventet indkomst for næste år: \n");
        System.out.println(controller.calculateTotalExpectedIncome() + " kr.");
    }


    ///********** Træneren - Methods to handle Sports team and competitors *********************************///

    public void overViewofAllCompetitors () {
        System.out.println("Hvilken aldersgrupe vil du se på?");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println("3. Alle aldersgrupper");
        String userChoice = input.nextLine();
        if(userChoice.equals("1")){
            System.out.println("Vælg hvilken svømme disciplin, du gerne vil se:");
            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning \n 5. Alle");
            int index = readIntWithValidation("indtast et hel tal mellem 1 og 5 ", 1, 5);
            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
            String chosendisciplin = enumDis.getDiscipline();
            System.out.println("Her er listen over alle Juniormedlemmer der konkurrere");
            System.out.println(controller.getClubMembers().getAgeGrups(userChoice, chosendisciplin));
        } else if(userChoice.equals("2")){
            System.out.println("Vælg hvilken svømme disciplin, du gerne vil se:");
            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning \n 5. Alle");
            int index = readIntWithValidation("indtast et hel tal mellem 1 og 5 ", 1, 5);
            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
            String chosendisciplin = enumDis.getDiscipline();
            System.out.println("Her er listen over alle Seniormedlemmer der konkurrere");
            System.out.println(controller.getClubMembers().getAgeGrups(userChoice, chosendisciplin));
        } else if(userChoice.equals("3")){
            System.out.println("Her er listen over alle medlemmer der konkurrere");
            System.out.println(controller.getClubMembers().getAgeGrups(userChoice, null));
        } else {
            System.out.println("Ugyldig input");
        }
        input.nextLine();
    }

    public void top5Discipline(){
        System.out.println("Hvilken aldersgrupe vil du se på?");
        System.out.println("1. Junior");
        System.out.println("2. Senior");
        System.out.println("3. Alle aldersgrupper");
        String userChoice = input.nextLine();
        if(userChoice.equals("1")){
            System.out.println("Vælg hvilken svømme disciplin, du gerne vil se top 5:");
            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning \n 5. Alle");
            int index = readIntWithValidation("indtast et hel tal mellem 1 og 5 ", 1, 5);
            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
            String chosendisciplin = enumDis.getDiscipline();
            System.out.println("Her er top 5 over alle Juniormedlemmer der konkurrere");
            System.out.println(controller.top5Discipline(chosendisciplin, userChoice));
        } else if(userChoice.equals("2")){
            System.out.println("Vælg hvilken svømme disciplin, du gerne vil se top 5:");
            System.out.println("\n 1. Bryst Svømning \n 2. Butterfly \n 3. Crawl \n 4. Rygsvømning \n 5. Alle");
            int index = readIntWithValidation("indtast et hel tal mellem 1 og 5 ", 1, 5);
            SwimmingDiscipline enumDis = SwimmingDiscipline.values()[index-1];
            String chosendisciplin = enumDis.getDiscipline();
            System.out.println("Her er top 5 over alle Seniormedlemmer der konkurrere");
            System.out.println(controller.top5Discipline(chosendisciplin, userChoice));
        } else if(userChoice.equals("3")){
            System.out.println("Her er top 5 over alle medlemmer der konkurrere");
            System.out.println(controller.top5Discipline(null, userChoice));
        } else {
            System.out.println("Ugyldig input");
        }
        input.nextLine();

    }

    public void setCompetitionInformation(CompetitionMember newMember){
        System.out.print("Navnet på Konkurrence: ");
        String competitionName = input.nextLine();
        System.out.print("Placering i konkurrence: ");
        int placement = readIntWithValidation("Indtast et heltal mellem 1 og 5", 1, 5);
        System.out.print("Hvad var medlemmets tid?: ");
        double competitionTime = readDoubleWithValidation("Indtast et tal med komma seperation", 0.1, 100.2);
        newMember.setCompetitionName(competitionName);
        newMember.setPlacement(placement);
        newMember.setCompetitionTime(competitionTime);
    }

    public void setDefaultCompetitionInformation(CompetitionMember newMember){
        newMember.setCompetitionName("n/a");
        newMember.setPlacement(0);
        newMember.setCompetitionTime(0);
    }

    //****************** testing ************************************* //

    //to catch InputMismatch exception - takes min & max int parameters as boundaries.
    private int readIntWithValidation (String prompt, int min, int max) {
        int userInput = 0;
        boolean flagDown = false;

        while(!flagDown) {
            try {
                System.out.println(prompt);
                userInput = input.nextInt();
                input.nextLine();

                if (userInput <= max && userInput >= min) {
                    flagDown = true;
                } else {
                    System.out.println("Venligst indtast et tal mellem " + min + " og " + max);
                }

            } catch (InputMismatchException ime) {
                System.out.println("Ugyldigt input! Indtast venligst et hel-tal mellem " + min + " og " + max);
                input.nextLine();
            }
        } return userInput;
    }

    //to catch InputMismatch exception - takes min & max double parameters as boundaries.

    private double readDoubleWithValidation (String prompt, double min, double max) {
        double userInput = 0;
        boolean flagDown = false;

        while(!flagDown) {
            try {
                System.out.println(prompt);
                userInput = input.nextDouble();


                if (userInput <= max && userInput >= min) {
                    flagDown = true;
                } else {
                    System.out.println("Venligst indtast et tal mellem " + min + " og " + max);
                }

            } catch (InputMismatchException ime) {
                System.out.println("Ugyldigt input! Indtast venligst et hel-tal mellem " + min + " og " + max);
                input.nextLine();
            }
        } return userInput;
    }


    // *** den her ligger under formanden *** //
    public void hasMemberPaid(Member member){
        System.out.println("Har medlemmet lavet en indbetaling ?");
        System.out.println("Ja / nej");
        String userChoice = input.nextLine();

        if (userChoice.equalsIgnoreCase("ja")) {
            member.getMemberAccount().setBalance(0);
        }
    }

    // controller.sortingCompetitionMemberOnDiscipline();
    // controller.sortingCompetitionMember();








}
