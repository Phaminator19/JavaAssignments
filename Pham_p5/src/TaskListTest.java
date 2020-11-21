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
        TaskItem testTask = st.getTaskItem(0);
        assertDoesNotThrow(() -> st.MarkAsComplete(testTask));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lol");
        m.addTask(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.MarkAsComplete(m.getTaskItem(2)));
    }

    @Test
    public void editingTaskItemChangesValues() {
        TaskItem elements = new TaskItem("abc", "2020-11-16", "");
        assertDoesNotThrow(()->elements.edit("Task #1", "2020-11-15", "This is a test"));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        assertDoesNotThrow(()->elements.editDescription("This editDescription is working"));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.addTask(elements);
        TaskItem itemExample = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->itemExample.editDescription("This editDescription is working"));
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

        TaskItem test = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->test.editDate("2020-11-21"));
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
        TaskItem test = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->test.editTitle("test the title changer"));
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
        TaskItem elements_2 = new TaskItem("Task #2", "2020-10-30", "lol");

        m.addTask(elements);
        m.addTask(elements_2);
        m.remove(elements);
        m.remove(elements_2);

        assertEquals(0, m.size());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.addTask(elements);
        TaskItem test = m.getTaskItem(2);

        assertThrows(IndexOutOfBoundsException.class, ()->m.remove(test));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        String filename = "output.txt";
        TaskList m = new TaskList();
        assertThrows(FileNotFoundException.class, ()->m.write(filename));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lmao");
        m.addTask(elements);
        TaskItem testItem = m.getTaskItem(0);
        m.MarkAsComplete(testItem);
        assertDoesNotThrow(() -> m.UnmarkAsComplete(testItem));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "ROFL");
        TaskItem testItem = m.getTaskItem(1);
        assertThrows(IndexOutOfBoundsException.class, () -> m.UnmarkAsComplete(testItem));
    }

}