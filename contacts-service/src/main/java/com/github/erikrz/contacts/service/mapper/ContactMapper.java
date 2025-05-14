package com.github.erikrz.contacts.service.mapper;

import com.github.erikrz.contacts.api.dto.request.CreateContactDto;
import com.github.erikrz.contacts.api.dto.response.ContactDto;
import com.github.erikrz.contacts.service.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Interface that declares functions to map models to DTOs and vice-versa.
 *
 * @author erikrz
 */
@Mapper
public interface ContactMapper {
    Contact toContact(CreateContactDto contact);

    Contact toContact(ContactDto contact);

    ContactDto toContactDto(Contact contact);

    void updateFromCreateContactDto(CreateContactDto contactDto, @MappingTarget Contact contact);
}
