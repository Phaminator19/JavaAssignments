import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TaskItem {
    private String Title;
    private String Description;
    private String Due_Date;

    public TaskItem(String Title, String Due_Date, String Description)
    {
        if (isTitleValid(Title)) {
            this.Title = Title;
        } else throw new InvalidTitleException("WARNING: Title must be at least 1 string, task is not created");

        if (isDueDateValid(Due_Date)) {
            if(isDueDateCorrectFormat(Due_Date)) {
                this.Due_Date = Due_Date;
            }else throw new InvalidDateException("WARNING: Due date is incorrect, task is not created%n");
        }else throw new InvalidDateException("WARNING: Due date is empty, task is not created%n");

        this.Description = Description;
    }

    public void setTitle(String Title) {
        if (isTitleValid(Title)) {
            this.Title = Title;
        } else throw new InvalidTitleException("WARNING: New title is not at least 1 string long. It did not save the new title");
    }

    public void setDescription (String description) {
        this.Description = description;
    }

    public void setDue_Date (String date) {
        if (isDueDateCorrectFormat(date)) {
            this.Due_Date = date;
        } else throw new InvalidDateException("Warning: New date is not in the correct format. It did not save the new date");
    }

    public void editDescription(String Description) {
        setDescription(Description);
    }

    public void editTitle(String title) {
            setTitle(title);
    }

    public void editDate(String date) {
        setDue_Date(date);
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
        boolean validity;
        try {
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            dateformat.setLenient(false);
            dateformat.parse(Due_Date);
            validity = true;
        }catch (ParseException e) {
            validity = false;
        }
        return validity;
    }

static class InvalidTitleException extends IllegalArgumentException {
        public InvalidTitleException (String msg) {
            super(msg);
        }
}

static class InvalidDateException extends IllegalArgumentException {
        public InvalidDateException(String msg) {
            super(msg);
        }
}

}
