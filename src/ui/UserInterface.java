package ui;

import domain_model.Controller;
import domain_model.Member;

import java.util.Scanner;

public class UserInterface {
    Scanner input = new Scanner(System.in);
    private Controller controller;

    public UserInterface(){
        this.controller = new Controller();

    }



    public void menu(){
        String userChoice = "";
        while (!userChoice.equals("7")){
            printMenu();
            userChoice = input.nextLine();

            switch (userChoice){
                case "1" -> searchClubMembers();
                case "2" -> createMember();
                case "3" -> getClubMembers();
                case "4" -> removeClubMembers();
                case "5" -> System.out.println("Lukker ned...");
            }

        }
    }

    public void printMenu(){
        System.out.print("\n***------------------------------------------------------------------------------------------------***" +
                "\n" +
                "   _____                                     _           _       _____       _  __ _                  \n" +
                "  / ____|                                   | |         | |     |  __ \\     | |/ _(_)                 \n" +
                " | (_____   ______  _ __ ___  _ __ ___   ___| |__   __ _| |     | |  | | ___| | |_ _ _ __   ___ _ __  \n" +
                "  \\___ \\ \\ / / _//\\| '_ ` _ \\| '_ ` _ \\ / _ \\ '_ \\ / _` | |     | |  | |/ _ \\ |  _| | '_ \\ / _ \\ '_ \\ \n" +
                "  ____) \\ V / (//) | | | | | | | | | | |  __/ | | | (_| | |     | |__| |  __/ | | | | | | |  __/ | | |\n" +
                " |_____/ \\_/ \\//__/|_| |_| |_|_| |_| |_|\\___|_| |_|\\__,_|_|     |_____/ \\___|_|_| |_|_| |_|\\___|_| |_|\n" +
                "                                                                                                      \n" +
                "***------------------------------------------------------------------------------------------------***                                                                                                      \n");
        System.out.println("\t\t\tVelkommen til programmet: StamDelfy\n\tFind relevante info omkring bruger stamdata og økonomi.");
        System.out.println();
        System.out.println("\t1. Søg efter en bestemt bruger");
        System.out.println("\t2. Opret bruger i systemet");
        System.out.println("\t3. Se liste over alle bruger i systemet");
        System.out.println("\t4. Slet bruger i systemet");
        System.out.println("\t5. Se bruger der mangler at betale(restance)");
        System.out.println("\t6. Få næste års økonomi budget");
        System.out.println("\t7. Luk programmet ned");
    }

    public void createMember(){
        System.out.println("Indtast navn på bruger:");
        String name = input.nextLine();
        System.out.println("Indtast alder på bruger:");
        int age = input.nextInt();
        System.out.println("Indtast om bruger vil være aktiv eller passiv(a/p):");
        boolean isActive = input.next().equalsIgnoreCase("a");
        controller.addMember(new Member(name, age, isActive));
    }

    public void getClubMembers(){
        System.out.print("Her er liste over bruger i systemet:");
        System.out.print(controller.getClubMembers());
        input.nextLine();
    }

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

    public void removeClubMembers(){
        System.out.println("Angiv hvilken bruger du vil fjerne i systemet:");

    }
}
