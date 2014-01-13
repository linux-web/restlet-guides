package cc.pp.restlet.tutorial;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class UserResource extends ServerResource {

	String userName;

	Object user;

	@Override
	public void doInit() {
		this.userName = (String) getRequestAttributes().get("user");
		this.user = null;
	}

	@Override
	@Get
	public String toString() {
		return "用户的帐号为：" + this.userName;
	}

}
