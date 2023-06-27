package com.common.server.mapper;

import com.common.server.domain.Actions;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Mapper
public interface ActionsMapper extends tk.mybatis.mapper.common.Mapper<Actions>{

    @Select("<script>" +
            "SELECT a.*,GROUP_CONCAT(a.cid) AS cids FROM tb_actions as a  " +
            " where 1=1 " +
            "<if test='tid!=null'> and a.typeid = #{tid} </if>" +
            "<if test='uid!=null'> and a.uid = #{uid} </if>" +
            "<if test='status!=null'> and a.status >= #{status} </if>" +
            " GROUP BY a.exid " +
            "</script>")
    List<Actions> queryByExample(@Param("tid")Integer tid,@Param("uid")Long uid,@Param("status")Integer status);
}
