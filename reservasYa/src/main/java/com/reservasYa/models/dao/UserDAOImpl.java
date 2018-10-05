package com.reservasYa.models.dao;

import com.reservasYa.models.User;
import com.reservasYa.repository.AbstractHibernateDAO;
import com.reservasYa.repository.GenericHibernateDao;
import com.reservasYa.repository.IGenericDao;
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
