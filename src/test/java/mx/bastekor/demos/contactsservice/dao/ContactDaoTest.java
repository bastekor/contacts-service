package mx.bastekor.demos.contactsservice.dao;

import mx.bastekor.demos.contactsservice.model.Contact;
import mx.bastekor.demos.contactsservice.repository.ContactsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactDaoTest {

    @Mock
    private ContactsRepository contactsRepository;

    @InjectMocks
    private ContactDao contactDao;

    private static final String USER_ID = "123456qwerty";

    private static final String CONTACT_ID = "6a95f54d-4f4b-432a-9912-536f43a979bd";

    private List<Contact> contacts;

    @BeforeEach
    void load() {
        var uuid1 = UUID.randomUUID().toString();
        var c1 = new Contact(uuid1, USER_ID, uuid1, "Name 001", "Sur Name 001", "Alias 001", "Phone Number 001", "Email 001", "Address 001");
        var uuid2 = UUID.randomUUID().toString();
        var c2 = new Contact(uuid2, USER_ID, uuid2, "Name 002", "Sur Name 002", "Alias 002", "Phone Number 002", "Email 002", "Address 002");
        var uuid3 = UUID.randomUUID().toString();
        var c3 = new Contact(uuid3, USER_ID, uuid3, "Name 003", "Sur Name 003", "Alias 003", "Phone Number 003", "Email 003", "Address 003");
        contacts = List.of(c1, c2, c3);
    }

    @Test
    void getContacts() {

        when(contactsRepository.findByUserId(anyString())).thenReturn(contacts);
        List<Contact> contacts = contactDao.getContacts(USER_ID);

//        contacts.forEach(System.out::println);

        assertNotNull(contacts);
        assertEquals(contacts.size(), this.contacts.size());
    }

    @Test
    void getContact() {
        when(contactsRepository.findByUserIdAndContactId(anyString(), anyString()))
                .thenReturn(Optional.of(this.contacts.get(2)));
        Contact contact = contactDao.getContact(USER_ID, CONTACT_ID);

//        System.out.println(contact);

        assertNotNull(contact);
        assertEquals(contact.getUserId(), USER_ID);
    }

    @Test
    void createContact() {

        when(contactsRepository.save(any())).thenReturn(new Contact());

        var contact = new Contact();
        contactDao.createContact(contact);

        verify(contactsRepository, times(1)).save(any());
    }

    @Test
    void updateContact() {

        when(contactsRepository.save(any())).thenReturn(new Contact());

        var contact = new Contact();
        contactDao.updateContact(USER_ID, CONTACT_ID, contact);

        verify(contactsRepository, times(1)).save(any());
    }

    @Test
    void deleteContact() {

        Mockito.doNothing().when(contactsRepository).deleteByUserIdAndContactId(anyString(), anyString());

        var contact = new Contact();
        contactDao.deleteContact(USER_ID, CONTACT_ID);

        verify(contactsRepository, times(1)).deleteByUserIdAndContactId(anyString(), anyString());
    }
}