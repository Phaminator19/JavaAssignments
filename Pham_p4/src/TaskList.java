import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<TaskItem> list;
    public TaskList () {
        list = new ArrayList<>();
    }

    public void View_List () {
        for (TaskItem taskItem : list) {
            System.out.println(taskItem.getTitle() + taskItem.getDue_Date());
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


}

