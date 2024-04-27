package mx.bastekor.demos.contactsservice.service;

import mx.bastekor.demos.contactsservice.model.ContactRequest;
import mx.bastekor.demos.contactsservice.model.ContactResponse;

import java.util.List;

public interface IContactsService {

    List<ContactResponse> findAllContactsByUserId(String userId);

    ContactResponse findContactById(String userId, String contactId);

    ContactResponse addContact(String userId, ContactRequest contactRequest);

    ContactResponse updateContact(String userId, String contactId, ContactRequest contactRequest);

    void removeContact(String userId, String contactId);
}