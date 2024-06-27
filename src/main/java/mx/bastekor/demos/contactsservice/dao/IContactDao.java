package mx.bastekor.demos.contactsservice.dao;

import mx.bastekor.demos.contactsservice.model.Contact;

import java.util.List;

public interface IContactDao {

    List<Contact> getContacts(String userId);
    Contact getContact(String userId, String contactId);
    void createContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(String userId, String contactId);
}