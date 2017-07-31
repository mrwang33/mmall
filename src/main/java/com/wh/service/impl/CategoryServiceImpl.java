package com.wh.service.impl;

import com.wh.common.ServerResponse;
import com.wh.dao.CategoryMapper;
import com.wh.pojo.Category;
import com.wh.service.ICategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("iCategoryService")
public class CategoryServiceImpl implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse addCategory(String categoryName, int parentId) {
        if (categoryName==null||"".equals(categoryName)) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        Category category = new Category();
        category.setName(categoryName);
        category.setParentId(parentId);
        category.setStatus(true);
        int insert = categoryMapper.insert(category);
        if (insert>0) {
            return ServerResponse.createBySuccessMessage("添加新分类成功");
        } else {
            return ServerResponse.createByErrorMessage("发生未知错误，请联系网站管理员");
        }
    }

    @Override
    public ServerResponse modifyCategory(Category category) {
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        if (i>0) {
            ServerResponse.createBySuccessMessage("更新分类成功");
        }
        return ServerResponse.createByErrorMessage("更新分类失败");
    }
}
