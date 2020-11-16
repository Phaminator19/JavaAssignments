import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
//    This program is designed to make a to-do list
//    It will contain a 0 or more task times and will has a a Title
//    This Title shall be 1 or more characters in length
//    It will also contain a Description that will be 0 or more characters in length
//    And finally, it will has Due Date in the format of YYYY-MM-DD
//
//    Scenario: Quang usually forgot to do the homework, and he also has alot of other stuff going
//    on at the same time. Quang wants to be productive and stay on track to things so that he
//    will not get point deduction because he didn't do his works. Therefore, he needs a task list that
//    can remind him at a specific day so that he can get prepared to do it. So he begins to create a to-do
//    list program and he will Title it as "Quang_Personal_To_do_List" The program asks if he wishes to enter additional
//    information. Quang will continues to enter the information until he ke tells the program that he done
//    After he done, the program writes all the data to a file.
//
//    The program needs encapsulate item data in a class called TaskItem and list data in a
//    class called TaskList. App class will handle the user interaction.
//
    private static final Scanner userInput = new Scanner (System.in);
    private final TaskList list;

    public App() {
        list = new TaskList();
    }

    public void Main_menu () {
        System.out.println("Main Menu:");
        System.out.println("------------");
        int choice = askWhichOptions();
            if (choice == 1) {
                List_Operation_menu();
            }
            else if (choice == 2) {
                DoReadingData();
            }
    }

    private int askWhichOptions() {
        while(true) {
            try {
                System.out.println("1) create a new list");
                System.out.println("2) load an existing list");
                System.out.println("3) quit");

                int choice = userInput.nextInt();

                return choice;

            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
    }

    private void DoReadingData() {
        /* reading file */
    }

    private void List_Operation_menu() {
        System.out.println("List Operation Menu");
        System.out.println(("--------------"));
            int choice = askOtherOptions();

            if (choice == 1) {
                View_TaskList();
            }
            if (choice == 2) {
                create_Task_item();
            }
            if (choice == 3) {
                edit_Task_item();
            }
            if (choice == 4) {
                remove_Task_item();
            }
            if (choice == 5) {
                mark_Task_completed();
            }
            if (choice == 6) {
                Unmark_Task_Completed();
            }
            if (choice == 7) {
                save_TaskList_output();
            }
            if(choice ==8) {
                Main_menu();
            }
    }

    private int askOtherOptions() {
        while (true) {
            try {
                System.out.println("1) view the list");
                System.out.println("2) add an item");
                System.out.println("3) edit an item");
                System.out.println("4) remove an item");
                System.out.println("5) mark an item completed");
                System.out.println("6) un-mark item as completed");
                System.out.println("7) save the current list");
                System.out.println("8) quit to the main menu");

                return userInput.nextInt();

            } catch (InputMismatchException string) {
                System.err.printf("%nException: %s%n", string);
                userInput.nextLine();
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
    }

    private void View_TaskList() {
        if(list.size() <= 0){
            System.out.println("Sorry, empty list. Maybe add some tasks instead?\n");
        }
        list.View_List();
        List_Operation_menu();
    }
    private void View_Completed_List() {
        list.View_Completed_List();
    }
    private String createTaskTitle() {
        System.out.println("Task Title:");
        return userInput.nextLine();
    }

    private String createTaskDescription() {
        System.out.println("Task Description:");
        return userInput.nextLine();
    }

    private String createTaskDueDate() {
        System.out.println("Task Due Date (YYYY-MM-DD):");
        return userInput.nextLine();
    }

    private void create_Task_item() {
        TaskItem taskItem = null;
        while (true) {
            try {
                String title = createTaskTitle();
                String description = createTaskDescription();
                String Due_Date = createTaskDueDate();

                taskItem = new TaskItem(title, Due_Date, description);

                storedTaskItem(taskItem);

                break;

            } catch (IllegalArgumentException err) {
                userInput.nextLine();
            }
        }

        List_Operation_menu();
    }

    private void storedTaskItem (TaskItem task) {
        list.add(task);
    }

    private void edit_Task_item () {
        System.out.println("Current Task:");
        View_TaskList();
        while (true) {
            try {
                System.out.println("Choose number which task would you like to edit?");
                TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                list.editTitle(minhAnh);
                System.out.println("Task Title: ");
                String title = userInput.nextLine();

                list.editDate(minhAnh);
                System.out.println("New date (YYYY-MM-DD):");
                String date = userInput.nextLine();

                list.editDescription(minhAnh);
                System.out.println("Enter description: ");
                String description = userInput.nextLine();

                list.edit(title, date, description, minhAnh);
                System.out.println("Your task is successfully edited! Returning...");

                break;

            } catch (IllegalArgumentException err) {
                userInput.nextLine();
            } catch (IndexOutOfBoundsException outOfBoundsException) {
                System.out.println("Your chosen task-list isn't exist, try again with the given number.");
                userInput.nextLine();
            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        List_Operation_menu();
    }

    private boolean shouldContinue(String userInput) {
        return userInput.toLowerCase().startsWith("y");
    }
    private static String askShouldContinueRemoval() {
        System.out.println("Do you wish to remove a task? Once you have removed you will not be able to retrieve it. Answer (y/n):");
        return userInput.nextLine();
    }
    private static String askShouldContinueRegular() {
        System.out.println("Do you want to continue? Answer (y/n):");
        return userInput.nextLine();
    }

    private void remove_Task_item() {
        /*your code here */
        System.out.println("Current Task:");
        View_TaskList();

        while (shouldContinue(askShouldContinueRemoval())) {
            while (true) {
                try {
                    System.out.println("Choose number which task would you like to remove?");
                    TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                    list.remove(minhAnh);
                    System.out.println("Your task is successfully removed! Returning...");

                    break;
                } catch (InputMismatchException ex) {
                    System.err.printf("%nException: %s%n", ex);
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                }
            }
        }
        List_Operation_menu();
    }

    private void mark_Task_completed () {
        System.out.println("Current Tasks:");
        System.out.println("-------------------");
        View_TaskList();
        while (shouldContinue(askShouldContinueRegular())) {
            View_TaskList();
            while (true) {
                try {
                    System.out.println("Choose number which task would you like to mark complete?");
                    TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                    list.MarkAsComplete(minhAnh);
                    System.out.println("Your task is successfully marked as completed! Returning...");

                    break;
                } catch (InputMismatchException ex) {
                    System.err.printf("%nException: %s%n", ex);
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                }
            }
        }

        View_Completed_List();
        List_Operation_menu();
    }

    private void Unmark_Task_Completed() {
        System.out.println("Current Tasks:");
        System.out.println("-------------------");
        View_TaskList();
        while (shouldContinue(askShouldContinueRegular())) {
            while (true) {
                try {
                    System.out.println("Choose number which task would you like to un-mark complete?");
                    TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                    list.UnmarkAsComplete(minhAnh);
                    System.out.println("Your task is successfully un-marked as completed! Returning...");

                    break;
                } catch (InputMismatchException ex) {
                    System.err.printf("%nException: %s%n", ex);
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                }
            }
        }
        List_Operation_menu();
    }
//    UserInput == 7; save the current list
//            -Prompt the user to enter the filename to save as
//    name = read string from user
//             -create a file with the name
//             -Print the string "task list has been saved"
    private void save_TaskList_output () {
        System.out.print("Enter a file name: ");
        String filename = userInput.nextLine();
        list.write(filename);
    }

    public static void main (String[] args) {
        App app = new App();
        app.List_Operation_menu();
        //app.Main_menu();
        System.out.println("Thank you for using me. Goodbye!");
    }
}
