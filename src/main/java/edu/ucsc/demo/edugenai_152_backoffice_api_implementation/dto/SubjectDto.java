package edu.ucsc.demo.edugenai_152_backoffice_api_implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Long subjectId;
    private String name;
    private String description;
}
