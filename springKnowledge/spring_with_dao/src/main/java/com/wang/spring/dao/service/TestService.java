package com.wang.spring.dao.service;

import com.wang.spring.dao.entity.Test;
import java.util.List;

/**
 * (Test)表服务接口
 *
 * @author makejava
 * @since 2020-05-14 17:33:01
 */
public interface TestService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Test queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Test> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test insert(Test test);

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    Test update(Test test);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}