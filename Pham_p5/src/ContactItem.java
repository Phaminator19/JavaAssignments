public class ContactItem{

    private String FirstName;
    private String LastName;
    private String EmailAddress;
    private String PhoneNumber;

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public ContactItem(String firstname, String LastName, String EmailAddress, String PhoneNumber) {
            if (isValueAtLeastOne(firstname) || isValueAtLeastOne(LastName)
                    || isValueAtLeastOne(PhoneNumber) || isValueAtLeastOne(EmailAddress)) {
                //This if case will check there is at least one value that is not blank.
                // If all value has blank string then throw an exception.

                this.FirstName = firstname;
                this.LastName = LastName;
                this.EmailAddress = EmailAddress;
                this.PhoneNumber = PhoneNumber;
            }
            else throw new InvalidValueException ("Contact Item need to has at least 1 value that is not empty. " +
                    "Please try again!");

    }

    private void setFirstName(String firstName) {
        FirstName = firstName;
    }

    private void setLastName(String lastName) {
        LastName = lastName;
    }

    private void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    private void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


    public void editContact(String FirstName, String LastName, String EmailAddress, String PhoneNumber) {
            if ((isValueAtLeastOne(FirstName) || isValueAtLeastOne(LastName))
                    || (isValueAtLeastOne(PhoneNumber) || isValueAtLeastOne(EmailAddress))) {
                setFirstName(FirstName);
                setLastName(LastName);
                setEmailAddress(EmailAddress);
                setPhoneNumber(PhoneNumber);
            }
        else throw new InvalidValueException ("Contact did not get edited. " +
                    "Contact Item need to has at least 1 value that is not empty. Please try again!");
    }

    private boolean isValueAtLeastOne (String value) {
        /*
        This function will is check for many parameter. Check for at least there is one value. Otherwise return false.
        */
        return value.length() > 0;
    }

    static class InvalidValueException extends IllegalArgumentException {
        public InvalidValueException (String msg) {
            super(msg);
        }
    }

    @Override
    public String toString () {
        return FirstName + ", " + LastName + " Email: " + EmailAddress + " and phone number: "+ PhoneNumber;
    }
}
