package Converter;

import Entity.Clasificacion;
import Facade.DatosAccesorioFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("clasificacionConverter")
public class ClasificacionConverter implements Converter {

    @Inject
    private DatosAccesorioFacade datosAccesorioFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return datosAccesorioFacade.findClasificacionById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof Clasificacion) {
            return String.valueOf(((Clasificacion) value).getIdClasificacion());
        }
        return "";
    }
}