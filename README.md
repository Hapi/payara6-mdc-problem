# General

This project indicates an MDC problem with Payara6 2023-02 version.


# Build

```sh
mvn clean package
```

# Problem

If an application uses MDC (org.slf4j.MDC), it fails.


# Steps to reproduce

1. Use Java 17
2. Install Payara6 2023-2
3. asadmin start-domain
4. Build the test application
5. Deploy the test application to Payara's autodeploy folder (domain1)
6. Execute this command:

    ```sh
    curl http://localhost:8080/payara6-mdc-problem/send
    ```

7. Check Payara's `server.log`. There will be a stack trace similar to this:

    ```sh
    [2023-02-23T13:22:29.159+0200] [Payara 6.2023.2] [WARNING] [] [org.glassfish.jersey.server.ServerRuntime$Responder] [tid: _ThreadID=118 _ThreadName=http-thread-pool::http-listener-1(5)] [timeMillis: 1677151349159] [levelValue: 900] [[
    An exception mapping did not successfully produce and processed a response. Logging the exception propagated to the default exception mapper.
    java.lang.ExceptionInInitializerError
        at org.slf4j.MDC.bwCompatibleGetMDCAdapterFromBinder(MDC.java:99)
        at org.slf4j.MDC.<clinit>(MDC.java:108)
        at fish.payara6.mdcproblem.TestService.send(TestService.java:22)
        at fish.payara6.mdcproblem.TestService$Proxy$_$$_WeldClientProxy.send(Unknown Source)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at org.glassfish.jersey.server.model.internal.ResourceMethodInvocationHandlerFactory.lambda$static$0(ResourceMethodInvocationHandlerFactory.java:52)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher$1.run(AbstractJavaResourceMethodDispatcher.java:134)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.invoke(AbstractJavaResourceMethodDispatcher.java:177)
        at org.glassfish.jersey.server.model.internal.JavaResourceMethodDispatcherProvider$TypeOutInvoker.doDispatch(JavaResourceMethodDispatcherProvider.java:219)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.dispatch(AbstractJavaResourceMethodDispatcher.java:81)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:478)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:400)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:81)
        at org.glassfish.jersey.server.ServerRuntime$1.run(ServerRuntime.java:260)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:248)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:244)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:292)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:274)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:244)
        at org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:265)
        at org.glassfish.jersey.server.ServerRuntime.process(ServerRuntime.java:239)
        at org.glassfish.jersey.server.ApplicationHandler.handle(ApplicationHandler.java:697)
        at org.glassfish.jersey.servlet.WebComponent.serviceImpl(WebComponent.java:394)
        at org.glassfish.jersey.servlet.WebComponent.service(WebComponent.java:346)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:357)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:311)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:205)
        at org.apache.catalina.core.StandardWrapper.service(StandardWrapper.java:1569)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:259)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:166)
        at org.apache.catalina.core.StandardPipeline.doInvoke(StandardPipeline.java:757)
        at org.apache.catalina.core.StandardPipeline.invoke(StandardPipeline.java:577)
        at com.sun.enterprise.web.WebPipeline.invoke(WebPipeline.java:99)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:158)
        at org.apache.catalina.connector.CoyoteAdapter.doService(CoyoteAdapter.java:372)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:239)
        at com.sun.enterprise.v3.services.impl.ContainerMapper$HttpHandlerCallable.call(ContainerMapper.java:520)
        at com.sun.enterprise.v3.services.impl.ContainerMapper.service(ContainerMapper.java:217)
        at org.glassfish.grizzly.http.server.HttpHandler.runService(HttpHandler.java:174)
        at org.glassfish.grizzly.http.server.HttpHandler.doHandle(HttpHandler.java:153)
        at org.glassfish.grizzly.http.server.HttpServerFilter.handleRead(HttpServerFilter.java:196)
        at org.glassfish.grizzly.filterchain.ExecutorResolver$9.execute(ExecutorResolver.java:88)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeFilter(DefaultFilterChain.java:246)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeChainPart(DefaultFilterChain.java:178)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.execute(DefaultFilterChain.java:118)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.process(DefaultFilterChain.java:96)
        at org.glassfish.grizzly.ProcessorExecutor.execute(ProcessorExecutor.java:51)
        at org.glassfish.grizzly.nio.transport.TCPNIOTransport.fireIOEvent(TCPNIOTransport.java:510)
        at org.glassfish.grizzly.strategies.AbstractIOStrategy.fireIOEvent(AbstractIOStrategy.java:82)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy.run0(WorkerThreadIOStrategy.java:83)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy$WorkerThreadRunnable.run(WorkerThreadIOStrategy.java:101)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.doWork(AbstractThreadPool.java:535)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.run(AbstractThreadPool.java:515)
        at java.base/java.lang.Thread.run(Thread.java:833)
    Caused by: java.lang.UnsupportedOperationException: This code should never make it into the jar
        at org.slf4j.impl.StaticMDCBinder.<init>(StaticMDCBinder.java:43)
        at org.slf4j.impl.StaticMDCBinder.<clinit>(StaticMDCBinder.java:40)
        ... 57 more
    ]]
    ```

# Changing classloading order

We are aware that `<class-loader delegate="false" />` removes the issue in this simple case but in the real applications
changing the class loading order is not wanted (i.e. it must be `<class-loader delegate="true" />`).

