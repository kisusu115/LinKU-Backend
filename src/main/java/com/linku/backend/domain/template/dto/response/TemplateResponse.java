package com.linku.backend.domain.template.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class TemplateResponse {

    private Long templateId;
    private String name;
    private Integer height;
    private boolean cloned;

    private List<TemplateItemResponse> items;
}
