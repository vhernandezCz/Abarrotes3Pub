package Converter;

import Entity.Orden;
import Facade.OrdenFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("ordenConverter")
public class OrdenConverter implements Converter {

    @Inject
    private OrdenFacade ordenFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return ordenFacade.findOrdenById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Orden) {
            return String.valueOf(((Orden) value).getIdOrden());
        }
        return "";
    }
}