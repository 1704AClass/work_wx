package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.itheima.constant.MessageConstant;
import com.itheima.pojo.CheckGroup;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckGroupService;
import com.itheima.service.CheckItemService;

import entity.PageResult;
import entity.QueryPageBean;
import entity.Result;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.Arrays;
import java.util.List;

/**
 * 检查组
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference//远程调用服务
    private CheckGroupService checkGroupService;

    /**
     * 检查项新增
     */ 
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkItemId) {
        try {
        	checkGroupService.add(checkGroup,checkItemId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    
    /**
     * 检查项分页查询
     */ 
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = checkGroupService.pageQuery(queryPageBean);
        return pageResult;
    }

    /**
     * 检查项删除
     */ 
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
        	checkGroupService.dels(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项批量编辑
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup) {
        try {
        	checkGroupService.edit(checkGroup);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项批量编辑回显
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
        	CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            //服务调用失败
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
