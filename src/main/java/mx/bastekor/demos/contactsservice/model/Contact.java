package mx.bastekor.demos.contactsservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Contacts")
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