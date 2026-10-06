package com.priyanka.password_security_analyzer.controller;

import com.priyanka.password_security_analyzer.model.PasswordAnalysis;
import com.priyanka.password_security_analyzer.repository.PasswordAnalysisRepository;
import com.priyanka.password_security_analyzer.service.PasswordAnalyzerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/password")
@CrossOrigin
public class PasswordController {

    private final PasswordAnalyzerService analyzerService;
    private final PasswordAnalysisRepository repository;

    public PasswordController(PasswordAnalyzerService analyzerService,
                              PasswordAnalysisRepository repository) {
        this.analyzerService = analyzerService;
        this.repository = repository;
    }

    @PostMapping("/analyze")
    public Map<String, Object> analyze(@RequestBody Map<String, String> request) {

        String password = request.get("password");

        int score = analyzerService.calculateScore(password);
        String strength = analyzerService.getStrength(score);

        boolean repeated =
                analyzerService.hasRepeatedCharacters(password);

        boolean sequential =
                analyzerService.hasSequentialPattern(password);

        boolean common =
                analyzerService.isCommonPassword(password);

        List<String> recommendations =
                analyzerService.getRecommendations(password);

        PasswordAnalysis analysis = new PasswordAnalysis();

        analysis.setScore(score);
        analysis.setStrength(strength);
        analysis.setLength(password.length());
        analysis.setHasUppercase(password.matches(".*[A-Z].*"));
        analysis.setHasLowercase(password.matches(".*[a-z].*"));
        analysis.setHasNumber(password.matches(".*\\d.*"));
        analysis.setHasSpecial(password.matches(".*[^a-zA-Z0-9].*"));
        analysis.setAnalyzedAt(LocalDateTime.now());

        repository.save(analysis);

        Map<String, Object> response = new HashMap<>();

        response.put("score", score);
        response.put("strength", strength);
        response.put("length", password.length());
        response.put("hasUppercase", analysis.isHasUppercase());
        response.put("hasLowercase", analysis.isHasLowercase());
        response.put("hasNumber", analysis.isHasNumber());
        response.put("hasSpecial", analysis.isHasSpecial());

        response.put("repeatedCharacters", repeated);
        response.put("sequentialPattern", sequential);
        response.put("commonPassword", common);

        response.put("recommendations", recommendations);

        return response;
    }
        @GetMapping("/history")
    public List<PasswordAnalysis> getHistory() {
        return repository.findAll(
                org.springframework.data.domain.Sort.by(
                        org.springframework.data.domain.Sort.Direction.DESC,
                        "analyzedAt"
                )
        );
    }

}
