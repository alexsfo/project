package br.com.alexsandro.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexsandro.project.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
	Usuario findByCpf(String cpf);
	
	
}
