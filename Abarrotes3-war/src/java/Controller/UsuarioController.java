/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categoria;
import Entity.TipoUsuario;
import Entity.Usuario;
import Entity.Usuarios;
import Facade.CategoriaFacade;
import Facade.TipoUsuarioFacade;
import Facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author lenovo
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario = new Usuario();

    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;

    @EJB

    private List<Usuarios> usuarioList = new ArrayList<>();
    private boolean confirm = false;
    private String password = "";
    private TipoUsuario tipoUsuario;
    private String confirmacionPass = "";

    //private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    //Constructor
    public UsuarioController() {
    }

    //Metods
    public List<TipoUsuario> findAllTipoUsuario() {
        FacesMessage msj;
        try {
            List<TipoUsuario> tiposUsuarios = new ArrayList<>();
            tiposUsuarios = this.tipoUsuarioFacade.findAll();

            if (tiposUsuarios.size() > 0) {
                return tiposUsuarios;
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha registrado ningun tipo de usuario en la base de datos, favor de agregarlo.", "");
                FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);
                return null;
            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error, consulte al administrador.", "");
            FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);

            return null;
        }
    }

    public String guardarUsuario() {
        FacesMessage msj;
        try {
            if (!this.usuario.getUsername().equals("")
                    && !this.password.equals("")
                    && this.getTipoUsuario() != null) {
                //String encodedPassword = bCryptPasswordEncoder.encode(password);
                //boolean isMatch = bCryptPasswordEncoder.matches(password, encodedPassword);

                /*if (isMatch == true) {
                  //  this.usuario.setPasswordHash(encodedPassword);
                    usuarioFacade.insert(usuario);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha agregado el nuevo usuario " + usuario.getUsername() + "  con exito.", "");
                    FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);
                    clean();
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo usuario " + usuario.getUsername() + " no pudo ser guardado, verifique las contraseñas.", "");
                    FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);
                }*/
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre es requerido.", "");
                FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El usuario " + usuario.getUsername() + " no pudo ser guardado " + e, "");
            FacesContext.getCurrentInstance().addMessage("AltaUsuario", msj);

        }
        return "AltaCategoria";
    }

    /*  public void delete(Categoria c) {
        FacesMessage msj;
        try {
            // categoria = c;
            //categoriaFacade.delete(categoria);
            categoriaFacade.delete(c);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + c.getNombre() + " fue eliminado con exito.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaCategoria", msj);
            mainClean("ConsultaCategoria");
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + c.getNombre() + " no pudo eliminarse.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaCategoria", msj);

        }

    }

    public String prepareDelete() {
        setConfirm(true);
        return "ConsultaCategoria";
    }

    public String prepareEdit(Categoria c) {
        categoria = c;
        return "EditarCategoria";
    }

    public String update() {
        FacesMessage msj;
        try {
            Categoria cat = findByName(categoria.getNombre());

            if (cat == null) {
                categoriaFacade.update(categoria);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La categoría " + categoria.getNombre() + " se ha actualizado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("EditarCategoria", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La categoría " + categoria.getNombre() + " no pudo ser actualizada debido a que ya se ha registrado el nombre anteriormente", "");
                FacesContext.getCurrentInstance().addMessage("EditarCategoria", msj);
            }

        } catch (Exception e) {

        }
        return "";
    }
     */
    public String mainClean(String url) {
        usuario = new Usuario();
        return url;
    }

    public String clean() {
        usuario = new Usuario();
        return "";
    }

    /*
    //Setter y Getter
    public CategoriaFacade getCategoriaFacade() {
        return categoriaFacade;
    }

    public void setCategoriaFacade(CategoriaFacade categoriaFacade) {
        this.categoriaFacade = categoriaFacade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }
     */
    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the tipoUsuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipoUsuario the tipoUsuario to set
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * @return the confirmacionPass
     */
    public String getConfirmacionPass() {
        return confirmacionPass;
    }

    /**
     * @param confirmacionPass the confirmacionPass to set
     */
    public void setConfirmacionPass(String confirmacionPass) {
        this.confirmacionPass = confirmacionPass;
    }

}
