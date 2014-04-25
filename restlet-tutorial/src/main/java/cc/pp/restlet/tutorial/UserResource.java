package cc.pp.restlet.tutorial;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import cc.pp.restlet.tutorial.domain.User;

public class UserResource extends ServerResource {

	String userName;

	Object user;

	@Override
	public void doInit() {
		this.userName = (String) getRequestAttributes().get("user");
		this.user = null;
	}

	@Get
	public Object getData() {
		//		return "用户的帐号为：" + this.userName;
		User user = new User();
		user.setId(123);
		user.setName("wgybzb");
		return user;
	}

}
