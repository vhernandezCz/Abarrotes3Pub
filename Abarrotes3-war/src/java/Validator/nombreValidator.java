/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author lenovo
 */
@FacesValidator(value = "nombreValidator")
public class nombreValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        FacesMessage msj;
        if (value == null) {
            msj = new FacesMessage("Los campos son requeridos.", "");
            msj.setSeverity(FacesMessage.SEVERITY_INFO);

            throw new ValidatorException(msj);
        } else {
            if (value instanceof String) {
                String str = (String) value;
                if (!str.matches("[a-zA-Z]*")) {
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "El dato " + value.toString() + " no es correcto, solo se aceptan caracteres alfabéticos.", ""));

                }
            } else {
                msj = new FacesMessage("El tipo de dato no es el correcto, intente de nuevo.", "");
                msj.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(msj);
            }
        }
    }
}
