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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lenovo
 */
@Entity
@Table(schema = "ABADB260824")
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuarios", nullable = false)
    private Long idUsuarios;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdUsuarios() != null ? getIdUsuarios().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.getIdUsuarios() == null && other.getIdUsuarios() != null) || (this.getIdUsuarios() != null && !this.idUsuarios.equals(other.idUsuarios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Usuarios[ id=" + getIdUsuarios() + " ]";
    }

    /**
     * @return the idUsuarios
     */
    public Long getIdUsuarios() {
        return idUsuarios;
    }

    /**
     * @param idUsuarios the idUsuarios to set
     */
    public void setIdUsuarios(Long idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password_hash
     */
    public String getPassword_hash() {
        return password_hash;
    }

    /**
     * @param password_hash the password_hash to set
     */
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    
}
