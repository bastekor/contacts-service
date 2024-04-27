package mx.bastekor.demos.contactsservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public record ContactRequest(
        @JsonProperty("name") String name,
        @JsonProperty("sur_name") String surName,
        @JsonProperty("alias") String alias,
        @JsonProperty("phone_number") String phoneNumber,
        @JsonProperty("email") String email,
        @JsonProperty("address") String address
//        ,@JsonProperty("photo")
//        MultipartFile photo
) {
}