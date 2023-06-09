package com.time.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mjw
 * @date 2023/6/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDTO {

    private String nickName;

    private String password;

    private String avatar;

    private String token;
}
