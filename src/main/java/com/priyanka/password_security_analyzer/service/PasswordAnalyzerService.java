package com.priyanka.password_security_analyzer.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PasswordAnalyzerService {

    public int calculateScore(String password) {
        int score = 0;

        if (password.length() >= 8) score += 20;
        if (password.length() >= 12) score += 10;
        if (password.matches(".*[A-Z].*")) score += 15;
        if (password.matches(".*[a-z].*")) score += 15;
        if (password.matches(".*\\d.*")) score += 15;
        if (password.matches(".*[^a-zA-Z0-9].*")) score += 15;

        if (hasRepeatedCharacters(password))
            score -= 10;

        if (hasSequentialPattern(password))
            score -= 10;

        if (isCommonPassword(password))
            score -= 20;

        return Math.max(0, Math.min(score, 100));
    }

    public String getStrength(int score) {
        if (score >= 90) return "VERY STRONG";
        if (score >= 70) return "STRONG";
        if (score >= 40) return "MEDIUM";
        return "WEAK";
    }

    public boolean hasRepeatedCharacters(String password) {
        return password.matches(".*(.)\\1\\1.*");
    }

    public boolean hasSequentialPattern(String password) {
        String p = password.toLowerCase();

        String[] patterns = {
                "1234", "2345", "3456", "4567",
                "5678", "6789", "abcd", "bcde",
                "cdef", "defg", "efgh", "qwer"
        };

        for (String pattern : patterns) {
            if (p.contains(pattern))
                return true;
        }

        return false;
    }

    public boolean isCommonPassword(String password) {

        String p = password.toLowerCase();

        String[] commonPasswords = {
                "password",
                "123456",
                "12345678",
                "qwerty",
                "admin",
                "letmein",
                "welcome",
                "password123",
                "admin123"
        };

        for (String common : commonPasswords) {
            if (p.equals(common))
                return true;
        }

        return false;
    }

    public List<String> getRecommendations(String password) {

        List<String> recommendations = new ArrayList<>();

        if (password.length() < 8)
            recommendations.add("Use at least 8 characters.");

        if (password.length() < 12)
            recommendations.add("Use 12 or more characters for better security.");

        if (!password.matches(".*[A-Z].*"))
            recommendations.add("Add at least one uppercase letter.");

        if (!password.matches(".*[a-z].*"))
            recommendations.add("Add at least one lowercase letter.");

        if (!password.matches(".*\\d.*"))
            recommendations.add("Add at least one number.");

        if (!password.matches(".*[^a-zA-Z0-9].*"))
            recommendations.add("Add at least one special character.");

        if (hasRepeatedCharacters(password))
            recommendations.add("Avoid repeating the same character multiple times.");

        if (hasSequentialPattern(password))
            recommendations.add("Avoid predictable sequences such as 1234 or abcde.");

        if (isCommonPassword(password))
            recommendations.add("Avoid commonly used passwords.");

        return recommendations;
    }
}