package br.com.contmatic.templates;

import static br.com.contmatic.constantes.Regex.REGEX_CEP;
import static br.com.contmatic.enums.EnumEstadosBrasileiros.SP;
import static br.com.contmatic.enums.EnumTipoEndereco.RUA;

import br.com.contmatic.empresa.Endereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class EnderecoTemplate implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Endereco.class).addTemplate("valido", new Rule() {{
			add("numero", random(null, 12, 21, 404, 4, 702, 903));
			add("logradouro", random("Rua A", "Travessa dos Nomes Mágicos", "Rua das Flores", "Av. Paulista"));
			add("bairro", random("Piraju", "Tatuapé", "Jd. Romano", "Jd. Helena", "Monte Belo", "Jd. do Carmo", "Vila Verde"));
			add("cidade", random("Itaquaquecetuba", "Suzano", "Mogi das Cruzes", "Poá", "São Paulo", "Guarulhos", "Arujá"));
			add("uf", SP);
			add("pais", "Brasil");
			add("cep", regex(REGEX_CEP));
			add("tipo", RUA);
		}});
		
		Fixture.of(Endereco.class).addTemplate("mock", new Rule() {{
			add("numero", 400);
			add("logradouro", "Av. Paulista");
			add("bairro", "Tatuapé");
			add("cidade", "São Paulo");
			add("uf", SP);
			add("pais", "Brasil");
			add("cep", "08577-404");
			add("tipo", RUA);
		}});
		
	}
}
