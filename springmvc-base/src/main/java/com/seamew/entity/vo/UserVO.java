package com.seamew.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserVO
{
    private Integer id;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    // 当需要从 requestParam 中将字符串转化为 Date 类型时（反序列化），会按照 pattern 进行转换
    // @DateTimeFormat(pattern = "yyyy/MM/dd")
    // 当需要从 JSON 数据中将字符串转化为日期类型或将日期类型转化为 JSON 时（序列化和反序列化），
    // 会按照 pattern 进行转换
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT-8")
    @Past(message = "生日不能早于今天")
    private LocalDate birthday;

    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",
             message = "手机号格式错误")
    private String phone;

    @Email(message = "邮箱格式错误")
    private String email;
}
