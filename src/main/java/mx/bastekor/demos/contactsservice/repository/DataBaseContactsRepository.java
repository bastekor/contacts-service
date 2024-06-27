package mx.bastekor.demos.contactsservice.repository;

import jakarta.transaction.Transactional;
import mx.bastekor.demos.contactsservice.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public interface DataBaseContactsRepository extends JpaRepository<Contact, String> {

    List<Contact> findByUserId(String userId);
    Optional<Contact> findByUserIdAndContactId(String userId, String contactId);
    void deleteByUserIdAndContactId(String userId, String contactId);
}