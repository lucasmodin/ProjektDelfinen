package ui;

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
                case "5" -> System.out.println("Mangler");
                case "6" -> System.out.println("Mangler");
                case "7" -> System.out.println("Mangler");
                case "8" -> System.out.println("Mangler");
                case "9" -> System.out.println("Mangler");
                case "10" -> System.out.println("Lukker ned...");
            }

        }
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
        System.out.println("Indtast navn på bruger:");
        String name = input.nextLine();
        System.out.println("Indtast alder på bruger:");
        int age = input.nextInt();
        System.out.println("Indtast om bruger vil være aktiv eller passiv(a/p):");
        boolean isActive = input.next().equalsIgnoreCase("a");
        controller.addMember(new Member(name, age, isActive));
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
        System.out.println("Indtast navnet på bruger du ønseker at se");
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
}
