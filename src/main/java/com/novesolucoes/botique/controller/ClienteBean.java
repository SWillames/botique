/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.controller;

import com.novesolucoes.botique.model.Cliente;
import com.novesolucoes.botique.repository.ClienteRepository;
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
@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean {
private List<Cliente> clientes;
    private Cliente cliente = new Cliente();
    private DataModel clienteList = new ListDataModel();
    private Cliente clienteSelected;

    //  private final EntityTransaction trx = manager.getTransaction();
    //  private final FacesContext context = FacesContext.getCurrentInstance();
    public ClienteBean() {
    }
    
    public void salvar() {
        EntityManager manager = JPAUtil.getEntityManager();
        ClienteRepository repository = new ClienteRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        if (cliente.getId() == null) {
            repository.adicionar(cliente);
            
        } else {
            this.setCliente(manager.merge(this.getCliente()));
        }
        //clientes.add(cliente);
        et.commit();
        manager.close();
        this.setCliente(new Cliente());
    }
    
    public void remover() {
        EntityManager manager = JPAUtil.getEntityManager();
        ClienteRepository repository = new ClienteRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Cliente clienteEx = getMarcaFromEditOrDelete();
        
        repository.excluir(clienteEx);
       // this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
    }
    
    public Cliente getMarcaFromEditOrDelete(){  
       Cliente clienteSel = (Cliente) clienteList.getRowData();  
        return clienteSel;  
    }
   
    public void prepararAlterar(){
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Long id = Long.parseLong(params.get("id"));
        EntityManager manager = JPAUtil.getEntityManager();
        ClienteRepository repository = new ClienteRepository(manager);
        this.cliente = repository.porId(id);
        //repository.alterar(cliente);
    }

    public Cliente getClienteEditorDelete(){
        Cliente clienteSelected = (Cliente) clienteList.getRowData();
        return clienteSelected;
    }
    
    public List<Cliente> getClientes() {
        if (clientes == null) {
            EntityManager em = JPAUtil.getEntityManager();
            ClienteRepository repository = new ClienteRepository(em);
            clientes = repository.all();
        }
        return clientes;
    }
    
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg;
        msg = new FacesMessage("Car Edited", ((Cliente) event.getObject()).getNome());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
        EntityManager manager = JPAUtil.getEntityManager();
        ClienteRepository repository = new ClienteRepository(manager);
        EntityTransaction et = manager.getTransaction();
        et.begin();
        
        Cliente clienteEdited  = (Cliente) event.getObject();
        repository.alterar(clienteEdited);
        
        et.commit();
        manager.close();
       
      
        et.begin();
        
       // marca = getMarcaFromEditOrDelete();
        
        repository.alterar(cliente);
        //this.marcas.remove(marcaEx);
        
         et.commit();
        manager.close();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DataModel getClienteList() {
        return clienteList;
    }

    public void setClienteList(DataModel clienteList) {
        this.clienteList = clienteList;
    }
    
}
