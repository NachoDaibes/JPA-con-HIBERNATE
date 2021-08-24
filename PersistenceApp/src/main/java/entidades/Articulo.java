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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Artículo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Audited
public class Articulo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "Cantidad", length = 10)
	private int cantidad;
	
	@Column(name = "Denominación", length = 30)
	private String denominacion;
		
	@Column(name = "Precio")
	private int precio;
	
	@OneToMany(mappedBy = "articulo", cascade = CascadeType.PERSIST)
	@Builder.Default
	private List<DetalleFactura> detalle = new ArrayList<DetalleFactura>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
				name = "Articulo_Categoria", 
				joinColumns = @JoinColumn(name = "Articulo_Id"),
				inverseJoinColumns = @JoinColumn(name = "Categoria_Id"))
	@Builder.Default
	private List<Categoria> categorias = new ArrayList<Categoria>();
}
