package Entity;

import Entity.DatosAccesorio;
import Entity.Orden;
import Entity.Proveedor;
import Entity.TipoProducto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-20T01:00:54")
@StaticMetamodel(OrdenAccesorio.class)
public class OrdenAccesorio_ { 

    public static volatile SingularAttribute<OrdenAccesorio, DatosAccesorio> datosAccesorio;
    public static volatile SingularAttribute<OrdenAccesorio, TipoProducto> tipoProducto;
    public static volatile SingularAttribute<OrdenAccesorio, Integer> idOrdenAccesorio;
    public static volatile SingularAttribute<OrdenAccesorio, Integer> precioInicial;
    public static volatile SingularAttribute<OrdenAccesorio, Proveedor> proveedor;
    public static volatile SingularAttribute<OrdenAccesorio, Orden> orden;
    public static volatile SingularAttribute<OrdenAccesorio, Integer> precioSugerido;

}