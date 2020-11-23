import java.util.ArrayList;
import java.util.List;

public class ContactList extends TaskList{
    List<ContactItem> contactList;
    public ContactList () {
        contactList = new ArrayList<>();
    }
    public void ViewContactList() {
        super.View_List();
    }


    public void addContactItem(ContactItem contactItem) {
        contactList.add(contactItem);
    }

    public void editConfirm(int index, String FirstName, String LastName, String PhoneNumber, String EmailAddress) {
        try {
            ContactItem temp = contactList.get(index);
            temp.setFirstName(FirstName);
            temp.setLastName(LastName);
            temp.setPhoneNumber(PhoneNumber);
            temp.setEmailAddress(EmailAddress);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("WARNING: The contact you try to access doesn't exist. Please try again!");
        }
    }

    public void remove(int index) {
        super.remove(index);
        contactList.remove(index);

    }
}
