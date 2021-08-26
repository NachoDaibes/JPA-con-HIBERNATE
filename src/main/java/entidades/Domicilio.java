package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Domicilio")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Audited
public class Domicilio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Calle", length = 30)
	private String nombreCalle;
	
	@Column(name = "Número", length = 10)
	private int numero;
	
	@OneToOne(mappedBy= "domicilio")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Cliente cliente;

}
