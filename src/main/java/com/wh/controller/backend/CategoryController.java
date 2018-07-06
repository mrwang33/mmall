package com.wh.controller.backend;

import com.wh.common.Const;
import com.wh.common.ResponseCode;
import com.wh.common.ServerResponse;
import com.wh.pojo.Category;
import com.wh.pojo.User;
import com.wh.service.ICategoryService;
import com.wh.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager/category")
public class CategoryController {
    @Resource
    private IUserService iUserService;
    @Resource
    private ICategoryService iCategoryService;

    /**
     * 添加分类
     * @param session 登录session 用于检测用户是否有权限
     * @param categoryName 分类名
     * @param parentId 父分类id 默认值为0
     * @return
     */
    @RequestMapping(value = "addCategory",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCategory(HttpSession session,String categoryName,@RequestParam(value = "parentId",defaultValue = "0") int parentId) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录");
        }
        if (iUserService.checkUser(user).isSuccess()) {
            return iCategoryService.addCategory(categoryName,parentId);
        } else {
            return ServerResponse.createByErrorMessage("无权限");
        }
    }

	
	
    /**
     * 更新分类
     * @param session
     * @param category
     * @return
     */
    @RequestMapping(value = "modifyCategory",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse modifyCategory(HttpSession session, Category category) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"请登录");
        }
        if (iUserService.checkUser(user).isSuccess()) {
            return iCategoryService.modifyCategory(category);
        } else {
            return ServerResponse.createByErrorMessage("无权限");
        }
    }
}
