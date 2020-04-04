package br.com.contmatic.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.contmatic.empresa.DepartamentoTest;
import br.com.contmatic.empresa.DependenteTest;
import br.com.contmatic.empresa.EmpresaTest;
import br.com.contmatic.empresa.FuncionarioTest;
import br.com.contmatic.empresa.SocioTest;
import br.com.contmatic.endereco.EnderecoTest;
import br.com.contmatic.telefone.TelefoneTest;

/**
 * The Class MyTestRunner.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	EmpresaTest.class,
	DepartamentoTest.class,
	DependenteTest.class,
	EnderecoTest.class,
	FuncionarioTest.class,
	TelefoneTest.class,
	SocioTest.class
})

public final class MyTestRunner {

}
