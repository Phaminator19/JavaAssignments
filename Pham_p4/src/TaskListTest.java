import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();

        st.add(elements);
        TaskItem testTask = st.getTaskItem(0);
        assertDoesNotThrow(() -> st.MarkAsComplete(testTask));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "lol");
        m.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.MarkAsComplete(m.getTaskItem(2)));
    }

    @Test
    public void editingTaskItemChangesValues() {
        TaskItem elements = new TaskItem("abc", "2020-11-16", "");
        TaskList minhAnh = new TaskList();
        assertDoesNotThrow(()->minhAnh.edit("Task #1", "2020-11-15", "This is a test", elements));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        st.add(elements);
        TaskItem test = st.getTaskItem(0);
        assertDoesNotThrow(()->st.editDescription(test));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.add(elements);
        TaskItem test = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editDescription(test));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abc");
        TaskList st = new TaskList();
        st.add(elements);
        TaskItem test = st.getTaskItem(0);
        assertDoesNotThrow(()->st.editDate(test));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.add(elements);
        TaskItem test = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editDate(test));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        TaskItem elements = new TaskItem("Task", "2020-11-12", "abc");
        TaskList st = new TaskList();
        st.add(elements);
        TaskItem test = st.getTaskItem(0);
        assertDoesNotThrow(()->st.editTitle(test));
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        TaskItem elements = new TaskItem("Task #2", "2020-11-12", "abde");
        TaskList testList = new TaskList();
        testList.add(elements);
        TaskItem test = testList.getTaskItem(2);
        assertThrows(IndexOutOfBoundsException.class, ()->testList.editTitle(test));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        TaskList st = new TaskList();
        m.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->st.getTaskDescription(2));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.add(elements);
      assertDoesNotThrow(()->m.getTaskDescription(0));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.getTaskDueDate(2));
    }

    @Test
    public void
    gettingTaskItemTitleFailsWithInvalidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12", "abc");
        m.add(elements);
        assertThrows(IndexOutOfBoundsException.class, ()->m.getTaskTitle(3));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task 5", "2020-11-12", "abc");
        m.add(elements);
        assertDoesNotThrow(()->m.getTaskTitle(0));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task 5", "2020-11-12", "abc");
        m.add(elements);
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
        m.add(elements);
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