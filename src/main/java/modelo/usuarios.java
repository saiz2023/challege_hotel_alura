package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class usuarios {
	@Id
    @Column(unique = true)
	private String usuario;
	private String pass;

	
	public usuarios() {
		}

	public usuarios(String user, String pass) {
		this.usuario = user;
		this.pass = pass;
	}

	
	public String getUser() {
		return usuario;
	}
	public void setUser(String user) {
		this.usuario = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
