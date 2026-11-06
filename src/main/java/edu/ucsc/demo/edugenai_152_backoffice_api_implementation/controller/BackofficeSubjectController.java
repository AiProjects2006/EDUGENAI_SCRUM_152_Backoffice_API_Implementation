package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.controller;

import edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto.ApiResponse;
import edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto.SubjectDto;
import edu.ucsc.demo.edugenai_152_backoffice_api_implementation.service.BackofficeCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/backoffice/subjects")
public class BackofficeSubjectController {

    private final BackofficeCourseService courseService;

    public BackofficeSubjectController(BackofficeCourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubjectDto>>> getAllSubjects() {
        List<SubjectDto> subjects = courseService.getAllSubjects();
        return ResponseEntity.ok(ApiResponse.ok("Subjects retrieved successfully.", subjects));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SubjectDto>> createSubject(@RequestBody Map<String, String> body) {
        String name = body.getOrDefault("name", "General");
        String desc = body.getOrDefault("description", "");
        SubjectDto subject = courseService.createSubject(name, desc);
        return new ResponseEntity<>(
                ApiResponse.ok("Subject created successfully.", subject),
                HttpStatus.CREATED
        );
    }
}
