package com.common.server.mapper;
import com.common.server.domain.Voice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VoiceMapper extends tk.mybatis.mapper.common.Mapper<Voice>{
    @Select("SELECT * FROM tb_voice ORDER BY times DESC")
    List<Voice> TopDate();
}
