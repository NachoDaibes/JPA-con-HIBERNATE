package main;
//Este es el cambio
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Articulo;
import entidades.Categoria;
import entidades.Cliente;
import entidades.DetalleFactura;
import entidades.Domicilio;
import entidades.Factura;

public class PersistenceApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceAppPU");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Factura factura1 = new Factura();
			
			factura1.setNumero(12);
			factura1.setFecha("10/08/2021");
			
			Domicilio dom = Domicilio.builder().nombreCalle("LosCuadros").numero(449).build();
			Cliente cliente = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(155883883).build();
			cliente.setDomicilio(dom);
			dom.setCliente(cliente);
			
			factura1.setCliente(cliente);
			
			Categoria perecederos = Categoria.builder().denominacion("Perecederos").build();
			Categoria lacteos = Categoria.builder().denominacion("Lacteos").build();
			Categoria limpieza = Categoria.builder().denominacion("Limpieza").build();
			
			Articulo art1 = Articulo.builder().
					cantidad(200).
					denominacion("Yogurt Ser Sabor Frutilla").
					precio(20).
					build();
			
			Articulo art2 = Articulo.builder()
					.cantidad(300)
					.denominacion("Detergente Magistral")
					.precio(80)
					.build();
			
			art1.getCategorias().add(perecederos);
			art1.getCategorias().add(lacteos);
			lacteos.getArticulos().add(art1);
			perecederos.getArticulos().add(art1);
			
			art2.getCategorias().add(limpieza);
			limpieza.getArticulos().add(art2);
			
			DetalleFactura detalle1 = DetalleFactura.builder()
					.articulo(art1)
					.cantidad(2)
					.subtotal(20)
					.build();
			
			art1.getDetalle().add(detalle1);
			factura1.getDetalles().add(detalle1);
			detalle1.setFactura(factura1);
			
			DetalleFactura detalle2 = DetalleFactura.builder()
					.articulo(art2)
					.cantidad(1)
					.subtotal(80)
					.build();
			
			art2.getDetalle().add(detalle2);
			factura1.getDetalles().add(detalle2);
			detalle2.setFactura(factura1);
			
			factura1.setTotal(120);
			/*Factura factura1 = em.find(Factura.class, 5L);*/
			
			
			em.persist(factura1);
			
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		
		em.close();
		emf.close();
	} 
}
