package br.com.contmatic.templates;

import static br.com.contmatic.enums.EnumTipoParentesco.ENTEADO;
import static br.com.contmatic.enums.EnumTipoParentesco.FILHO;
import static br.com.contmatic.enums.EnumTipoParentesco.SOBRINHO;

import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Dependente;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Socio;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DependenteTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Dependente.class).addTemplate("valido", new Rule() {{
			add("nome", random("Ana", "Julia", "Amanda", "Fernando", "Jonas", "Afonso", "Renato"));
			add("rg", random("266706721", "266481565", "199307817", "479579404", "418359404", "225151443", "378009941"));
			add("cpf", random("05704367020", "69556138048", "90091412099", "10045227039", "73006374002", "07944624053", "58746000003"));
			add("dataNascimento", random(new LocalDate(1997, 12, 25), new LocalDate(1978, 8, 2), new LocalDate(2000, 9, 16)));
			add("provedor", one(Funcionario.class, "valido"));
			add("parentesco", random(FILHO, ENTEADO, SOBRINHO));
			add("idade", random(Integer.class, range(0, 17)));
		}});
		
		Fixture.of(Dependente.class).addTemplate("mock", new Rule() {{
			add("nome", "Joana");
			add("rg", "266706721");
			add("cpf", "05704367020");
			add("dataNascimento", new LocalDate(1998, 9, 21));
			add("provedor", one(Funcionario.class, "mock"));
			add("parentesco", FILHO);
			add("idade", 10);
		}});
		
		Fixture.of(Dependente.class).addTemplate("valido_for_socio", new Rule() {{
			add("nome", random("Ana", "Julia", "Amanda", "Fernando", "Jonas", "Afonso", "Renato"));
			add("rg", random("266706721", "266481565", "199307817", "479579404", "418359404", "225151443", "378009941"));
			add("cpf", random("05704367020", "69556138048", "90091412099", "10045227039", "73006374002", "07944624053", "58746000003"));
			add("dataNascimento", random(new LocalDate(1997, 12, 25), new LocalDate(1978, 8, 2), new LocalDate(2000, 9, 16)));
			add("provedor", one(Socio.class, "valido"));
			add("parentesco", random(FILHO, ENTEADO, SOBRINHO));
			add("idade", random(Integer.class, range(0, 17)));
		}});
		
		Fixture.of(Dependente.class).addTemplate("mock_for_socio", new Rule() {{
			add("nome", "Joana");
			add("rg", "266706721");
			add("cpf", "05704367020");
			add("dataNascimento", new LocalDate(1998, 9, 21));
			add("provedor", one(Socio.class, "mock"));
			add("parentesco", FILHO);
			add("idade", 10);
		}});
		
	}
	
}
