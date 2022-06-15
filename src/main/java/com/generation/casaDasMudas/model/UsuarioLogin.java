package com.generation.casaDasMudas.model;

public class UsuarioLogin {

	private Long idLogin;

	private String nomeLogin;

	private String usuarioLogin;

	private String senhaLogin;

	private String tokenLogin;

	private String fotoLogin;

	public UsuarioLogin(Long idLogin, String nomeLogin, String usuarioLogin, String senhaLogin, String tokenLogin,
			String fotoLogin) {
		this.idLogin = idLogin;
		this.nomeLogin = nomeLogin;
		this.usuarioLogin = usuarioLogin;
		this.senhaLogin = senhaLogin;
		this.tokenLogin = tokenLogin;
		this.fotoLogin = fotoLogin;
	}
	
	public UsuarioLogin() {	}

	public Long getIdLogin() {
		return idLogin;
	}
	
	public void setIdLogin(Long idLogin) {
		this.idLogin = idLogin;
	}

	public String getNomeLogin() {
		return nomeLogin;
	}

	public void setNomeLogin(String nomeLogin) {
		this.nomeLogin = nomeLogin;
	}

	public String getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(String usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	public String getSenhaLogin() {
		return senhaLogin;
	}

	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}

	public String getTokenLogin() {
		return tokenLogin;
	}

	public void setTokenLogin(String tokenLogin) {
		this.tokenLogin = tokenLogin;
	}

	public String getFotoLogin() {
		return fotoLogin;
	}

	public void setFotoLogin(String fotoLogin) {
		this.fotoLogin = fotoLogin;
	}

}
