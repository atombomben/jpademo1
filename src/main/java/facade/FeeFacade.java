/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dat3.jpademo.entities.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class FeeFacade {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static FeeFacade feeFacade;
    
    public static FeeFacade getFeeFacade(String persistenceName) {
        if (feeFacade == null) {
            feeFacade = new FeeFacade();
            emf = Persistence.createEntityManagerFactory(persistenceName);
            em = emf.createEntityManager();
            return feeFacade;
        }
        return feeFacade;
    }
    
        public void findAllFees(EntityManager em) {
        int totalFee = 0;
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        for (Fee p : fees) {
            totalFee += p.getAmount();
           
            
        }
        System.out.println(totalFee);
    }
        
        public void findLowAndHighFee(EntityManager em) {
        int lowFee = 0;
        int highFee = 0;
        TypedQuery<Fee> q1 = em.createQuery("SELECT f FROM Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        for (Fee p : fees) {
            if (lowFee == 0) {
                lowFee = p.getAmount();
            }
            else if (lowFee > p.getAmount()) {
                lowFee = p.getAmount();
            }
            if (highFee == 0) {
            highFee = p.getAmount();
            }
            else if (highFee < p.getAmount()) {
                highFee = p.getAmount();
            }
            
            
        }
        System.out.println(lowFee);
        System.out.println(highFee);
    }
    
}
