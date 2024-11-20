package Converter;

import Entity.Categoria;
import Facade.DatosAccesorioFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("categoriaConverter")
public class CategoriaConverter implements Converter {

    @Inject
    private DatosAccesorioFacade datosAccesorioFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return datosAccesorioFacade.findCategoriaById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Categoria) {
            return String.valueOf(((Categoria) value).getIdCategoria());
        }
        return "";
    }
}