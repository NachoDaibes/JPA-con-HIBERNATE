package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Factura")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class Factura implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Fecha", length = 10)
	private String fecha;
	
	@Column(name = "Número", length = 20)
	private int numero;
	
	@Column(name = "Total", length = 10)
	private int total;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "FK_Cliente")	
	private Cliente cliente;
	
	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();
	
	@OneToMany(mappedBy="factura",cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
	private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();

}
