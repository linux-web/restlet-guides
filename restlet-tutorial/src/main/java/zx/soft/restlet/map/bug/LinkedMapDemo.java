package zx.soft.restlet.map.bug;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedMapDemo {

	public static void main(String[] args) {
		HashMap<String, Integer> map = new LinkedHashMap<>();
		map.put("安徽网", 15);
		map.put("20974", 10);
		map.put("Utopia4231", 8);
		map.put("随风而动1", 7);
		map.put("荣礼记小叶紫檀", 6);
		map.put("bUtopia4231", 5);
		map.put("a随风而动1", 5);
		map.put("BrotherLi--", 4);
		map.put("d合肥发布", 3);
		map.put("c新闻热点", 2);
		map.put("老牛", 1);
		map.put("1020", 0);
		System.out.println(map);
	}

}
