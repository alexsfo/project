package br.com.alexsandro.project.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.alexsandro.project.entities.Sexo;
import br.com.alexsandro.project.entities.Usuario;

public class UsuarioDTO implements Serializable, BaseResponse {

	private static final long serialVersionUID = 1L;
	
	Long id;

	@NotBlank
	@NotNull
	String nome;

	Sexo sexo;
	
	String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date dataNascimento;

	String naturalidade;

	String nacionalidade;

	@NotBlank
	@NotNull
	String cpf;
	
	List<Erro> erros = new ArrayList<>();
	 
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(Usuario entity) {
		id = entity.getId();
		nome = entity.getNome();
		sexo = entity.getSexo();
		email = entity.getEmail();
		dataNascimento = entity.getDataNascimento();
		naturalidade = entity.getNaturalidade();
		nacionalidade = entity.getNacionalidade();
		cpf = entity.getCpf();
	}

	@Override
	public List<Erro> getErros() {
		return this.erros;
	}

	@Override
	public void setErros(List<Erro> erros) {
		
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Usuario toEntity(UsuarioDTO dto) {
		return new Usuario(dto.getId(), dto.getNome(), dto.getSexo(), dto.getEmail(), new Date(), dto.getNaturalidade(), dto.getNacionalidade(), dto.getCpf(), null, null);
	}

}
