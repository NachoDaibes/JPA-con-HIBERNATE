package main;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.envers.query.AuditQueryCreator;

import Audit.Revision;
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
		
		em.getTransaction().begin();
		
		//createFactura1Completa(em);
		
		createClienteDomicilio(em);
		//removerDomicilio(em);
		//removerCliente1(em);
		
		//queryRevisionInfo(em, Cliente.class);
		em.getTransaction().commit();
	}
	
	public static void queryRevisionInfo(EntityManager em, Class clazz){
		AuditReader auditReader = AuditReaderFactory.get(em);
		AuditQueryCreator queryCreator = auditReader.createQuery();
		AuditQuery query = queryCreator.forRevisionsOfEntity(clazz, true);
		List<Revision> list = query.getResultList();
		for(Revision revision : list){
			System.out.println(revision);
		}
	}
	
	public static void createArticulo1(EntityManager em) {
		Articulo art1 = Articulo.builder().
				cantidad(200).
				denominacion("Yogurt Ser Sabor Frutilla").
				precio(20).
				build();
		em.persist(art1);
	}
	
	public static void createArticulo2(EntityManager em){
		Articulo art2 = Articulo.builder()
				.cantidad(300)
				.denominacion("Detergente Magistral")
				.precio(80)
				.build();
		em.persist(art2);
	}
	
	public static void createCategoriaArticulo(EntityManager em){
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
//		lacteos.getArticulos().add(art1);
//		perecederos.getArticulos().add(art1);
		
		art2.getCategorias().add(limpieza);
//		limpieza.getArticulos().add(art2);
		
		
	}
	
	public static void createFactura1(EntityManager em){
		Factura factura1 = new Factura();
		
		factura1.setNumero(12);
		factura1.setFecha("10/08/2021");
		
		em.persist(factura1);
	}
	
	public static void createClienteDomicilio(EntityManager em){
		Domicilio dom = Domicilio.builder().nombreCalle("LosCuadros").numero(449).build();
		Cliente cliente = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(155883883).build();
		cliente.setDomicilio(dom);
//		dom.setCliente(cliente);
		em.persist(cliente);
	}
	
	public static void createFactura1Completa(EntityManager em){
		Domicilio dom = Domicilio.builder().nombreCalle("LosCuadros").numero(449).build();
		Cliente cliente = Cliente.builder().nombre("Pablo").apellido("Muñoz").dni(155883883).build();
		cliente.setDomicilio(dom);
//		dom.setCliente(cliente);
		
		Factura factura1 = new Factura();
		factura1.setNumero(0);
		factura1.setFecha("20/09/2021");
		factura1.setCliente(cliente);
		
		Articulo art1 = Articulo.builder().
				cantidad(200).
				denominacion("Yogurt Ser Sabor Frutilla").
				precio(20).
				build();
		
		Articulo art2 = Articulo.builder().
				cantidad(400).
				denominacion("Schampoo elvive").
				precio(40).
				build();
		
		DetalleFactura detalle1 = DetalleFactura.builder()
				.articulo(art1)
				.cantidad(2)
				.subtotal(20)
				.build();
		
		DetalleFactura detalle2 = DetalleFactura.builder()
				.articulo(art1)
				.cantidad(5)
				.subtotal(50)
				.build();
		
		art1.getDetalle().add(detalle1);
		art2.getDetalle().add(detalle2);
		factura1.getDetalles().add(detalle1);
		factura1.getDetalles().add(detalle2);
//		detalle1.setFactura(factura1);
		
		em.persist(factura1);
	}
	
	public static void removerFactura1(EntityManager em){
		Factura factura1 = em.find(Factura.class, 1L);
		
		em.remove(factura1);
	}
	
	public static void removerDomicilio(EntityManager em){
		Domicilio dom = em.find(Domicilio.class, 1L);
		
		em.remove(dom);
	}
	
	public static void removerCliente1(EntityManager em){
		Cliente cliente1 = em.find(Cliente.class, 1L);
		
		em.remove(cliente1);
	}
}
