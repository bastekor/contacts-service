package mx.bastekor.demos.contactsservice.service;

import mx.bastekor.demos.contactsservice.dao.IContactDao;
import mx.bastekor.demos.contactsservice.model.Contact;
import mx.bastekor.demos.contactsservice.model.ContactRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactsServiceTest {

    @Mock
    private IContactDao contactDao;

    @InjectMocks
    private ContactsService contactsService;

    private static final String USER_ID = "123456qwerty";

    private static final String CONTACT_ID = "6a95f54d-4f4b-432a-9912-536f43a979bd";

    private List<Contact> contacts;

    private ContactRequest contactRequest;

    @BeforeEach
    void load() {
        contacts = new ArrayList<>();
        var uuid1 = UUID.randomUUID().toString();
        var c1 = new Contact(uuid1, USER_ID, uuid1, "Name 001", "Sur Name 001", "Alias 001", "Phone Number 001", "Email 001", "Address 001");
        var uuid2 = UUID.randomUUID().toString();
        var c2 = new Contact(uuid2, USER_ID, uuid2, "Name 002", "Sur Name 002", "Alias 002", "Phone Number 002", "Email 002", "Address 002");
        var uuid3 = UUID.randomUUID().toString();
        var c3 = new Contact(uuid3, USER_ID, uuid3, "Name 003", "Sur Name 003", "Alias 003", "Phone Number 003", "Email 003", "Address 003");
        contacts = List.of(c1, c2, c3);

        contactRequest = new ContactRequest("Name 001", "Sur Name 001", "Alias 001", "Phone Number 001", "Email 001", "Address 001");
    }

    @Test
    void findAllContactsByUserId() {
        when(contactDao.getContacts(anyString())).thenReturn(contacts);
        var contactResponseList = contactsService.findAllContactsByUserId(USER_ID);
        assertThat(contactResponseList)
                .isNotNull()
                .isNotEmpty()
                .size()
                .isEqualTo(3);
    }

    @Test
    void findContactById() {

        when(contactDao.getContact(anyString(), anyString())).thenReturn(contacts.get(0));
        var contact = contactsService.findContactById(USER_ID, CONTACT_ID);
        assertThat(contact)
                .isNotNull()
                .extracting("name")
                .isEqualTo("Name 001");
    }

    @Test
    void addContact() {

        var contactResponse = contactsService.addContact(USER_ID, contactRequest);
        assertThat(contactResponse)
                .isNotNull()
                .extracting("contactId", "date", "photo")
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void updateContact() {
        var contactResponse = contactsService.updateContact(USER_ID, CONTACT_ID, contactRequest);
        assertThat(contactResponse)
                .isNotNull()
                .extracting("contactId", "date", "photo")
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void removeContact() {
        doNothing().when(contactDao).deleteContact(anyString(), anyString());
        contactsService.removeContact(USER_ID, CONTACT_ID);
        Mockito.verify(contactDao, Mockito.times(1)).deleteContact(anyString(), anyString());

    }
}