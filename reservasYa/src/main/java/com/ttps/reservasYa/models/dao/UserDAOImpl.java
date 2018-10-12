package com.ttps.reservasYa.models.dao;

import com.ttps.reservasYa.models.User;
import com.ttps.reservasYa.repository.GenericHibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserDAOImpl implements Serializable {

    @Autowired
    GenericHibernateDao<User> dao;

    public void setDao(GenericHibernateDao<User> daoToSet) {
        dao = daoToSet;
        dao.setClazz(User.class);
    }

    public GenericHibernateDao<User> getDao() {
        return dao;
    }
}
