package com.common.server.service;

import com.common.server.domain.ActionScore;
import com.common.server.domain.Actions;
import com.common.server.domain.User;
import com.common.server.mapper.ActionScoreMapper;
import com.common.server.utils.PageResult;
import com.common.server.utils.TabulatorRes;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionScoreService {
    @Autowired
    private ActionScoreMapper actionScoreMapper;
    @Autowired
    private ActionsService actionsService;
    @Autowired
    private UserService userService;

    public List<ActionScore> list() {
        return actionScoreMapper.selectAll();
    }

    public List<ActionScore> list(Long aid) {
        ActionScore actionScore = new ActionScore();
        actionScore.setAid(aid);
        return actionScoreMapper.select(actionScore);
    }

    public void insert(ActionScore actionScore) {

        actionScoreMapper.insert(actionScore);
    }

    public void update(ActionScore actionScore) {
        actionScoreMapper.updateByPrimaryKey(actionScore);

    }

    public ActionScore findOne(Long uid, Long aid) {
        ActionScore actionScore = new ActionScore();
        actionScore.setUid(uid);
        actionScore.setAid(aid);
        return this.actionScoreMapper.selectOne(actionScore);
    }

    //获取单个对象
    public ActionScore findOne(Long id) {
        ActionScore actionScore = new ActionScore();
        actionScore.setId(id);


        return this.actionScoreMapper.selectOne(actionScore);
    }

    public void del(Long id) {
        ActionScore actionScore = this.findOne(id);
        if (actionScore != null) {
            actionScoreMapper.delete(actionScore);
        }
    }

    public PageResult<ActionScore> findAllByPage(Integer page, Integer rows, String key, String sortBy, Boolean desc) {
        Example example = new Example(ActionScore.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        PageHelper.startPage(page, rows);
        List<ActionScore> actionScores = this.actionScoreMapper.selectByExample(example);
        PageInfo<ActionScore> pageInfo = new PageInfo<>(actionScores);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    public TabulatorRes<ActionScore> findTabulatorRes(Integer page, Integer size, String key, List<String> filter, List<String> sorters, Long aid, Long uid,Long cid) {

        PageHelper.startPage(page, size);
        Page<ActionScore> actionScores = (Page<ActionScore>) this.actionScoreMapper.queryByExample(aid,uid,cid);
        List<ActionScore> collect = actionScores.stream().map(c -> {
            if (c.getAid() != null) {
                Actions one = actionsService.findOne(c.getAid());
                if (one != null) {
                    c.setAname(one.getName());
                }
            }
            if (c.getUid() != null) {
                User one = userService.findOne(c.getUid());
                if (one != null) {
                    c.setUname(one.getName());
                }
            }
            return c;
        }).collect(Collectors.toList());
        Integer totalpage = actionScores.size() % size > 0 ? actionScores.size() / size + 1 : actionScores.size() / size;
        return new TabulatorRes<>(totalpage, collect);
    }

    public String getLv(Long aid, Double score,Long cid) {
        DecimalFormat format = new DecimalFormat("#.00");
        Actions actions = actionsService.findOne(aid);
        List<User> users = this.userService.queryBycid(actions.getCid(), Long.valueOf(4));
        int size = users.size();
        int mans = getClassGL(aid, score,cid);
        Double lv = Double.valueOf(mans)  / Double.valueOf(size) *100;
        return format.format(lv)+"%";
    }

    public Integer getClassGL(Long aid, Double score,Long cid) {
        Integer res = 0;
        List<ActionScore> list = actionScoreMapper.queryByExample(aid,null,cid);


        for (ActionScore actionScore : list) {
            if (actionScore.getScore() >= score) {
                res += 1;
            }
        }
        return res;
    }

    public List<ActionScore> limtDatas(Long aid) {
        return actionScoreMapper.limtDatas(aid);
    }

}
