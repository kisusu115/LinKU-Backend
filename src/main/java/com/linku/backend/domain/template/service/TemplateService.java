package com.linku.backend.domain.template.service;

import com.linku.backend.domain.common.enums.Status;
import com.linku.backend.domain.template.Template;
import com.linku.backend.domain.template.TemplateItem;
import com.linku.backend.domain.template.dto.request.TemplateCreateRequest;
import com.linku.backend.domain.template.dto.request.TemplateItemUpdateRequest;
import com.linku.backend.domain.template.dto.request.TemplateUpdateRequest;
import com.linku.backend.domain.template.dto.response.TemplateListResponse;
import com.linku.backend.domain.template.dto.response.TemplateResponse;
import com.linku.backend.domain.template.dto.TemplateItemMapper;
import com.linku.backend.domain.template.dto.TemplateItemIconRequestMapper;
import com.linku.backend.domain.template.dto.TemplateItemPositionRequestMapper;
import com.linku.backend.domain.template.dto.TemplateItemSizeRequestMapper;
import com.linku.backend.domain.template.repository.TemplateRepository;
import com.linku.backend.domain.template.repository.TemplateItemRepository;
import com.linku.backend.domain.user.User;
import com.linku.backend.domain.user.repository.UserRepository;
import com.linku.backend.global.exception.LinkuException;
import com.linku.backend.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateItemRepository templateItemRepository;
    private final UserRepository userRepository;

    private final TemplateItemMapper templateItemMapper;
    private final TemplateItemPositionRequestMapper templateItemPositionRequestMapper;
    private final TemplateItemSizeRequestMapper templateItemSizeRequestMapper;
    private final TemplateItemIconRequestMapper templateItemIconRequestMapper;

    @Transactional
    public TemplateResponse createTemplate(TemplateCreateRequest request) {
        Long userId = getCurrentUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> LinkuException.of(ResponseCode.USER_NOT_FOUND));

        Template template = Template.builder()
                .name(request.getName())
                .height(request.getHeight())
                .owner(user)
                .cloned(false)
                .status(Status.ACTIVE)
                .build();

        Template savedTemplate = templateRepository.save(template);

        List<TemplateItem> items = request.getItems().stream()
                .map(itemReq -> TemplateItem.builder()
                        .template(savedTemplate)
                        .name(itemReq.getName())
                        .siteUrl(itemReq.getSiteUrl())
                        .position(templateItemPositionRequestMapper.toEntity(itemReq.getPosition()))
                        .size(templateItemSizeRequestMapper.toEntity(itemReq.getSize()))
                        .icon(templateItemIconRequestMapper.toEntity(itemReq.getIcon()))
                        .status(Status.ACTIVE)
                        .build())
                .collect(Collectors.toList());

        templateItemRepository.saveAll(items);
        template.setItems(items);

        return TemplateResponse.builder()
            .templateId(template.getTemplateId())
            .name(template.getName())
            .height(template.getHeight())
            .cloned(Boolean.TRUE.equals(template.getCloned()))
            .items(template.getItems().stream()
                .filter(item -> item.getStatus() == Status.ACTIVE)
                .map(templateItemMapper::toResponse)
                .collect(Collectors.toList()))
            .build();
    }

    @Transactional(readOnly = true)
    public List<TemplateListResponse> getOwnedTemplates() {
        Long userId = getCurrentUserId();

        List<Template> templates = templateRepository.findAllByOwner_UserIdAndClonedFalseAndStatus(userId, Status.ACTIVE);

        return templates.stream()
            .map(template -> TemplateListResponse.builder()
                .templateId(template.getTemplateId())
                .name(template.getName())
                .height(template.getHeight())
                .cloned(Boolean.TRUE.equals(template.getCloned()))
                .items(template.getItems() != null ? template.getItems().size() : 0)
                .build())
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TemplateListResponse> getClonedTemplates() {
        Long userId = getCurrentUserId();

        List<Template> templates = templateRepository.findAllByOwner_UserIdAndClonedTrueAndStatus(userId, Status.ACTIVE);

        return templates.stream()
            .map(template -> TemplateListResponse.builder()
                .templateId(template.getTemplateId())
                .name(template.getName())
                .height(template.getHeight())
                .cloned(Boolean.TRUE.equals(template.getCloned()))
                .items(template.getItems() != null ? template.getItems().size() : 0)
                .build())
            .collect(Collectors.toList());
    }

    @Transactional
    public TemplateResponse updateTemplate(Long templateId, TemplateUpdateRequest request) {
        Long userId = getCurrentUserId();

        Template template = templateRepository.findByTemplateIdAndOwner_UserIdAndStatus(templateId, userId, Status.ACTIVE)
                .orElseThrow(() -> LinkuException.of(ResponseCode.TEMPLATE_NOT_FOUND));

        template.setName(request.getName());
        template.setHeight(request.getHeight());

        List<TemplateItem> existingItems = templateItemRepository.findAllByTemplate_TemplateIdAndStatus(templateId, Status.ACTIVE);

        List<Long> requestIds = request.getItems().stream()
                .map(TemplateItemUpdateRequest::getTemplateItemId)
                .filter(Objects::nonNull)
                .toList();

        existingItems.stream()
                .filter(item -> !requestIds.contains(item.getTemplateItemId()))
                .forEach(item -> {
                    item.setStatus(Status.DELETED);
                    item.setDeletedAt(LocalDateTime.now());
                });

        for (TemplateItemUpdateRequest itemReq : request.getItems()) {

            if (itemReq.getTemplateItemId() != null) {
                TemplateItem item = existingItems.stream()
                        .filter(e -> e.getTemplateItemId().equals(itemReq.getTemplateItemId()))
                        .findFirst()
                        .orElseThrow(() -> LinkuException.of(ResponseCode.TEMPLATE_ITEM_NOT_FOUND));

                item.setName(itemReq.getName());
                item.setSiteUrl(itemReq.getSiteUrl());
                item.setPosition(templateItemPositionRequestMapper.toEntity(itemReq.getPosition()));
                item.setSize(templateItemSizeRequestMapper.toEntity(itemReq.getSize()));
                item.setIcon(templateItemIconRequestMapper.toEntity(itemReq.getIcon()));
                item.setStatus(Status.ACTIVE);
            }

            else {
                TemplateItem newItem = TemplateItem.builder()
                        .template(template)
                        .name(itemReq.getName())
                        .siteUrl(itemReq.getSiteUrl())
                        .position(templateItemPositionRequestMapper.toEntity(itemReq.getPosition()))
                        .size(templateItemSizeRequestMapper.toEntity(itemReq.getSize()))
                        .icon(templateItemIconRequestMapper.toEntity(itemReq.getIcon()))
                        .status(Status.ACTIVE)
                        .build();
                templateItemRepository.save(newItem);
            }
        }

        return TemplateResponse.builder()
            .templateId(template.getTemplateId())
            .name(template.getName())
            .height(template.getHeight())
            .cloned(Boolean.TRUE.equals(template.getCloned()))
            .items(template.getItems().stream()
                .filter(item -> item.getStatus() == Status.ACTIVE)
                .map(templateItemMapper::toResponse)
                .collect(Collectors.toList()))
            .build();
    }

    @Transactional
    public void deleteTemplate(Long templateId) {
        Long userId = getCurrentUserId();

        Template template = templateRepository.findByTemplateIdAndOwner_UserIdAndStatus(templateId, userId, Status.ACTIVE)
                .orElseThrow(() -> LinkuException.of(ResponseCode.TEMPLATE_NOT_FOUND));
        template.setStatus(Status.DELETED);
        template.setDeletedAt(LocalDateTime.now());

        List<TemplateItem> items = templateItemRepository.findAllByTemplate_TemplateIdAndStatus(templateId, Status.ACTIVE);
        items.forEach(item -> {
            item.setStatus(Status.DELETED);
            item.setDeletedAt(LocalDateTime.now());
        });
    }

    @Transactional(readOnly = true)
    public TemplateResponse getTemplateDetail(Long templateId) {
        Long userId = getCurrentUserId();

        Template template = templateRepository.findByTemplateIdAndOwner_UserIdAndStatus(templateId, userId, Status.ACTIVE)
                .orElseThrow(() -> LinkuException.of(ResponseCode.TEMPLATE_NOT_FOUND));

        return TemplateResponse.builder()
            .templateId(template.getTemplateId())
            .name(template.getName())
            .height(template.getHeight())
            .cloned(Boolean.TRUE.equals(template.getCloned()))
            .items(template.getItems().stream()
                .filter(item -> item.getStatus() == Status.ACTIVE)
                .map(templateItemMapper::toResponse)
                .collect(Collectors.toList()))
            .build();
    }

    private Long getCurrentUserId() {
        // TODO 인증&인가 측 구현 완료 후 SecurityContext 기반 사용자 ID 반환 로직 추가
        return 1L;
    }
}
