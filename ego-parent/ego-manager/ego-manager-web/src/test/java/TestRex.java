import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestRex {
	public static void main(String[] args) {
		String[] images = "http://192.168.112.160:8888/group1/M00/00/00/wKhwoFrpjfqAJaibAAqLZZ4Yt0A176.jpg".split("/");
		String realImage = "";
		for(int i = 4; i < images.length; i++){
			realImage += images[i];
			if(i<images.length-1){
				realImage = realImage+"/";
			}
		}
		Map<String, String> a = new HashMap<>();
		a.put("a", "hh");
		a.put("a","kk");
		System.out.println(a.get("a"));
		System.out.println(realImage);
	}
}
