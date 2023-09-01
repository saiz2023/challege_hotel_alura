package Dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import modelo.huespedes;

public class HuespedDAO {
	private EntityManager em;

	public HuespedDAO(EntityManager em) {
		this.em = em;
	}

	
	public void guardar(huespedes huesped) {
		this.em.persist(huesped);
	}
	
	
	public void actualizar(huespedes huesped) {
		this.em.merge(huesped);
	}
	
	public void remover(huespedes huesped) {
		huesped=this.em.merge(huesped);
		this.em.remove(huesped);
	}
	
	
	public List<huespedes> buscarHuespedPorParametro(Object parametro) {
	    String jpql;
	    TypedQuery<huespedes> query;
	    

	    if (parametro instanceof Long) { // Agregamos la búsqueda por id
	        jpql = "SELECT h FROM huespedes h WHERE h.id = :parametro";
	        query = em.createQuery(jpql, huespedes.class);
	        query.setParameter("parametro", (Long) parametro);
	    } else if (parametro instanceof String) {
	        jpql = "SELECT h FROM huespedes h WHERE h.nombre = :parametro OR h.apellido = :parametro OR h.nacionalidad = :parametro OR h.telefeno = :parametro";
	        query = em.createQuery(jpql, huespedes.class);
	        query.setParameter("parametro", (String) parametro);
	    } else if (parametro instanceof Date) {
	        jpql = "SELECT h FROM huespedes h WHERE h.fechaDeNacimiento = :parametro";
	        query = em.createQuery(jpql, huespedes.class);
	        query.setParameter("parametro", (Date) parametro);
	    } else {
	        throw new IllegalArgumentException("Tipo de parámetro no admitido");
	    }

	    return query.getResultList();
	}
	public void eliminar(huespedes huesped) {
	    em.getTransaction().begin();
	    huesped = em.find(huespedes.class, huesped.getId());
	    em.remove(huesped);
	    em.getTransaction().commit();
	    em.close();
	}

	public List<huespedes> consultagenera(String text) {
		String jqpl= "SELECT P FROM huespedes AS P";
		return em.createQuery(jqpl,huespedes.class).getResultList();
	}
	public huespedes consultarPorId(long id) {
		 String jpql = "SELECT r FROM huespedes r WHERE r.id = :id";
		    TypedQuery<huespedes> query = em.createQuery(jpql, huespedes.class);
		    query.setParameter("id", id);

		    try {
		        return query.getSingleResult();
		    } catch (NoResultException ex) {
		        JOptionPane.showMessageDialog(null, ex);
		        return null;
		    }
	}
	
	  public List<huespedes> obtenerHuespedesPorIdReserva(long idReserva) {
	        TypedQuery<huespedes> query = em.createQuery(
	            "SELECT h FROM huespedes h WHERE h.reserva.id = :idReserva",
	            huespedes.class
	        );
	        query.setParameter("idReserva", idReserva);
	        return query.getResultList();
	    }


	  public void actualizarHuesped(huespedes huesped, huespedes huespedUpdate) {
		    em.getTransaction().begin();

		    // Cargar la entidad original desde la base de datos
		    huespedes huespedExistente = em.find(huespedes.class, huesped.getId());

		    if (huespedExistente != null) {
		   	    	
		         huespedExistente.setNombre(huespedUpdate.getNombre());
		         huespedExistente.setApellido(huespedUpdate.getApellido());
		         huespedExistente.setFechaDeNacimiento(huespedUpdate.getFechaDeNacimiento());
		         huespedExistente.setNacionalidad(huespedUpdate.getNacionalidad());
		         huespedExistente.setTelefeno(huespedUpdate.getTelefeno());
		         huespedExistente.setReserva(huespedUpdate.getReserva());

		        // Realizar el merge para actualizar la entidad en la base de datos
		        em.merge(huespedExistente);
		    }

		    em.getTransaction().commit();
		    em.close();
		}
}
