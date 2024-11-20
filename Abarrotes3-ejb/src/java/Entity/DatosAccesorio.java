/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class DatosAccesorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDatosAccesorio", nullable = false)
    private Integer idDatosAccesorio;

    @Column(name = "sku", length = 50, nullable = false)
    private String sku;
    
    @Column(name = "descripcion", nullable = true)
    private String descripcion;

    @Column(name = "imagen", nullable = true)
    private byte[] imagen;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = true)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idClasificacion", referencedColumnName = "idClasificacion", nullable = true)
    private Clasificacion clasificacion;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "idProveedor", referencedColumnName = "idProveedor", nullable = false)
    private Proveedor proveedor;
        
    @OneToMany(mappedBy = "datosAccesorio", cascade = CascadeType.ALL)
    private List<Venta> venta;

    @OneToMany(mappedBy = "datosAccesorio", cascade = CascadeType.ALL)
    private List<OrdenAccesorio> ordenAccesorioList;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdDatosAccesorio() != null ? getIdDatosAccesorio().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DatosAccesorio)) {
            return false;
        }
        DatosAccesorio other = (DatosAccesorio) object;
        if ((this.getIdDatosAccesorio() == null && other.getIdDatosAccesorio() != null) || (this.getIdDatosAccesorio() != null && !this.idDatosAccesorio.equals(other.idDatosAccesorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DatosAccesorio[ id=" + getIdDatosAccesorio() + " ]";
    }

    /**
     * @return the idDatosAccesorio
     */
    public Integer getIdDatosAccesorio() {
        return idDatosAccesorio;
    }

    /**
     * @param idDatosAccesorio the idDatosAccesorio to set
     */
    public void setIdDatosAccesorio(Integer idDatosAccesorio) {
        this.idDatosAccesorio = idDatosAccesorio;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the imagen
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the venta
     */
    public List<Venta> getVenta() {
        return venta;
    }

    /**
     * @param venta the venta to set
     */
    public void setVenta(List<Venta> venta) {
        this.venta = venta;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the ordenAccesorioList
     */
    public List<OrdenAccesorio> getOrdenAccesorioList() {
        return ordenAccesorioList;
    }

    /**
     * @param ordenAccesorioList the ordenAccesorioList to set
     */
    public void setOrdenAccesorioList(List<OrdenAccesorio> ordenAccesorioList) {
        this.ordenAccesorioList = ordenAccesorioList;
    }

    /**
     * @return the clasificacion
     */
    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
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

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku the sku to set
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

}
