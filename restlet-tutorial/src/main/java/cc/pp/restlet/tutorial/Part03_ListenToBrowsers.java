package cc.pp.restlet.tutorial;

import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * 监听Web浏览器
 * @author wgybzb
 *
 */
public class Part03_ListenToBrowsers extends ServerResource {

	public static void main(String[] args) throws Exception {

		// 创建HTTP服务器，监听8111端口
		new Server(Protocol.HTTP, 8111, Part03_ListenToBrowsers.class).start();

	}

	/**
	 * 注意，添加Get返回响应
	 */
	@Override
	@Get
	public String toString() {
		return "Hello, world";
	}

}
