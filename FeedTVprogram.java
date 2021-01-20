package dws;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FeedTVprogram extends Feed{

	public FeedTVprogram(String urlString) {
		super.getFeed(urlString);
	}

	public ArrayList<ItemTVprogram> getItemList(Document document) {
		ArrayList<ItemTVprogram> itemList = new ArrayList<ItemTVprogram>();
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			NodeList itemNodeList = (NodeList)xPath.evaluate("/RDF/item",document, XPathConstants.NODESET);

			//全部だと数が多いため1000件取得
			int size = 0;
			if(itemNodeList.getLength() < 1000) {
				size = itemNodeList.getLength();  //1000件以下のときは全て取得
			}else {
				size = 1000;
			}
			for(int i = 0; i < size; i++) {
				Node itemNode = itemNodeList.item(i);
				String title = xPath.evaluate("title",itemNode);
				String link = xPath.evaluate("link",itemNode);
				String description = xPath.evaluate("description",itemNode);
				itemList.add(new ItemTVprogram(title,link,description));
			}
		}
		catch (DOMException e) {
            System.err.println("DOMエラー:" + e);
		}
		catch (XPathExpressionException e) {
            System.err.println("XPath 表現のエラー:" + e);
		}
		return itemList;
	}
}
