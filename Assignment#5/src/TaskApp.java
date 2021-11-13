import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp {

    private static Scanner userInput = new Scanner (System.in);
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final TaskList list;

    public TaskApp() {
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
            else {
                Home_menu main = new Home_menu();
                main.Select_application();
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

            list.load(filename);

            System.out.println("File is successfully loaded into the program.");

            if (list.size() <= 0) {
                System.out.println("Sorry, it's an empty list! Returning to the Main Menu...");
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
            list.view();
        }
        List_Operation_menu();
    }
    private void Current_task() {
        if(list.size() <= 0){
            System.out.println("Sorry, empty list. Maybe add some tasks instead?\n");
        }
        else {
            list.view();
        }
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
                System.out.println("Please type a string date.");
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


                    String title = createTaskTitle();
                    String description = createTaskDescription();
                    String date = createTaskDueDate();


                    list.editConfirm(index, title, date, description);

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
                    System.out.println("WARNING: Your chosen task-list isn't exist. Please try again.");
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
            boolean continueloop = true;
            do {
                try {
                    System.out.println("Choose number which task would you like to remove?");
                    list.remove(userInput.nextInt());
                    System.out.println("Your task is successfully removed! Returning...");
                    continueloop = false;
                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (IndexOutOfBoundsException err) {
                    userInput.nextLine();
                    System.out.println("WARNING: Your Task doesn't exist to be remove. Please try again.");
                    Current_task();
                }
            } while (continueloop);
            List_Operation_menu();
        }
    }

    private void mark_Task_completed () {
        Current_task();
        if (list.size() > 0) {
            boolean continueloop = true;
            do {
                try {
                    System.out.println("Choose number which task would you like to mark complete?");
                    list.MarkAsComplete(userInput.nextInt());
                    System.out.println("Your task is successfully marked as completed! Returning...");
                    continueloop = false;
                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("WARNING: Your Task doesn't exist to be mark as complete. Please try again.");
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    Current_task();
                }
            } while (continueloop);
            List_Operation_menu();
        }
    }

    private void Unmark_Task_Completed() {
        Current_task();
        if (list.size() > 0) {
            boolean continueLoop = true;
            do {
                try {
                    System.out.println("Choose number which task would you like to un-mark complete?");
                    list.UnmarkAsComplete(userInput.nextInt());
                    System.out.println("Your task is successfully un-marked as completed! Returning...");
                    continueLoop = false;

                } catch (InputMismatchException ex) {
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    System.out.printf("You must enter integers. Please try again. %n%n");
                    Current_task();
                } catch (IndexOutOfBoundsException ex) {
                    System.out.println("WARNING: Your Task doesn't exist to be unmarked. Please try again.");
                    userInput.nextLine(); //this will discard string input to let the user try again.
                    Current_task();
                }
            } while (continueLoop);
            List_Operation_menu();
        }
    }

    private void save_TaskList_output () {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a file name (no need to type .txt): ");
        String filename = input.nextLine() + ".txt";
        if (list.size() > 0) {
            list.write(filename);
            System.out.printf("Your File is successfully created and written! Returning...%n");
        }
        else {
            System.out.printf("Task list is empty. No save. Returning...%n");
        }

        List_Operation_menu();
    }

    public void Task_application () {
        TaskApp taskApp = new TaskApp();

        taskApp.Main_menu();
    }
}
