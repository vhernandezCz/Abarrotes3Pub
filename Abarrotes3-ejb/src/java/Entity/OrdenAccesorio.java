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
public class OrdenAccesorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrdenAccesorio", nullable = false)
    private Integer idOrdenAccesorio;
    
    @Column(name="precioInicial", length = 50, nullable = false)
    private int precioInicial;

    @Column(name="precioSugerido", length = 50, nullable = false)
    private int precioSugerido;
   
    @ManyToOne(optional = false)
    @JoinColumn(name = "idDatosAccesorio", referencedColumnName = "idDatosAccesorio", nullable = false)
    private DatosAccesorio datosAccesorio;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idOrden", referencedColumnName = "idOrden", nullable = true)
    private Orden orden;
   
     @ManyToOne(optional = false)
    @JoinColumn(name = "idTipoProducto", referencedColumnName = "idTipoProducto", nullable = true)
    private TipoProducto tipoProducto;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor", nullable = false)
    private Proveedor proveedor;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdOrdenAccesorio() != null ? getIdOrdenAccesorio().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenAccesorio)) {
            return false;
        }
        OrdenAccesorio other = (OrdenAccesorio) object;
        if ((this.getIdOrdenAccesorio() == null && other.getIdOrdenAccesorio() != null) || (this.getIdOrdenAccesorio() != null && !this.idOrdenAccesorio.equals(other.idOrdenAccesorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.OrdenAccesorio[ id=" + getIdOrdenAccesorio() + " ]";
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
     * @return the orden
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * @return the idOrdenAccesorio
     */
    public Integer getIdOrdenAccesorio() {
        return idOrdenAccesorio;
    }

    /**
     * @param idOrdenAccesorio the idOrdenAccesorio to set
     */
    public void setIdOrdenAccesorio(Integer idOrdenAccesorio) {
        this.idOrdenAccesorio = idOrdenAccesorio;
    }

    /**
     * @return the precioSugerido
     */
    public int getPrecioSugerido() {
        return precioSugerido;
    }

    /**
     * @param precioSugerido the precioSugerido to set
     */
    public void setPrecioSugerido(int precioSugerido) {
        this.precioSugerido = precioSugerido;
    }

    /**
     * @return the precioInicial
     */
    public int getPrecioInicial() {
        return precioInicial;
    }

    /**
     * @param precioInicial the precioInicial to set
     */
    public void setPrecioInicial(int precioInicial) {
        this.precioInicial = precioInicial;
    }

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public void setOrden(String nombreSinEspacios) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    
}
