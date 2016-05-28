/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.tests;

import com.novesolucoes.botique.model.Cliente;
import com.novesolucoes.botique.uil.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author sergio
 */
public class TestaInsereCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        Cliente c = new Cliente();
        c.setNome("John Doe");
        c.setCpf("01234567810");
        c.setEndereco("Rua dos bobos");
        c.setFaixasalarial(2000);

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(c);

        tx.commit();

        em.close();
    }
    
}
