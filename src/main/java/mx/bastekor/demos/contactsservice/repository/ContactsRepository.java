package mx.bastekor.demos.contactsservice.repository;

import mx.bastekor.demos.contactsservice.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactsRepository extends MongoRepository<Contact, String> {

    List<Contact> findByUserId(String userId);
    Contact findByUserIdAndContactId(String userId, String contactId);
    void deleteByUserIdAndContactId(String userId, String contactId);
}