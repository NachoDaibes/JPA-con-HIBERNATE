package entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "Detalle_Factura")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Audited
public class DetalleFactura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Cantidad", length = 10)
	private int cantidad;
	
	@Column(name = "Subtotal", length = 10)
	private int subtotal;
	
	@ManyToOne(cascade = CascadeType.PERSIST )
	@JoinColumn(name  = "FK_factura")
	private Factura factura;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_articulo")
	private Articulo articulo;

}
