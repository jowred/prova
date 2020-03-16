package br.com.contmatic.templates;

import br.com.contmatic.empresa.Dependente;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DependenteTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Dependente.class).addTemplate("valido", new Rule() {{
			
		}});
		
		Fixture.of(Dependente.class).addTemplate("mock", new Rule() {{
			
		}});
		
	}
	
}
