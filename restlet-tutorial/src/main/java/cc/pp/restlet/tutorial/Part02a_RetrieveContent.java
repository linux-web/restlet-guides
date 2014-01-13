package cc.pp.restlet.tutorial;

import java.io.IOException;

import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * 提取网页内容
 * @author wgybzb
 *
 */
public class Part02a_RetrieveContent {

	public static void main(String[] args) throws ResourceException, IOException {

		// 输出网页内容
		new ClientResource("http://www.restlet.org").get().write(System.out);

	}

}
