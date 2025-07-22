package com.linku.backend.domain.icon.controller;

import com.linku.backend.domain.icon.dto.request.IconNameRequestDTO;
import com.linku.backend.domain.icon.dto.response.IconResponseDTO;
import com.linku.backend.domain.icon.service.IconService;
import com.linku.backend.global.response.BaseResponse;
import com.linku.backend.global.response.ResponseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/icons")
public class IconController {

    private final IconService iconService;

    @PostMapping
    public BaseResponse<?> uploadIcon(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String iconName
    ) {
        IconResponseDTO response = iconService.saveIconWithImageUpload(iconName, file);

        return BaseResponse.of(
                ResponseCode.SUCCESS,
                response
        );
    }

    @GetMapping
    public BaseResponse<?> getUserIcons() {
        List<IconResponseDTO> responses = iconService.getUserIcons();

        return BaseResponse.of(
                ResponseCode.SUCCESS,
                responses
        );
    }

    @PutMapping("/{iconId}/rename")
    public BaseResponse<?> renameIcon(
            @PathVariable Long iconId,
            @Valid @RequestBody IconNameRequestDTO requestDTO
    ) {
        IconResponseDTO updatedResponse = iconService.renameIcon(iconId, requestDTO.getName());

        return BaseResponse.of(
                ResponseCode.SUCCESS,
                updatedResponse
        );
    }

    @DeleteMapping("/{iconId}")
    public BaseResponse<?> deleteIcon(@PathVariable Long iconId) {
        iconService.deleteIcon(iconId);

        return BaseResponse.of(
                ResponseCode.SUCCESS,
                null
        );
    }
}