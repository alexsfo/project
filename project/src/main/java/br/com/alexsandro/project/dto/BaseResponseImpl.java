package br.com.alexsandro.project.dto;

import java.util.ArrayList;
import java.util.List;


public class BaseResponseImpl implements BaseResponse{
	private List<Erro> erros;
	
	public BaseResponseImpl() {
		erros = new ArrayList<Erro>();
	}

	public List<Erro> getErros() {
		return erros;
	}

	public void setErros(List<Erro> erros) {
		this.erros = erros;
	}
	
	
}