package Converter;

import Entity.TipoUsuario;
import Facade.TipoUsuarioFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

@FacesConverter("tipoUsuarioConverter")
public class TipoUsuarioConverter implements Converter {

    @Inject
    private TipoUsuarioFacade tipoUsuarioFacade;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return tipoUsuarioFacade.findById(Integer.parseInt(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        if (value instanceof TipoUsuario) {
            return String.valueOf(((TipoUsuario) value).getIdTipoUsuario());
        }
        return "";
    }
}