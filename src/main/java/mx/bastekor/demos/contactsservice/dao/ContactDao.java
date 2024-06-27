package mx.bastekor.demos.contactsservice.dao;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.bastekor.demos.contactsservice.exception.ContactNotFoundException;
import mx.bastekor.demos.contactsservice.model.Contact;
import mx.bastekor.demos.contactsservice.repository.DataBaseContactsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class ContactDao implements IContactDao {

    private final DataBaseContactsRepository repository;

    @Override
    public List<Contact> getContacts(final String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Contact getContact(final String userId, final String contactId) {
        return repository.findByUserIdAndContactId(userId, contactId)
                .orElseThrow(ContactNotFoundException::new);
    }

    @Override
    public void createContact(Contact contact) {
        repository.save(contact);
    }

    @Override
    public void updateContact(final Contact contact) {
        repository.save(contact);
    }

    @Override
    public void deleteContact(final String userId, final String contactId) {
        repository.deleteByUserIdAndContactId(userId, contactId);
    }
}