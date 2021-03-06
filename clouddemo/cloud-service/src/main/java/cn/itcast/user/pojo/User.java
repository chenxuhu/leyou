package cn.itcast.user.pojo;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;


@Table(name="tb_user")
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;
    // 出生日期
    private Date birthday;
    // 创建时间
    private Date created;
    // 更新时间
    private Date updated;
    // 备注
    @Transient
    private String note;


}
