package br.com.alexsandro.project.dto;

public class Erro {
	private Integer codigo;
	private String mensagem;
	
	
	public Erro(Integer codigo, String msg) {
		this.codigo = codigo;
		this.mensagem = msg;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}