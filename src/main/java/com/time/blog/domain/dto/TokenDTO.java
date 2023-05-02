package com.time.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mjw
 * @date 2023/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDTO {

    /**
     * 当前授权的token
     */
    private String access_token;

    /**
     * 时间
     */
    private long expires_in;
}
