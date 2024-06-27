package mx.bastekor.demos.contactsservice;

import mx.bastekor.demos.contactsservice.model.Contact;
import mx.bastekor.demos.contactsservice.repository.DataBaseContactsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

import static java.util.UUID.randomUUID;

@SpringBootApplication
public class ContactsServiceMain {

	public static void main(String[] args) {
		SpringApplication.run(ContactsServiceMain.class, args);
	}

	@Bean
	CommandLineRunner init(DataBaseContactsRepository dataBaseContactsRepository) {

		var user001 = randomUUID().toString();
		var user002 = randomUUID().toString();
		var user003 = randomUUID().toString();
		var user004 = randomUUID().toString();

		final var USER_ID = "1qaz2wsx3edC";


		return args -> {
			List<Contact> contacts = Arrays.asList(
					new Contact(user001, USER_ID, user001, "Luis Erik", "Diaz Zuñiga", "Bastekor", "5585975672",  "luiserik.diaz@mail.com", "Cuchilla del Tesoro"),
					new Contact(user002, USER_ID, user002, "Denisse Viridiana", "Diaz Morquecho", "Devii", "1234567890",  "denisseviridiana.diaz@mail.com", "Cuchilla del Tesoro"),
					new Contact(user003, USER_ID, user003, "Ethan Dominik", "Diaz Diaz", "Cacheton", "1234567890",  "ethandominik.diaz@mail.com", "Cuchilla del Tesoro"),
					new Contact(user004, USER_ID, user004, "Ikhal Dimitri", "Diaz Diaz", "Papadon", "1234567890",  "ikhaldimitri.diaz@mail.com", "Cuchilla del Tesoro")
			);
			dataBaseContactsRepository.saveAll(contacts);

		};
	}
}