package Converter;

import Entity.Proveedor;
import Facade.ProveedorFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("proveedorConverter")
public class ProveedorConverter implements Converter {

    @Inject
    private ProveedorFacade proveedorFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return proveedorFacade.findById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Proveedor) {
            return String.valueOf(((Proveedor) value).getIdProveedor());
        }
        return "";
    }
}