package com.time.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author mjw
 * @date 2023/6/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDTO {

    /**
     * 发送邮箱列表
     */
    private List<String> tos;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

}
