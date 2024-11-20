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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria", nullable = false)
    private Integer idCategoria;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<DatosAccesorio> datosAccesorioList;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL)
    private List<Proveedor> proveedorList;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdCategoria() != null ? getIdCategoria().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.getIdCategoria() == null && other.getIdCategoria() != null) || (this.getIdCategoria() != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Categoria[ id=" + getIdCategoria() + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<DatosAccesorio> getDatosAccesorioList() {
        return datosAccesorioList;
    }

    public void setDatosAccesorioList(List<DatosAccesorio> datosAccesorioList) {
        this.datosAccesorioList = datosAccesorioList;
    }

    //Constructor
    public Categoria() {
    }

    /**
     * @return the proveedorList
     */
    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    /**
     * @param proveedorList the proveedorList to set
     */
    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }
}
