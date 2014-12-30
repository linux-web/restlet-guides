package zx.soft.restlet.tutorial;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * 提取网页的内容（详细的）
 * @author wgybzb
 *
 */
public class Part02b_RetrieveContent {

	public static void main(String[] args) throws ResourceException, IOException {

		// 创建客户端资源
		ClientResource resource = new ClientResource("http://www.restlet.org");

		// 自定义推荐（referrer）属性
		resource.setReferrerRef("http://www.mysite.org");

		// 将响应的实体写到控制台
		resource.get().write(System.out);
	}

}
