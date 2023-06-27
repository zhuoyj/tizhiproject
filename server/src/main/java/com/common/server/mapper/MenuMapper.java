package com.common.server.mapper;

import com.common.server.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuMapper extends tk.mybatis.mapper.common.Mapper<SysMenu> {
    @Select("<script>" +
            "SELECT m.*,!ISNULL(rm.id) as isck FROM tb_menu as m" +
            " LEFT  JOIN tb_rolemenu as rm " +
            "on m.id=rm.menuid " +
            "<if test='rid!=null'> AND rm.roleid=#{rid} </if>" +
            "GROUP BY sort" +
            "</script>")
    List<SysMenu> queryByRid(@Param("rid") Long rid);

    @Select("select m.* FROM tb_rolemenu as rm LEFT JOIN tb_menu as m on rm.menuid=m.id WHERE rm.roleid=#{rid} AND pid=#{pid}")
    List<SysMenu> querys(@Param("rid") Long rid, @Param("pid") Long pid);

    @Select("<script>" +
            "SELECT m.*," +
            "m.name as title," +
            "m.url as pageName " +
            "FROM tb_rolemenu  as rm " +
            " LEFT  JOIN tb_menu as m " +
            "on m.id=rm.menuid " +
            "<if test='rid!=null'> AND rm.roleid=#{rid} </if>" +
            "GROUP BY m.sort" +
            "</script>")
    List<SysMenu> selectByRid(@Param("rid") Long rid);
}
