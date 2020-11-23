import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp{
    private static Scanner userInput = new Scanner (System.in);
    private final ContactList contacts;

    public ContactApp () {
        contacts = new ContactList();
    }

    public void Contact_Application () {
        ContactApp app = new ContactApp();

        app.Main_menu();
    }

    private void Main_menu() {
        System.out.println("Main Menu:");
        System.out.println("-----------");
        int choice = askWhichOptionsList();
        if (choice == 1) {
            System.out.println("New contact list is created.\n");
            List_Operation_menu();
        }
        else if (choice == 2) {
            Do_Read_user_file();
        }
        else {
            Home_menu main = new Home_menu();
            main.Select_application();
        }
    }

    public int askWhichOptionsList() {
        while(true) {
            try {
                System.out.println("1) create a new list");
                System.out.println("2) load an existing list");
                System.out.println("3) quit");

                return userInput.nextInt();


            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
    }

    private void Do_Read_user_file() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("What is your file name called (no need to type .txt)? ");
            String filename = input.nextLine() + ".txt";

            contacts.load(filename);

            System.out.println("File is successfully loaded into the program.\n");

            if (contacts.size() <= 0) {
                System.out.println("Sorry, it's an empty list! Returning to the Main Menu...\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("WARNING: File does not found. Please try again");
        } catch (TaskItem.InvalidTitleException ex) {
            System.out.println("WARNING: Title is not at least 1 string long. Please double check. File did not save.");
        }  catch (TaskItem.InvalidDateException ex) {
            System.out.println("WARNING: Date is in wrong format. Please double check. File did not save.");
        }
        Main_menu();
    }

    private void Current_task() {
        if(contacts.size() <= 0){
            System.out.println("Sorry, empty list. Maybe add some contacts instead?\n");
        }
        else {
            contacts.view();
        }
    }

    private void List_Operation_menu() {
        System.out.println("List Operation Menu");
        System.out.println(("------------------"));
        int choice = askOtherOptions();

        if (choice == 1) {
            View_Contact_List();
        }
        if (choice == 2) {
            create_Contact_item();
        }
        if (choice == 3) {
            edit_Contact_item();
        }
        if (choice == 4) {
            remove_Contact_item();
        }
        if (choice == 5) {
            save_current_contact();

        }
        if(choice ==6) {
            Main_menu();
        }
    }

    private void save_current_contact() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name (no need to type .txt): ");
        String filename = input.nextLine() + ".txt";
        if (contacts.size() > 0) {
            contacts.write(filename);
            System.out.printf("Your File is successfully created and written! Returning...%n");
        }
        else {
            System.out.printf("Contact list is empty. No save. Returning...%n");
        }
        List_Operation_menu();
    }

    private void remove_Contact_item() {
        Current_task();
        if (contacts.size() > 0) {
            boolean continueloop = true;
            do {
                try {
                    System.out.println("Choose number which contact would you like to remove?");
                    contacts.remove(userInput.nextInt());
                    System.out.println("Your contact is successfully removed! Returning...\n");
                    continueloop = false;
                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (IndexOutOfBoundsException err) {
                    userInput.nextLine();
                    System.out.println("WARNING: Your Task doesn't exist to be remove. Please try again.\n");
                    Current_task();
                }
            } while (continueloop);
            List_Operation_menu();
        }
    }

    private void edit_Contact_item() {
        Current_task();
        if (contacts.size() > 0) {
            boolean continueLoop = true;
            do {
                try {
                    System.out.println("Choose number which contact would you like to edit?");
                    int index = userInput.nextInt();


                    String FirstName = createFirstName();
                    String LastName = createLastName();
                    String EmailAddress = createEmailAddress();
                    String PhoneNumber = createPhoneNumber();


                    contacts.editConfirm(index, FirstName, LastName, EmailAddress, PhoneNumber);

                    System.out.println("Your task is successfully edited! Returning...\n");
                    continueLoop = false;

                } catch (TaskItem.InvalidTitleException err) {
                    userInput.nextLine();
                    System.out.println("Warning: Your Title was not at least 1 string log. Please double check it and try again. Task was not edited.\n");
                    Current_task();
                } catch (TaskItem.InvalidDateException err) {
                    userInput.nextLine();
                    System.out.println("Warning: Your Date was invalid format. Please double check it and try again. Task was not edited.\n");
                    Current_task();
                } catch (IndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("WARNING: Your chosen task-list isn't exist. Please try again.\n");
                    userInput.nextLine();
                    Current_task();
                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("WARNING: You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (Exception ex2) {
                    System.out.println("WARNING: There's an error, task is not edited. Please choose which task to edit again.\n");
                    userInput.nextLine();
                    Current_task();
                }
            }while (continueLoop);
        }
        List_Operation_menu();
    }

    private String createPhoneNumber() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Phone Number (xxx-xxx-xxxx): ");
        return input.nextLine();
    }

    private String createEmailAddress() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Email Address (x@y.z): ");
        return input.nextLine();
    }

    private String createLastName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Last Name: ");
        return input.nextLine();
    }

    private String createFirstName() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter First Name: ");
        return input.nextLine();
    }
    private void storedContactItem(ContactItem item) {
        contacts.addContactItem(item);
    }

    private void create_Contact_item() {
        ContactItem contactItem = null;
        boolean continueLoop = true;
        do {
            try {
                String FirstName = createFirstName();
                String LastName = createLastName();
                String EmailAddress = createEmailAddress();
                String PhoneNumber = createPhoneNumber();

                contactItem = new ContactItem(FirstName, LastName, EmailAddress, PhoneNumber);

                storedContactItem(contactItem);

                continueLoop = false; //end looping

            } catch (ContactItem.InvalidValueException err) {
                System.out.println("Warning: Need at least one of the value to be not blank. Please try again.\n");
            } catch (InputMismatchException err) {
                userInput.nextLine();
                System.out.println("Please type as a string");
            }

        } while(continueLoop);

        List_Operation_menu();
    }

    private void View_Contact_List() {
        if(contacts.size() <= 0){
            System.out.println("Sorry, empty list. Maybe add some contacts instead?\n");
        }
        else {
            contacts.view();
        }
        List_Operation_menu();
    }

    private int askOtherOptions() {
        while (true) {
            try {
                System.out.println("1) view the list");
                System.out.println("2) add an item");
                System.out.println("3) edit an item");
                System.out.println("4) remove an item");
                System.out.println("5) save the current list");
                System.out.println("6) quit to the main menu");

                return userInput.nextInt();

            } catch (InputMismatchException string) {
                System.err.printf("%nException: %s%n", string);
                userInput.nextLine();
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
    }


}
