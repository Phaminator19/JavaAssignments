import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskItem {
    private static final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private String Title;
    private String Description;
    private String Due_Date;
    public TaskItem(String Title, String Due_Date, String Description)
    {
        if (isTitleValid(Title)) {
            this.Title = Title;
        } else throw new IllegalArgumentException("WARNING: Title must be at least 1 string, task is not created");

        if (isDueDateValid(Due_Date)) {
            if(isDueDateCorrectFormat(Due_Date)) {
                this.Due_Date = Due_Date;
            }
        } else throw new IllegalArgumentException("WARNING: Due date is empty, task is not created");

        this.Description = Description;
    }

    public void setTitle(String Title) {
        if (isTitleValid(Title)) {
            this.Title = Title;
        } else throw new IllegalArgumentException("WARNING: Title must be at least 1 string, task is not created");
    }

    public void setDescription (String description) {
        this.Description = description;
    }

    public void setDue_Date (String date) {
        if (isDueDateValid(Due_Date)) {
            if(isDueDateCorrectFormat(Due_Date)) {
                this.Due_Date = Due_Date;
            }
        } else throw new IllegalArgumentException("WARNING: Due date is empty, task is not created");
    }

    public String getTitle () {
        return Title;
    }

    public String getDue_Date() {
        return Due_Date;
    }

    public String getDescription () {
        return Description;
    }

    private boolean isTitleValid(String Title) {
        return Title.length() > 0;
    }

    private boolean isDueDateValid(String Due_Date) {
        return Due_Date.length() > 0;
    }

    private boolean isDueDateCorrectFormat(String Due_Date) {

        boolean validity = false;
        try {
            date.parse(Due_Date);
            date.setLenient(false);
            validity = true;
        }catch (ParseException e) {
            e.printStackTrace();
            validity = false;
        }
        return validity;
    }

    public static void setFormatDate(String Due_date) {
        date.format(Due_date);
    }



    /*




     */
}
