package zx.soft.restlet.tutorial;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Extractor;
import org.restlet.routing.Redirector;
import org.restlet.routing.Router;

/**
 * URI重写、属性提取和重定向
 * @author wgybzb
 *
 */
public class Part10 extends Application {

	public static void main(String[] args) throws Exception {

		// 创建一个组件
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);

		// 创建一个应用
		Application application = new Part10();

		// 绑定应用到组件上，并启动
		component.getDefaultHost().attachDefault(application);
		component.start();
	}

	@Override
	public Restlet createInboundRoot() {

		// 创建一个root router
		Router router = new Router(getContext());

		// 创建一个Redirector到Google搜索服务
		//		String target = "http://www.google.com/search?q=site:mysite.org+{keywords}";
		String target = "https://www.google.com.hk/webhp#newwindow=1&q={keywords}&safe=strict";
		Redirector redirector = new Redirector(getContext(), target, Redirector.MODE_CLIENT_TEMPORARY);

		/**
		 * 当路由请求到重定向器的时候，提取“kwd”请求参数。
		 * 例如：http://localhost:8111/search?kwd=myKeyword1+myKeyword2 将会被路由到
		 *     http://www.google.com/search?q=site:mysite.org+myKeyword1+myKeyword2
		 *     http://localhost:8111/search?kwd=hadoop
		 */
		Extractor extractor = new Extractor(getContext(), redirector);
		extractor.extractFromQuery("keywords", "kwd", true);

		// 绑定extractor到router上
		router.attach("/search", extractor);

		// 返回root router
		return router;
	}

}
