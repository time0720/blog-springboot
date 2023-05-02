package com.time.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author mjw
 * @date 2022/8/26
 */
@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /*
    成功
     */
    SUCCESS(200, "success"),

    /**
     * 失败
     */
    FAIL(500, "failed"),

    /**
     * 状态码
     */
    HTTP_STATUS_200(200, "ok"),
    HTTP_STATUS_400(400, "request error"),
    HTTP_STATUS_401(401, "no authentication"),
    HTTP_STATUS_403(403, "authorities forbidden"),
    HTTP_STATUS_500(500, "server error");

    public static final List<ResponseStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
            Arrays.asList(HTTP_STATUS_200, HTTP_STATUS_400, HTTP_STATUS_401, HTTP_STATUS_403, HTTP_STATUS_500
            ));

    /**
     * response code
     */
    private final long responseCode;

    /**
     * description.
     */
    private final String description;
}
