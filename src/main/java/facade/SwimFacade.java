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


public class SwimFacade {
    
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static SwimFacade swimFacade;
    
    public static SwimFacade getSwimFacade(String persistenceName) {
        if (swimFacade == null) {
            swimFacade = new SwimFacade();
            emf = Persistence.createEntityManagerFactory(persistenceName);
            em = emf.createEntityManager();
            return swimFacade;
        }
        return swimFacade;
    }
    
    
}
