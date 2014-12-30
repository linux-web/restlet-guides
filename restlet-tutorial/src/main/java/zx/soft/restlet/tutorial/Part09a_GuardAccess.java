package zx.soft.restlet.tutorial;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MapVerifier;

/**
 * 保护获取Restlet资源
 * @author wgybzb
 *
 */
public class Part09a_GuardAccess extends Application {

	public static void main(String[] args) throws Exception {

		// 创建一个组件
		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);
		component.getClients().add(Protocol.FILE);

		// 创建一个应用
		Application application = new Part09a_GuardAccess();

		// 将应用绑定到组件上，然后启动
		component.getDefaultHost().attachDefault(application);
		component.start();
	}

	@Override
	public Restlet createInboundRoot() {

		// 创建一个简单的密码验证
		MapVerifier verifier = new MapVerifier();
		verifier.getLocalSecrets().put("wgybzb", "123456".toCharArray());

		// 创建保护
		ChallengeAuthenticator authenticator = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC,
				"Tutorial");
		authenticator.setVerifier(verifier);

		// 创建一个目录，可以返回文件的深层次结构
		Directory directory = new Directory(getContext(), Constants.ROOT_URI);
		directory.setListingAllowed(true);
		authenticator.setNext(directory);

		return authenticator;
	}

}
