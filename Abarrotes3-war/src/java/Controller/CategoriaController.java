/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categoria;
import Facade.CategoriaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@Named(value = "categoriaController")
@SessionScoped
public class CategoriaController implements Serializable {

    @EJB
    private CategoriaFacade categoriaFacade;
    private Categoria categoria = new Categoria();
    private List<Categoria> categoriaList = new ArrayList<>();
    private boolean confirm = false;

    //Constructor
    public CategoriaController() {
    }

    //Metods
    public List<Categoria> findAll() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            categorias = categoriaFacade.findAll();
        } catch (Exception e) {

        }
        return categorias;
    }

    public List<Categoria> findAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            if (!categoriaFacade.findAllCategorias().isEmpty()) {
                categorias.addAll(categoriaFacade.findAllCategorias());
            } 
        } catch (Exception e) {

        }
        return categorias;
    }

    public Categoria findByName(String nombre) {
        try {
            Categoria categoria = new Categoria();
            categoria = categoriaFacade.findByName(nombre);
            return categoria;
        } catch (Exception e) {
            return null;
        }
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (categoria.getNombre() != null
                    && !categoria.getNombre().isEmpty()) {
                Categoria cat = findByName(categoria.getNombre());
                if (cat == null) {
                    categoriaFacade.insert(categoria);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La nueva categoría " + categoria.getNombre() + "  se ha guardado correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
                    clean();
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La categoría " + categoria.getNombre() + " no pudo ser guardada ya que ya se ha registrada con anterioridad.", "");
                    FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre es requerido.", "");
                FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva categoría " + categoria.getNombre() + " no pudo ser guardada " + e, "");
            FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);

        }
        return "AltaCategoria";
    }

    public void delete(Categoria c) {
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

    public String mainClean(String url) {
        categoria = new Categoria();
        setConfirm(false);
        return url;
    }

    public String clean() {
        categoria = new Categoria();
        return "";
    }

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

}
