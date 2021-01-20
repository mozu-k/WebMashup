package dws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TVprogramDetail {
	private ArrayList<String> subUtilList = new ArrayList<String>();
	private final String charset = "UTF-8";

	public TVprogramDetail(String urlString) {
		ArrayList<String> lineList = new ArrayList<String>();

		try{
			URL url = new URL(urlString);
            lineList = read(url);
        }catch(Exception e){
            e.printStackTrace();
        }

		setSubUtil(lineList);

	}

	public ArrayList<String> getSubUtilList(){
		return this.subUtilList;
	}

	public void setSubUtil(List<String> lineList) {
		Pattern patternTitle = Pattern.compile("<h3 class=\"blTitleSub basicTit\">(.+?)</h3>");
		Pattern pattern = Pattern.compile("<p class=\"basicTxt\">(.+?)</p>");

		int flag = 0;
		StringBuffer detail = new StringBuffer();
		for(String line:lineList) {
			Matcher matcherTitle = patternTitle.matcher(line);
			Matcher matcher = pattern.matcher(line);

			if(flag == 1 && line.matches("						</p>")) {
				flag--;
				if(detail.length() != 0) {
					this.subUtilList.add(new String(detail));
					detail = new StringBuffer();
				}
			}
			if(flag == 1 && (line.matches("						</p>") == false)) {
				detail.append(line);
			}
			while(matcherTitle.find()) {
				this.subUtilList.add(matcherTitle.group(1));
			}
			while(matcher.find()) {
				this.subUtilList.add(matcher.group(1));
			}
			if(line.matches("						<p class=\"basicTxt\">")) {
				flag++;
			}
		}
	}

	public ArrayList<String> read(URL url){
        ArrayList<String> lineList = new ArrayList<String>();
        try{
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),this.charset));

            String line = null;
            while((line = br.readLine()) != null){
                lineList.add(line);
            }

            return lineList;

        }catch(IOException e){
            System.err.println("I/O Error: " + e.toString());
        }
        return lineList;
    }
}
