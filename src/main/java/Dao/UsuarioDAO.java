package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import modelo.usuarios;


public class UsuarioDAO {

	private EntityManager em;

	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}

	
	public void guardar(usuarios usuario) {
		this.em.persist(usuario);
	}
	
	public List<usuarios> consultagenera(String text) {
		String jqpl= "SELECT P FROM usuarios AS P";
		return em.createQuery(jqpl,usuarios.class).getResultList();
	}



	public List<usuarios> buscarPorParametro(Object parametro) {
	    String jpql;
	    TypedQuery<usuarios> query;

	    if (parametro instanceof Long) {
	        jpql = "SELECT u FROM usuarios u WHERE u.id = :parametroId";
	        query = em.createQuery(jpql, usuarios.class);
	        query.setParameter("parametroId", (Long) parametro);
	    } if (parametro instanceof String) {
	        jpql = "SELECT u FROM usuarios u WHERE u.usuario = :parametroString OR u.pass = :parametroString";
	        query = em.createQuery(jpql, usuarios.class);
	        query.setParameter("parametroString", (String) parametro);
	    } else {
	        throw new IllegalArgumentException("Tipo de parámetro no admitido");
	    }

	    return query.getResultList();
	}
	
	public void remover(usuarios usuario) {
		  em.getTransaction().begin();
		  em.remove(usuario);
		  em.getTransaction().commit();
		  em.close();
	}


	public void actualizar(usuarios usuario, usuarios usuarioUdate) {
		em.getTransaction().begin();

		  
	    usuarios usuarioExistente = em.find(usuarios.class, usuario.getUser());

	    if (usuarioExistente != null) {
	        
	        usuarioExistente.setPass(usuarioUdate.getPass());
	      

	        // Realizar el merge para actualizar la entidad en la base de datos
	        em.merge(usuarioExistente);
	    }

	    em.getTransaction().commit();
	    em.close();
		
	}


	public Boolean comprobarlogin(String usuario, String pass) {
		
	    usuarios user = em.find(usuarios.class, usuario); // Buscar el usuario por nombre de usuario

	    if (user != null && user.getPass().equals(pass)) {
	        em.close();
	        return true; // Credenciales válidas
	    }

	    em.close();
	    return false; // Credenciales inválidas
	}


	public usuarios consultarPorUsuario(String usuario) {
		String jpql = "SELECT u FROM usuarios u WHERE u.usuario = :usuario";
		TypedQuery<usuarios> query = em.createQuery(jpql, usuarios.class);
		query.setParameter("usuario", usuario);

		try {
		    return query.getSingleResult();
		} catch (NoResultException ex) {
		    JOptionPane.showMessageDialog(null, "No se encontró el usuario");
		    return null;
		}
		
	}


	public boolean comprobarExistencia(String usuario) {
	    String jpql = "SELECT COUNT(u) FROM usuarios u WHERE u.usuario = :usuario";
	    TypedQuery<Long> query = em.createQuery(jpql, Long.class);
	    query.setParameter("usuario", usuario);

	    Long count = query.getSingleResult();
	    return count > 0;
	}
	


}
