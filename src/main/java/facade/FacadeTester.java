/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dat3.jpademo.entities.Address;
import dat3.jpademo.entities.Fee;
import dat3.jpademo.entities.Person;
import dat3.jpademo.entities.SwimStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Atom
 */
public class FacadeTester {
    
    public static void main(String[] args) {
        
        PersonFacade personFacade = PersonFacade.getPersonFacade("pu");
        FeeFacade feeFacade = FeeFacade.getFeeFacade("pu");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        
        Person p1 = new Person("Jønke", 1963);
        Person p2 = new Person("Blondie", 1959);
        
        Address a1 = new Address("Store Torv 1", 2323, "Nr. Snede");
        Address a2 = new Address("Langgade 34", 1212, "Valby");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        Fee f4 = new Fee(50);
       
        p1.addFee(f1);
        p1.addFee(f3);
        p2.addFee(f2);
        p2.addFee(f4);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("ButterFly");
        SwimStyle s3 = new SwimStyle("Breast stroke");
        
        p1.addSwimStyle(s1);
        p1.addSwimStyle(s3);
        p2.addSwimStyle(s2);
        
                        
        em.getTransaction().begin();
            em.persist(p1);
            em.persist(p2);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
            em.remove(s3);
            //p1.removeSwimStyle(s3);
        em.getTransaction().commit();
        
        personFacade.allPersonsAndFees(em);
        personFacade.allPersonsAndSwimStyles(em);
        personFacade.getPersonsByStyleName(em, "Crawl");
        
        feeFacade.findAllFees(em);
        feeFacade.findLowAndHighFee(em);
        
        
        em.close();
    }
    
    
}
