package zx.soft.restlet.tutorial;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Restlets组件
 * @author wgybzb
 *
 */
public class Part05_RestletComponents extends ServerResource {

	public static void main(String[] args) throws Exception {

		// 创建一个新的Restlet组件，再在该组件上添加一个HTTP服务器连接器
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);

		// 绑定到本地主机上的trace路径下面
		component.getDefaultHost().attach("/trace", Part05_RestletComponents.class);

		// 启动组件
		// 注意：HTTP服务器连接器也可以自动启动
		component.start();
	}

	@Override
	@Get
	public String toString() {

		// 打印请求的URI路径
		return "Resource URI : " + getReference() + '\n' + //
				"Root URI : " + getRootRef() + '\n' + //
				"Routed part : " + getReference().getBaseRef() + '\n' + //
				"Remaining part : " + getReference().getRemainingPart();
	}

}
