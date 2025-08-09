package com.linku.backend.domain.icon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@NoArgsConstructor
public class IconUploadRequest {

    @NotBlank
    private String name;

    @NotNull
    private MultipartFile file;
}