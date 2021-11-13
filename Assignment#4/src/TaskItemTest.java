import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("Task #1", "", "abc"), "Date is not in the correct format");
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("", "11-12-2019", "abc"), "Title has less than 1 string");
    }

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem user = new TaskItem("Task #1", "2020-11-12", "abc");
        assertEquals("2020-11-12", user.getDue_Date());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem user = new TaskItem("Task #2", "2020-11-12", "abc");
        assertEquals("Task #2", user.getTitle());
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        TaskItem user = new TaskItem("Task #1", "2020-11-12", "abc");
        assertThrows(TaskItem.InvalidDateException.class, () -> user.setDue_Date(""), "Date is not save or set");
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem user = new TaskItem("Task #1", "2020-11-12", "abc");
        user.setDue_Date("2020-11-25");
        assertEquals("2020-11-25", user.getDue_Date());
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        TaskItem user = new TaskItem("Task #1", "2020-11-12", "abc");
        assertThrows(IllegalArgumentException.class, () -> user.setTitle(""), "Title is not save or set");
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem user = new TaskItem("test", "2020-11-12", "abc");
        user.setTitle("Task #1");
        assertEquals("Task #1", user.getTitle());
    }



}