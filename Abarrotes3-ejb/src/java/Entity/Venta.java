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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenta", nullable = false)
    private Integer idVenta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idDatosAccesorio", referencedColumnName = "idDatosAccesorio", nullable = false)
    private DatosAccesorio datosAccesorio;

    @Column(name = "gananciaTipoPieza", nullable = false)
    private int gananciaTipoPieza;

    @Column(name = "piezasVendidas", nullable = false)
    private int piezasVendidas;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdVenta() != null ? getIdVenta().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.getIdVenta() == null && other.getIdVenta() != null) || (this.getIdVenta() != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Venta[ id=" + getIdVenta() + " ]";
    }

    /**
     * @return the idOrden
     */
    public Integer getIdVenta() {
        return idVenta;
    }

    /**
     * @return the datosAccesorio
     */
    public DatosAccesorio getDatosAccesorio() {
        return datosAccesorio;
    }

    /**
     * @param datosAccesorio the datosAccesorio to set
     */
    public void setDatosAccesorio(DatosAccesorio datosAccesorio) {
        this.datosAccesorio = datosAccesorio;
    }

    /**
     * @return the gananciaTipoPieza
     */
    public int getGananciaTipoPieza() {
        return gananciaTipoPieza;
    }

    /**
     * @param gananciaTipoPieza the gananciaTipoPieza to set
     */
    public void setGananciaTipoPieza(int gananciaTipoPieza) {
        this.gananciaTipoPieza = gananciaTipoPieza;
    }

    /**
     * @return the piezasVendidas
     */
    public int getPiezasVendidas() {
        return piezasVendidas;
    }

    /**
     * @param piezasVendidas the piezasVendidas to set
     */
    public void setPiezasVendidas(int piezasVendidas) {
        this.piezasVendidas = piezasVendidas;
    }

    /**
     * @param idVenta the idVenta to set
     */
    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    
}
