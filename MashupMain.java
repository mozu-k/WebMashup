package dws;

import java.util.List;

public class MashupMain {
	public static void main(String[] args) {

		//電子書籍ランキング上位100位
		Feed fb = new FeedBook("https://books.rakuten.co.jp/RBOOKS/xml/rss/000/101/000/rss.xml");
		List<ItemBook> bookList = fb.getItemList(fb.getDocument());

		//ドラマ情報
		Feed ftdrama = new FeedTVprogram("https://www.tvkingdom.jp/rss/schedulesBySearch.action?condition.genres%5B0%5D.id=103000&stationPlatformId=0&condition.keyword=&stationAreaId=23&submit.x=&submit.y=");
		List<ItemTVprogram> dramaList = ftdrama.getItemList(ftdrama.getDocument());

		//アニメ情報
		Feed ftanime = new FeedTVprogram("https://www.tvkingdom.jp/rss/schedulesBySearch.action?condition.genres%5B0%5D.id=107000&stationPlatformId=0&condition.keyword=&stationAreaId=23&submit.x=&submit.y=");
		List<ItemTVprogram> animeList = ftanime.getItemList(ftanime.getDocument());

		//映画情報
		Feed ftmovie = new FeedTVprogram("https://www.tvkingdom.jp/rss/schedulesBySearch.action?condition.genres%5B0%5D.id=106000&stationPlatformId=0&condition.keyword=&stationAreaId=23&submit.x=&submit.y=");
		List<ItemTVprogram> movieList = ftmovie.getItemList(ftmovie.getDocument());



		for(ItemBook book: bookList) {
			System.out.println(book.toString());

			int categoryFlag = 0;

			for(ItemTVprogram drama: dramaList) {
				if(drama.getTitle().contains(book.getTitle())) {
					if(categoryFlag == 0) {
						System.out.println("【ドラマ情報】");
					}
					System.out.println(drama.toString());

					TVprogramDetail detail = new TVprogramDetail(drama.getLink());
					for(int i = 0; i < detail.getSubUtilList().size(); i++) {
						String s = detail.getSubUtilList().get(i);
						if(s.equals("人名リンク")) {
							i++;
						}else {
							System.out.println(s);
						}
					}
					System.out.println("");
					categoryFlag++;
				}
			}

			categoryFlag = 0;
			for(ItemTVprogram anime: animeList) {
				if(anime.getTitle().contains(book.getTitle())) {
					if(categoryFlag == 0) {
						System.out.println("【アニメ情報】");
					}
					System.out.println(anime.toString());

					TVprogramDetail detail = new TVprogramDetail(anime.getLink());
					for(int i = 0; i < detail.getSubUtilList().size(); i++) {
						String s = detail.getSubUtilList().get(i);
						if(s.equals("人名リンク")) {
							i++;
						}else {
							System.out.println(s);
						}
					}
					System.out.println("");
					categoryFlag++;
				}
			}
			categoryFlag = 0;
			for(ItemTVprogram movie: movieList) {
				if(movie.getTitle().contains(book.getTitle())) {
					if(categoryFlag == 0) {
						System.out.println("【映画情報】");
					}
					System.out.println(movie.toString());

					TVprogramDetail detail = new TVprogramDetail(movie.getLink());
					for(int i = 0; i < detail.getSubUtilList().size(); i++) {
						String s = detail.getSubUtilList().get(i);
						if(s.equals("人名リンク")) {
							i++;
						}else {
							System.out.println(s);
						}
					}
					System.out.println("");
					categoryFlag++;
				}
			}
			System.out.println("");
		}
	}
}
