package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.controller;

import edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto.*;
import edu.ucsc.demo.edugenai_152_backoffice_api_implementation.service.BackofficeCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/backoffice/courses")
public class BackofficeCourseController {

    private final BackofficeCourseService courseService;

    public BackofficeCourseController(BackofficeCourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Get all courses for backoffice (Content Creator & Administrator)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<BackofficeCourseDto>>> getAllCourses(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String search) {

        List<BackofficeCourseDto> courses = courseService.getAllCourses(status, search);
        return ResponseEntity.ok(ApiResponse.ok("Courses retrieved successfully.", courses));
    }

    /**
     * Get course statistics for dashboards
     */
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<CourseStatsDto>> getStats() {
        CourseStatsDto stats = courseService.getCourseStats();
        return ResponseEntity.ok(ApiResponse.ok("Course stats retrieved successfully.", stats));
    }

    /**
     * Get single course details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BackofficeCourseDto>> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(course -> ResponseEntity.ok(ApiResponse.ok("Course retrieved successfully.", course)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Course not found with id: " + id)));
    }

    /**
     * Create a new course (called by Content Creator UI)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<BackofficeCourseDto>> createCourse(
            @RequestBody CreateBackofficeCourseRequest request) {

        BackofficeCourseDto created = courseService.createCourse(request);
        return new ResponseEntity<>(
                ApiResponse.ok("Course created and saved successfully.", created),
                HttpStatus.CREATED
        );
    }

    /**
     * Update an existing course (called by Content Creator UI or Admin UI)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BackofficeCourseDto>> updateCourse(
            @PathVariable Long id,
            @RequestBody UpdateBackofficeCourseRequest request) {

        return courseService.updateCourse(id, request)
                .map(updated -> ResponseEntity.ok(ApiResponse.ok("Course updated successfully.", updated)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.error("Course not found with id: " + id)));
    }

    /**
     * Update course status (e.g. "Approved", "Archived", "Published", "Draft")
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Void>> updateCourseStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> statusBody) {

        String newStatus = statusBody.getOrDefault("status", "Published");
        boolean updated = courseService.updateCourseStatus(id, newStatus);
        if (updated) {
            return ResponseEntity.ok(ApiResponse.ok("Course status updated to " + newStatus, null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Course not found with id: " + id));
    }

    /**
     * Delete course
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(@PathVariable Long id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return ResponseEntity.ok(ApiResponse.ok("Course deleted successfully.", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Course not found with id: " + id));
    }

    /**
     * Upload course content PDF organized by Grade and Subject into Google Drive
     */
    @PostMapping(value = "/upload-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Map<String, Object>>> uploadCourseContentPdf(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "grade", defaultValue = "Grade 3") String grade,
            @RequestParam(value = "subject", defaultValue = "Mathematics") String subject) {

        Map<String, Object> uploadResult = courseService.uploadContentPdf(file, grade, subject);
        return ResponseEntity.ok(ApiResponse.ok("Content PDF uploaded for Google Drive.", uploadResult));
    }
}
