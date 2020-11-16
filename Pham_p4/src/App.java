import java.io.File;
import java.io.FileNotFoundException;
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
    private static Scanner userInput = new Scanner (System.in);
    private final TaskList list;

    private App() {
        list = new TaskList();
    }

    private void Main_menu () {
        System.out.println("Main Menu:");
        System.out.println("---------------");
        int choice = askWhichOptions();
            if (choice == 1) {
                List_Operation_menu();
            }
            else if (choice == 2) {
                Do_Read_user_file();
            }
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

    private void Do_Read_user_file() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("What is your file name called (no need to type .txt)? ");
            String filename = input.nextLine() + ".txt";
            File myObj = new File(filename);
            Scanner reader = new Scanner (myObj);

            while (reader.hasNext())
            {
                TaskItem myItem = new TaskItem("Test", "placeholder", "2001-09-11");
                myItem.setTitle(reader.nextLine());
                myItem.setDescription(reader.nextLine());
                myItem.setDue_Date(reader.nextLine());
                list.addTask(myItem);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not found. Please try again");
        }
    }

    private void List_Operation_menu() {
        System.out.println("List Operation Menu");
        System.out.println(("--------------------"));
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
        else {
            list.View_List();
        }
        List_Operation_menu();
    }
    private void Current_task() {
        if(list.size() <= 0){
            System.out.println("Sorry, empty list. Maybe add some tasks instead?\n");
        }
        else {
            list.View_List();
        }
    }
    private void View_Completed_List() {
        list.View_Completed_List();
    }

    private String createTaskTitle() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Title:");
        return input.nextLine();
    }

    private String createTaskDescription() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Description:");
        return input.nextLine();
    }

    private String createTaskDueDate() {
        Scanner input = new Scanner(System.in);
        System.out.println("Task Due Date (YYYY-MM-DD):");
        return input.nextLine();
    }

    private void create_Task_item() {
        TaskItem taskItem = null;
        boolean continueLoop = true;
        do {
            try {
                String title = createTaskTitle();
                String description = createTaskDescription();
                String Due_Date = createTaskDueDate();

                taskItem = new TaskItem(title, Due_Date, description);

                storedTaskItem(taskItem);

                continueLoop = false; //end looping

            } catch (TaskItem.InvalidTitleException err) {
                System.out.println("Warning: your Title was invalid, please double check it and try again");
            } catch (TaskItem.InvalidDateException err) {
                System.out.println("Warning: Your Date was invalid, please double check it and try again");
            } catch (InputMismatchException err) {
                userInput.nextLine();
                System.out.println("Please type a string date");
            }
        } while(continueLoop);
        List_Operation_menu();
    }

    private void storedTaskItem (TaskItem task) {
        list.addTask(task);
    }

    private void edit_Task_item () {
        Current_task();
        if (list.size() > 0) {
            boolean continueLoop = true;
            do {
                try {
                    System.out.println("Choose number which task would you like to edit?");
                    int index = userInput.nextInt();
                    TaskItem minhAnh = list.getTaskItem(index);

                /*list.editTitle(minhAnh);
                list.editDate(minhAnh);
                list.editDescription(minhAnh);*/

                    String title = createTaskTitle();
                    String date = createTaskDueDate();
                    String description = createTaskDescription();

                    list.edit(title, date, description, minhAnh);

                    System.out.println("Your task is successfully edited! Returning...");
                    continueLoop = false;

                } catch (TaskItem.InvalidTitleException err) {
                    System.err.printf("%nException: %s%n", err);
                    userInput.nextLine();
                    System.out.println("Warning: your Title was invalid, please double check it and try again");

                } catch (TaskItem.InvalidDateException err) {
                    System.err.printf("%nException: %s%n", err);
                    userInput.nextLine();
                    System.out.println("Warning: Your Date was invalid, please double check it and try again");
                } catch (IndexOutOfBoundsException outOfBoundsException) {
                    System.err.printf("%nException: %s%n", outOfBoundsException);
                    System.out.println("Your chosen task-list isn't exist, try again with the given number.");
                    userInput.nextLine();
                } catch (InputMismatchException ex) {
                    System.err.printf("%nException: %s%n", ex);
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    userInput.nextLine();
                }
            }while (continueLoop);
        }

        List_Operation_menu();
    }


/*    private boolean shouldContinue(String userInput) {
        return userInput.toLowerCase().startsWith("y");
    }
    private static String askShouldContinueRemoval() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you wish to remove a task? Once you have removed you will not be able to retrieve it. Answer (y/n):");
        return input.nextLine();
    }
    private static String askShouldContinueRegular() {
        Scanner input = new Scanner(System.in);
        System.out.println("Do you want to continue? Answer (y/n):");
        return input.nextLine();
    }*/

    private void remove_Task_item() {
        Current_task();
        if (list.size() > 0) {
            try {
                userInput.nextLine();
                System.out.println("Choose number which task would you like to remove?");
                TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                list.remove(minhAnh);
                System.out.println("Your task is successfully removed! Returning...");

            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
        List_Operation_menu();
    }

    private void mark_Task_completed () {
        Current_task();
        if (list.size() > 0) {
            try {
                userInput.nextLine();
                System.out.println("Choose number which task would you like to mark complete?");
                TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                list.MarkAsComplete(minhAnh);
                System.out.println("Your task is successfully marked as completed! Returning...");

            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
            View_Completed_List();
            List_Operation_menu();
    }

    private void Unmark_Task_Completed() {
        Current_task();
        if (list.size() > 0) {
            try {
                userInput.nextLine();
                System.out.println("Choose number which task would you like to un-mark complete?");
                TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                list.UnmarkAsComplete(minhAnh);
                System.out.println("Your task is successfully un-marked as completed! Returning...");

            } catch (InputMismatchException ex) {
                System.err.printf("%nException: %s%n", ex);
                userInput.nextLine(); //this will discard string input to let the user try again.
                System.out.printf("You must enter integers. Please try again. %n%n");
            }
        }
        List_Operation_menu();
    }
    private void save_TaskList_output () {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name: ");
        String filename = input.nextLine()+ ".txt";
        list.write(filename);
        System.out.println("Your File has successfully created and written!");
        List_Operation_menu();
    }

    public static void main (String[] args) {
        App app = new App();

        app.Main_menu();
        System.out.println("Thank you for using me. Goodbye!");
    }
}
