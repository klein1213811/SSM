package com.yt.furn.service;

import com.yt.furn.bean.Furn;

import java.util.List;

public interface FurnService {
    public void save(Furn furn);
    public List<Furn> findAll();
    public void update(Furn furn);
    public void delete(Integer id);
    public List<Furn> findByCondition(String furnName);
}
