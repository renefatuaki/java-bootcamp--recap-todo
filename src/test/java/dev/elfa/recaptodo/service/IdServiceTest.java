package dev.elfa.recaptodo.service;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class IdServiceTest {
    @Test
    void generateUUID() {
        UUID expectedUUID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        try (MockedStatic<UUID> uuid = mockStatic(UUID.class)) {
            uuid.when(UUID::randomUUID).thenReturn(expectedUUID);
            assertThat(UUID.randomUUID().toString()).isEqualTo(expectedUUID.toString());
        }
    }
}