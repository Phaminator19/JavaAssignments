import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {
    List<TaskItem> AppList;

    public TaskList() {
        AppList = new ArrayList<>();
    }

    public void View_List() {
        int i = 0;
        System.out.println("Current task:");
        System.out.println("----------------");
        for (TaskItem taskItem : AppList) {
            System.out.printf("%d) " + taskItem.getTitle() + ": " + taskItem.getDescription() + " due on " + taskItem.getDue_Date(), i);
            System.out.println("\n");
            i++;
        }
    }

    public void View_Completed_List() {
        for (int i = 0; i < AppList.size(); i++) {
            TaskItem taskItem = AppList.get(i);
            if (taskItem.getDue_Date().startsWith("[Completed] ")) {
                System.out.println(taskItem.getTitle() + ": " + taskItem.getDescription() + " due on " + taskItem.getDue_Date());
            }
        }
    }

    public void addTask(TaskItem tasks) {
        AppList.add(tasks);
    }


    public int size() {
        return AppList.size();
    }

    public void remove(TaskItem tasks) {
        AppList.remove(tasks);
    }

    public TaskItem getTaskItem(int index) {
        TaskItem data = null;
        try {
            data = AppList.get(index);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("WARNING: The task item doesn't exist.");
        }
        return data;
    }

    public String getTaskDescription(int index) {
        return AppList.get(index).getDescription();
    }


    public String getTaskTitle(int index) {
        return AppList.get(index).getTitle();
    }


    public String getTaskDueDate(int index) {
        return AppList.get(index).getDue_Date();
    }


    public void UnmarkAsComplete(TaskItem testItem) {
        try {
            String temp = testItem.getTitle().replace("[Completed]", "");
            testItem.setTitle(temp);
        } catch (Exception err) {
        err.printStackTrace();
        System.out.println("Error: Task Item isn't exist");
        }
    }

    public void write(String filename) {

        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < AppList.size(); i++) {
                TaskItem data = getTaskItem(i);
                output.format("%s %s %s%n", data.getTitle(), data.getDescription(), data.getDue_Date());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public void MarkAsComplete(TaskItem taskItem) {
        try {
            if (taskItem != null) {
                taskItem.setTitle("[Completed]" + taskItem.getTitle());
            }
        } catch (Exception err) {
            err.printStackTrace();
            System.out.println("Error: Task Item isn't exist");
        }
    }
}

