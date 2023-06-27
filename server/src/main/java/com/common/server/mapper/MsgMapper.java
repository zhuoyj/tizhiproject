package com.common.server.mapper;

import com.common.server.domain.Msg;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MsgMapper extends tk.mybatis.mapper.common.Mapper<Msg>{

}
