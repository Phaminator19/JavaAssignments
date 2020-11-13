import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        List<TaskItem> items = new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30");

        m.add(items.add(elements));
        m.add(items.add(elements_2));

        assertEquals(2,items.size());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");

        items.add(elements);
        assertDoesNotThrow(() -> TaskList.MarkAsComplete(items.get(1)));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        /*
        List <TaskItem> tasks = new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem();
        App app = new App();
*/
        List<TaskItem> items = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, ()->TaskList.MarkAsComplete(items.get(1));
    }

    @Test
    public void editingTaskItemChangesValues() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");

        items.add(elements);
        assertDoesNotThrow(()->TaskList.edit(items.get(1)));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");

        items.add(elements);
        assertDoesNotThrow(()->TaskList.editDescription(items.get(1)));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12");

        assertThrows(IndexOutOfBoundsException.class, ()->TaskList.editDescription(items.get(1)));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12");
        items.add(elements);
        assertDoesNotThrow(()->TaskList.editDate(items.get(1)));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->TaskList.editDate(items.get(2)));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task", "2020-11-12");
        items.add(elements);
        assertDoesNotThrow(()->TaskList.editTitle(items.get(1)));
    }

    @Test
    public void
    editingTaskItemTitleFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->TaskList.editTitle(items.get(2)));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->elements.getDescription(items.get(1)));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        items.add(elements);
      assertDoesNotThrow(()->elements.getDescription(items.get(1)));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->elements.getDate(items.get(2)));
    }

    @Test
    public void
    gettingTaskItemTitleFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->elements.getTitle(items.get(2)));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task 5", "2020-11-12");
        items.add(elements);
        assertDoesNotThrow(()->elements.getTitle(items.get(1)));
    }

    @Test
    public void newTaskListIsEmpty() {
        String s = "test.txt";
        assertThrows(FileNotFoundException.class, ()->TaskList.OpenFile(s));
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        List<TaskItem> items = new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30");

        m.add(items.add(elements));
        m.add(items.add(elements_2));
        m.remove(items.remove(elements));
        m.remove(items.remove(elements_2));

        assertEquals(0, items.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");

        assertThrows(IndexOutOfBoundsException.class, ()->m.remove(items.remove(elements)));
    }

    @Test
    public void savedTaskListCanBeLoaded() {

    }
    /*
uncompletingTaskItemChangesStatus()
uncompletingTaskItemFailsWithInvalidIndex()
     */
}