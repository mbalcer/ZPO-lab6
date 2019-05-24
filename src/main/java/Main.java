import controller.MainController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MainController mainController = new MainController();

        while(true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wpisz nazwę klasy(wraz z pakietem) aby uruchomić program: ");
            String nameClass = scanner.nextLine();

            Class<?> aClass = mainController.searchClass(nameClass);
            if (aClass==null)
                System.out.println("Nie ma takiej klasy");
            else {
                System.out.println("Klasa "+nameClass+" istnieje. Metody z adnotacją Scheduled zostały uruchomione co zadany czas.");
                mainController.createObject(aClass);
            }
        }

    }
}
