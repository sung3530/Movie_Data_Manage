package Data_Accesse;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import MovieManager.Movie;

public class Web_Crawling {
   
   public ArrayList<Movie> Crawl() throws IOException {
      ArrayList<Movie> returnData = new ArrayList<Movie>();
      
      Document doc = Jsoup
            .connect(
                  "http://www.kobis.or.kr/kobis/business/stat/boxs/findRealTicketList.do?loadEnd=0&repNationCd=&areaCd=&repNationSelected=&totIssuAmtRatioOrder=&totIssuAmtOrder=&addTotIssuAmtOrder=&totIssuCntOrder=&totIssuCntRatioOrder=&addTotIssuCntOrder=&dmlMode=search&allMovieYn=Y")
            .get();
      Elements movie_status_temp = doc.select("tr#tr_ td");
      Elements movie_code_temp = doc.select("tr#tr_ td.ellipsis"); // ��ȭ�ڵ� �̱��
      String[] movie_status = new String[8];//HTML �ӽ�����
      String[] movie_code_temp2 = new String[5];//HTML �ӽ�����
      for (int i = 0; i < 6; i++) {
         Movie temp=new Movie();
    	  
         for (int j = 0; j < 8; j++) {
            movie_status[j] = new String(movie_status_temp.get(i * 8 + j).text());
         }
         
         movie_code_temp2 = movie_code_temp.get(i).getElementsByTag("a").attr("onclick").split("'");      
       
         temp.setRank(Integer.parseInt(movie_status[0]));
         temp.setMovieName(movie_status[1]);
         temp.setPlayDate(movie_status[2].replace("-", ""));
         try {
        	 temp.setRunningTime(DataManager.getInstance().getMovie_Accesse().getRunningTime(movie_code_temp2[3]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         temp.setExpectedAudience(Integer.parseInt(movie_status[6].replace(",", "")));
         returnData.add(temp);
      }
      return returnData;
   }
}
// ��ȭ�� ������ ������ ���Ÿ���� ��������� ���Ű����� ����������
// tr#class id
// td.class
