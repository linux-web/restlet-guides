package cc.pp.restlet.map.bug;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * <tr>
 *    这是Restlet的一个Bug，也就是LinkedHashMap返回值顺序被改变了。
 * </tr>
 * 
 * http://localhost:8000/bug/map
 * 
 * @author wanggang
 *
 */
public class MapServer {

	public static void main(String[] args) throws Exception {

		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8000);
		component.getDefaultHost().attach("/bug", new MapApplication());
		component.start();

	}

}
