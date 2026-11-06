package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBackofficeCourseRequest {
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
        return null;
    }

    public String resolveStatus() {
        if (status != null && !status.isBlank()) {
            String s = status.trim();
            if (s.equalsIgnoreCase("published") || s.equalsIgnoreCase("approved")) return "Published";
            if (s.equalsIgnoreCase("archived")) return "Archived";
            if (s.equalsIgnoreCase("draft") || s.equalsIgnoreCase("pending")) return "Draft";
            return s;
        }
        return null;
    }
}
