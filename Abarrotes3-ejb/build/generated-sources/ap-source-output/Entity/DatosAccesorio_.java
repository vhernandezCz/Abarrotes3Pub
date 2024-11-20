package Entity;

import Entity.Categoria;
import Entity.Clasificacion;
import Entity.OrdenAccesorio;
import Entity.Proveedor;
import Entity.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-20T01:00:54")
@StaticMetamodel(DatosAccesorio.class)
public class DatosAccesorio_ { 

    public static volatile SingularAttribute<DatosAccesorio, String> descripcion;
    public static volatile ListAttribute<DatosAccesorio, Venta> venta;
    public static volatile SingularAttribute<DatosAccesorio, Integer> idDatosAccesorio;
    public static volatile SingularAttribute<DatosAccesorio, Categoria> categoria;
    public static volatile ListAttribute<DatosAccesorio, OrdenAccesorio> ordenAccesorioList;
    public static volatile SingularAttribute<DatosAccesorio, byte[]> imagen;
    public static volatile SingularAttribute<DatosAccesorio, Proveedor> proveedor;
    public static volatile SingularAttribute<DatosAccesorio, Clasificacion> clasificacion;
    public static volatile SingularAttribute<DatosAccesorio, String> sku;
    public static volatile SingularAttribute<DatosAccesorio, String> nombre;

}