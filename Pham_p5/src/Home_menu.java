import java.util.InputMismatchException;
import java.util.Scanner;

public class Home_menu {
    private static Scanner userInput = new Scanner(System.in);

    public static void main (String[] args) {
        Home_menu main = new Home_menu();

        main.Select_application();

        System.out.println("Exiting the program. Good bye and have a good day!");
    }

    public void Select_application () {
        System.out.println("Select Your Application");
        System.out.println("------------------------");
        int choice = askWhichApplication();
        if (choice == 1) {
            TaskApp task = new TaskApp();
            task.Task_application();
        }
        if (choice == 2) {
            ContactApp contact = new ContactApp();
            contact.Contact_Application();
        }
    }

    private int askWhichApplication() {
        while(true) {
            try {
                System.out.println("1) task list");
                System.out.println("2) contact list");
                System.out.println("3) quit");

                return userInput.nextInt();


            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
    }
}
