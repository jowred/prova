package br.com.contmatic.templates;

import br.com.contmatic.empresa.Departamento;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DepartamentoTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Departamento.class).addTemplate("valido", new Rule() {{
			
		}});
		
		Fixture.of(Departamento.class).addTemplate("mock", new Rule() {{
			
		}});
		
	}
}
