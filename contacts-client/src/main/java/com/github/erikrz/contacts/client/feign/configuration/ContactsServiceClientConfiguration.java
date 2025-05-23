package com.github.erikrz.contacts.client.feign.configuration;

import com.github.erikrz.contacts.client.feign.ContactsClientFactory;
import com.github.erikrz.contacts.client.feign.properties.ContactsClientProperties;
import com.github.erikrz.contacts.client.feign.targets.ContactsIdempotentClient;
import com.github.erikrz.contacts.client.feign.targets.ContactsNonIdempotentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Contacts Service Client Configuration. Spring Configuration class that configures and instances the Contacts Service
 * Client. Just {@code @import} this configuration class into your application, provide the required properties defined
 * in {@link ContactsClientProperties} and you are good to go and use the client.
 *
 * @author erikrz
 */
@EnableConfigurationProperties(ContactsClientProperties.class)
@Configuration
public class ContactsServiceClientConfiguration {
    private final ContactsClientFactory factory;

    @Autowired
    public ContactsServiceClientConfiguration(ContactsClientProperties properties) {
        this.factory = new ContactsClientFactory(properties);
    }

    @Bean("contactsIdempotentClient")
    public ContactsIdempotentClient contactsIdempotentClient() {
        return factory.contactsIdempotentClient();
    }

    @Bean("contactsNonIdempotentClient")
    public ContactsNonIdempotentClient contactsNonIdempotentClient() {
        return factory.contactsNonIdempotentClient();
    }
}
