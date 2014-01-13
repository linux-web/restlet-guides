package cc.pp.restlet.tutorial;

public class OrdersResource extends UserResource {

	@Override
	public String toString() {
		return "用户的顺序是： " + this.userName;
	}

}
