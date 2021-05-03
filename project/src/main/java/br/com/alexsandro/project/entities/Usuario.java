package br.com.alexsandro.project.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.alexsandro.project.dto.UsuarioDTO;


@Entity
public class Usuario {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Column(nullable = false)
	String nome;
	
	@Enumerated(EnumType.STRING)
	Sexo sexo;
	
	String email;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	Date dataNascimento;
	
	String naturalidade;
	
	String nacionalidade;
	
	@Column(nullable = false)
	String cpf;
	
	Date dataCadastro;
	
	Date dataUltimaAlteracao;
	
	public Usuario() {}
	
	
	public Usuario(Long id, String nome, Sexo sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade,
			String cpf, Date dataCadastro, Date dataUltimaAlteracao) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.cpf = cpf;
		this.dataCadastro = dataCadastro;
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	public Usuario(Long id, String nome, Sexo sexo, String email, Date dataNascimento, String naturalidade, String nacionalidade,
			String cpf, Date dataUltimaAlteracao) {
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.naturalidade = naturalidade;
		this.nacionalidade = nacionalidade;
		this.cpf = cpf;
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	
	
	public Usuario(UsuarioDTO usuario) {
		
		
		this.nome = usuario.getNome();
		this.sexo = usuario.getSexo();
		this.email = usuario.getEmail();
		this.dataNascimento = usuario.getDataNascimento();
		this.naturalidade = usuario.getNaturalidade();
		this.nacionalidade = usuario.getNacionalidade();
		this.cpf = usuario.getCpf();
//		this.dataUltimaAlteracao = usuario.get;
		
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getUltimaAlteracao() {
		return dataUltimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.dataUltimaAlteracao = ultimaAlteracao;
	}


	
	
	

}
