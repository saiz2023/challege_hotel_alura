package modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class huespedes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String apellido;
	@Temporal(TemporalType.DATE)
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private String telefeno;
	@ManyToOne
    @JoinColumn(name = "reserva_id")  // Esta columna hace referencia al ID del huésped en la tabla
    private reservas reserva;
	
	
	
	public huespedes() {
	}
	

	public huespedes(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefeno,reservas reserva_id) { 
			this.nombre = nombre;
			this.apellido = apellido;
			this.fechaDeNacimiento = fechaDeNacimiento;
			this.nacionalidad = nacionalidad;
			this.telefeno = telefeno;
			this.reserva =reserva_id;
}


	public long getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefeno() {
		return telefeno;
	}

	public void setTelefeno(String telefeno) {
		this.telefeno = telefeno;
	}


	public reservas getReserva() {
		return reserva;
	}


	public void setReserva(reservas reserva) {
		this.reserva = reserva;
	}


}
