import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        TaskItem user = new TaskItem(System.in);
        System.out.print("Task Due Date (YYYY-MM-DD): ");
        String date = user.nextLine();
        assert(TaskItem.getDate()) : "WARNING: Invalid due date; your task is not created " + date;
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        TaskItem user = new TaskItem(System.in);
        System.out.println("Task title: ");
        String title = user.nextLine();
        assert(TaskItem.getTitle()) : "WARNING: Your title must be at least 1 character long; your task is not created "
                + title;
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem user = new TaskItem(System.in);
        System.out.println("Task Due Date (YYYY-MM-DD): ");
        String date = user.nextLine();

        assert(!TaskItem.getDate()) : "Your task is created because your date format is correct " + date;
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem user = new TaskItem(System.in);
        System.out.println("Task Due Date (YYYY-MM-DD): ");
        String title = user.nextLine();

        assert(!TaskItem.getTitle()) : "Your task is created because your date format is correct " + title;
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        assertThrows(InputDateException.class, () -> TaskItem.setDate(), "Fail to edit the task");
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {

    }
/*





settingTaskItemTitleFailsWithInvalidTitle()
settingTaskItemTitleSucceedsWithValidTitle()
 */

}