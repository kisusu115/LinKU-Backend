package com.linku.backend.domain.icon.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class IconRenameRequest {

    @NotBlank
    String name;
}