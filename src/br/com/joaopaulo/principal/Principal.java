package br.com.joaopaulo.principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.thoughtworks.xstream.XStream;

import br.com.joaopaulo.entidade.Autor;
import br.com.joaopaulo.entidade.Propositura;

public class Principal {
	
	public static final String NATUREZA_DA_PROPOSITURA = "Natureza da Propositura";
	public static final String QUANTIDADE = "Quantidade";
	public static final String URL_PROPOSITURAS_2016 = "http://177.200.32.195:9673/sapl/relatorios_administrativos/propositurasAnoAutorTipo/view?ano=2016";

	public static void main(String[] args) throws Exception {

		System.setProperty("http.proxyHost", "10.100.100.216"); // NÃO PRECISA
		System.setProperty("http.proxyPort", "3128"); // NÃO PRECISA

		List<Autor> autorList = montarPropositurasPorAutor();
		
		XStream xstream = new XStream();
		xstream.alias("autores", List.class);
		xstream.alias("autor", Autor.class);
		xstream.alias("propositura", Propositura.class);
        String representacao= xstream.toXML(autorList);
        
        System.out.println(representacao);

	}

	private static List<Autor> montarPropositurasPorAutor() throws Exception {
		Document doc = Jsoup.connect(URL_PROPOSITURAS_2016).get();
		Integer anoPropositura = Integer.parseInt(doc.select("#conteudo > fieldset:nth-child(3) > legend").text().replaceAll("[^0-9]", ""));
		
		int iterator = 1;
		boolean ehAutor = true;
		Autor autor = new Autor();
		HashMap<String, Autor> autorHashMap = new HashMap<>();
		while (!doc.select("#conteudo > fieldset:nth-child(3) > table > tbody > tr:nth-child(" + iterator + ") > td").isEmpty()) {
			String linha = doc.select("#conteudo > fieldset:nth-child(3) > table > tbody > tr:nth-child(" + iterator + ") > td").text();
			if (linha.isEmpty()) {
				ehAutor = true;
			} else {
				if (!(linha.contains(NATUREZA_DA_PROPOSITURA) || linha.contains(QUANTIDADE))) {
					if (ehAutor) {
						autor = new Autor();
						autor.setNome(linha);
						autor.setProposituraList(new ArrayList<>());
						ehAutor = false;
					} else {
						Propositura propositura = new Propositura();
						propositura.setNatureza(linha.replaceAll("[0-9]", ""));
						propositura.setAno(anoPropositura);
						propositura.setQuantidade(Integer.parseInt(linha.replaceAll("[^0-9]", "")));
						autor.getProposituraList().add(propositura);
						autorHashMap.put(autor.getNome(), autor);
					}
				}
			}
			iterator++;
		}
		
		List<Autor> autorList = new ArrayList<>(autorHashMap.values());
		Collections.sort(autorList); //ORDERNA POR NOME
		return autorList;
	}

}