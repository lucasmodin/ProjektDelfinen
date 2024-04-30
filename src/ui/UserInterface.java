package ui;

import domain_model.CompetitionMember;
import domain_model.Controller;
import domain_model.Member;

import java.util.Scanner;

//changes!!!
public class UserInterface {
    Scanner input = new Scanner(System.in);
    private Controller controller;

    public UserInterface(){
        this.controller = new Controller();

    }



    //changes!!!
    public void menu() throws Exception {

        controller.loadDatabase();


        String userChoice = "";
        printLogo();
        while (!userChoice.equals("10")){
            printMenu();
            userChoice = input.nextLine();

            switch (userChoice){
                case "1" -> searchClubMembers();
                case "2" -> createMember();
                case "3" -> getClubMembers();
                case "4" -> removeClubMembers();
                case "5" -> editClubMembers();
                case "6" -> System.out.println("Mangler");
                case "7" -> System.out.println("Mangler");
                case "8" -> System.out.println(controller.overViewOfCompetitionMembers());
                case "9" -> System.out.println("Mangler");
                case "10" -> System.out.println("\tLukker ned...");
            }

        }

        controller.saveDatabse();
    }

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
            Thread.sleep(2);
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
        System.out.println("\t6. Se bruger der mangler at betale(restance)");
        System.out.println("\t7. Få næste års økonomi budget");
        System.out.println("\t8. Få en oversigt af konkurrence svømmer");
        System.out.println("\t9. Se top 5 bedste svømmer i klubben");
        System.out.println("\t10. Luk programmet ned");
    }

    //changes!!!
    public void createMember(){
        System.out.println("Vil du oprette en motionist(m) eller en konkurrence svømmer(k)?");
        String userChoice = input.nextLine();
        if(userChoice.equals("m")){
            System.out.print("Indtast navn på bruger: ");
            String name = input.nextLine();
            System.out.print("Indtast alder på bruger: ");
            int age = input.nextInt();
            System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
            boolean isActive = input.next().equalsIgnoreCase("a");
            controller.addMember(new Member(name, age, isActive));
            input.nextLine();
        } else if (userChoice.equals("k")){
            System.out.print("Indtast navn på bruger: ");
            String name = input.nextLine();
            System.out.print("Indtast alder på bruger: ");
            int age = input.nextInt();
            System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
            boolean isActive = input.next().equalsIgnoreCase("a");
            System.out.print("Bedste svømme resultat: ");
            double tid = input.nextDouble();
            System.out.print("Svømmerens diciplin: ");
            input.nextLine();
            String diciplin = input.nextLine();
            System.out.print("Datoen for resultatet: ");
            String dato = input.nextLine();
            controller.addMember(new CompetitionMember(name, age,isActive, tid, diciplin, dato));
            input.nextLine();
        } else {
            System.out.println("forkert input");
        }
    }

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
                userChoice = input.nextLine();
                switch (userChoice) {
                    case "1" -> member.setName(input.nextLine());
                    case "2" -> member.setAge(input.nextInt());
                    case "3" -> member.setActive(input.next().equalsIgnoreCase("a"));
                    case "4" -> ((CompetitionMember) member).setTime(input.nextDouble());
                    case "5" -> ((CompetitionMember) member).setDiscipline(input.nextLine());
                    case "6" -> ((CompetitionMember) member).setDate(input.nextLine());
                    case "7" -> {
                        System.out.print("Indtast navn på bruger: ");
                        member.setName(input.nextLine());
                        System.out.print("Indtast alder på bruger: ");
                        member.setAge(input.nextInt());
                        System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p): ");
                        member.setActive(input.next().equalsIgnoreCase("a"));
                        System.out.print("Bedste svømme resultat: ");
                        ((CompetitionMember) member).setTime(input.nextDouble());
                        System.out.print("Svømmerens diciplin: ");
                        input.nextLine();
                        ((CompetitionMember) member).setDiscipline(input.nextLine());
                        System.out.print("Datoen for resultatet: ");
                        ((CompetitionMember) member).setDate(input.nextLine());
                    }
                }
            } else {
                System.out.println("Vælg hvad du vil ændre");
                System.out.println("1. Navn");
                System.out.println("2. Alder");
                System.out.println("3. Aktiv/Passiv");
                System.out.println("4. Ændre alt");
                userChoice = input.nextLine();
                switch (userChoice) {
                    case "1" -> member.setName(input.nextLine());
                    case "2" -> member.setAge(input.nextInt());
                    case "3" -> member.setActive(input.next().equalsIgnoreCase("a"));
                    case "4" -> {
                        System.out.print("Indtast navn på bruger:");
                        member.setName(input.nextLine());
                        System.out.print("Indtast alder på bruger:");
                        member.setAge(input.nextInt());
                        System.out.print("Indtast om bruger vil være aktiv eller passiv(a/p):");
                        member.setActive(input.next().equalsIgnoreCase("a"));
                    }
                }
            }
        }

    }



}
