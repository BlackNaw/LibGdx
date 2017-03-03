package Filtros;

public class ProbandoMasking {
public static void main(String[] args) {
	final short PHYSICS_ENTITY = 0x1; // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
	//La 0x es hexadecimal
	final short OTROS=0x4;//0100
	final short FATAL=0xF;//1111 o con -1 tambien funciona
	//Cuantos más digitos tras la x mas bits (cada uno 4 bits)
	
	System.out.println(PHYSICS_ENTITY);
	System.out.println(WORLD_ENTITY);
	System.out.println(OTROS);
	System.out.println(FATAL);
	final short grupoA=WORLD_ENTITY|OTROS;
/*	Hay dos elementos categorias y mask. definimos una categoria por tipo
 * y las mascaras para filtrar colisiones entre objetos de ese tipo
 * OSea yo define la categoria de mi objeto con filter.categoryBits
 * y con quien me choco mediante maskBits
 * Como digo que me choco con más de uno?
 * Haciendo operaciones de bits con las máscaras de los otros
 * Si me choco con Worl_entity y fatal digo que la maskara=worl_entity|fatal
 * los bits a uno seran los de ambos y cuando haga el filtro seran disparados
 * Para decir que choca con todos usa -1
 */
	System.out.println(PHYSICS_ENTITY&OTROS);
	System.out.println(PHYSICS_ENTITY&WORLD_ENTITY);
	System.out.println(PHYSICS_ENTITY&FATAL);
	System.out.println(PHYSICS_ENTITY&grupoA);
}
}
