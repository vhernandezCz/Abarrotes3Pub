/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class DatosUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDatosUsuario", nullable = false)
    private Long idDatosUsuario;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idTipoUsuario")
    private TipoUsuario tipoUsuario;

    @Column(name = "esActivo", nullable = false)
    private boolean esActivo;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdDatosUsuario() != null ? getIdDatosUsuario().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosUsuario)) {
            return false;
        }
        DatosUsuario other = (DatosUsuario) object;
        if ((this.getIdDatosUsuario() == null && other.getIdDatosUsuario() != null) || (this.getIdDatosUsuario() != null && !this.idDatosUsuario.equals(other.idDatosUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DatosUsuario[ id=" + getIdDatosUsuario() + " ]";
    }

    /**
     * @return the idDatosUsuario
     */
    public Long getIdDatosUsuario() {
        return idDatosUsuario;
    }

    /**
     * @param idDatosUsuario the idDatosUsuario to set
     */
    public void setIdDatosUsuario(Long idDatosUsuario) {
        this.idDatosUsuario = idDatosUsuario;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the enStock
     */
    public boolean isEnStock() {
        return esActivo;
    }

    /**
     * @param enStock the enStock to set
     */
    public void setEnStock(boolean enStock) {
        this.esActivo = enStock;
    }

}
