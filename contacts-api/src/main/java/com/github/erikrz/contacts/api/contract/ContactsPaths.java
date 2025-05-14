package com.github.erikrz.contacts.api.contract;

/**
 * Class that defines URL paths to use in Contacts Rest API.
 *
 * @author erikrz
 */
public class ContactsPaths {

    public static final String BASE_PATH = "/rest-api/v1/contacts";
    public static final String ALL_CONTACTS_PATH = BASE_PATH + "/all";
    public static final String SINGLE_CONTACT_PATH = BASE_PATH + "/{contactId}";
}
