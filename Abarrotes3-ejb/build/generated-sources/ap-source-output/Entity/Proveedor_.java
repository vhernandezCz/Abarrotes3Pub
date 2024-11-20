package Entity;

import Entity.Categoria;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-11-20T01:00:54")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, Integer> lada;
    public static volatile SingularAttribute<Proveedor, Categoria> tipo;
    public static volatile SingularAttribute<Proveedor, String> diasVisita;
    public static volatile SingularAttribute<Proveedor, Integer> idProveedor;
    public static volatile SingularAttribute<Proveedor, String> correo;
    public static volatile SingularAttribute<Proveedor, String> direccion;
    public static volatile SingularAttribute<Proveedor, BigInteger> telefono;
    public static volatile SingularAttribute<Proveedor, String> nombre;

}