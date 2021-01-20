package dws;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSParser;

public abstract class Feed {
	private String urlString;
	private Document document = null;
	private final String charset = "UTF-8";

	public void getFeed(String urlString) {

		this.urlString = urlString;

		try {
			// InputStreamの用意
			URL url = new URL(this.urlString);
			URLConnection connection = url.openConnection();
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			// DOMツリーの構築
			buildDocument(inputStream);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract ArrayList getItemList(Document document);

	/* DOM Tree の構築 */
	public void buildDocument(InputStream inputStream) {
		try {
			// DOM実装(implementation)の用意 (Load and Save用)
			DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			DOMImplementationLS implementation = (DOMImplementationLS)registry.getDOMImplementation("XML 1.0");
			// 読み込み対象の用意
			LSInput input = implementation.createLSInput();
			input.setByteStream(inputStream);
			input.setEncoding(this.charset);
			// 構文解析器(parser)の用意
			LSParser parser = implementation.createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
			parser.getDomConfig().setParameter("namespaces", false);
			// DOMの構築
			this.document = parser.parse(input);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Document getDocument() {
		return this.document;
	}
}
