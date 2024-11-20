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
public class ConfiguracionDeducciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConfiguracionDeduccion", nullable = false)
    private Integer idConfiguracionDeduccion;
    
    @Column(name="comisionesPorcentaje", length = 2, nullable = false)
    private int comisionesPorcentaje;

    @Column(name="empaquetado", nullable = false)
    private int empaquetado;

    @Column(name="entrega", nullable = false)
    private int entrega;
    
    @Column(name="importacion", nullable = false)
    private int importacion;

    @Column(name="porcentajeMargenBeneficio", length = 2, nullable = false)
    private int porcentajeMargenBeneficio;

    @Column(name="esActiva", nullable = false)
    private boolean esActiva;

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionDeduccion != null ? idConfiguracionDeduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionDeducciones)) {
            return false;
        }
        ConfiguracionDeducciones other = (ConfiguracionDeducciones) object;
        if ((this.idConfiguracionDeduccion == null && other.idConfiguracionDeduccion != null) || (this.idConfiguracionDeduccion != null && !this.idConfiguracionDeduccion.equals(other.idConfiguracionDeduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ConfiguracionDeducciones[ id=" + idConfiguracionDeduccion + " ]";
    }

    /**
     * @return the idConfiguracionDeduccion
     */
    public Integer getIdConfiguracionDeduccion() {
        return idConfiguracionDeduccion;
    }

    /**
     * @param idConfiguracionDeduccion the idConfiguracionDeduccion to set
     */
    public void setIdConfiguracionDeduccion(Integer idConfiguracionDeduccion) {
        this.idConfiguracionDeduccion = idConfiguracionDeduccion;
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
     * @return the empaquetado
     */
    public int getEmpaquetado() {
        return empaquetado;
    }

    /**
     * @param empaquetado the empaquetado to set
     */
    public void setEmpaquetado(int empaquetado) {
        this.empaquetado = empaquetado;
    }

    /**
     * @return the entrega
     */
    public int getEntrega() {
        return entrega;
    }

    /**
     * @param entrega the entrega to set
     */
    public void setEntrega(int entrega) {
        this.entrega = entrega;
    }

    /**
     * @return the importacion
     */
    public int getImportacion() {
        return importacion;
    }

    /**
     * @param importacion the importacion to set
     */
    public void setImportacion(int importacion) {
        this.importacion = importacion;
    }

    /**
     * @return the porcentajeMargenBeneficio
     */
    public int getPorcentajeMargenBeneficio() {
        return porcentajeMargenBeneficio;
    }

    /**
     * @param porcentajeMargenBeneficio the porcentajeMargenBeneficio to set
     */
    public void setPorcentajeMargenBeneficio(int porcentajeMargenBeneficio) {
        this.porcentajeMargenBeneficio = porcentajeMargenBeneficio;
    }

    /**
     * @return the esActiva
     */
    public boolean isEsActiva() {
        return esActiva;
    }

    /**
     * @param esActiva the esActiva to set
     */
    public void setEsActiva(boolean esActiva) {
        this.esActiva = esActiva;
    }
    
}
