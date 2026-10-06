package com.priyanka.password_security_analyzer.repository;

import com.priyanka.password_security_analyzer.model.PasswordAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordAnalysisRepository extends JpaRepository<PasswordAnalysis, Long> {
}