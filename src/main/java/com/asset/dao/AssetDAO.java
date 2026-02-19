package com.asset.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.asset.bean.Asset;
import com.asset.util.HibernateUtil;

public class AssetDAO {

    
    public Asset findAsset(String assetTag) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Asset asset = null;

        try {
            asset = session.createQuery(
                    "FROM Asset WHERE assetTag = :tag",
                    Asset.class)
                    .setParameter("tag", assetTag)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return asset;
    }

    
    public List<Asset> viewAllAssets() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Asset> list = null;

        try {
            list = session.createQuery("FROM Asset", Asset.class)
                          .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;
    }

    
    public boolean insertAsset(Asset asset) {

        Transaction tx = null;
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            session.persist(asset);
            tx.commit();
            result = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    
    public boolean updateAsset(Asset asset) {

        Transaction tx = null;
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();
            session.merge(asset);
            tx.commit();
            result = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    
    public boolean deleteAsset(String assetTag) {

        Transaction tx = null;
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            tx = session.beginTransaction();

            Asset asset = session.createQuery(
                    "FROM Asset WHERE assetTag = :tag",
                    Asset.class)
                    .setParameter("tag", assetTag)
                    .uniqueResult();

            if (asset != null) {
                session.remove(asset);
                result = true;
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
}
