package br.com.contmatic.prova01;

public class Pessoa {
	private String nome;
	private String rg;
	private String cpf;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String rg, String cpf) {
		setNome(nome);
		setRg(rg);
		setCpf(cpf);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome == null)
			throw new NullPointerException("Nome não pode ser nulo.");
		
		if(nome.equals(""))
			throw new IllegalArgumentException("Nome não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<nome.length(); i++)
			if(Character.isAlphabetic(nome.charAt(i)))
				qtdeLetras++;
			
		if(qtdeLetras < 2 || nome.length() > 100)
			throw new IllegalArgumentException("Nome deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		
		if(!Character.isAlphabetic(nome.charAt(0)))
			throw new IllegalArgumentException("Nome deve obrigatoriamente começar com uma letra.");
		
		for(int i=0; i<nome.length(); i++)
			if(!Character.isAlphabetic(nome.charAt(i)) &&
					(nome.charAt(i) != ' ') &&
					(nome.charAt(i) != '.'))
				throw new IllegalArgumentException("Nome pode ser composto apenas por letras e espaços.");
		
		String temp = nome.toLowerCase(); 
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<nome.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == nome.length()-1)
			throw new IllegalArgumentException("Nome não pode ser composto unicamente pelo mesmo caractere.");
		
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		if(rg == null)
			throw new NullPointerException("RG não pode ser nulo.");
		
		if(rg.equals(""))
			throw new IllegalArgumentException("RG não pode estar em branco.");
		
		if(rg.length() < 8 || rg.length() > 9)
			throw new IllegalArgumentException("RG precisa ter 9 dígitos.");
		
		for(int i=0; i<rg.length(); i++)
			if(!Character.isDigit(rg.charAt(i)))
				throw new IllegalArgumentException("RG deve conter apenas dígitos.");
		
		char primeiro = rg.charAt(0);
		int repetidos = 0;
		for(int i=0; i<rg.length()-1; i++)
			if(primeiro == rg.charAt(i+1))
				repetidos++;
			else
				break;
		if(repetidos == rg.length()-1)
			throw new IllegalArgumentException("RG não pode ser composto por dígitos iguais.");
		
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if(cpf == null)
			throw new NullPointerException("CPF não pode ser nulo.");
		
		if(cpf.equals(""))
			throw new IllegalArgumentException("CPF não pode ser deixado em branco.");
		
		if(cpf.length() != 11)
			throw new IllegalArgumentException("CPF precisa ter 11 dígitos.");
		
		for(int i=0; i<cpf.length(); i++)
			if(!Character.isDigit(cpf.charAt(i)))
				throw new IllegalArgumentException("CPF deve conter apenas dígitos.");
		
		char primeiro = cpf.charAt(0);
		int repetidos = 0;
		for(int i=0; i<cpf.length()-1; i++)
			if(primeiro == cpf.charAt(i+1))
				repetidos++;
			else
				break;
		if(repetidos == cpf.length()-1)
			throw new IllegalArgumentException("CPF não pode ser composto por dígitos iguais.");
		
		if(!cpfValido(cpf))
			throw new IllegalArgumentException("CPF inválido.");
		
		this.cpf = cpf;
	}

	protected boolean primeiroDigitoValido(int[] cpf, int digitoVerif) {
		int verificador = 0;
		int somatorio = 0;
		for(int i=0; i<cpf.length; i++)//Até o dígito n9
			somatorio += cpf[i] * (10 - i);
		verificador = 11 - (somatorio % 11);
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == digitoVerif;
	}
	
	protected boolean segundoDigitoValido(int[] cpf, int[] digitoVerif) {
		int verificador = 0;
		int somatorio = 0;
		for(int i=0; i<cpf.length; i++)
			somatorio += cpf[i] * (11 - i);
		somatorio += digitoVerif[0] * 2;
		verificador = 11 - (somatorio % 11);
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == digitoVerif[1];
	}
	
	protected boolean cpfValido(String cpf) {		 
		int[] verif = new int[2];
		verif[0] = cpf.charAt(9) - 48;
		verif[1] = cpf.charAt(10) - 48;
		int[] iCpf = new int[9];
		for(int i=0; i<9; i++)
			iCpf[i] = cpf.charAt(i) - 48;
		
		return primeiroDigitoValido(iCpf, verif[0]) && segundoDigitoValido(iCpf, verif);
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\n"
				+ "RG: " + rg + "\n"
				+ "CPF: " + cpf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cpf.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		
		Pessoa other = (Pessoa)obj;
		if(!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
