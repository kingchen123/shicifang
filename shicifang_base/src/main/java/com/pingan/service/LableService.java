package com.pingan.service;

import com.pingan.dao.LableDao;
import com.pingan.pojo.Lable;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

@Service
@Transactional
public class LableService {

    @Autowired
    private LableDao lableDao;
    @Autowired
    private IdWorker idWorker;

    public void add(Lable lable) {
       lable.setId(idWorker.nextId()+"");
        lableDao.save(lable);

    }

    public void deleteById(String labelId) {
        lableDao.deleteById(labelId);
    }


    public void update(Lable lable) {
        lableDao.save(lable);
    }

    public void selectById(String labelId) {
        lableDao.findById(labelId);
    }
}
