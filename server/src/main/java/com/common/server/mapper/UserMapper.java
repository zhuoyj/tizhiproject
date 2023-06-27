package com.common.server.mapper;
import com.common.server.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{
    @Select("SELECT * FROM tb_user WHERE " +
            "( account=#{username} AND `password`=#{password} ) or " +
            "(  userid=#{username} AND RIGHT(idcardno,4) = #{password} )")
    User login(String username, String password);
}
