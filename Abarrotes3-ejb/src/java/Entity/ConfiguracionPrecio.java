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
public class ConfiguracionPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConfiguracionPrecio", nullable = false)
    private Integer idConfiguracionPrecio;
    
    @Column(name="comisionesPorcentaje", length = 2, nullable = false)
    private int comisionesPorcentaje;

    @Column(name="precioInicial", nullable = false)
    private int precioInicial;
    
    @Column(name="precioRecalculado", nullable = false)
    private int precioRecalculado;
   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionPrecio != null ? idConfiguracionPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionPrecio)) {
            return false;
        }
        ConfiguracionPrecio other = (ConfiguracionPrecio) object;
        if ((this.idConfiguracionPrecio == null && other.idConfiguracionPrecio != null) || (this.idConfiguracionPrecio != null && !this.idConfiguracionPrecio.equals(other.idConfiguracionPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ConfiguracionPrecio[ id=" + idConfiguracionPrecio + " ]";
    }

    /**
     * @return the idConfiguracionPrecio
     */
    public Integer getIdConfiguracionPrecio() {
        return idConfiguracionPrecio;
    }

    /**
     * @param idConfiguracionPrecio the idConfiguracionPrecio to set
     */
    public void setIdConfiguracionPrecio(Integer idConfiguracionPrecio) {
        this.idConfiguracionPrecio = idConfiguracionPrecio;
    }

    /**
     * @return the comisionesPorcentaje
     */
    public int getComisionesPorcentaje() {
        return comisionesPorcentaje;
    }

    /**
     * @param comisionesPorcentaje the comisionesPorcentaje to set
     */
    public void setComisionesPorcentaje(int comisionesPorcentaje) {
        this.comisionesPorcentaje = comisionesPorcentaje;
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
     * @return the precioRecalculado
     */
    public int getPrecioRecalculado() {
        return precioRecalculado;
    }

    /**
     * @param precioRecalculado the precioRecalculado to set
     */
    public void setPrecioRecalculado(int precioRecalculado) {
        this.precioRecalculado = precioRecalculado;
    }
    
    
}
