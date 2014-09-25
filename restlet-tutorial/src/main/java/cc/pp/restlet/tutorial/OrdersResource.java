package cc.pp.restlet.tutorial;

import org.restlet.resource.Get;

public class OrdersResource extends UserResource {

	@Override
	@Get
	public String toString() {
		return "用户的顺序是： " + this.userName;
	}

}
