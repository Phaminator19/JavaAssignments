import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30", "bef");

        m.addTask(elements);
        m.addTask(elements_2);

        assertEquals(2,m.size());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        st.addTask(elements);
        assertDoesNotThrow(() -> st.MarkAsComplete(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lol");
        m.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.MarkAsComplete(1));
    }

    @Test
    public void editingTaskItemChangesValues() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("abc", "2020-11-16", "");
        m.addTask(elements);
        assertDoesNotThrow(()->m.editConfirm(0, "Task #1", "2020-11-15", "This is a test"));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);
        assertDoesNotThrow(()->elements.editDescription("This edit Description is working"));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editConfirm(1, elements.getTitle(), elements.getDue_Date(), "Testing to change"));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abc");
        assertDoesNotThrow(()->elements.editDate("2020-11-21"));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");

        TaskList testList = new TaskList();

        testList.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editConfirm(1, elements.getTitle(), "2020-12-23", elements.getDescription()));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskItem elements = new TaskItem("Task", "2020-11-12", "abc");
        assertDoesNotThrow(()->elements.editTitle("test the title changer"));
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editConfirm(1, "Title Changing this", elements.getDue_Date(), elements.getDescription()));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        m.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->st.getTaskDescription(2));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);
      assertDoesNotThrow(()->m.getTaskDescription(0));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.getTaskDueDate(2));
    }

    @Test
    public void
    gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.getTaskTitle(3));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task 5", "2020-11-12", "abc");
        m.addTask(elements);
        assertDoesNotThrow(()->m.getTaskTitle(0));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task 5", "2020-11-12", "abc");
        m.addTask(elements);
        assertDoesNotThrow(()->m.getTaskDueDate(0));
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList testList = new TaskList();

        String filename = "test.txt";
        assertDoesNotThrow(()->testList.write(filename));
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");

        m.addTask(elements);
        m.remove(0);


        assertEquals(0, m.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);

        assertThrows(IndexOutOfBoundsException.class, ()->m.remove(1));
    }

    @Test
    public void savedTaskListCanBeLoaded() throws FileNotFoundException {
        String filename = "output.txt";
        TaskList m = new TaskList();
        m.addTask(new TaskItem("Task #1", "2020-11-23", "My first task"));
        m.addTask(new TaskItem("Task #2", "2020-11-30", "My second task"));
        m.write(filename);
        m.load(filename);

        assertEquals(4, m.size());
        assertEquals("Task #1 My first task 2020-11-23%n", m.getTaskText(0));
        assertEquals("Task #2 My second task 2020-11-30%n", m.getTaskText(1));

    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lmao");
        m.addTask(elements);
        m.MarkAsComplete(0);
        assertDoesNotThrow(() -> m.UnmarkAsComplete(0));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "ROFL");
        m.addTask(elements);
        m.MarkAsComplete(0);
        assertThrows(IndexOutOfBoundsException.class, () -> m.UnmarkAsComplete(1));
    }

}