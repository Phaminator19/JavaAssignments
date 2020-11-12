import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate(){
       TaskItem task = new TaskItem();
       assertEquals("Warning: invalid due date; task is not created", task.getDate("11-10-2020"));
    }
/*
creatingTaskItemFailsWithInvalidTitle()
creatingTaskItemSucceedsWithValidDueDate()
creatingTaskItemSucceedsWithValidTitle()
settingTaskItemDueDateFailsWithInvalidDate()
settingTaskItemDueDateSucceedsWithValidDate()
settingTaskItemTitleFailsWithInvalidTitle()
settingTaskItemTitleSucceedsWithValidTitle()
 */

}