package cc.pp.restlet.tutorial;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.routing.Template;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

/**
 * 路由器和层级URIs
 * @author wgybzb
 *
 */
public class Part11_Routing extends Application {

	/**
	 * 访问示例：
	 *  1、http://localhost:8111/hadoop/
	 *  2、http://localhost:8111/users/wgybzb
	 *  3、http://localhost:8111/users/wgybzb/orders
	 *  4、http://localhost:8111/users/wgybzb/orders/wanggang
	 */
	public static void main(String[] args) throws Exception {

		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);
		component.getClients().add(Protocol.FILE);

		Application application = new Part11_Routing();

		component.getDefaultHost().attach(application);
		component.start();
	}

	@Override
	public Restlet createInboundRoot() {

		// 创建一个root router
		Router router = new Router();

		// 创建简单的密码验证
		MapVerifier verifier = new MapVerifier();
		verifier.getLocalSecrets().put("wgybzb", "123456".toCharArray());

		// 创建保护，并将保护特性添加到目录中
		ChallengeAuthenticator guard = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "Tutorial");
		guard.setVerifier(verifier);
		router.attach("/hadoop/", guard).setMatchingMode(Template.MODE_STARTS_WITH);

		// 创建包含文件的目录
		Directory directory = new Directory(getContext(), Constants.ROOT_URI);
		guard.setNext(directory);

		// 创建account handle
		Restlet account = new Restlet() {
			@Override
			public void handle(Request request, Response response) {
				String message = "Account of user \"" + request.getAttributes().get("user") + "\"";
				response.setEntity(message, MediaType.TEXT_PLAIN);
			}
		};

		// 创建orders handler
		Restlet orders = new Restlet() {
			@Override
			public void handle(Request request, Response response) {
				String message = "Orders of user \"" + request.getAttributes().get("user") + "\"";
				response.setEntity(message, MediaType.TEXT_PLAIN);
			}
		};

		// 创建order handler
		Restlet order = new Restlet() {
			@Override
			public void handle(Request request, Response response) {
				String message = "Order \"" + request.getAttributes().get("order") + "\" for user \""
						+ request.getAttributes().get("user") + "\"";
				response.setEntity(message, MediaType.TEXT_PLAIN);
			}
		};

		// 绑定handlers到root router上
		router.attach("/users/{user}", account);
		router.attach("/users/{user}/orders", orders);
		router.attach("/users/{user}/orders/{order}", order);

		// 返回router
		return router;
	}

}
