import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

    private static Scanner userInput = new Scanner (System.in);
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final TaskList list;

    private TaskApp() {
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
                TaskItem myItem = new TaskItem("Test", "2001-09-11", "placeholder");

                String title = reader.next();
                String description = reader.next();
                String date = reader.next();

                myItem.setTitle(title);
                myItem.setDescription(description);
                myItem.setDue_Date(date);

                list.addTask(myItem);

                System.out.println("File is successfully loaded into the program. Returning to the Main Menu...");

                if (list.size() <= 0) {
                    System.out.println("Empty list! Returning to the Main Menu...");
                }
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

    private void List_Operation_menu() {
        System.out.println("List Operation Menu");
        System.out.println(("------------------"));
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

                    String title = createTaskTitle();
                    String date = createTaskDueDate();
                    String description = createTaskDescription();

                    minhAnh.edit(title, date, description);

                    System.out.println("Your task is successfully edited! Returning...\n");
                    continueLoop = false;

                } catch (TaskItem.InvalidTitleException err) {
                    userInput.nextLine();
                    System.out.println("Warning: Your Title was not at least 1 string log. Please double check it and try again. Task was not edited.");
                    Current_task();

                } catch (TaskItem.InvalidDateException err) {
                    userInput.nextLine();
                    System.out.println("Warning: Your Date was invalid format. Please double check it and try again. Task was not edited.");
                    Current_task();
                } catch (IndexOutOfBoundsException outOfBoundsException) {
                    System.out.println("Your chosen task-list isn't exist.");
                    userInput.nextLine();
                    Current_task();
                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("WARNING: You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (Exception ex2) {
                    System.out.println("WARNING: There's an error, task is not edited. Please choose which task to edit again.");
                    userInput.nextLine();
                    Current_task();
                }
            }while (continueLoop);
        }

        List_Operation_menu();
    }

    private void remove_Task_item() {
        Current_task();
        if (list.size() > 0) {
            try {
                userInput.nextLine();
                System.out.println("Choose number which task would you like to remove?");
                TaskItem minhAnh = list.getTaskItem(userInput.nextInt());

                list.remove(minhAnh);
                System.out.println("Your task is successfully removed! Returning...%n");

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
                System.out.println("Your task is successfully marked as completed! Returning...%n");

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
                System.out.println("Your task is successfully un-marked as completed! Returning...%n");

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
        System.out.print("Enter a file name (no need to type .txt): ");
        String filename = input.nextLine() + ".txt";
        list.write(filename);
        System.out.println("Your File is successfully created and written! Returning...%n");
        List_Operation_menu();
    }

    public static void main (String[] args) {
        TaskApp taskApp = new TaskApp();

        taskApp.Main_menu();

        System.out.println("Thank you for using me. Goodbye!");
    }
}
