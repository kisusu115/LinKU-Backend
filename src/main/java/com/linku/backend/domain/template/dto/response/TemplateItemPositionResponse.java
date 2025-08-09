package com.linku.backend.domain.template.dto.response;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class TemplateItemPositionResponse {

    private Integer x;
    private Integer y;
}
