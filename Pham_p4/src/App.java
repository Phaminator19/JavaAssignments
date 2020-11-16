import com.sun.tools.javac.Main;

import java.util.InputMismatchException;
import java.util.List;
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
    private static Scanner userInput = new Scanner (System.in);
    private TaskList list;

    public void Main_menu () {
        System.out.println("Main Menu:");
        System.out.println("----------");

        while (chooseOptions(askWhichOptions())) {
            if (askWhichOptions() == 1) {
                List_Operation_menu();
            }
            else if (askWhichOptions() == 2) {
                DoReadingData();
            }
            else if (askWhichOptions() == 3) {
                return;
            }
        }
    }

    private boolean chooseOptions (int userInput) {
        if (userInput == 1) {
            return true;
        }
        if(userInput == 2) {
            return true;
        }
        return userInput == 3;
    }

    private int askWhichOptions() {
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

    private void DoReadingData() {
        /* reading file */
    }
//
//        List Operation Menu:
    private void List_Operation_menu() {
        System.out.println("List Operation Menu");
        System.out.println(("--------------"));
        while(chooseOtherOptions(askOtherOptions())) {
            if (askOtherOptions() == 1) {
                View_TaskList();
            }
            if (askOtherOptions() == 2) {
                create_Task_item();
            }
            if (askOtherOptions() == 3) {
                edit_Task_item();
            }
            if (askOtherOptions() == 4) {
                remove_Task_item();
            }
            if (askOtherOptions() == 5) {
                mark_Task_completed();
            }
            if (askOtherOptions() == 6) {
                Unmark_Task_Completed();
            }
            if (askOtherOptions() == 7) {
                save_TaskList_output();
            }
            if(askOtherOptions()==8) {
                Main_menu();
            }
        }
        if (!chooseOtherOptions(askOtherOptions())) {
            Main_menu();
        }
    }

    private boolean chooseOtherOptions(int userInput) {
            if (userInput == 1) {
                return true;
            }
            if (userInput == 2) {
                return true;
            }
            if (userInput == 3) {
                return true;
            }
            if (userInput == 4) {
                return true;
            }
            if (userInput == 5) {
                return true;
            }
            if (userInput == 6) {
                return true;
            }
            if (userInput == 7) {
                return true;
            }
            if (userInput == 8) {
                return true;
            } else {
                return false;
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
//
//    Prompt ask user for the following menu option.
//    Need to return back the menu with the List Operation Menu again after we done with one of the option
//
//    loop is here:
//
    private void View_TaskList() {
        list.View_List();
        List_Operation_menu();
    }

    private void create_Task_item() {

        while (true) {
            try {
                System.out.println("Task Title: ");
                String title = userInput.nextLine();


                System.out.println("Task Description: ");
                String description = userInput.nextLine();


                System.out.println("Task Due Date (YYYY-MM-DD): ");
                String Due_Date = userInput.nextLine();

                TaskItem taskItem = new TaskItem(title, Due_Date, description);

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
                TaskItem minhAnh = list.get(userInput.nextInt());

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

//
//        UserInput == 4; remove an item
//                Prompt the user to which item to remove an item
//                    String prints the current tasks.
//                Prompt the user if they want to quit instead or continue
//                        UserInput = No
//                            continue;
//                        else UserInput = yes
//                            return back to the beginning of the loop
//                    -if user enter any thing else other those numbers, return a string with Invalid Input
//                    and return back to this prompt
//                -User choose the task to be remove
//                -dispose the tasks that were chosen by the user
//                Return to the beginning of the loop;

    private boolean shouldContinue(String userInput) {
        return userInput.toLowerCase().startsWith("y");
    }
    private static String askShouldContinueRemoval() {
        System.out.println("Do you wish to remove a task? Once you have removed you will not be able to retrieve it. Answer (y/n):");
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
                    TaskItem minhAnh = list.get(userInput.nextInt());

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
//
//        UserInput == 5; mark an item as completed
//            Prompt the user to which item to mark as completed an item
//                String prints the current tasks.
//            Prompt the user if they want to quit
//                        UserInput = No
//                            continue;
//                        else UserInput = yes
//                            return back to the beginning of the loop
//                -if user enter any thing else other those numbers, return a string with Invalid Input
//                 and repeat the prompt
//                -User choose the task to be mark as completed
//                -Mark the tasks that were chosen by the user with some kind of way
//                -Return to the beginning of the loop;
    private void mark_Task_completed () {
        /*your code here */
    }
//
//        UserInput == 6; un-mark an item as completed
//                Prompt the user to which item to un-mark a completed item
//                String prints the current tasks.
//                Prompt the user if they want to quit
//                        UserInput = No
//                            continue;
//                        else UserInput = yes
//                            return back to the beginning of the loop
//                    -if user enter any thing else other those numbers, return a string with Invalid Input
//                    and repeat the prompt
//                -User choose the task to be un-mark as completed
//                -Un-Mark the tasks that were chosen by the user with some kind of way
//                -Return to the beginning of the loop;
    private void Unmark_Task_Completed() {

    }
//    UserInput == 7; save the current list
//            -Prompt the user to enter the filename to save as
//    name = read string from user
//             -create a file with the name
//             -Print the string "task list has been saved"
    private void save_TaskList_output () {

    }

    public static void main (String[] args) {
        App app = new App();

        app.Main_menu();
        System.out.println("Thank you for using me. Goodbye!");
    }
}
