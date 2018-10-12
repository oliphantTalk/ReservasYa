package com.ttps.reservasYa.repository;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


public abstract class AbstractHibernateDAO<T extends Serializable> {



    private SessionFactory sessionFactory;

    private Class< T > clazz;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public final void setClazz(Class< T > clazzToSet ){
        this.clazz = clazzToSet;
    }

    public T findOne( long id ){
        return (T) getCurrentSession().get( clazz, id );
    }
    public List< T > findAll(){
        return getCurrentSession().createQuery( "from " + clazz.getName() ).list();
    }

    @Transactional
    public void create( T entity ){
        getCurrentSession().persist( entity );
    }

    public void update( T entity ){
        getCurrentSession().merge( entity );
    }

    public void delete( T entity ){
        getCurrentSession().delete( entity );
    }
    public void deleteById( long entityId ) {
        T entity = findOne( entityId );
        delete( entity );
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
