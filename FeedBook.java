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

public class FeedBook extends Feed{
	private Document document;

	public FeedBook(String urlString) {
		super.getFeed(urlString);
	}

	public ArrayList<ItemBook> getItemList(Document document) {
		this.document = document;
		ArrayList<ItemBook> itemList = new ArrayList<ItemBook>();
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			NodeList itemNodeList = (NodeList)xPath.evaluate("/rss/channel/item",this.document, XPathConstants.NODESET);

			for(int i = 0; i < itemNodeList.getLength(); i++) {
				Node itemNode = itemNodeList.item(i);
				String title = xPath.evaluate("title",itemNode);
				String link = xPath.evaluate("link",itemNode);
				itemList.add(new ItemBook(title,link));
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
	/*
	public String getLastBuildDate() {
		String dayweek = "";
		String day = "";
		String month = "";
		String year = "";
		List<String> daysJa = Arrays.asList("日","月","火","水","木","金","土");
		List<String> daysEn = Arrays.asList("Sun","Mon","Tue","Wed","Thu","Fri","Sat");
		List<String> months = Arrays.asList("Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec");

		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xPath = factory.newXPath();
			String lastBuildDate = xPath.evaluate("/rss/channel/lastBuildDate",document);
			Pattern titlePattern = Pattern.compile("(.+?), ([0-9,]+) (.+?) ([0-9,]+) .+?");
			Matcher matcher = titlePattern.matcher(lastBuildDate);
			while(matcher.find()) {
				dayweek = matcher.group(1);
				day = matcher.group(2);
				month = matcher.group(3);
				year = matcher.group(4);
			}

			//英語→日本語変換
			dayweek = daysJa.get(daysEn.indexOf(dayweek));
			int monthInt = months.indexOf(month)+1;

			return year+"年"+monthInt+"月"+day+"日 "+dayweek+"曜日";

		}catch(XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;

	}*/
}
