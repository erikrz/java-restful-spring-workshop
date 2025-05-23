package com.github.erikrz.contacts.service.service;

import com.github.erikrz.contacts.api.dto.request.CreateContactDto;
import com.github.erikrz.contacts.api.dto.response.ContactDto;
import com.querydsl.core.types.Predicate;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interfaces that describes contact operations.
 *
 * @author erikrz
 */
public interface ContactsService {

    /**
     * Function to save a contact.
     *
     * @param createContactDto dto containing data to save a new contact.
     * @return the saved contact information.
     */
    ContactDto saveContact(CreateContactDto createContactDto);

    /**
     * Function to retrieve all existing contacts.
     *
     * @return a list containing all contacts.
     */
    Page<ContactDto> getAllContacts(Pageable pageable);

    /**
     * Function to search contacts.
     *
     * @param predicate the predicate to use to filter contacts.
     * @param pageable the pagination information.
     * @return a page of results containing contacts that match the predicate.
     */
    Page<ContactDto> searchContacts(Predicate predicate, Pageable pageable);

    /**
     * Function to get a contact by its Id.
     *
     * @param id the contact ID.
     * @return the found contact, if any.
     */
    Optional<ContactDto> getContactById(Long id);

    /**
     * Function to update a contact with new data.
     *
     * @param id the contact ID.
     * @param updatedContactDto the new info to be persisted in the given contact.
     * @return the updated contact, if any.
     */
    Optional<ContactDto> updateContactById(Long id, CreateContactDto updatedContactDto);

    /**
     * Function to delete a contact.
     *
     * @param id the ID of the contact to delete.
     * @return the deleted contact, if any.
     */
    Optional<ContactDto> deleteContactById(Long id);
}
