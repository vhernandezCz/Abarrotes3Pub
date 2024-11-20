package Converter;

import Entity.TipoProducto;
import Facade.TipoProductoFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("tipoProductoConverter")
public class TipoProductoConverter implements Converter {

    @Inject
    private TipoProductoFacade tipoProductoFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return tipoProductoFacade.findById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof TipoProducto) {
            return String.valueOf(((TipoProducto) value).getIdTipoProducto());
        }
        return "";
    }
}