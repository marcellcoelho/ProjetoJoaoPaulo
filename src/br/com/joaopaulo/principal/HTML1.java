package br.com.joaopaulo.principal;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTML1 {

	public static void main(String[] args) throws IOException {

		Document doc = Jsoup.connect("http://177.200.32.195:9673/sapl/relatorios_administrativos/propositurasAnoAutorTipo/view?ano=2016").get();

		System.out.println(doc.select("td").get(0).text());
		System.out.println(doc.select("td").get(1).text());
		System.out.println(doc.select("td").get(2).text());
		System.out.println(doc.select("td").get(3).text());
		System.out.println(doc.select("td").get(4).text());
		System.out.println(doc.select("td").get(5).text());
		System.out.println(doc.select("td").get(6).text());
		System.out.println(doc.select("td").get(7).text());
		System.out.println(doc.select("td").get(8).text());
		System.out.println(doc.select("td").get(9).text());
		System.out.println(doc.select("td").get(10).text());

	}

}