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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInventario", nullable = false)
    private Integer idInventario;

    @Column(name = "enStock", nullable = false)
    private int enStock;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idOrdenAccesorio")
    private OrdenAccesorio ordenAccesorio;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdInventario() != null ? getIdInventario().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.getIdInventario() == null && other.getIdInventario() != null) || (this.getIdInventario() != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Inventario[ id=" + getIdInventario() + " ]";
    }

    /**
     * @return the idInventario
     */
    public Integer getIdInventario() {
        return idInventario;
    }

    /**
     * @param idInventario the idInventario to set
     */
    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    /**
     * @return the enStock
     */
    public int getEnStock() {
        return enStock;
    }

    /**
     * @param enStock the enStock to set
     */
    public void setEnStock(int enStock) {
        this.enStock = enStock;
    }

    /**
     * @return the ordenAccesorio
     */
    public OrdenAccesorio getOrdenAccesorio() {
        return ordenAccesorio;
    }

    /**
     * @param ordenAccesorio the ordenAccesorio to set
     */
    public void setOrdenAccesorio(OrdenAccesorio ordenAccesorio) {
        this.ordenAccesorio = ordenAccesorio;
    }

}
