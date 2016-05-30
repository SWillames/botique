/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.uil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sergio
 */
public class JPAUtil {
    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("Botique");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
