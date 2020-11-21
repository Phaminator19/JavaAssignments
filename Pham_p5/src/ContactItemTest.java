import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> new ContactItem("", "", "", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "555-555-5555", ""));
    }


    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("", "Dover", "555-555-5555", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "", "555-555-5555", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z"));
    }

    @Test
    public void editingFailsWithAllBlankValues() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertThrows(IllegalArgumentException.class, () -> contactItem.editContact( "", "", "", ""));
    }

    @Test
    public void editingSucceedsWithBlankEmail() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(() -> contactItem.editContact("Ben", "Dover", "555-555-5555", ""));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(() -> contactItem.editContact("", "Dover", "555-555-5555", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "", "555-555-5555", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "Ketchup", "", "a@b.c"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(() -> contactItem.editContact("Ash", "Ketchup", "777-888-9999", "a@b.c"));
    }

    @Test
    public void testToString() {
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        assertDoesNotThrow(contactItem::toString);
    }

}
