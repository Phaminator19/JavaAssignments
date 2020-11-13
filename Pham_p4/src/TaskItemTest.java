import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        assertThrows(InvalidDateException.class, () -> new TaskItem("Task #1", "11122019"), "Date is not in the correct format");
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(InvalidTitleException.class, () -> new TaskItem("", "11-12-2019"), "Title has less than 1 string");
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem user = new TaskItem("Task #1", "2020-11-12");
        assertEquals("2020-11-12", user.createDate());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem user = new TaskItem("Task #2", "2020-11-12");
        assertEquals("Task #2", user.createTitle());
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        assertThrows(InvalidDateException.class, () -> TaskItem.setDate("1122019"), "Date is not save or set");
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem user = new TaskItem("Task #1", "test");
        user.setDate("2020-11-12");
        assertEquals("test", user.createDate());
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        assertThrows(InvalidDateException.class, () -> TaskItem.setTitle(""), "Title is not save or set");
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem user = new TaskItem("", "2020-11-12");
        user.setTitle("Task #1");
        assertEquals("", user.createTitle());
    }



}