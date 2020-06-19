package com.wang.spring.dao.service.impl;

import com.wang.spring.dao.entity.Test;
import com.wang.spring.dao.mapper.TestDao;
import com.wang.spring.dao.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Test)表服务实现类
 *
 * @author makejava
 * @since 2020-05-14 17:33:06
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Test queryById(Long id) {
        return this.testDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Test> queryAllByLimit(int offset, int limit) {
        return this.testDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    @Override
    public Test insert(Test test) {
        this.testDao.insert(test);
        return test;
    }

    /**
     * 修改数据
     *
     * @param test 实例对象
     * @return 实例对象
     */
    @Override
    public Test update(Test test) {
        this.testDao.update(test);
        return this.queryById(test.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.testDao.deleteById(id) > 0;
    }
}