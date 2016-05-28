/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novesolucoes.botique.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author sergio
 */
@Entity
public class Mercadoria implements Serializable {

    @SequenceGenerator(name = "mercadoriaGenerator",
            sequenceName = "MERCADORIA_SEQ",
            allocationSize = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "mercadoriaGenerator")
    private Long id;
    private Float preco;
    private String indetificaMercadoria;
    //@ManyToOne
    private String marca;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public String getIndetificaMercadoria() {
        return indetificaMercadoria;
    }

    public void setIndetificaMercadoria(String indetificaMercadoria) {
        this.indetificaMercadoria = indetificaMercadoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mercadoria other = (Mercadoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
