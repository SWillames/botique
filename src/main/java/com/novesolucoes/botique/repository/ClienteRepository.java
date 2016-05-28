/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.repository;

import com.novesolucoes.botique.model.Cliente;
import com.novesolucoes.botique.model.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author sergio
 */
public class ClienteRepository {
    private EntityManager manager;
    

    public ClienteRepository(EntityManager manager) {
        this.manager = manager;
    }

    public List<Cliente> all() {
        TypedQuery<Cliente> query = manager.createQuery("from Cliente", Cliente.class);
        return query.getResultList();
    }
    
    public void adicionar(Cliente cliente){
        this.manager.persist(cliente);
    }
    
    public Cliente porId(Long id){
        return this.manager.find(Cliente.class, id);
    }
    
    public void excluir(Cliente cliente){
        Cliente clienteEx = manager.merge(cliente);
        this.manager.remove(clienteEx);
    }
    
    public void alterar(Cliente cliente){
        this.manager.merge(cliente);
    }
}
