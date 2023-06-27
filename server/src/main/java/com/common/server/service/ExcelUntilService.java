package com.common.server.service;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.common.server.domain.ActionScore;
import com.common.server.domain.Actions;
import com.common.server.domain.User;
import com.common.server.domain.UserClass;
import com.common.server.excelModel.ImportSocre;
import com.common.server.excelModel.ImportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExcelUntilService {

    @Autowired
    private UserService userService;
    @Autowired
    private UserClassService userClassService;

    public Boolean importUsers(MultipartFile file) {

        ImportParams importParams = new ImportParams();
        //标题行设置为1行，默认是0，可以不设置；依实际情况设置。
        // importParams.setTitleRows(1);
        // 表头设置为1行
        // importParams.setHeadRows(1);
        try {
            //读取excel
            List<ImportUser> users = ExcelImportUtil.importExcel(file.getInputStream(), ImportUser.class, importParams);
//            for (ImportUser user : users) {
//                System.out.println(user.getName());
//            }
            //批量插入
            this.saveUsers(users);
            //返回结果
            // return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    public void saveUsers(List<ImportUser> users) {
        for (ImportUser user : users) {
            User u = new User();
            u.setUserid(Long.valueOf(user.getUserid()));
            User userByUserId = userService.QueryOne(u);
            if (userByUserId != null) {
                break;
            }
            User u1 = new User();
            u.setIdcardno(user.getIdcardno());
            User userByIdCard = userService.QueryOne(u);
            if (userByIdCard != null) {
                break;
            }
            UserClass userClass = new UserClass();
            userClass.setName(user.getUserclassid());
            UserClass userClass1 = userClassService.QueryOne(userClass);
            if (userClass1 == null) {
                break;
            }
            User user1 = new User("", user.getName(), Long.valueOf(4), user.getSex().equals("男") ? true : false, user.getAge().longValue(), user.getPhone(), user.getAddress(), user.getIdcardno(), Long.valueOf(user.getUserid()), userClass1.getId(), null);
            userService.insert(user1);
        }
    }

    @Autowired
    private ActionScoreService actionScoreService;
    @Autowired
    private ActionsService actionsService;
    public void importScore(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        //标题行设置为1行，默认是0，可以不设置；依实际情况设置。
        // importParams.setTitleRows(1);
        // 表头设置为1行
        // importParams.setHeadRows(1);
        try {
            //读取excel
            List<ImportSocre> socres = ExcelImportUtil.importExcel(file.getInputStream(), ImportSocre.class, importParams);
//            for (ImportUser user : users) {
//                System.out.println(user.getName());
//            }
            //批量插入
            this.saveScores(socres);
            //返回结果
            // return isSuccess;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void saveScores(List<ImportSocre> socres) {
        for (ImportSocre socre : socres) {
            Actions actions = actionsService.findOne(socre.getAname());
            if (actions==null){
                break;
            }
            User user = userService.findOneUserId(Long.valueOf(socre.getUserid()));
            if (user==null){
                break;
            }
            ActionScore one = actionScoreService.findOne(actions.getId(), user.getId());
            if (one!=null){
                break;
            }
            ActionScore actionScore=new ActionScore(actions.getId(),user.getId(), Double.valueOf(socre.getScore())  );
            actionScoreService.insert(actionScore);
        }
    }
}
