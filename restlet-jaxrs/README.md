
本项目实现了一个关于RESTLET接口的小例子。

　　Restlet的思想是：HTTP客户端与HTTP服务器之间的差别，对架构来说无所谓。一个软件应可以既充当Web客户端又充当Web服务器，而无须采用两套完全不同的APIs。

　　Restlet提供了多个版本:Java SE、Java EE、android、Google AppEngine、Google Web Toolkit、Android。这里我们使用的是jee版本。

　　RESTLET的实现可以采用JAX-RS方式，也可以采用其他方式。

本例子是采用JAX-RS的API开发的，这种方式提供了一种基于注解的模型来描述分布式资源，可以利用注解的功能提供资源的位置、传递等。可以在一个Resource类中同时对外提供多个rest接口服务。

在类的上面标注的PATH注解，作为全局的一个路径变量，方法上部的PATH注解都以类注解作为相对路径。并且，Produce表示返回的数据格式，此处是JSON类型。至于GET和POST，可以简单的理解为：

   POST   /uri     创建 

   DELETE /uri/xxx 删除

   PUT    /uri/xxx 更新或创建  

   GET    /uri/xxx 查看 
   
###### 运行

服务端启动后：

八月 27, 2014 3:51:43 下午 org.restlet.engine.connector.NetServerHelper start
信息: Starting the internal [HTTP/1.1] server on port 8082
The restlet server started ...

客户端启动后的客户端信息：

八月 27, 2014 3:52:10 下午 org.restlet.engine.connector.HttpClientHelper start
信息: Starting the internal HTTP client
Student get {"sex":0,"grade":3,"name":"Scott","id":1,"age":18}
八月 27, 2014 3:52:10 下午 org.restlet.engine.connector.HttpClientHelper start
信息: Starting the internal HTTP client
Student update 1
八月 27, 2014 3:52:10 下午 org.restlet.engine.connector.HttpClientHelper start
信息: Starting the internal HTTP client
Teacher get {"subject":"MATH","sex":1,"name":"Toney","id":1,"age":27}
八月 27, 2014 3:52:10 下午 org.restlet.engine.connector.HttpClientHelper start
信息: Starting the internal HTTP client
Teacher update 1

客户端启动的服务端信息：

八月 27, 2014 3:51:43 下午 org.restlet.engine.connector.NetServerHelper start
信息: Starting the internal [HTTP/1.1] server on port 8082
The restlet server started ...
八月 27, 2014 3:52:10 下午 org.restlet.engine.log.LogFilter afterHandle
信息: 2014-08-27	15:52:10	127.0.0.1	-	-	8082	GET	/TestRestlet/student/1/json	-	200	-	0	22	http://localhost:8082	Restlet-Framework/2.2.0	-
八月 27, 2014 3:52:10 下午 org.restlet.engine.log.LogFilter afterHandle
信息: 2014-08-27	15:52:10	127.0.0.1	-	-	8082	PUT	/TestRestlet/student/update	-	200	1	39	13	http://localhost:8082	Restlet-Framework/2.2.0	-
八月 27, 2014 3:52:10 下午 org.restlet.engine.log.LogFilter afterHandle
信息: 2014-08-27	15:52:10	127.0.0.1	-	-	8082	GET	/TestRestlet/teacher/1/json	-	200	-	0	1	http://localhost:8082	Restlet-Framework/2.2.0	-
八月 27, 2014 3:52:10 下午 org.restlet.engine.log.LogFilter afterHandle
信息: 2014-08-27	15:52:10	127.0.0.1	-	-	8082	PUT	/TestRestlet/teacher/update	-	200	1	47	1	http://localhost:8082	Restlet-Framework/2.2.0	-


