package com.agorohov.greetingservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.agorohov.greetingservice.constants.GreetingServiceTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {

    private final GreetingService out = new GreetingService();

    @Test
//    @Disabled - отключает тест
    public void shouldReturnDefaultMessageWhenNameIsNoExists() {
        String result = out.generateGreetings();
        assertTrue(result.contains(DEFAULT_NAME));
        assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsNull() {
        String result = out.generateGreetings(null);
        assertTrue(result.contains(DEFAULT_NAME));
        assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsEmpty() {
        String result = out.generateGreetings(EMPTY_NAME);
        assertTrue(result.contains(DEFAULT_NAME));
        assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldReturnDefaultMessageWhenNameIsSpace() {
        String result = out.generateGreetings(ONLY_SPACES_NAME);
        assertTrue(result.contains(DEFAULT_NAME));
        assertEquals(DEFAULT_MESSAGE, result);
    }

    @Test
    public void shouldThrowIIllegalArgumentExceptionWhenNameContainsIllegalCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> out.generateGreetings(ILLEGAL_CHARS_NAME)
        );
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsCorrect() {
        String result = out.generateGreetings(CORRECT_NAME);
        assertTrue(result.contains(CORRECT_NAME));
        assertEquals(CORRECT_MESSAGE, result);
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsInUpperCase() {
        String result = out.generateGreetings(UPPER_CASE_NAME);
        assertTrue(result.contains(CORRECT_NAME));
        assertEquals(CORRECT_MESSAGE, result);
    }

    @Test
    public void shouldReturnCorrectMessageWhenNameIsInLowerCase() {
        String result = out.generateGreetings(LOWER_CASE_NAME);
        assertTrue(result.contains(CORRECT_NAME));
        assertEquals(CORRECT_MESSAGE, result);
    }

    // Параметризованные тесты
    private static Stream<Arguments> provideParamsForTest() {
        return Stream.of(
                Arguments.of(null, DEFAULT_NAME, DEFAULT_MESSAGE),
                Arguments.of(EMPTY_NAME, DEFAULT_NAME, DEFAULT_MESSAGE),
                Arguments.of(ONLY_SPACES_NAME, DEFAULT_NAME, DEFAULT_MESSAGE),
                Arguments.of(CORRECT_NAME, CORRECT_NAME, CORRECT_MESSAGE),
                Arguments.of(UPPER_CASE_NAME, CORRECT_NAME, CORRECT_MESSAGE),
                Arguments.of(LOWER_CASE_NAME, CORRECT_NAME, CORRECT_MESSAGE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    // Если мы не указываем имя для @MethodSource,
    // JUnit будет искать исходный метод с тем же именем, что и тестовый метод.
    // Есть и другие варианты, например: @ValueSource(), @NullAndEmptySource, @EnumSource и другие.
    public void shouldGenerateCorrectMessages(String inputName, String messageName, String expectedMessage) {
        String result = out.generateGreetings(inputName);
        assertTrue(result.contains(messageName));
        assertEquals(expectedMessage, result);
    }
}