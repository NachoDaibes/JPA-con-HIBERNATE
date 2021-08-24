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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "Cliente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Audited
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Nombre", length = 30)
	private String nombre;
	@Column(name = "Apellido", length = 30)
	private String apellido;
	
	@Column(name = "DNI", unique = true, length = 20)
	private int dni;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_Domicilio")
	private Domicilio domicilio;
	
	@OneToMany(mappedBy = "cliente")
	@Builder.Default
	private List<Factura> facturas = new ArrayList<Factura>();

}
