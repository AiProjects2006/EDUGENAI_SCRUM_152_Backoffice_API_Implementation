package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBackofficeCourseRequest {
    private String title;
    private String name;
    private String subject;
    private Long subjectId;
    private String gradeLevel;
    private String grade;
    private String level;
    private String description;
    private String objectives;
    private String primaryGrade;
    private String prerequisites;
    private String thumbnail;
    private String thumbnailUrl;
    private String contentPdf;
    private String contentPdfUrl;
    private String contentPdfName;
    private String contentPdfDriveId;
    private String author;
    private String creator;
    private Long creatorId;
    private String status;

    public String resolveTitle() {
        if (title != null && !title.isBlank()) return title.trim();
        if (name != null && !name.isBlank()) return name.trim();
        return "Untitled Course";
    }

    public String resolveSubject() {
        if (subject != null && !subject.isBlank()) return subject.trim();
        return "General";
    }

    public String resolveStatus() {
        if (status != null && !status.isBlank()) {
            String s = status.trim();
            if (s.equalsIgnoreCase("published") || s.equalsIgnoreCase("approved")) return "Published";
            if (s.equalsIgnoreCase("archived")) return "Archived";
            return "Draft";
        }
        return "Published";
    }

    public String resolveLevel() {
        if (level != null && !level.isBlank()) return level.toUpperCase();
        if (primaryGrade != null) {
            String pg = primaryGrade.toLowerCase();
            if (pg.contains("beginner")) return "BEGINNER";
            if (pg.contains("intermediate")) return "INTERMEDIATE";
            if (pg.contains("advanced")) return "ADVANCED";
        }
        if (gradeLevel != null) {
            String gl = gradeLevel.toLowerCase();
            if (gl.contains("1") || gl.contains("2") || gl.contains("3")) return "BEGINNER";
            if (gl.contains("4") || gl.contains("5") || gl.contains("6")) return "INTERMEDIATE";
            if (gl.contains("7") || gl.contains("8") || gl.contains("9")) return "ADVANCED";
        }
        return "BEGINNER";
    }
}
