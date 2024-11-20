package Entity;

import java.io.Serializable;
import java.math.BigInteger;
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
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private Integer idProveedor;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "lada", length = 2)
    private Integer lada;

    @Column(name = "telefono", length = 20)
    private BigInteger telefono;

    @Column(name = "correo", length = 30)
    private String correo;

    @Column(name = "direccion", length = 100)
    private String direccion;

    //Proveedor fijo o Abastos
    @ManyToOne(optional = false)
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria", nullable = false)
    private Categoria tipo;
    
    @Column(name = "diasVisita", length = 100)
    private String diasVisita;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdProveedor() != null ? getIdProveedor().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.getIdProveedor() == null && other.getIdProveedor() != null) || (this.getIdProveedor() != null && !this.idProveedor.equals(other.getIdProveedor()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Proveedor[ id=" + getIdProveedor() + " ]";
    }

    /**
     * @return the idProveedor
     */
    public Integer getIdProveedor() {
        return idProveedor;
    }

    /**
     * @param idProveedor the idProveedor to set
     */
    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
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
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the lada
     */
    public Integer getLada() {
        return lada;
    }

    /**
     * @param lada the lada to set
     */
    public void setLada(Integer lada) {
        this.lada = lada;
    }

    /**
     * @return the diasVisita
     */
    public String getDiasVisita() {
        return diasVisita;
    }

    /**
     * @param diasVisita the diasVisita to set
     */
    public void setDiasVisita(String diasVisita) {
        this.diasVisita = diasVisita;
    }

    /**
     * @return the telefono
     */
    public BigInteger getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(BigInteger telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the tipo
     */
    public Categoria getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(Categoria tipo) {
        this.tipo = tipo;
    }

    
}
