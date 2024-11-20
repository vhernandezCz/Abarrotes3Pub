package Entity;

import Entity.TipoUsuario;
import Entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-20T01:00:54")
@StaticMetamodel(DatosUsuario.class)
public class DatosUsuario_ { 

    public static volatile SingularAttribute<DatosUsuario, Boolean> esActivo;
    public static volatile SingularAttribute<DatosUsuario, Usuario> usuario;
    public static volatile SingularAttribute<DatosUsuario, TipoUsuario> tipoUsuario;
    public static volatile SingularAttribute<DatosUsuario, Long> idDatosUsuario;

}