package com.github.erikrz.contacts.client.feign.targets;

import static com.github.erikrz.contacts.api.contract.ContactsPaths.BASE_PATH;

import com.github.erikrz.contacts.api.contract.ContactsNonIdempotentOperations;
import com.github.erikrz.contacts.api.dto.request.CreateContactDto;
import com.github.erikrz.contacts.api.dto.response.ContactDto;
import feign.Body;
import feign.Param;
import feign.RequestLine;

/**
 * Contacts non-idempotent Client.
 *
 * @author erikrz
 */
public interface ContactsNonIdempotentClient extends ContactsNonIdempotentOperations {

    /**
     * POST to create a single contact.
     *
     * @param createContactDto data of the contact to create.
     * @return the created contact.
     */
    @Override
    @RequestLine("POST " + BASE_PATH)
    @Body("{createContactDto}")
    ContactDto createContact(@Param("createContactDto") CreateContactDto createContactDto);
}
