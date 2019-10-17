package br.com.contmatic.empresa;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmpresaTest.class,
	DepartamentoTest.class,
	DependenteTest.class,
	EnderecoTest.class,
	FuncionarioTest.class,
	PessoaTest.class,
	TelefoneTest.class
})

public class MyTestRunner {

}