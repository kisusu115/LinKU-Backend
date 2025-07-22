package com.linku.backend.domain.icon.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IconNameRequestDTO {
    @NotBlank
    String name;
}