
package com.sridar.codereview.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecretMaskingUtilTest {

    @Test
    void shouldMaskDoubleQuotedPassword() {

        String input = "password = \"Admin@123\"";

        String result = SecretMaskingUtil.mask(input);

        assertEquals("password = \"********\"", result);
    }

    @Test
    void shouldMaskSingleQuotedPassword() {

        String input = "password = 'Admin@123'";

        String result = SecretMaskingUtil.mask(input);

        assertEquals("password = '********'", result);
    }

    @Test
    void shouldMaskUnquotedApiKey() {

        String input = "apiKey = sk_test_ABC123";

        String result = SecretMaskingUtil.mask(input);

        assertEquals("apiKey = ********", result);
    }

    @Test
    void shouldMaskDatabaseUrlPassword() {

        String input =
                "jdbc:postgresql://admin:SecretDB123@localhost:5432/mydb";

        String result = SecretMaskingUtil.mask(input);

        assertFalse(result.contains("SecretDB123"));
        assertTrue(result.contains("********"));
    }

    @Test
    void shouldMaskKnownSecretInAiOutput() {

        String originalCode =
                "password = \"LeakTest@98765\"";

        String aiOutput =
                "Corrected password: LeakTest@98765";

        String result = SecretMaskingUtil.maskKnownSecrets(
                aiOutput,
                originalCode
        );

        assertFalse(result.contains("LeakTest@98765"));
        assertTrue(result.contains("********"));
    }

    @Test
    void shouldPreserveNormalText() {

        String input = "public class Student {}";

        assertEquals(input, SecretMaskingUtil.mask(input));
    }

    @Test
    void shouldHandleNullInput() {

        assertNull(SecretMaskingUtil.mask(null));
    }

    @Test
    void shouldHandleBlankInput() {

        assertEquals("", SecretMaskingUtil.mask(""));
    }

    @Test
    void shouldMaskMultipleSecrets() {

        String input = """
                password = "Admin123"
                apiKey = 'sk_test_123'
                """;

        String result = SecretMaskingUtil.mask(input);

        assertFalse(result.contains("Admin123"));
        assertFalse(result.contains("sk_test_123"));

        assertEquals(
                2,
                result.split("\\*\\*\\*\\*\\*\\*\\*\\*", -1).length - 1
        );
    }
}
