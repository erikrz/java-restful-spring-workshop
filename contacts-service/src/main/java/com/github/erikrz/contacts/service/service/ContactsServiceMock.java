package com.github.erikrz.contacts.service.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.github.erikrz.contacts.api.dto.request.CreateContactDto;
import com.github.erikrz.contacts.api.dto.response.ContactDto;
import com.github.erikrz.contacts.service.mapper.ContactMapper;
import com.github.erikrz.contacts.service.mapper.ContactMasker;
import com.github.javafaker.Faker;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;

/**
 * Service that simulates persisting contacts.
 *
 * @author erikrz
 */
@XSlf4j
@RequiredArgsConstructor
public class ContactsServiceMock implements ContactsService {

    private final Faker faker;
    private final ContactMapper contactMapper;
    private final ContactMasker contactMasker;

    @Override
    public ContactDto saveContact(CreateContactDto createContactDto) {
        log.entry(createContactDto);
        var result = contactMapper.toContactDto(contactMapper.toContact(createContactDto));
        log.exit(result);
        return result;
    }

    @Override
    public Page<ContactDto> getAllContacts(Pageable pageable) {
        log.entry(pageable);
        var contacts = LongStream.range(0, pageable.getPageSize())
                .mapToObj(this::createFakeContact)
                .toList();
        var page = new PageImpl<>(contacts, pageable, pageable.getPageSize());
        log.exit(page);
        return page;
    }

    @Override
    public Page<ContactDto> searchContacts(Predicate predicate, Pageable pageable) {
        log.entry(predicate, pageable);
        var contactsPage = getAllContacts(pageable);
        log.exit(contactsPage);
        return contactsPage;
    }

    @Override
    public Optional<ContactDto> getContactById(Long id) {
        log.entry(id);
        Optional<ContactDto> result = Optional.empty();
        if (faker.bool().bool()) {
            result = Optional.of(createFakeContact(id));
        }
        log.exit(result);
        return result;
    }

    @Override
    public Optional<ContactDto> updateContactById(Long id, CreateContactDto updatedContactDto) {
        log.entry(id, contactMasker.mask(updatedContactDto));
        Optional<ContactDto> result = Optional.empty();
        if (faker.bool().bool()) {
            result = Optional.of(contactMapper.toContactDto(contactMapper.toContact(updatedContactDto)));
        }
        log.exit(result);
        return result;
    }

    @Override
    public Optional<ContactDto> deleteContactById(Long id) {
        log.entry(id);
        Optional<ContactDto> result = Optional.empty();
        if (faker.bool().bool()) {
            result = Optional.of(createFakeContact(id));
        }
        log.exit(result);
        return result;
    }

    private ContactDto createFakeContact(Long id) {
        var firstName = faker.name().firstName();
        var lastName = faker.name().lastName();
        var username = StringUtils.deleteWhitespace(
                StringUtils.join(
                        firstName.replace("'", "").toLowerCase(),
                        ".",
                        lastName.replace("'", "").toLowerCase()));
        return ContactDto.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .email(faker.internet().emailAddress(username))
                .phoneNumber(faker.phoneNumber().cellPhone())
                .createdDate(faker.date().past(5, TimeUnit.DAYS).toInstant())
                .lastModifiedDate(faker.date().past(2, TimeUnit.HOURS).toInstant())
                .build();
    }

}
