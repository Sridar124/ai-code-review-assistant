
package com.sridar.codereview.security;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SecretMaskingUtil {

    private static final String MASK = "********";

    private static final String SECRET_NAMES =
            "(?i:\\b(?:password|passwd|pwd|api[_-]?key|"
            + "access[_-]?token|refresh[_-]?token|"
            + "secret[_-]?key|client[_-]?secret)\\b)";

    private static final Pattern DOUBLE_QUOTED = Pattern.compile(
            "(" + SECRET_NAMES + "\\s*[:=]\\s*\")([^\"]*)(\")"
    );

    private static final Pattern SINGLE_QUOTED = Pattern.compile(
            "(" + SECRET_NAMES + "\\s*[:=]\\s*')([^']*)(')"
    );

    private static final Pattern UNQUOTED = Pattern.compile(
            "(" + SECRET_NAMES + "\\s*[:=]\\s*)(?![\"'])"
            + "([^\\s,;\\]}]+)()"
    );

    private static final Pattern URL_CREDENTIALS = Pattern.compile(
            "(?i)(://[^:/\\s]+:)([^@/\\s]+)(@)"
    );

    private SecretMaskingUtil() {
    }

    public static String mask(String text) {

        if (text == null || text.isBlank()) {
            return text;
        }

        String masked = replaceSecret(text, DOUBLE_QUOTED);
        masked = replaceSecret(masked, SINGLE_QUOTED);
        masked = replaceSecret(masked, UNQUOTED);
        masked = replaceSecret(masked, URL_CREDENTIALS);

        return masked;
    }

    public static String maskKnownSecrets(
            String output,
            String originalCode) {

        if (output == null || originalCode == null) {
            return output;
        }

        List<String> secrets = new ArrayList<>();

        collectSecrets(originalCode, DOUBLE_QUOTED, secrets);
        collectSecrets(originalCode, SINGLE_QUOTED, secrets);
        collectSecrets(originalCode, UNQUOTED, secrets);
        collectSecrets(originalCode, URL_CREDENTIALS, secrets);

        String sanitized = output;

        for (String secret : secrets) {
            if (!secret.isBlank()) {
                sanitized = sanitized.replace(secret, MASK);
            }
        }

        return sanitized;
    }

    private static void collectSecrets(
            String text,
            Pattern pattern,
            List<String> secrets) {

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String secret = matcher.group(2);

            if (secret != null && !secret.isBlank()) {
                secrets.add(secret);
            }
        }
    }

    private static String replaceSecret(
            String text,
            Pattern pattern) {

        Matcher matcher = pattern.matcher(text);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {

            String replacement =
                    matcher.group(1)
                    + MASK
                    + matcher.group(3);

            matcher.appendReplacement(
                    result,
                    Matcher.quoteReplacement(replacement)
            );
        }

        matcher.appendTail(result);

        return result.toString();
    }
}
