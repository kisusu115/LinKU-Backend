package com.linku.backend.domain.template.dto.request;

import com.linku.backend.domain.common.template.IconSnapshot;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TemplateItemIconRequest {

    @NotBlank
    private String iconName;

    @NotBlank
    private String iconUrl;
}
