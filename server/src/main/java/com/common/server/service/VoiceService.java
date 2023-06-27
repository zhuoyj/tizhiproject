package com.common.server.service;

import com.common.server.domain.User;
import com.common.server.domain.Voice;
import com.common.server.mapper.VoiceMapper;
import com.common.server.utils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.common.server.mapper.RoleMapper;
import com.common.server.mapper.SysMapper;
import com.common.server.mapper.UserMapper;

@Service
public class VoiceService {


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
    public List<Voice> list() {
        return voiceMapper.selectAll();
    }

    //增加
    public void insert(Voice voice) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        voice.setTimes(format);
        voiceMapper.insert(voice);
    }

    //修改
    public void update(Voice voice) {
        voiceMapper.updateByPrimaryKey(voice);
    }

    //获取单个对象
    public Voice findOne(Long id) {
        Voice voice = new Voice();
        voice.setId(id);
        voice = this.voiceMapper.selectOne(voice);
        if (voice.getUser_Id() != null) {
            if (voice.getUser_Id() > 0) {
                User user = userService.findOne(voice.getUser_Id());
                if (user != null) {
                    voice.setUser(user);
                }
            }
        }
        return voice;
    }

    //删除
    public void del(Long id) {
        Voice voice = this.findOne(id);
        if (voice != null) {
            voiceMapper.delete(voice);
        }
    }

    //分页
    public PageResult<Voice> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(Voice.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Voice> voices = this.voiceMapper.selectByExample(example);
        List<Voice> results = new ArrayList<>();
        List<Voice> finalResults = results;
        voices.forEach(item -> {
            finalResults.add(this.findOne(item.getId()));
        });
        PageHelper.startPage(page, rows);
        PageInfo<Voice> pageInfo = new PageInfo<>(results);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public List<Voice> TopDate() {
        return  voiceMapper.TopDate();
    }
}
