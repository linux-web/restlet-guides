package zx.soft.restlet.tutorial;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;

/**
 * 使用一个应用提供静态文件服务
 * @author wgybzb
 *
 */
public class Part06_ServeStaticFiles {

	public static void main(String[] args) throws Exception {

		// 创建一个组件
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);
		component.getClients().add(Protocol.FILE);

		// 创建一个应用
		Application application = new Application() {
			@Override
			public Restlet createInboundRoot() {
				return new Directory(getContext(), Constants.ROOT_URI);
			}
		};

		// 将应用绑定到组件上，然后启动组件
		component.getDefaultHost().attach(application);
		component.start();
	}

}
