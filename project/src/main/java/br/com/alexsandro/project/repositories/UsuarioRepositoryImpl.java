package br.com.alexsandro.project.repositories;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.alexsandro.project.dto.UsuarioDTO;
import br.com.alexsandro.project.entities.Usuario;

public class UsuarioRepositoryImpl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EntityManager em;

	@Autowired
	UsuarioRepository repository;

	@Transactional
	public UsuarioDTO edit(Long id, UsuarioDTO usuario) {
		Optional<Usuario> u = repository.findById(id);

		if (u.isPresent()) {
			Usuario user = em.find(Usuario.class, id);
			user = usuario.toEntity(usuario);
			user.setId(id);
			em.merge(user);
			this.em.flush();
		}

		return new UsuarioDTO(u.get());
	}

}
