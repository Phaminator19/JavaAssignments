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
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30", "bef");

        m.add(elements);
        m.add(elements_2);

        assertEquals(2,m.size());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();

        items.add(elements);
        assertDoesNotThrow(() -> st.MarkAsComplete(items.get(1)));
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
        assertThrows(IndexOutOfBoundsException.class, ()->TaskList.MarkAsComplete(items.get(1)));
    }

    @Test
    public void editingTaskItemChangesValues() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("", "", "");
        TaskList minhAnh = new TaskList();
        assertDoesNotThrow(()->minhAnh.edit("Task #1", "2020-11-15", "This is a test", elements));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        items.add(elements);
        TaskItem test = items.get(1);
        assertDoesNotThrow(()->st.editDescription(test));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        items.add(elements);
        TaskItem test = items.get(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editDescription(test));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abc");
        TaskList st = new TaskList();
        items.add(elements);
        TaskItem test = items.get(1);
        assertDoesNotThrow(()->st.editDate(test));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        items.add(elements);
        TaskItem test = items.get(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editDate(test));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task", "2020-11-12", "abc");
        TaskList st = new TaskList();
        items.add(elements);
        TaskItem test = items.get(1);
        assertDoesNotThrow(()->st.editTitle(test));
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        items.add(elements);
        TaskItem test = items.get(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editTitle(test));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->st.get(items.get(1)));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        items.add(elements);
      assertDoesNotThrow(()->elements.getDescription());
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        items.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->elements.getDate(items.get(2)));
    }

    @Test
    public void
    gettingTaskItemTitleFailsWithInvalidIndex() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
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
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30", "lol");

        m.add(elements);
        m.add(elements_2);
        m.remove(elements);
        m.remove(elements_2);

        assertEquals(0, m.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.add(elements);
        TaskItem test = m.get(2);

        assertThrows(IndexOutOfBoundsException.class, ()->m.remove(test));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        String filename = "output.txt";
        TaskList m = new TaskList();
        assertThrows(FileNotFoundException.class, ()->m.OpenFile(filename));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lmao");
        m.add(elements);
        TaskItem testItem = m.get(1);

        assertDoesNotThrow(() -> m.UnmarkAsComplete(testItem));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "ROFL");
        TaskItem testItem = m.get(1);
        assertThrows(IndexOutOfBoundsException.class, () -> m.UnmarkAsComplete(testItem));
    }

}