package com.itheima.service;

import com.itheima.pojo.CheckGroup;

import entity.PageResult;
import entity.QueryPageBean;

public interface CheckGroupService {

	CheckGroup findById(Integer id);

	void edit(CheckGroup checkGroup);

	void dels(Integer id);

	PageResult pageQuery(QueryPageBean queryPageBean);

	void add(CheckGroup checkGroup, Integer[] checkItemId);
	
}
