package br.com.joaopaulo.principal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.tidy.Tidy;

public class HTML2 {

	public static void main(String[] args) throws Exception {

		//ACESSAR SITE
		Document doc = Jsoup.connect("http://177.200.32.195:9673/sapl/relatorios_administrativos/propositurasAnoAutorTipo/view?ano=2016").get();
		String string = doc.select("#conteudo > fieldset:nth-child(4)").html();

		
		
		//CONVERTER EM XML
		Tidy td = new Tidy();
		td.setXmlTags(true);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes("UTF-8"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		td.parseDOM(inputStream, outputStream);
		System.out.println(outputStream.toString("UTF-8"));

		
		
		//SALVAR EM ARQUIVO
		FileWriter arquivo = new FileWriter(new File("Arquivo.txt"));
		arquivo.write(outputStream.toString("UTF-8"));
		arquivo.close();

	}

}
