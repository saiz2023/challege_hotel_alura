package Dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import modelo.huespedes;
import modelo.reservas;

public class ReservaDAO {
	private EntityManager em;

	public ReservaDAO(EntityManager em) {
		this.em = em;
	}

	
	public void guardar(reservas reserva) {
		this.em.persist(reserva);
	}
	
	
	public void actualizar(reservas reserva, reservas reservaUpdate) {
		 em.getTransaction().begin();

		  
		    reservas reservaExistente = em.find(reservas.class, reserva.getId());

		    if (reservaExistente != null) {
		        reservaExistente.setFechaDeEntrada(reservaUpdate.getFechaDeEntrada());
		        reservaExistente.setFechaDeSalida(reservaUpdate.getFechaDeSalida());
		        reservaExistente.setValor(reservaUpdate.getValor());
		        reservaExistente.setFormaDePago(reservaUpdate.getFormaDePago());

		        // Realizar el merge para actualizar la entidad en la base de datos
		        em.merge(reservaExistente);
		    }

		    em.getTransaction().commit();
		    em.close();
	}
	
	public void remover(reservas reserva) {
		reserva=this.em.merge(reserva);
		this.em.remove(reserva);
	}


	public void sethuesped(huespedes huesped) {
	
		    em.merge(huesped); // Actualizar la entidad Reserva
		 
		
	}
	
	public List<reservas> buscarPorParametro(Object parametro) {
	    String jpql;
	    TypedQuery<reservas> query;

	    if (parametro instanceof Long) {
	        jpql = "SELECT r FROM reservas r WHERE r.id = :parametroId";
	        query = em.createQuery(jpql, reservas.class);
	        query.setParameter("parametroId", (Long) parametro);
	    } else if (parametro instanceof Date) {
	        jpql = "SELECT r FROM reservas r WHERE r.fechaDeEntrada = :parametroDate OR r.fechaDeSalida = :parametroDate";
	        query = em.createQuery(jpql, reservas.class);
	        query.setParameter("parametroDate", (Date) parametro);
	    } else if (parametro instanceof Double) {
	        jpql = "SELECT r FROM reservas r WHERE r.valor = :parametroDouble";
	        query = em.createQuery(jpql, reservas.class);
	        query.setParameter("parametroDouble", (Double) parametro);
	    } else if (parametro instanceof String) {
	        jpql = "SELECT r FROM reservas r WHERE r.formaDePago = :parametroString";
	        query = em.createQuery(jpql, reservas.class);
	        query.setParameter("parametroString", (String) parametro);
	    } else {
	        throw new IllegalArgumentException("Tipo de parámetro no admitido");
	    }

	    return query.getResultList();
	}
	public List<reservas> consultagenera(String text) {
		String jqpl= "SELECT P FROM reservas AS P";
		return em.createQuery(jqpl,reservas.class).getResultList();
	}


	
		
	
	public reservas consultarPorId(long id) {
		 String jpql = "SELECT r FROM reservas r WHERE r.id = :id";
		    TypedQuery<reservas> query = em.createQuery(jpql, reservas.class);
		    query.setParameter("id", id);

		    try {
		        return query.getSingleResult();
		    } catch (NoResultException ex) {
		        JOptionPane.showMessageDialog(null, "No se encontro la reserva");
		        return null;
		    }
	}


	public void eliminarReservaYDesvincularHuesped(reservas reserva) {
	    em.getTransaction().begin();
	    reserva = em.find(reservas.class, reserva.getId());
	    List<huespedes> huespedesAsociados = reserva.getHuespedes(reserva.getId(),em);
	    
	    for (huespedes huesped : huespedesAsociados) {
	        huesped.setReserva(null);
	    }
	    
	    em.remove(reserva);
	    
	    em.getTransaction().commit();
	    em.close();
	}
}
