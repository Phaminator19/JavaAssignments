import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TaskList {
    List<TaskItem> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void View_List() {
        int i = 0;
        for (TaskItem taskItem : list) {
            System.out.printf("%d)" + taskItem.getDue_Date() + taskItem.getTitle(), i);
            System.out.println("\n");
            i++;
        }
    }

    public void View_Completed_List() {
        for (int i = 0; i < list.size(); i++) {
            TaskItem taskItem = list.get(i);
            if (taskItem.getDue_Date().startsWith("*")) {
                System.out.println(taskItem.getDue_Date() + taskItem.getTitle() + ": " + taskItem.getDescription());
            }
        }
    }

    public void add(TaskItem tasks) {
        list.add(tasks);
    }

    public int size() {
        return list.size();
    }

    public void remove(TaskItem tasks) {
        list.remove(tasks);
    }

    public TaskItem getTaskItem(int index) {
        TaskItem data = null;
        try {
            data = list.get(index);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("WARNING: The task item doesn't exist.");
        }
        return data;
    }

    public String getTaskDescription(int index) {
        return list.get(index).getDescription();
    }


    public String getTaskTitle(int index) {
        return list.get(index).getTitle();
    }


    public String getTaskDueDate(int index) {
        return list.get(index).getDue_Date();
    }

    public void edit(String title, String date, String Description, TaskItem tasks) {
        try {
            tasks.setTitle(title);
            tasks.setDescription(Description);
            tasks.setDue_Date(date);
        } catch (IllegalArgumentException err) {
            throw new IllegalArgumentException("WARNING: Task is not created. Title or Date isn't correct");
        }
    }

    public void editDescription(TaskItem tasks) {
        tasks.setDescription("");
    }

    public void editTitle(TaskItem tasks) {
        tasks.setTitle("");
    }

    public void editDate(TaskItem tasks) {
        tasks.setDue_Date("");
    }


    public void UnmarkAsComplete(TaskItem testItem) {
        String temp = testItem.getTitle().replace("[Completed]", "");
        testItem.setTitle(temp);
    }

    public void write(String filename) {
        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < list.size(); i++) {
                TaskItem data = getTaskItem(i);
                output.format("[%s] %s: %s%n", data.getDue_Date(), data.getTitle(), data.getDescription());
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

