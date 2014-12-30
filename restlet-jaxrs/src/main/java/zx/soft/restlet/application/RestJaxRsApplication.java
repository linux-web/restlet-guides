package zx.soft.restlet.application;

import org.restlet.Context;
import org.restlet.ext.jaxrs.JaxRsApplication;

public class RestJaxRsApplication extends JaxRsApplication {

	public RestJaxRsApplication(Context context) {
		super(context);
		this.add(new MyApplication());
	}

}
