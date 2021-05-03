package br.com.alexsandro.project.dto;

import java.util.List;

public interface BaseResponse {

	public List<Erro> getErros();
	
	public void setErros(List<Erro> erros);
}
