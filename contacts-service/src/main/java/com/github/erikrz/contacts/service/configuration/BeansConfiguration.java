package com.github.erikrz.contacts.service.configuration;

import java.util.Locale;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.erikrz.contacts.service.mapper.ContactMapper;
import com.github.erikrz.contacts.service.mapper.ContactMasker;
import com.github.erikrz.contacts.service.service.ContactsService;
import com.github.erikrz.contacts.service.service.ContactsServiceMock;
import com.github.javafaker.Faker;

/**
 * Beans Configuration.
 *
 * @author erikrz
 */
@Configuration
public class BeansConfiguration {

    @Bean
    public ContactMapper contactMapper() {
        return Mappers.getMapper(ContactMapper.class);
    }

    @Bean
    public ContactMasker contactMasker() {
        return Mappers.getMapper(ContactMasker.class);
    }

    @Bean
    public Faker faker() {
        return new Faker(new Locale("es-MX"));
    }

    @Bean
    public ContactsService mockContactsService(Faker faker, ContactMapper contactMapper,
                                           ContactMasker contactMasker) {
        return new ContactsServiceMock(faker, contactMapper, contactMasker);
    }

}
