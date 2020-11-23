import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {
    @Test
    public void newListIsEmpty() {
        ContactList contactList = new ContactList();
        assertEquals(0, contactList.size());
    }
    @Test
    public void addingItemsIncreasesSize() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertEquals(1, contactList.size());
    }

    @Test
    public void editingItemsFailsWithAllBlankValues() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertThrows(ContactItem.InvalidValueException.class, ()->contactList.editConfirm(0, "","","",""));
    }

    @Test
    public void editingItemsFailsWithInvalidIndex() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);

        assertThrows(IndexOutOfBoundsException.class, ()->contactList.editConfirm(1, contactItem.getFirstName(), "Ten", "123-456-7890", "a@x.z"));
    }

    @Test
    public void editingSucceedsWithBlankFirstName() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertDoesNotThrow(()->contactList.editConfirm(0, "", "Ten", "123-456-7890", "a@x.z"));
    }

    @Test
    public void editingSucceedsWithBlankLastName() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertDoesNotThrow(()->contactList.editConfirm(0, "Ash", "", "123-456-7890", "a@x.z"));
    }

    @Test
    public void editingSucceedsWithBlankPhone() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertDoesNotThrow(()->contactList.editConfirm(0, "Ash", "Ketchum", "", "a@x.z"));
    }

    @Test
    public void editingSucceedsWithNonBlankValues() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        assertDoesNotThrow(()->contactList.editConfirm(0, "Ash", "Ketchum", "407-227-9593", "a@x.z"));
    }

    @Test
    public void removingItemsDecreasesSize() {
        ContactList contactList = new ContactList();
        ContactItem contactItem = new ContactItem("Ben", "Dover", "555-555-5555", "x@y.z");
        contactList.addContactItem(contactItem);
        contactList.remove(0);

        assertEquals(0, contactList.size());
    }

    @Test
    public void savedContactListCanBeLoaded() throws FileNotFoundException {
        ContactList contactList = new ContactList();

        contactList.addContactItem(new ContactItem("Ash", "Ketchum", "407-777-7777", "a@y.z"));
        contactList.addContactItem(new ContactItem("Brian", "Angel", "555-555-5555",
                "a@z.m"));

        contactList.write("contact.txt");
        contactList.load("contact.txt");

        assertEquals("Ash Ketchum , Email Address: a@y.z , Phone Number: 407-777-7777", contactList.getContactText(0));
        assertEquals("Brian Angel , Email Address: a@z.m , Phone Number: 555-555-5555", contactList.getContactText(1));
    }


}
