package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Categoría")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Audited
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Column(name = "Denominación", length = 30)
	private String denominacion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany(mappedBy = "categorias")
	@Builder.Default
	private List<Articulo> articulos = new ArrayList<Articulo>();

}
