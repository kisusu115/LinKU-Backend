package com.linku.backend.domain.template.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TemplateListResponse {

    private Long templateId;
    private String name;
    private Integer height;
    private boolean cloned;
    private Integer items;
}
