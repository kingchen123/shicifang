package com.pingan.spit.service;

import com.pingan.spit.dao.SpitDao;
import com.pingan.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 增
     *
     * @param spit
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if(spit.getParentid()!=null && !"".equals(spit.getParentid())){//如果存在上级ID,评论(因为是comment字段)
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 删
     *
     * @param spitId
     */
    public void dele(String spitId) {
        spitDao.deleteById(spitId);
    }

    public void update(Spit spit) {
        spitDao.save(spit);

    }

    public void findByOne(String spitId) {
        spitDao.findById(spitId);
    }

    public void findAll() {
        spitDao.findAll();
    }

    public Page<Spit> findByParentid(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentid, pageable);
    }

    public void updateThumbup(String spitId) {
       /* Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);*/
        //Query query:查询条件spitId
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //Update update：更新的字段 点赞数每次+1
        Update update = new Update();
        update.inc("thumbup", 1);//inc运算符比较高效  每次+1
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /* public void updateThumbup(String spitId) {
        *//*Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);*//*
        //Query query:查询条件spitId
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        //Update update：更新的字段 点赞数每次+1
        Update update = new Update();
        update.inc("thumbup", 1);//inc运算符比较高效  每次+1
        mongoTemplate.updateFirst(query, update, "spit");
    }*/

}
