public class App {
    /*
    This program is designed to make a to-do list
    It will contain a 0 or more task times and will has a a Title
    This Title shall be 1 or more characters in length
    It will also contain a Description that will be 0 or more characters in length
    And finally, it will has Due Date in the format of YYYY-MM-DD

    Scenario: Quang usually forgot to do the homework, and he also has alot of other stuff going
    on at the same time. Quang wants to be productive and stay on track to things so that he
    will not get point deduction because he didn't do his works. Therefore, he needs a task list that
    can remind him at a specific day so that he can get prepared to do it. So he begins to create a to-do
    list program and he will Title it as "Quang_Personal_To_do_List" The program asks if he wishes to enter additional
    information. Quang will continues to enter the information until he ke tells the program that he done
    After he done, the program writes all the data to a file.

    The program needs encapsulate item data in a class called TaskItem and list data in a
    class called TaskList. App class will handle the user interaction.

    Pseudocode:

        Main Menu:
    prompt to ask for user input to create a new task list
            - Go to the List Operation menu
    prompt to ask for user input to load an existing list
            - read the input list
            -Print the list onto the output
    prompt to quit the program entirely
            -Ask the user if they really want to quit
            - terminate the program if true
            - return back to the Main Menu if false
     */

    /*
        List Operation Menu:

    Prompt ask user for the following menu option.
    Need to return back the menu with the List Operation Menu again after we done with one of the option

    loop is here:

        UserInput == 1; view the list
           String prints the current tasks
                -If empty, print the string empty list
                -Else print the list
                -Return to the List operation menu;

        UserInput == 2; add an item

        ask the user for a title
              name = read string from user
             if the user enter wrong title return an invalid title string
            else return a string that said task item succeeds with valid title set it

        ask the user for description
            description = read string from user

        ask the user for task due date (YYYY-MM-DD)
            date = read string from user
           if the user enter wrong date return an invalid date string and let the user input the title
              again.
           else, set it to "date return a string that said task item is created successfully.


        Loop back to the menu after done the required inputting;

        UserInput == 3; Edit an item
            String print the current tasks with numbered next to it

            prompt the user to choose which task to edit
                -if userInput doesn't match, return a string with Invalid Input and ask for a correct input
                -Automatically return back this method and try again

            prompt the user to make new title for that specific chosen task
               -if the user enter wrong title return an invalid title string and let the user input the title
               again.
               -else return a string that said task item succeeds with valid title

            prompt the user to make a new description for that specific chosen task

            prompt the user to enter a new task due date (YYYY-MM-DD) for that chosen task
               -if the user enter wrong date return an invalid date string and let the user input the title
               again.
               -else return a string that said task item succeeds with valid date
               -Return to the beginning of the loop;

        UserInput == 4; remove an item
                Prompt the user to which item to remove an item
                    String prints the current tasks.
                Prompt the user if they want to quit instead or continue
                        UserInput = No
                            continue;
                        else UserInput = yes
                            return back to the beginning of the loop
                    -if user enter any thing else other those numbers, return a string with Invalid Input
                    and return back to this prompt
                -User choose the task to be remove
                -dispose the tasks that were chosen by the user
                Return to the beginning of the loop;

        UserInput == 5; mark an item as completed
            Prompt the user to which item to mark as completed an item
                String prints the current tasks.
            Prompt the user if they want to quit
                        UserInput = No
                            continue;
                        else UserInput = yes
                            return back to the beginning of the loop

                -if user enter any thing else other those numbers, return a string with Invalid Input
                 and repeat the prompt
                -User choose the task to be mark as completed
                -Mark the tasks that were chosen by the user with some kind of way
                -Return to the beginning of the loop;

        UserInput == 6; un-mark an item as completed
                Prompt the user to which item to un-mark a completed item
                String prints the current tasks.
                Prompt the user if they want to quit
                        UserInput = No
                            continue;
                        else UserInput = yes
                            return back to the beginning of the loop

                    -if user enter any thing else other those numbers, return a string with Invalid Input
                    and repeat the prompt
                -User choose the task to be un-mark as completed
                -Un-Mark the tasks that were chosen by the user with some kind of way
                -Return to the beginning of the loop;

        UserInput == 7; save the current list
            -Prompt the user to enter the filename to save as
                name = read string from user
             -create a file with the name
             -Print the string "task list has been saved"
        UserInput == 8; quit to the main menu
            - return to the original main menu
        if user enter any thing else other those numbers, return a string with Invalid Input
        and return back to the loop
     */
public static void main () {
    System.out.println("Hell World");
}


}
