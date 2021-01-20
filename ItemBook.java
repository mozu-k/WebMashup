package dws;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemBook implements Item{
	private String title;
	private String link;
	private int rank;

	public ItemBook(String title,String link) {
		Pattern pattern = Pattern.compile("([0-9,]+)位：(【.+?】)*(.+?)[\s(（【〜　]+[.)）】]*");
		Pattern pattern2 = Pattern.compile("([0-9,]+)+位：(.+)");
		Matcher matcher = pattern.matcher(title);
		Matcher matcher2 = pattern2.matcher(title);
		while(matcher.find()) {
			this.title = matcher.group(3);
			this.rank = Integer.parseInt(matcher.group(1));
		}
		if(this.title == null) {
			while(matcher2.find()) {
				this.title = matcher2.group(2);
				this.rank = Integer.parseInt(matcher2.group(1));
			}
		}
		this.link = link;
	}
	public String getTitle() {
		return this.title;
	}
	public String getLink() {
		return this.link;
	}
	public int getRank() {
		return this.rank;
	}
	@Override
	public String toString() {
		return this.rank+"位："+this.title;
	}
}
