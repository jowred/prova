package br.com.contmatic.templates;

import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Dependente;
import br.com.contmatic.empresa.Socio;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class SocioTemplate.
 */
public class SocioTemplate implements TemplateLoader {

	/**
	 * Load.
	 */
	@Override
	public void load() {
		Fixture.of(Socio.class).addTemplate("valido", new Rule() {{
			add("nome", random("Ramon", "Josef", "Carla", "Fernando", "Rafaela", "Pedro", "Paulo"));
			add("rg", random("266706721", "266481565", "199307817", "479579404", "418359404", "225151443", "378009941"));
			add("cpf", random("05704367020", "69556138048", "90091412099", "10045227039", "73006374002", "07944624053", "58746000003"));
			add("dataNascimento", random(new LocalDate(1997, 12, 25), new LocalDate(1978, 8, 2), new LocalDate(2000, 9, 16)));
			String email = new String("${nome}" + (int)(Math.random() * 100) + "@gmail.com").toLowerCase();
			add("email", email);
			add("inicioSociedade", random(new LocalDate(2019, 12, 19), new LocalDate(2018, 8, 2), new LocalDate(2020, 1, 16)));
			add("dependentes", has(1).of(Dependente.class, "mock_for_socio"));
		}});
		
		Fixture.of(Socio.class).addTemplate("mock", new Rule() {{
			add("nome", "Fernanda");
			add("rg", "516879541");
			add("cpf", "50736121080");
			add("dataNascimento", new LocalDate(1994, 12, 11));
			add("email", "${nome}14@gmail.com");
			add("inicioSociedade", new LocalDate(2019, 9, 17));
		}});
	}
	
}
