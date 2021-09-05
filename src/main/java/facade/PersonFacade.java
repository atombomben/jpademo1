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

/**
 *
 * @author Atom
 */
public class PersonFacade {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PersonFacade personFacade;
    
    public static PersonFacade getPersonFacade(String persistenceName) {
        if (personFacade == null) {
            personFacade = new PersonFacade();
            emf = Persistence.createEntityManagerFactory(persistenceName);
            em = emf.createEntityManager();
            return personFacade;
        }
        return personFacade;
    }
    
    public void allPersonsAndFees(EntityManager em) {
        
        TypedQuery<Person> q1 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = q1.getResultList();
        for (Person p : persons) {
            String str = p.getName() + " = ";
            for (Fee f : p.getFees()) {
                str = str + f.getAmount() + " | ";
               
            }
            System.out.println(str);
        }
        
    }
    
    public void allPersonsAndSwimStyles (EntityManager em) {
        TypedQuery<Person> q1 = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = q1.getResultList();
        for (Person p : persons) {
            String allPersonsAndNumberOfSwimStyles = p.getName() + " = ";
            List<SwimStyle> swimStyles = p.getStyles();
                
        
            System.out.println(allPersonsAndNumberOfSwimStyles + swimStyles.size());
        }
        
    }
    
    public Person getPersonById (EntityManager em, long id) {
        TypedQuery<Person> q1 = em.createQuery("SELECT p FROM Person p WHERE p.p_id = " + id, Person.class);
        List<Person> persons = q1.getResultList();
        for (Person p : persons) {
            return p;
        }
        return null;
        
    }
    
    public void getPersonsByStyleName (EntityManager em, String swimStyle) {
        TypedQuery<Person> q1 = em.createQuery("SELECT p FROM Person p INNER JOIN p.styles s WHERE s.styleName = :swimStyle", Person.class);
        q1.setParameter("swimStyle",swimStyle);
        List<Person> persons = q1.getResultList();
        for (Person p : persons) {
            String str = p.getName();
            System.out.println(str);
        }
        
    }
    
    
}
