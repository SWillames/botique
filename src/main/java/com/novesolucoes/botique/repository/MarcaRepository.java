/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.repository;

import com.novesolucoes.botique.model.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author sergio
 */
public class MarcaRepository {
    private EntityManager manager;

    public MarcaRepository(EntityManager manager) {
        this.manager = manager;
    }
    
    public List<Marca> all(){
        TypedQuery<Marca> query = manager.createQuery("from Marca", Marca.class);
        return query.getResultList();
    }
    
    public void excluir(Marca marca) {
        /*Marca marca = this.porId(id);
        this.manager.remove(marca);**/
        Marca marcaEx = manager.merge(marca);
        this.manager.remove(marcaEx);
        
        
    }
    
    public void adicionar(Marca marca){
        this.manager.persist(marca);
    }
    
    public Marca porId(Long id){
        return this.manager.find(Marca.class, id); 
    }
    
    public void alterar(Marca marca){
        //Marca marcaEx = manager.merge(marca);
        this.manager.refresh(marca);
        //this.manager.flush();
        
    }
}
