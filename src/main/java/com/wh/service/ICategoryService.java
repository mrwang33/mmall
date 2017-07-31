package com.wh.service;

import com.wh.common.ServerResponse;
import com.wh.pojo.Category;

public interface ICategoryService {
    ServerResponse addCategory(String categoryName,int parentId);

    ServerResponse modifyCategory(Category category);
}
