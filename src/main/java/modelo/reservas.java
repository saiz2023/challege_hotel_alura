package modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import Dao.HuespedDAO;


@Entity
public class reservas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.DATE)
	private Date fechaDeEntrada;
	@Temporal(TemporalType.DATE)
	private Date fechaDeSalida;
	private double valor;
	private String formaDePago;
	
	public reservas() {
		
	}
	public reservas(Date fechaDeEntrada, Date fechaDeSalida, double valor, String formaDePago) {
		this.fechaDeEntrada = fechaDeEntrada;
		this.fechaDeSalida = fechaDeSalida;
		this.valor = valor;
		this.formaDePago = formaDePago;
	}
	


	public long getId() {
		return id;
	}
	public Date getFechaDeEntrada() {
		return fechaDeEntrada;
	}
	public void setFechaDeEntrada(Date fechaDeEntrada) {
		this.fechaDeEntrada = fechaDeEntrada;
	}
	public Date getFechaDeSalida() {
		return fechaDeSalida;
	}
	public void setFechaDeSalida(Date fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getFormaDePago() {
		return formaDePago;
	}
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}
	public List<huespedes> getHuespedes(long id, EntityManager em) {
		HuespedDAO huepedDao=new HuespedDAO(em);
		return huepedDao.obtenerHuespedesPorIdReserva(id);
	}
	
	
	
	

}
