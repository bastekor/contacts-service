package mx.bastekor.demos.contactsservice.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mx.bastekor.demos.contactsservice.model.Contact;
import mx.bastekor.demos.contactsservice.model.ContactRequest;
import mx.bastekor.demos.contactsservice.model.ContactResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ContactsMapper {

    public static Contact toContact(final String userId, final ContactRequest contactRequest) {
        if (Objects.nonNull(contactRequest)) {
            var uuid = UUID.randomUUID().toString();
            var contact = new Contact();
            contact.setId(uuid);
            contact.setUserId(userId);
            contact.setContactId(uuid);
            contact.setName(contactRequest.name());
            contact.setSurName(contactRequest.surName());
            contact.setAlias(contactRequest.alias());
            contact.setPhoneNumber(contactRequest.phoneNumber());
            contact.setEmail(contactRequest.email());
            contact.setAddress(contactRequest.address());
            return contact;
        }
        return null;
    }

    public static ContactResponse toContactResponse(Contact contact) {
        if (Objects.nonNull(contact)) {
            return new ContactResponse(
                    contact.getContactId(),
                    contact.getName(),
                    contact.getSurName(),
                    contact.getAlias(),
                    contact.getPhoneNumber(),
                    contact.getEmail(),
                    contact.getAddress(),
                    LocalDateTime.now().toString(),
                    true
            );
        }
        return null;
    }

    public static List<ContactResponse> toContactResponseList(List<Contact> contacts) {
        if (Objects.nonNull(contacts) && !contacts.isEmpty()) {
            return contacts.stream()
                    .map(ContactsMapper::toContactResponse)
                    .toList();
        }
        return null;
    }
}