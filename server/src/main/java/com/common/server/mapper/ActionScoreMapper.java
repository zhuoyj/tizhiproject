package com.common.server.mapper;

import com.common.server.domain.ActionScore;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActionScoreMapper extends tk.mybatis.mapper.common.Mapper<ActionScore>{

    @Select("<script>" +
            "SELECT * FROM tb_action_score as a LEFT JOIN tb_user as u ON a.uid=u.id " +
            "WHERE 1=1 " +
            "<if test='cid!=null'> and u.userclassid=#{cid} </if>" +
            "<if test='aid!=null'> and a.aid=#{aid} </if>" +
            "<if test='uid!=null'> and a.uid=#{uid} </if>" +
            "</script>")
    List<ActionScore> queryByExample(Long aid, Long uid, Long cid);

    @Select("<script>" +
            "SELECT s.*,u.`name` as uname,u.headpic FROM tb_action_score as s LEFT JOIN tb_user as u ON u.id=s.uid WHERE s.aid=#{aid} ORDER BY score DESC  LIMIT 5" +
            "</script>")
    List<ActionScore> limtDatas(@Param("aid") Long aid);
}
