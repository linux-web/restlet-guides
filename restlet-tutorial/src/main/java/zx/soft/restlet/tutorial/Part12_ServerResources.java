package zx.soft.restlet.tutorial;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * 到达目标资源
 * @author wgybzb
 *
 */
public class Part12_ServerResources extends Application {

	/**
	 * 访问示例：
	 *  1、http://localhost:8111/users/wgybzb
	 *  2、http://localhost:8111/users/wgybzb/orders
	 *  3、http://localhost:8111/users/wgybzb/orders/wanggang
	 */
	public static void main(String[] args) throws Exception {

		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);

		Application application = new Part12_ServerResources();

		component.getDefaultHost().attachDefault(application);
		component.start();
	}

	@Override
	public Restlet createInboundRoot() {

		Router router = new Router(getContext());

		router.attach("/users/{user}", UserResource.class);
		router.attach("/users/{user}/orders", OrdersResource.class);
		router.attach("/users/{user}/orders/{order}", OrderResource.class);

		return router;
	}

}
