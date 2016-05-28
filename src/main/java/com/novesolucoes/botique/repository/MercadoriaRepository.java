/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.repository;

import com.novesolucoes.botique.model.Mercadoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author sergio
 */
public class MercadoriaRepository {
    private EntityManager manager;

    public MercadoriaRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public List<Mercadoria> all(){
        TypedQuery<Mercadoria> query = manager.createQuery("from Mercadoria", Mercadoria.class);
        return query.getResultList();
    }
    
    public void adicionar(Mercadoria mercadoria){
        this.manager.persist(mercadoria);
    }
    
    public Mercadoria porId(Long id){
        return this.manager.find(Mercadoria.class, id);
    }
    
   public void excluir(Mercadoria mercadoria) {
        /*Marca marca = this.porId(id);
        this.manager.remove(marca);**/
        Mercadoria marcaEx = manager.merge(mercadoria);
        this.manager.remove(marcaEx);
        
        
    }
    
    public void alterar(Mercadoria mercadoria){
        this.manager.merge(mercadoria);
    }
    
}
