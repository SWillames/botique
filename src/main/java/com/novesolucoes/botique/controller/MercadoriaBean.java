/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.controller;

import com.novesolucoes.botique.model.Marca;
import com.novesolucoes.botique.model.Mercadoria;
import com.novesolucoes.botique.repository.MarcaRepository;
import com.novesolucoes.botique.repository.MercadoriaRepository;
import com.novesolucoes.botique.uil.JPAUtil;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author sergio
 */
@Named(value = "mercadoriaBean")
@SessionScoped
public class MercadoriaBean {

    private Mercadoria mercadoria = new Mercadoria();
    private List<Mercadoria> mercadorias;
    private DataModel mercadoriaList = new ListDataModel();
    private Mercadoria mercadoriaSelected;
    
    
    
    public MercadoriaBean() {
    }
    
    public void salvar() {
        EntityManager manager = JPAUtil.getEntityManager();
        MercadoriaRepository repository = new MercadoriaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        if (mercadoria.getId()== null) {
            repository.adicionar(mercadoria);
            
        } else {
            this.setMercadoria(manager.merge(this.getMercadoria()));
        }
        //manager.flush();
        mercadorias.add(mercadoria);
        et.commit();
        manager.close();
        this.setMercadoria(new Mercadoria());
    }
    
     
     
    public void remove() {
                
        EntityManager manager = JPAUtil.getEntityManager();
        MercadoriaRepository repository = new MercadoriaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Mercadoria marcaEx = getMarcaFromEditOrDelete();
        
        repository.excluir(marcaEx);
       // this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
    }
    
    public Mercadoria getMarcaFromEditOrDelete(){  
       Mercadoria marcaSel = (Mercadoria) mercadoriaList.getRowData();  
        return marcaSel;  
    } 

    public Mercadoria getMercadoria() {
        return mercadoria;
    }

    public void setMercadoria(Mercadoria mercadoria) {
        this.mercadoria = mercadoria;
    }
    
    public DataModel getMarcaList() {
        EntityManager em = JPAUtil.getEntityManager();
        MercadoriaRepository repository = new MercadoriaRepository(em);
        mercadorias = repository.all();
        setMercadoriaList(new ListDataModel(mercadorias));
        return mercadoriaList;
    }

    public List<Mercadoria> getMercadorias() {
        if (this.mercadorias == null){
        EntityManager em = JPAUtil.getEntityManager();
            MercadoriaRepository repository = new MercadoriaRepository(em);
            mercadorias = repository.all();
        }
        return mercadorias;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg;
        msg = new FacesMessage("Car Edited", ((Mercadoria) event.getObject()).getIndetificaMercadoria());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        EntityManager manager = JPAUtil.getEntityManager();
        MercadoriaRepository repository = new MercadoriaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Mercadoria mercadoriaEdited  = (Mercadoria) event.getObject();
        repository.alterar(mercadoriaEdited);
        
        et.commit();
        manager.close();
       
      
        et.begin();
        
       // marca = getMarcaFromEditOrDelete();
        
        repository.alterar(mercadoria);
        //this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
        
    }

    public void setMercadorias(List<Mercadoria> mercadorias) {
        this.mercadorias = mercadorias;
    }

    public DataModel getMercadoriaList() {
        return mercadoriaList;
    }

    public void setMercadoriaList(DataModel mercadoriaList) {
        this.mercadoriaList = mercadoriaList;
    }

    public Mercadoria getMercadoriaSelected() {
        return mercadoriaSelected;
    }

    public void setMercadoriaSelected(Mercadoria mercadoriaSelected) {
        this.mercadoriaSelected = mercadoriaSelected;
    }
}
