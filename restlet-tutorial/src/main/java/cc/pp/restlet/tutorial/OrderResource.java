package cc.pp.restlet.tutorial;

import org.restlet.resource.Get;

public class OrderResource extends UserResource {

	String orderId;

	Object order;

	@Override
	public void doInit() {
		super.doInit();
		this.orderId = (String) getRequestAttributes().get("order");
		this.order = null;
	}

	@Override
	@Get
	public String toString() {
		return "Order \"" + this.orderId + "\" for user \"" + this.userName + "\"";
	}

}
