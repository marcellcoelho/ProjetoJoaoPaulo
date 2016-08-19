package br.com.joaopaulo.principal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.w3c.tidy.Tidy;

public class HTML2 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String string = "<p><span class='text'><a name='text'><a name='text'/>Some texts</a><a name='text'/>Some texts<br/>";

		Tidy td = new Tidy();
		td.setWraplen(Integer.MAX_VALUE);
		td.setXmlTags(true);
		td.setSmartIndent(true);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(string.getBytes("UTF-8"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		td.parseDOM(inputStream, outputStream);
		System.out.println(outputStream.toString("UTF-8"));
	}

}
