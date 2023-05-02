package com.time.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mjw
 * @date 2023/4/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    /**
     * 种类名称
     */
    private String name;

    /**
     * 使用的次数
     */
    private String value;
}
