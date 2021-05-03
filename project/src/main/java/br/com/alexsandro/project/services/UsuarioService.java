package br.com.alexsandro.project.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alexsandro.project.dto.Erro;
import br.com.alexsandro.project.dto.UsuarioDTO;
import br.com.alexsandro.project.entities.Usuario;
import br.com.alexsandro.project.repositories.UsuarioRepository;
import br.com.alexsandro.project.repositories.UsuarioRepositoryImpl;
import br.com.alexsandro.project.utils.Validations;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	UsuarioRepositoryImpl repositoryImpl;

	@Transactional(readOnly = true)
	public List<UsuarioDTO> findAll() {
		List<Usuario> list = repository.findAll();
		return list.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
	}

	@Transactional
	public UsuarioDTO insert(UsuarioDTO dto) {

		// se o preenchimento estiver ok, insere o registro na base e retorna o registro
		// criado. Caso não, retorna o mesmo objeto do request com o(s) erro(s)
		// encontrados

		Usuario Usuario = new Usuario(null, dto.getNome(), dto.getSexo(), dto.getEmail(), new Date(),
				dto.getNaturalidade(), dto.getNacionalidade(), dto.getCpf(), new Date(), new Date());

		return new UsuarioDTO(Usuario = repository.save(Usuario));

	}

	// Verifica preenchimento dos campos obrigários e validações dos dados
	// informados
	@SuppressWarnings("unused")
	private boolean validarPreenchimentoInsert(UsuarioDTO dto) {
		List<Erro> erros = new ArrayList<>();

		if (dto.getCpf() == null || dto.getCpf().isBlank()) {
			erros.add(new Erro(HttpStatus.BAD_REQUEST.value(), "O CPF não pode ser nulo ou vazio."));
		}

		// caso preenchido o email, verifica se é válido.
		if (!dto.getEmail().isBlank()) {
			if (!Validations.isValidEmailAddressRegex(dto.getEmail())) {
				erros.add(new Erro(HttpStatus.BAD_REQUEST.value(), "Email inválido"));
			}
		}

		// verifica preenchimento e validade da data de nascimento
//		if (dto.getDataNascimento() == null ) {
//			erros.add(new Erro(HttpStatus.BAD_REQUEST.value(),
//					dto.getDataNascimento() == null ? "A data de nascimento é obrigatória" : "Data de nascimento inválida"));
//		}

		// verifica preenchimento, validade e se o cpf já está cadastrado na base
		if (dto.getCpf() == null || !Validations.isCPF(dto.getCpf()) || cpfJaExiste(dto.getCpf())) {
			erros.add(new Erro(HttpStatus.BAD_REQUEST.value(),
					dto.getCpf() == null ? "O CPF é obrigatório" : "CPF inválido"));

			if (cpfJaExiste(dto.getCpf())) {
				erros.add(new Erro(HttpStatus.BAD_REQUEST.value(), "Usuário já cadastrado"));
			}
		}

		dto.getErros().addAll(erros);
		return erros.isEmpty();
	}

	// verifica se o CPF informado já está cadastrado na base de dados
	private boolean cpfJaExiste(String cpf) {

		return (repository.findByCpf(cpf)) == null ? true : false;
	}

	public UsuarioDTO findById(Long id) {
		Optional<Usuario> optional = repository.findById(id);

		if (optional.isPresent()) {
			return new UsuarioDTO(optional.get());
		} else {
			UsuarioDTO dto = new UsuarioDTO();
			dto.getErros().add(new Erro(HttpStatus.NOT_FOUND.value(), "Usuário não encontrado"));
			return dto;
		}
	}

	public UsuarioDTO edit(Long id, UsuarioDTO request) {
		Optional<Usuario> usarioTemp = repository.findById(id);
		Usuario user = new Usuario();
		if (usarioTemp.isPresent()) {
			repository.deleteById(id);
			repository.save(usarioTemp.get());
			user = repository.findByCpf(usarioTemp.get().getCpf());
		}

		return new UsuarioDTO(user);
	}
	
	
	public void deleteById(Long id, UsuarioDTO dto) {
		repository.deleteById(id);
		 
	}
}
