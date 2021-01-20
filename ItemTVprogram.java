package dws;

public class ItemTVprogram implements Item{
	private String title;
	private String link;
	private String description;

	public ItemTVprogram(String title,String link,String description) {
		this.title = title;
		this.link = link;
		this.description = description;
	}
	public String getTitle() {
		return this.title;
	}
	public String getLink() {
		return this.link;
	}
	public String getDescription() {
		return this.description;
	}
	@Override
	public String toString() {
		return this.title+"："+this.description;
	}
}
