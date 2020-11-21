public class ContactItem{
    private String FirstName;
    private String LastName;
    private String EmailAddress;
    private String PhoneNumber;

    public ContactItem(String firstname, String LastName, String PhoneNumber, String EmailAddress) {
        this.FirstName = firstname;
        this.LastName = LastName;
        this.EmailAddress = EmailAddress;
        this.PhoneNumber = PhoneNumber;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


    public void editContact(String FirstName, String LastName, String PhoneNumber, String EmailAddress) {
        setFirstName(FirstName);
        setLastName(LastName);
        setEmailAddress(EmailAddress);
        setPhoneNumber(PhoneNumber);
    }

    @Override
    public String toString () {
        return FirstName + ", " + LastName + " Email: " + EmailAddress + " and phone number: "+ PhoneNumber;
    }
}
