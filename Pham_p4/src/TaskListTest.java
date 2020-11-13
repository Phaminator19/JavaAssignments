import org.junit.jupiter.api.Test;

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
        List<TaskItem> tasks= new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem("Task #1", "2020-11-12");
        App app = new App();

        m.add(tasks.add(elements));
        String s = m.MarkAsComplete(tasks.get(1));
        assertEquals("[Completed]" + s, app.ViewATask(1));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        /*
        List <TaskItem> tasks = new ArrayList<>();
        TaskList m = new TaskList();
        TaskItem elements = new TaskItem();
        App app = new App();
*/


    }
}