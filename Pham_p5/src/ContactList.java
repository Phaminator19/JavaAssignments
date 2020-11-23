import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class ContactList extends ListType{

    List<ContactItem> contactList;

    public ContactList () {
        contactList = new ArrayList<>();
    }

    public void addContactItem(ContactItem contactItem) {
        contactList.add(contactItem);
    }

    public void editConfirm(int index, String FirstName, String LastName, String PhoneNumber, String EmailAddress) {
        try {
            ContactItem temp = contactList.get(index);
            temp.setFirstName(FirstName);
            temp.setLastName(LastName);
            temp.setEmailAddress(EmailAddress);
            temp.setPhoneNumber(PhoneNumber);
        } catch (IndexOutOfBoundsException ex) {
            throw new IndexOutOfBoundsException("WARNING: The contact you try to access doesn't exist. Please try again!");
        }
    }

    public ContactItem getContactItem(int index) {
        return contactList.get(index);
    }

    public String getContactText (int index) {
        ContactItem contactItem = contactList.get(index);
        return contactItem.getFirstName() + " " + contactItem.getLastName() + " , " + "Email Address: " + contactItem.getEmailAddress()
                + " , " + "Phone Number: " + contactItem.getPhoneNumber();
    }

    @Override
    public void remove(int index) {
        contactList.remove(index);
    }

    @Override
    public int size() {
        return contactList.size();
    }

    @Override
    public void write (String filename) {
        try (Formatter output = new Formatter(filename)) {
            for (int i = 0; i < contactList.size(); i++) {
                ContactItem data = getContactItem(i);
                output.format("%s%n%s%n%s%n%s%n", data.getFirstName(), data.getLastName(), data.getEmailAddress(), data.getPhoneNumber());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find the file...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void view() {
        int i = 0;
        System.out.println("Current contacts:");
        System.out.println("-------------------");
        for (ContactItem contactItem : contactList) {
            System.out.printf("%d) " + "Name: " + contactItem.getFirstName() + " " + contactItem.getLastName() + " Email address: " + contactItem.getEmailAddress()
                    + " Phone Number: " + contactItem.getPhoneNumber(), i);
            System.out.println("\n");
            i++;
        }
    }

    @Override
    public void load (String filename) throws FileNotFoundException {
        try {

            File myObj = new File(filename);
            Scanner reader = new Scanner(myObj);

            while (reader.hasNextLine()) {

                ContactItem myItem = new ContactItem("Test", "placeholder", "123-456-7890", "a@b.c");

                String FirstName = reader.nextLine();
                String LastName = reader.nextLine();
                String EmailAddress = reader.nextLine();
                String PhoneNumber = reader.nextLine();

                myItem.setFirstName(FirstName);
                myItem.setLastName(LastName);
                myItem.setEmailAddress(EmailAddress);
                myItem.setPhoneNumber(PhoneNumber);

                addContactItem(myItem);

            }
        } catch (FileNotFoundException error) {
            throw new FileNotFoundException("WARNING: File does not found. Please try again and double check.");
        } catch (ContactItem.InvalidValueException ex) {
            throw new ContactItem.InvalidValueException("Warning: There is not at least a value that is 1 string long. All blank is invalid.");
        }
    }
}

