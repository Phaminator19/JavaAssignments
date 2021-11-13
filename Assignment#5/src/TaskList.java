import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class TaskList extends ListType{

    List<TaskItem> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void View_Completed_List() {
        System.out.printf("Completed List:%n" + "------------ %n");
        for (int i = 0; i < taskList.size(); i++) {
            TaskItem taskItem = taskList.get(i);
            if (taskItem.getDue_Date().startsWith("[Completed] ")) {
                System.out.println(taskItem.getTitle() + ": " + taskItem.getDescription() + " due on " + taskItem.getDue_Date());
            }
        }
    }

    public void addTask(TaskItem tasks) {
        taskList.add(tasks);
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public void remove(int index) {
        taskList.remove(taskList.get(index));
    }

    public TaskItem getTaskItem(int index) {
        TaskItem data = null;
        try {
            data = taskList.get(index);
        } catch (IndexOutOfBoundsException error) {
            System.out.println("WARNING: The task item doesn't exist.");
        }
        return data;
    }

    public String getTaskDescription(int index) {
        try {
            return taskList.get(index).getDescription();
        } catch (IndexOutOfBoundsException error) {
            throw new IndexOutOfBoundsException("WARNING: The task item doesn't exist. Try again.");
        }
    }

    public String getTaskTitle(int index) {
        try {
            return taskList.get(index).getTitle();
        } catch (IndexOutOfBoundsException error) {
            throw new IndexOutOfBoundsException("Warning: The task item doesn't exist. Try again.");
        }
    }

    public String getTaskDueDate(int index) {
        try {
            return taskList.get(index).getDue_Date();
        } catch (IndexOutOfBoundsException error) {
            throw new IndexOutOfBoundsException("WARNING: The task item doesn't exist. Try again");
        }
    }

    public void UnmarkAsComplete(int index) {
        try {
            TaskItem item = taskList.get(index);
            String temp = item.getTitle().replace("[Completed] ", "");
            item.setTitle(temp);
        } catch (IndexOutOfBoundsException err) {
            throw new IndexOutOfBoundsException("Error: The task item doesn't exist. Cannot be unmarked");
        }
    }

    public void editConfirm(int index, String title, String date, String Description) {
        try {
            TaskItem temp = taskList.get(index);
            temp.editTitle(title);
            temp.editDescription(Description);
            temp.editDate(date);
        } catch (TaskItem.InvalidTitleException err) {
            throw new TaskItem.InvalidTitleException("WARNING: Task is not edited. Title is not at least 1 string long");
        } catch (TaskItem.InvalidDateException dateError) {
            throw new TaskItem.InvalidDateException("WARNING: Task is not edited. Date is not in the correct format");
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("ERROR: The Task doesn't exist to be edited. Please try again.");
        }
    }
    @Override
    public void write(String filename) {

        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < taskList.size(); i++) {
                TaskItem data = getTaskItem(i);
                output.format("%s%n%s%n%s%n", data.getTitle(), data.getDescription(), data.getDue_Date());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void MarkAsComplete(int index) {
        try {
            TaskItem temp = taskList.get(index);
            if (temp != null) {
                temp.setTitle("[Completed] " + temp.getTitle());
            }
        } catch (IndexOutOfBoundsException err) {
            throw new IndexOutOfBoundsException("Error: Task Item isn't exist");
        }
    }

    @Override
    public void load(String filename) throws FileNotFoundException {
        try {

            File myObj = new File(filename);
            Scanner reader = new Scanner(myObj);

            while (reader.hasNextLine()) {
                TaskItem myItem = new TaskItem("Test", "2001-09-11", "placeholder");

                String title = reader.nextLine();
                String description = reader.nextLine();
                String date = reader.nextLine();

                myItem.editTitle(title);
                myItem.editDescription(description);
                myItem.editDate(date);

                addTask(myItem);

            }
        } catch (FileNotFoundException error) {
            throw new FileNotFoundException("WARNING: File does not found. Please try again and double check.");
        } catch (TaskItem.InvalidTitleException error) {
            throw new TaskItem.InvalidTitleException("Warning: Title is not at least 1 string long.");
        } catch (TaskItem.InvalidDateException error) {
            throw new TaskItem.InvalidDateException("Warning: Date is not in the correct format.");
        }
    }

    public String getTaskText(int index) {
        TaskItem item = taskList.get(index);
        return item.getTitle() + " " + item.getDescription() + " " + item.getDue_Date() + "%n";
    }

    @Override
    public void view() {
        int i = 0;
        System.out.println("Current task:");
        System.out.println("----------------");
        for (TaskItem taskItem : taskList) {
            System.out.printf("%d) " + taskItem.getTitle() + ": " + taskItem.getDescription() + " due on " + taskItem.getDue_Date(), i);
            System.out.println("\n");
            i++;
        }
    }
}

