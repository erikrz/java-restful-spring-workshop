package com.github.erikrz.contacts.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ContactMaskerTest {
    private final ContactMasker contactMasker = Mappers.getMapper(ContactMasker.class);

    @Test
    void maskEmail() {
        assertThat(contactMasker.maskEmail(null)).isNull();
        assertThat(contactMasker.maskEmail("")).isEmpty();
        assertThat(contactMasker.maskEmail("null")).isEqualTo("null");
        assertThat(contactMasker.maskEmail("john.doe@live.com")).isEqualTo("joh*****@live.com");
    }

    @Test
    void maskPhoneNumber() {
        assertThat(contactMasker.maskPhoneNumber(null)).isNull();
        assertThat(contactMasker.maskPhoneNumber("")).isEmpty();
        assertThat(contactMasker.maskPhoneNumber("null")).isEqualTo("null");
        assertThat(contactMasker.maskPhoneNumber("(123) 456 7890")).isEqualTo("(***) *** ****");
        assertThat(contactMasker.maskPhoneNumber("+52(123) 456 7890")).isEqualTo("+**(***) *** ****");
    }
}
