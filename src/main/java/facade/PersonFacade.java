/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import dat3.jpademo.entities.Fee;
import dat3.jpademo.entities.Person;
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
    
}
