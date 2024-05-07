package mx.bastekor.demos.contactsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public record ContactRequest(
        @NotBlank(message = "Nombre(s) obligatorio(s)")
        @JsonProperty("name")
        String name,

        @NotBlank(message = "Apellidos(s) obligatorio(s)")
        @JsonProperty("sur_name")
        String surName,

        @JsonProperty("alias")
        String alias,

        @NotBlank(message = "El teléfono es obligatorio")
        @JsonProperty("phone_number")
        String phoneNumber,

        @Email(message = "El email es invalido")
        @NotBlank(message = "El email es obligatorio")
        @JsonProperty("email")
        String email,

        @JsonProperty("address")
        String address
//        ,@JsonProperty("photo")
//        MultipartFile photo
) { }