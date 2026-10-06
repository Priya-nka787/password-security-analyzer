package com.priyanka.password_security_analyzer.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_analysis")
public class PasswordAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String strength;
    private int length;
    private boolean hasUppercase;
    private boolean hasLowercase;
    private boolean hasNumber;
    private boolean hasSpecial;
    private LocalDateTime analyzedAt;

    public PasswordAnalysis() {
    }

    public Long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHasUppercase() {
        return hasUppercase;
    }

    public void setHasUppercase(boolean hasUppercase) {
        this.hasUppercase = hasUppercase;
    }

    public boolean isHasLowercase() {
        return hasLowercase;
    }

    public void setHasLowercase(boolean hasLowercase) {
        this.hasLowercase = hasLowercase;
    }

    public boolean isHasNumber() {
        return hasNumber;
    }

    public void setHasNumber(boolean hasNumber) {
        this.hasNumber = hasNumber;
    }

    public boolean isHasSpecial() {
        return hasSpecial;
    }

    public void setHasSpecial(boolean hasSpecial) {
        this.hasSpecial = hasSpecial;
    }

    public LocalDateTime getAnalyzedAt() {
        return analyzedAt;
    }

    public void setAnalyzedAt(LocalDateTime analyzedAt) {
        this.analyzedAt = analyzedAt;
    }
}