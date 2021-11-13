import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(ContactItem.InvalidValueException.class, () -> new ContactItem("", "", "", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "", "555-555-5555"));
    }


    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("", "Dover", "x@y.z", "555-555-5555"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "", "x@y.z", "555-555-5555"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "x@y.z", ""));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555"));
    }

    @Test
    public void editingFailsWithAllBlankValues() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertThrows(ContactItem.InvalidValueException.class, () -> contactItem.editContact( "", "", "", ""));
    }

    @Test
    public void editingSucceedsWithBlankEmail() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(() -> contactItem.editContact("Ben", "Dover", "555-555-5555", ""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(() -> contactItem.editContact("", "Dover", "555-555-5555", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "", "555-555-5555", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "Ketchup", "", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "Ketchup", "a@b.c", "777-888-9999"));
    }

    @Test
    public void testToString() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertDoesNotThrow(contactItem::toString);
    }

    @Test
    public void getFirstNameTest() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertEquals("Ben", contactItem.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertEquals("Dover", contactItem.getLastName());
    }

    @Test
    public void getEmailAddressTest() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertEquals("x@y.z", contactItem.getEmailAddress());
    }

    @Test
    void getPhoneNumberTest() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "x@y.z", "555-555-5555");
        assertEquals("555-555-5555", contactItem.getPhoneNumber());
    }

}
