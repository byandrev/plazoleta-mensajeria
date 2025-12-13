package com.pragma.powerup.application.dto.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MessageRequestDtoValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private MessageRequestDto createMessageDto() {
        return MessageRequestDto
                .builder()
                .to("+573123123")
                .message("Hello World")
                .build();
    }

    private void assertViolation(MessageRequestDto dto, String fieldName, String expectedMessage) {
        Set<ConstraintViolation<MessageRequestDto>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty(), "Debería haber violaciones de validación.");

        boolean found = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals(fieldName) && v.getMessage().equals(expectedMessage));

        assertTrue(found, String.format("No se encontró la violación esperada para el campo '%s' con mensaje '%s'. Total: %d",
                fieldName, expectedMessage, violations.size()));
    }

    @Test
    @DisplayName("Message válido debe pasar la validación")
    void saveValidate_Success() {
        MessageRequestDto dto = createMessageDto();
        Set<ConstraintViolation<MessageRequestDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "No deberían existir violaciones con datos válidos.");
    }

    @Test
    @DisplayName("'to' excede longitud máxima debe fallar")
    void toTooLong_shouldFailValidation() {
        MessageRequestDto dto = createMessageDto();
        dto.setTo("+5730012345678");
        assertViolation(dto, "to", "Teléfono inválido. Máx 13 caracteres y puede iniciar con +");
    }

    @Test
    @DisplayName("'to' válido (con y sin +) debe pasar")
    void toValid_shouldPass() {
        MessageRequestDto dto1 = createMessageDto();
        dto1.setTo("3001234567");
        assertTrue(validator.validate(dto1).isEmpty());

        MessageRequestDto dto2 = createMessageDto();
        dto2.setTo("+573001234567");
        assertTrue(validator.validate(dto2).isEmpty());
    }

    @Test
    @DisplayName("'message' inválido debe fallar")
    void messageInvalid_shouldFail() {
        MessageRequestDto dto = createMessageDto();
        dto.setMessage(null);
        assertViolation(dto, "message", "El 'message' no puede estar vacio");
    }

}
