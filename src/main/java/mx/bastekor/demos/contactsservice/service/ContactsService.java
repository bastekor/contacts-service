package mx.bastekor.demos.contactsservice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.bastekor.demos.contactsservice.dao.IContactDao;
import mx.bastekor.demos.contactsservice.mapper.ContactsMapper;
import mx.bastekor.demos.contactsservice.model.ContactRequest;
import mx.bastekor.demos.contactsservice.model.ContactResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ContactsService implements IContactsService {

    private IContactDao contactDao;

    @Override
    public List<ContactResponse> findAllContactsByUserId(final String userId) {
        return ContactsMapper.toContactResponseList(contactDao.getContacts(userId));
    }

    @Override
    public ContactResponse findContactById(final String userId, final String contactId) {
        var contact = contactDao.getContact(userId, contactId);
        return ContactsMapper.toContactResponse(contact);
    }

    @Override
    public ContactResponse addContact(String userId, ContactRequest contactRequest) {

        log.info("addContact request: {}", contactRequest);
        var contact = ContactsMapper.toContact(userId, contactRequest);
        contactDao.createContact(contact);
        return ContactsMapper.toContactResponse(contact);

    }

    @Override
    public ContactResponse updateContact(final String userId, final String contactId, final ContactRequest contactRequest) {

        log.info("updateContact request: {}", contactRequest);

        var contact = ContactsMapper.toContact(userId, contactRequest);
        contact.setId(contactId);
        contact.setContactId(contactId);
        contactDao.updateContact(userId, contactId, contact);
        return ContactsMapper.toContactResponse(contact);
    }

    @Override
    public void removeContact(final String userId, final String contactId) {
        contactDao.deleteContact(userId, contactId);
    }
}