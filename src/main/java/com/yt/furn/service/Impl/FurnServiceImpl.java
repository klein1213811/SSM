package com.yt.furn.service.Impl;

import com.yt.furn.bean.Furn;
import com.yt.furn.bean.FurnExample;
import com.yt.furn.dao.FurnMapper;
import com.yt.furn.service.FurnService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FurnServiceImpl implements FurnService {
    // 注入FurnMapper接口对象
    @Resource
    private FurnMapper furnMapper;

    /** 在这里直接使用mapper的方法就好了
     * @param furn
     **/
    @Override
    public void save(Furn furn) {
        furnMapper.insertSelective(furn);
    }
    
    /** 传入是null。就返回所有信息
 * @return java.util.List<com.yt.furn.bean.Furn>
     **/
    @Override
    public List<Furn> findAll() {
        return furnMapper.selectByExample(null);
    }

    @Override
    public void update(Furn furn) {
        furnMapper.updateByPrimaryKeySelective(furn);
    }

    @Override
    public void delete(Integer id) {
        furnMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Furn> findByCondition(String furnName) {
        // 这个是可以带上条件的
        FurnExample furnExample = new FurnExample();
        // 通过criteria设置查询的条件
        FurnExample.Criteria criteria = furnExample.createCriteria();
        // 按照模糊查询
        if (StringUtils.hasText(furnName)) {
            // 由于是模糊查询，所以两侧的 % 不能少
            criteria.andNameLike("%" + furnName + "%");
        }

        // 如果过没有一个能符合模糊查询的条件的话，就还是返回所有的
        return furnMapper.selectByExample(furnExample);
    }


}
