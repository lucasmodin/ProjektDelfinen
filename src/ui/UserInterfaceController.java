package ui;

import domain_model.Controller;

import java.util.Scanner;

public class UserInterfaceController {

    public void startProgram() throws Exception{
        Controller controller = new Controller();
        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.println("Velkommen til StamDelfy");
        System.out.println("1. Login som Club president");
        System.out.println("2. Login som Kasserer");
        System.out.println("3, Login som Træner");
        System.out.println("4. Login som Admin");
        System.out.println("5. Afslut programmet");
        String userChoice = "";

        while (!userChoice.equals("5")) {
                userChoice = input.nextLine();
            if (userChoice.equals("1")) {
                ClubPresidentUserinterface cUI = new ClubPresidentUserinterface(controller);
                cUI.menu();
                break;
            } else if (userChoice.equals("2")) {
                TreasureUserinterface tsUI = new TreasureUserinterface(controller);
                tsUI.menu();
                break;
            } else if (userChoice.equals("3")) {
                TrainerUserinterface trUI = new TrainerUserinterface(controller);
                trUI.menu();
                break;
            } else if (userChoice.equals("4")) {
                UserInterface ui = new UserInterface(controller);
                ui.menu();
                break;
            } else {
                System.out.println("Indtast venligst et gyldigt nummer");
            }
        }
    }
}
