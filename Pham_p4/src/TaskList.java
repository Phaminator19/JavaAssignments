import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<TaskItem> list;
    public TaskList () {
        list = new ArrayList<>();
    }

    public void View_List () {
        int i = 1;
        for (TaskItem taskItem : list) {
            System.out.printf("%d)" + taskItem.getTitle() + taskItem.getDue_Date(), i);
            System.out.println("\n");
            i++;
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

    public TaskItem get(int index) {
        return list.get(index);
    }

    public void edit (String title, String date, String Description, TaskItem tasks) {
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
    }

    public void OpenFile(String filename) {
    }
}

