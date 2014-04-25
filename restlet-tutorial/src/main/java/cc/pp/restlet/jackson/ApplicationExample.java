package cc.pp.restlet.jackson;

import java.util.List;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.engine.Engine;
import org.restlet.engine.converter.ConverterHelper;
import org.restlet.ext.jackson.JacksonConverter;
import org.restlet.routing.Router;

import cc.pp.restlet.tutorial.UserResource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationExample extends Application {

	/**
	 * 访问示例：
	 *  1、http://localhost:8111/users/wgybzb
	 *  2、http://localhost:8111/users/wgybzb/orders
	 *  3、http://localhost:8111/users/wgybzb/orders/wanggang
	 */
	public static void main(String[] args) throws Exception {

		Component component = new Component();
		component.getServers().add(Protocol.HTTP, 8111);

		Application application = new ApplicationExample();

		component.getDefaultHost().attachDefault(application);
		component.start();
	}

	@Override
	public Restlet createInboundRoot() {
		Router router = new Router(getContext());
		// Defines only one route
		router.attach("/users/{user}", UserResource.class);
		router.setRoutingMode(Router.MODE_BEST_MATCH);

		// add the GWT mapping in the metadata service
		getMetadataService().setDefaultMediaType(MediaType.APPLICATION_JSON);
		getMetadataService().addExtension("gwt", MediaType.APPLICATION_JAVA_OBJECT_GWT);

		// replace the OOTB JacksonConverter with the a converter that will work with the objectify Key class
		replaceConverter(JacksonConverter.class, new FixedJacksonConverter());
		replaceConverter(JacksonConverter.class, new LocalJacksonConverter(new ObjectMapper()));


		return router;
	}

	/**
	* Registers a new converter with the Restlet engine, after removing the first
	* registered converter of the given class.
	*
	* Taken from:
	* http://restlet.tigris.org/ds/viewMessage.do?dsForumId=4447&dsMessageId=2716118
	*/
	static void replaceConverter(Class<? extends ConverterHelper> converterClass, ConverterHelper newConverter) {

		ConverterHelper oldConverter = null;
		List<ConverterHelper> converters = Engine.getInstance().getRegisteredConverters();
		for (ConverterHelper converter : converters) {
			if (converter.getClass().equals(converterClass)) {
				converters.remove(converter);
				oldConverter = converter;
				break;
			}
		}

		converters.add(newConverter);
		if (oldConverter == null) {
			System.err.println("Added Converter to Restlet Engine: " + newConverter.getClass().getName());
		} else {
			System.err.println("Replaced Converter " + oldConverter.getClass().getName() + " with "
					+ newConverter.getClass().getName() + " in Restlet Engine");
		}
	}
}
