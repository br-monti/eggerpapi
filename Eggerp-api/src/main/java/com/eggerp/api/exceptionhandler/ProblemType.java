package com.eggerp.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	INVALIDY_DATA("/dados-invalidos", "Dados inválidos"),
	SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
	INVALIDY_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
	INCOMPREENSIBLE_MESSAGE("/mensagem-incompreensivel", "Mensagem incompreensível"),
	RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTITY_IN_USE("/entidade-em-uso", "Entidade em uso"),
	TRANSACTIONAL_ERROR("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://eggerp.com.br" + path;
		this.title = title;
	}
	
}