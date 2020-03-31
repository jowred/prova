package br.com.contmatic.templates;

import br.com.contmatic.empresa.Departamento;
import br.com.contmatic.empresa.Funcionario;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class DepartamentoTemplate.
 */
public class DepartamentoTemplate implements TemplateLoader {

	/**
	 * Load.
	 */
	@Override
	public void load() {
		Fixture.of(Departamento.class).addTemplate("valido", new Rule() {{
			add("nome", random("RH", "Finanças", "Novas tecnologias", "Vendas", "Administração", "Segurança", "Atendimento ao cliente"));
			add("descricao", "Departamento de ${nome}");
			add("funcionarios", has(5).of(Funcionario.class, "valido"));
		}});
		
		Fixture.of(Departamento.class).addTemplate("mock", new Rule() {{
			add("nome", "Novas tecnologias");
			add("descricao", "Departamento de ${nome}");
			add("funcionarios", has(2).of(Funcionario.class, "mock"));
		}});
		
	}
}
