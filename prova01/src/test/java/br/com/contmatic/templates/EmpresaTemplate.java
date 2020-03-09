package br.com.contmatic.templates;

import br.com.contmatic.empresa.Empresa;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EmpresaTemplate implements TemplateLoader {
	
	@Override
	public void load() {
		Fixture.of(Empresa.class).addTemplate("valido", new Rule() {{
			add("razaoSocial", random("Pepsico do Brasil Ltda", "Coca-Cola", "Oracle", "Lenovo", "Sony", "Nintendo", "Renato"));
			add("nomeFantasia", random("${razaoSocial}" + " Ltda", "${razaoSocial}" + " SA"));
			add("cnpj", random("21370294000113", "24829529000180", "41685581000120", "58028909000138", "97861611000166", "72180853000163", "84692899000140"));
			add("areaAtuacao", random("Indústria", "Comércio"));
			add("email", "sac@abcmail.com");
		}});
		
		Fixture.of(Empresa.class).addTemplate("mock", new Rule() {{
			add("razaoSocial", "MyCorp Ltd.");
			add("nomeFantasia", "MyCorp");
			add("cnpj", "48587314000169");
			add("areaAtuacao", "Comércio");
			add("email", "sac@mycorp.com");
		}});
		
	}
}
