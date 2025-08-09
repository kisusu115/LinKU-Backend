package com.linku.backend.domain.template.util;

import com.linku.backend.domain.template.dto.request.TemplateItemCreateRequest;
import com.linku.backend.domain.template.dto.request.TemplateItemUpdateRequest;
import com.linku.backend.global.exception.LinkuException;
import com.linku.backend.global.response.ResponseCode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TemplateValidator {

    private static final int TEMPLATE_WIDTH = 6;

    private record ValidationItem(int x, int y, int width, int height) {
        static ValidationItem from(TemplateItemCreateRequest request) {
            return new ValidationItem(
                    request.getPosition().getX(),
                    request.getPosition().getY(),
                    request.getSize().getWidth(),
                    request.getSize().getHeight()
            );
        }

        static ValidationItem from(TemplateItemUpdateRequest request) {
            return new ValidationItem(
                    request.getPosition().getX(),
                    request.getPosition().getY(),
                    request.getSize().getWidth(),
                    request.getSize().getHeight()
            );
        }
    }

    public void validateTemplateItemsForCreate(int templateHeight, List<TemplateItemCreateRequest> items) {
        List<ValidationItem> validationItems = items.stream()
                .map(ValidationItem::from)
                .toList();
        validateItemsInternal(templateHeight, validationItems);
    }

    public void validateTemplateItemsForUpdate(int templateHeight, List<TemplateItemUpdateRequest> items) {
        List<ValidationItem> validationItems = items.stream()
                .map(ValidationItem::from)
                .toList();
        validateItemsInternal(templateHeight, validationItems);
    }

    private void validateItemsInternal(int templateHeight, List<ValidationItem> items) {
        boolean[][] grid = new boolean[templateHeight][TEMPLATE_WIDTH];

        for (ValidationItem item : items) {
            int x = item.x();
            int y = item.y();
            int itemWidth = item.width();
            int itemHeight = item.height();

            if (isOutOfBounds(x, y, itemWidth, itemHeight, templateHeight)) {
                throw LinkuException.of(ResponseCode.TEMPLATE_ITEM_OUT_OF_BOUND);
            }

            for (int i = y; i < y + itemHeight; i++) {
                for (int j = x; j < x + itemWidth; j++) {
                    if (grid[i][j]) {
                        throw LinkuException.of(ResponseCode.TEMPLATE_ITEMS_OVERLAPPED);
                    }
                    grid[i][j] = true;
                }
            }
        }
    }

    private boolean isOutOfBounds(int x, int y, int width, int height, int templateHeight) {
        return x < 0 || y < 0 || x + width > TEMPLATE_WIDTH || y + height > templateHeight;
    }
}