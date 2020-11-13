import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        List<TaskItem> items = new ArrayList<>();
        TaskItem.add(items.add(elements));
        TaskItem.add(items.add(elements));
        assertEquals(2,items.size());
    }

    @Test
    public void
}