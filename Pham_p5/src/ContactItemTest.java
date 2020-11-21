import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues() {
        assertThrows(IllegalArgumentException.class, () -> new ContactItem("", "", "", "", "", "", ""));
    }

    @Test
    public void creationSucceedsWithBlankEmail() {
        assertDoesNotThrow(() -> new ContactItem("Test", "2020-11-21", "abc", "Ben", "Dover", "555-555-5555", ""));
    }


    @Test
    public void creationSucceedsWithBlankFirstName() {
        assertDoesNotThrow(() -> new ContactItem("Test", "2020-11-21", "abc", "", "Dover", "555-555-5555", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankLastName() {
        assertDoesNotThrow(() -> new ContactItem("Test", "2020-11-21", "abc", "Ben", "", "555-555-5555", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithBlankPhone() {
        assertDoesNotThrow(() -> new ContactItem("Test", "2020-11-21", "abc", "Ben", "Dover", "", "x@y.z"));
    }

    @Test
    public void creationSucceedsWithNonBlankValues() {
        assertDoesNotThrow(() -> new ContactItem("Test", "2020-11-21", "abc", "Ben", "Dover", "555-555-5555", "x@y.z"));
    }

    @Test
    public void editingFailsWithAllBlankValues() {

    }

}
/*







editingSucceedsWithBlankEmail()
editingSucceedsWithBlankFirstName()
editingSucceedsWithBlankLastName()
editingSucceedsWithBlankPhone()
editingSucceedsWithNonBlankValues()
testToString()
 */