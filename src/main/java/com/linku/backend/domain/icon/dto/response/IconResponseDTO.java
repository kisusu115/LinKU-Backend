package com.linku.backend.domain.icon.dto.response;

import com.linku.backend.domain.icon.Icon;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IconResponseDTO {

    private Long id;
    private String name;
    private String imageUrl;

    public static IconResponseDTO from(Icon icon) {
        return IconResponseDTO.builder()
                .id(icon.getIconId())
                .name(icon.getName())
                .imageUrl(icon.getImageUrl())
                .build();
    }
}