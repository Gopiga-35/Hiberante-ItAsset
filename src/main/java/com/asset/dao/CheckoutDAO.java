package com.asset.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.asset.bean.Checkout;
import com.asset.util.HibernateUtil;

public class CheckoutDAO {

    
    public boolean recordCheckout(Checkout checkout) {

        Transaction tx = null;
        boolean result = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            checkout.setStatus("OUT");

            session.persist(checkout);

            tx.commit();
            result = true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();   
            e.printStackTrace();
        }

        return result;
    }

    
    public List<Checkout> findActiveCheckoutByAsset(String assetTag) {

        List<Checkout> list = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            list = session.createQuery(
                    "FROM Checkout WHERE assetTag = :tag AND status IN ('OUT','LOST','DAMAGED')",
                    Checkout.class)
                    .setParameter("tag", assetTag)
                    .getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
    public Checkout findCheckoutByID(int checkoutID) {

        Checkout checkout = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            checkout = session.get(Checkout.class, checkoutID);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkout;
    }

    
    public boolean updateCheckoutStatusAndReturnDate(int checkoutID,
                                                     String status,
                                                     Date returnDate) {

        Transaction tx = null;
        boolean updated = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Checkout checkout = session.get(Checkout.class, checkoutID);

            if (checkout != null) {
                checkout.setStatus(status);
                checkout.setReturnDate(returnDate);
                updated = true;
            }

            tx.commit();

        } catch (Exception e) {
            if (tx != null) tx.rollback();   
            e.printStackTrace();
        }

        return updated;
    }
}
