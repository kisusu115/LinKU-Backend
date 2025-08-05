package com.linku.backend.domain.icon.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IconInfoResponse {

    private Long id;
    private String name;
    private String imageUrl;
}