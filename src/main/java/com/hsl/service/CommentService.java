package com.hsl.service;

import com.hsl.model.CommentRate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.conf.xml").buildSessionFactory();
//            sessionFactory.close();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public List<CommentRate> findAll() {
        String queryStr = "SELECT c FROM CommentRate AS c";
        TypedQuery<CommentRate> query = entityManager.createQuery(queryStr, CommentRate.class);
        return query.getResultList();
//        return new ArrayList<>();
    }

    public CommentRate save(CommentRate commentRate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            CommentRate origin = new CommentRate(commentRate.getRate(), commentRate.getAuthor(), commentRate.getComment(), 0);
            session.saveOrUpdate(origin);
            transaction.commit();
            return origin;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public CommentRate findOne(int id) {
        String queryStr = "SELECT c FROM CommentRate AS c WHERE c.id = :id";
        TypedQuery<CommentRate> query = entityManager.createQuery(queryStr, CommentRate.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void like(int id) {
        String queryStr = "update CommentRate set likeComment = likeComment + 1 WHERE CommentRate.id = :id";
        TypedQuery<CommentRate> query = entityManager.createQuery(queryStr, CommentRate.class);
        query.setParameter("id", id);
//        return query
    }

    public CommentRate update(CommentRate commentRate) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
//            CommentRate origin = new CommentRate(commentRate.getRate(), commentRate.getAuthor(), commentRate.getComment(), 0);
            session.saveOrUpdate(commentRate);
            transaction.commit();
            return commentRate;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
