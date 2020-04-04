package br.com.contmatic.templates;

import static br.com.contmatic.telefone.TelefoneType.CELULAR;
import static br.com.contmatic.telefone.TelefoneType.TELEFONE_FIXO;
import static br.com.contmatic.util.Regex.REGEX_TELEFONE;

import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * The Class TelefoneTemplate.
 */
public class TelefoneTemplate implements TemplateLoader {

	/**
	 * Load.
	 */
	@Override
	public void load() {
		Fixture.of(Telefone.class).addTemplate("valido", new Rule() {{
			add("codigoPais", random(55, 39, 81, 1, 34, 32, 82));
			add("ddd", random(11, 13, 21, 83));
			add("numero", regex(REGEX_TELEFONE));
			add("tipo", random(CELULAR, TELEFONE_FIXO));
		}});
		
		Fixture.of(Telefone.class).addTemplate("mock", new Rule() {{
			add("codigoPais", 55);
			add("ddd", 11);
			add("numero", "987654321");
			add("tipo", CELULAR);
		}});
	}

}
