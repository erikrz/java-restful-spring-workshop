package com.github.erikrz.contacts.service.repository;

import com.github.erikrz.contacts.service.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * This a repository interface. Spring Data will see this interface annotated with @Repository and will instance a
 * singleton bean at runtime, so you can use it to store and retrieve data form a Relational database. You just need to
 * configure the driver imported in pom.xml, update the connection in application.yml, and you are good to go and use
 * another server; 0 code changes needed.
 *
 * @author erikrz
 */
@Repository
public interface ContactsRepository extends JpaRepository<Contact, Long>, QuerydslPredicateExecutor<Contact> {}
