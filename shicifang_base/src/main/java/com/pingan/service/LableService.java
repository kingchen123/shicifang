package com.pingan.service;

import com.pingan.dao.LableDao;
import com.pingan.pojo.Lable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LableService {

    @Autowired
    private LableDao lableDao;
    @Autowired
    private IdWorker idWorker;

    public void add(Lable lable) {
        lable.setId(idWorker.nextId() + "");
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

    public List<Lable> selectByTerm(Lable lable) {
        Specification<Lable> specification = getLableSpecification(lable);
        return lableDao.findAll(specification);
    }

    public Page findByTerm(Lable lable, int page, int size) {
        Specification<Lable> specification = getLableSpecification(lable);
        PageRequest pageRequest = PageRequest.of(page-1, size);

        return lableDao.findAll(specification,pageRequest );

    }

    private Specification<Lable> getLableSpecification(Lable lable) {
        return new Specification<Lable>() {
            @Override
            public Predicate toPredicate(Root<Lable> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (lable.getLabelname() != null) {
                    list.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + lable.getLabelname() + "%"));
                }
                if (lable.getState() != null) {
                    list.add(criteriaBuilder.equal(root.get("state").as(String.class), lable.getState()));
                }
                if (lable.getCount() > 0) {
                    list.add(criteriaBuilder.ge(root.get("count").as(Long.class), lable.getCount()));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        };
    }
}
