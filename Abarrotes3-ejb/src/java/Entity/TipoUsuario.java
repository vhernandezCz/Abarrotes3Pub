/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoUsuario", nullable = false)
    private Long idTipoUsuario;

    @Column(name = "claveUsuario", nullable = false)
    private String claveUsuario;
    
    @Column(name = "tipoUsuario", nullable = false)
    private String tipoUsuario;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdTipoUsuario() != null ? getIdTipoUsuario().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.getIdTipoUsuario() == null && other.getIdTipoUsuario() != null) || (this.getIdTipoUsuario() != null && !this.idTipoUsuario.equals(other.idTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TipoUsuario[ id=" + getIdTipoUsuario() + " ]";
    }

    /**
     * @return the idTipoUsuario
     */
    public Long getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param idTipoUsuario the idTipoUsuario to set
     */
    public void setIdTipoUsuario(Long idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    /**
     * @return the claveUsuario
     */
    public String getClaveUsuario() {
        return claveUsuario;
    }

    /**
     * @param claveUsuario the claveUsuario to set
     */
    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
