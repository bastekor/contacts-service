package mx.bastekor.demos.contactsservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("contact-registers")
public class Contact {

    @Id
    private String id;
    private String userId;
    private String contactId;
    private String name;
    private String surName;
    private String alias;
    private String phoneNumber;
    private String email;
    private String address;
}