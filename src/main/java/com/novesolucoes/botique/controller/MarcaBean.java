/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.controller;

import com.novesolucoes.botique.model.Marca;
import com.novesolucoes.botique.repository.MarcaRepository;
import com.novesolucoes.botique.uil.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
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
@Named(value = "marcaBean")
@RequestScoped
public class MarcaBean {

    private List<Marca> marcas = new ArrayList();
    private Marca marca = new Marca();
    private DataModel marcaList = new ListDataModel();
    private Marca marcaSelected;
    
    
    public MarcaBean() {
    }
    
    public void salvar() {

        EntityManager manager = JPAUtil.getEntityManager();
        MarcaRepository repository = new MarcaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
       
        if (marca.getId()== null) {
            repository.adicionar(marca);
            
        } else {
            this.setMarca(manager.merge(this.getMarca()));
        }
        //manager.flush();
        marcas.add(marca);
        et.commit();
        manager.close();
        this.setMarca(new Marca());
    
    }
    
    public void alterar(){
       
        setMarca(getMarcaFromEditOrDelete());
       
    }
    
    public void prepararAlterar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long id = Long.parseLong(params.get("id"));
        EntityManager manager = JPAUtil.getEntityManager();
        MarcaRepository repository = new MarcaRepository(manager);
        this.marca = repository.porId(id);
        //repository.alterar(cliente);
    }

    private void listarMarcas(){
        if (marcas == null) {
           EntityManager em = JPAUtil.getEntityManager();
            MarcaRepository repository = new MarcaRepository(em);
            marcas = repository.all();
            setMarcaList(new ListDataModel(marcas));
        }
    }
    
    public List<Marca> getMarcas() {
        if (marcas == null) {
           EntityManager em = JPAUtil.getEntityManager();
            MarcaRepository repository = new MarcaRepository(em);
            marcas = repository.all();
            
        }
        return marcas;
    }
    
    public void remove() {
        /*Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long id = Long.parseLong(params.get("id"));
        EntityManager manager = JPAUtil.getEntityManager();
        MarcaRepository repository = new MarcaRepository(manager);
        repository.excluir(id); **/
                
        EntityManager manager = JPAUtil.getEntityManager();
        MarcaRepository repository = new MarcaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Marca marcaEx = getMarcaFromEditOrDelete();
        
        repository.excluir(marcaEx);
       // this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
    }
    
    public Marca getMarcaFromEditOrDelete(){  
       Marca marcaSel = (Marca) marcaList.getRowData();  
        return marcaSel;  
    } 

    public void setMarcas(List<Marca> marcas) {
        this.marcas = marcas;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
     public DataModel getMarcaList() {
        EntityManager em = JPAUtil.getEntityManager();
            MarcaRepository repository = new MarcaRepository(em);
            marcas = repository.all();
            setMarcaList(new ListDataModel(marcas));
         return marcaList;
    }

    public void setMarcaList(DataModel marcaList) {
        this.marcaList = marcaList;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg;
        msg = new FacesMessage("Car Edited", ((Marca) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        EntityManager manager = JPAUtil.getEntityManager();
        MarcaRepository repository = new MarcaRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Marca marcaEdited  = (Marca) event.getObject();
        repository.alterar(marcaEdited);
        
        et.commit();
        manager.close();
       
      
        et.begin();
        
       // marca = getMarcaFromEditOrDelete();
        
        repository.alterar(marca);
        //this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
        
    }
    
    public Marca getMarcaSelected() {
        return marcaSelected;
    }

    public void setMarcaSelected(Marca marcaSelected) {
        this.marcaSelected = marcaSelected;
    }
    
}
