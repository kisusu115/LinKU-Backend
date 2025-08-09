package com.linku.backend.global.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public enum ResponseCode {

    // 1000 번대 : global 요청 성공/실패
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    FAILURE(false, 1001, "요청에 실패하였습니다."),
    NOT_FOUND(false, 1002, "존재하지 않는 엔드포인트 입니다."),
    USER_NOT_FOUND(false, 1003, "인증 정보에 해당하는 사용자가 없습니다."), // 401
    FORBIDDEN(false, 1004, "접근 권한이 없습니다."),                     // 403
    INVALID_INPUT_VALUE(false, 1005, "입력 값이 유효하지 않습니다."),

    // 2000 번대 : Icon 관련 요청 성공/실패
    ICON_UPLOAD_FAILED(false, 2000, "아이콘 이미지 업로드 중 오류가 발생했습니다."),
    ICON_NOT_FOUND(false, 2001, "요청 iconId에 해당하는 아이콘이 없습니다."),
    ICON_NOT_OWNER(false, 2002, "작업 수행 대상 아이콘의 소유자가 아닙니다."),
    ICON_OVER_SIZE(false, 2003, "아이콘 이미지 파일이 최대 용량을 초과했습니다."),

    // 3000 번대 : Template 관련 요청 성공/실패
    TEMPLATE_NOT_FOUND(false, 3001, "요청 templateId에 해당하는 템플릿이 없습니다."),
    TEMPLATE_ITEM_NOT_FOUND(false, 3002, "요청 templateItemId에 해당하는 템플릿 아이템이 없습니다."),

    // 4000 번대 : PostedTemplate 관련 요청 성공/실패
    POSTED_TEMPLATE_NOT_FOUND(false, 4001, "요청 postedTemplateId에 해당하는 게시된 템플릿이 없습니다.");

    private boolean success;
    private int code;
    private String message;
}
