package br.com.contmatic.templates;

import br.com.contmatic.empresa.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EnderecoTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Endereco.class).addTemplate("valido", new Rule() {{
			
		}});
		
		Fixture.of(Endereco.class).addTemplate("mock", new Rule() {{
			
		}});
		
	}
}
