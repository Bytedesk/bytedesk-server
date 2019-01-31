## Overview

A sample demonstrating capabilities in the Spring Framework to build WebSocket-style messaging applications. The application uses [STOMP](http://stomp.github.io/) (over WebSocket) for messaging between browsers and server and [SockJS](https://github.com/sockjs/sockjs-protocol) for WebSocket fallback options.

Client-side libraries used:
* [stomp-websocket](https://github.com/jmesnil/stomp-websocket/)
* [sockjs-client](https://github.com/sockjs/sockjs-client)
* [Twitter Bootstrap](http://twitter.github.io/bootstrap/)
* [Knockout.js](http://knockoutjs.com/)

Server-side runs on Tomcat, Jetty, WildFly, Glassfish, and other Servlet 3.0+ containers with WebSocket support.

### Tomcat

For Tomcat, set `TOMCAT8_HOME` as an environment variable and use [deployTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/deployTomcat8.sh) and [shutdownTomcat8.sh](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/shutdownTomcat8.sh) in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-portfolio/index.html>

### Jetty 9

The easiest way to run on Jetty 9 is `mvn jetty:run`.

Open a browser and go to <http://localhost:8080/spring-websocket-portfolio/index.html>

**Note:** To deploy to a Jetty installation, add this to Jetty's `start.ini`:

    OPTIONS=plus
    etc/jetty-plus.xml
    OPTIONS=annotations
    etc/jetty-annotations.xml

### Glassfish

After unzipping Glassfish 4 start the server:

    <unzip_dir>/glassfish4/bin/asadmin start-domain

Set `GLASSFISH4_HOME` as an environment variable and use [deployGlassfish.sh](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/deployGlassfish.sh) in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-portfolio/index.html>

### WildFly/Undertow

Unzip the WildFly server.

Set `WILDFLY_HOME` as an environment variable and use [deployWildFly.sh](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/deployWildFly.sh) in this directory.

Open a browser and go to <http://localhost:8080/spring-websocket-portfolio/index.html>


### Using a Message Broker

Out of the box, a _"simple" message broker_ is used to send messages to subscribers (e.g. stock quotes) but you can optionally use a fully featured STOMP message broker such as `RabbitMQ`, `ActiveMQ`, and others, by following these steps:

1.   Install and start the message broker. For RabbitMQ make sure you've also installed the [RabbitMQ STOMP plugin](http://www.rabbitmq.com/stomp.html). For ActiveMQ you need to configure a [STOMP transport connnector](http://activemq.apache.org/stomp.html).
2.   Use the `MessageBrokerConfigurer` in [WebSocketConfig.java](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/src/main/java/org/springframework/samples/portfolio/config/WebSocketConfig.java) to enable the STOMP broker relay instead of the simple broker.
3.   You may also need to configure additional STOMP broker relay properties such as `relayHost`, `relayPort`, `systemLogin`, `systemPassword`, depending on your message broker. The default settings should work for RabbitMQ and ActiveMQ.


### Logging

To see all logging, enable TRACE for `org.springframework.messaging` and `org.springframework.samples` in [log4j.xml](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/src/main/resources/log4j.xml).

Keep in mind that will generate a lot of information as messages flow through the application. The [QuoteService](https://github.com/rstoyanchev/spring-websocket-portfolio/blob/master/src/main/java/org/springframework/samples/portfolio/service/QuoteService.java) for example generates a lot of messages frequently. You can modify it to send quotes less frequently or simply comment out the `@Scheduled` annotation.





