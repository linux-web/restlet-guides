package zx.soft.restlet.tutorial;

import java.io.IOException;

import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Status;
import org.restlet.resource.ClientResource;

/**
 * 向HTTP服务器认证
 * @author wgybzb
 *
 */
public class Part09b_AuthenticateClient {

	public static void main(String[] args) throws IOException {

		// 准备请求
		ClientResource resource = new ClientResource("http://localhost:8111/");

		// 在请求调用中增加客户端认证
		ChallengeScheme scheme = ChallengeScheme.HTTP_BASIC;
		ChallengeResponse authentication = new ChallengeResponse(scheme, "wgybzb", "123456");
		resource.setChallengeResponse(authentication);

		// 发送HTTP GET请求
		resource.get();

		if (resource.getStatus().isSuccess()) {
			// 输出请求响应数据到控制台
			resource.getResponseEntity().write(System.out);
		} else if (resource.getStatus().equals(Status.CLIENT_ERROR_UNAUTHORIZED)) {
			System.out.println("服务器认证失败！");
		} else {
			System.out.println("非正常状态返回： " + resource.getStatus());
		}
	}

}
