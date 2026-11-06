package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackofficeCourseDto {
    private Long id;
    private Long courseId;
    private String title;
    private String name;
    private String subject;
    private Long subjectId;
    private String grade;
    private String gradeLevel;
    private String level;
    private String author;
    private String creator;
    private Long creatorId;
    private Long enrollments;
    private String completion;
    private String status;
    private String date;
    private String createdAt;
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
    private Integer lessons;
    private Integer activities;

    public String getName() {
        return name != null && !name.isBlank() ? name : title;
    }

    public String getTitle() {
        return title != null && !title.isBlank() ? title : name;
    }

    public String getCreator() {
        return creator != null && !creator.isBlank() ? creator : author;
    }

    public String getAuthor() {
        return author != null && !author.isBlank() ? author : creator;
    }

    public String getGrade() {
        return grade != null && !grade.isBlank() ? grade : gradeLevel;
    }

    public String getGradeLevel() {
        return gradeLevel != null && !gradeLevel.isBlank() ? gradeLevel : grade;
    }

    public String getThumbnail() {
        return thumbnail != null && !thumbnail.isBlank() ? thumbnail : thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl != null && !thumbnailUrl.isBlank() ? thumbnailUrl : thumbnail;
    }
}
