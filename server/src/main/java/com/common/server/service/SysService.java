package com.common.server.service;
import com.common.server.domain.Sys;
import com.common.server.mapper.SysMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
 import com.common.server.utils.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import com.common.server.mapper.RoleMapper;
import com.common.server.mapper.UserMapper;
import com.common.server.mapper.VoiceMapper;
@Service
public class  SysService {


@Autowired
private RoleMapper roleMapper;
@Autowired
private RoleService roleService;
@Autowired
private SysMapper sysMapper;
@Autowired
private SysService sysService;
@Autowired
private UserMapper userMapper;
@Autowired
private UserService userService;
@Autowired
private VoiceMapper voiceMapper;
@Autowired
private VoiceService voiceService;
//获取列表
public List<Sys> list() {
 return sysMapper.selectAll();
}
//增加
public void insert(Sys sys){
sysMapper.insert(sys);
}
//修改
public void update(Sys sys){
sysMapper.updateByPrimaryKey(sys);
}
 //获取单个对象
 public Sys findOne(Long id) {
Sys sys = new Sys();
sys.setId(id);
sys=this.sysMapper.selectOne(sys);
  return  sys;
}
//删除
 public void del(Long id) {
Sys sys =  this.findOne(id);
 if (sys != null) {
  sysMapper.delete( sys);
}
}
//分页
public PageResult<Sys> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
 Example example = new Example(Sys.class);
Example.Criteria criteria = example.createCriteria();
  if (StringUtils.isNotBlank(sortBy)) {
  example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
}
 PageHelper.startPage(page, rows);
  List<Sys> syss = this.sysMapper.selectByExample(example);
  PageInfo<Sys> pageInfo = new PageInfo<>(syss);
  return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
}
}
