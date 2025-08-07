package com.linku.backend.domain.postedtemplate.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostedTemplateItemIconResponse {

    private String iconName;
    private String iconUrl;
} 