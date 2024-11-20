/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author lenovo
 */
@Entity
public class DiasSemana implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiasSemana)) {
            return false;
        }
        DiasSemana other = (DiasSemana) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DiasSemana[ id=" + id + " ]";
    }

    /**
     * @return the lunes
     */
    public boolean isLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public boolean isMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public boolean isMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public boolean isJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public boolean isViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the sabado
     */
    public boolean isSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    /**
     * @return the domingo
     */
    public boolean isDomingo() {
        return domingo;
    }

    /**
     * @param domingo the domingo to set
     */
    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }
    
}
