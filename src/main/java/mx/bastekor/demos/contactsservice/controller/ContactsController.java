package mx.bastekor.demos.contactsservice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import mx.bastekor.demos.contactsservice.model.ContactRequest;
import mx.bastekor.demos.contactsservice.model.ContactResponse;
import mx.bastekor.demos.contactsservice.service.IContactsService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/v1/contacts")
public class ContactsController {

    private final IContactsService contactsService;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = {"application/json"})
    public List<ContactResponse> getAllContacts(@RequestHeader("Authorization") String userId) {
        return contactsService.findAllContactsByUserId(userId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ContactResponse getContact(@RequestHeader("Authorization") String userId, @PathVariable("id") String contactId) {
        return contactsService.findContactById(userId, contactId);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public ContactResponse createContact(@RequestHeader("Authorization") String userId,
                                         @Validated @RequestBody ContactRequest contactRequest) {
        return contactsService.addContact(userId, contactRequest);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}", consumes = {"application/json"}, produces = {"application/json"})
    public ContactResponse updateContact(
            @RequestHeader("Authorization") String userId,
            @PathVariable("id") String contactId,
            @RequestBody ContactRequest contactRequest) {
        return contactsService.updateContact(userId, contactId, contactRequest);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    public void deleteContact(
            @RequestHeader("Authorization") String userId,
            @PathVariable("id") String contactId) {
        contactsService.removeContact(userId, contactId);
    }
}