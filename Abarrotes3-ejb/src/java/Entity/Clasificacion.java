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
public class Clasificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClasificacion", nullable = false)
    private Integer idClasificacion;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private List<DatosAccesorio> datosAccesorioList;
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdClasificacion() != null ? getIdClasificacion().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacion)) {
            return false;
        }
        Clasificacion other = (Clasificacion) object;
        if ((this.getIdClasificacion() == null && other.getIdClasificacion() != null) || (this.getIdClasificacion() != null && !this.idClasificacion.equals(other.idClasificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Clasificación[ id=" + getIdClasificacion() + " ]";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DatosAccesorio> getDatosAccesorioList() {
        return datosAccesorioList;
    }

    public void setDatosAccesorioList(List<DatosAccesorio> datosAccesorioList) {
        this.datosAccesorioList = datosAccesorioList;
    }
    
     public Integer getIdClasificacion() {
        return idClasificacion;
    }

    public void setIdClasificacion(Integer idClasificacion) {
        this.idClasificacion = idClasificacion;
    }

    
    //Constructor
    public Clasificacion() {
    }

}
