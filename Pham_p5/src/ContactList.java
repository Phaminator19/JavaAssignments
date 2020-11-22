import java.util.ArrayList;
import java.util.List;

public class ContactList extends TaskList{
    List<ContactList> contactList;
    public ContactList () {
        contactList = new ArrayList<>();
    }
    public void ViewContactList() {
        super.View_List();
    }
}
