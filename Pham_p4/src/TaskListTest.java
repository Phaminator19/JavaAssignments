import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30");

        m.add(elements);
        m.add(elements_2);

        assertEquals(2,m.size());
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
        String filename = "test.txt";
        assertThrows(FileNotFoundException.class, ()->TaskList.OpenFile(FileName));
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30");

        m.add(elements);
        m.add(elements_2);
        m.remove(elements);
        m.remove(elements_2);

        assertEquals(0, m.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        TaskItem index = m.get(1);

        assertThrows(IndexOutOfBoundsException.class, ()->m.remove(index));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        String filename = "output.txt";
        TaskList m = new TaskList();
        assertThrows(FileNotFoundException.class, ()->m.OpenFile(filename));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");

        items.add(elements);
        assertDoesNotThrow(() -> TaskList.UnmarkAsComplete(items.get(1)));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.UnmarkAsComplete(items.get(1)));
    }

}