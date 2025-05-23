package com.github.erikrz.contacts.service;

import com.github.erikrz.contacts.service.configuration.ContactsServiceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class.
 *
 * @author erikrz
 */
@SpringBootApplication
public class ContactsSpringServiceApplication {

    /**
     * Main entry point to start our gRPC Server.Here we start a Spring Application so that the Application Context
     * becomes ready and the singleton beans declared in {@link ContactsServiceConfiguration} are instanced.
     *
     * @param args no args used.
     */
    public static void main(String[] args) {
        SpringApplication.run(ContactsSpringServiceApplication.class, args);
    }
}
