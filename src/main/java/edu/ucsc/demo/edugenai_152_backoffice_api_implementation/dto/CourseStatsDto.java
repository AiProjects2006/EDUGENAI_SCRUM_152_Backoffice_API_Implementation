package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseStatsDto {
    private long totalCourses;
    private long publishedCourses;
    private long draftCourses;
    private long archivedCourses;
    private long pendingApprovalCourses;
    private long totalEnrollments;
}
