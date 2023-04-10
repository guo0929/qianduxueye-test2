package com.douding.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigServer
//@EnableEurekaClient
public class  ConfigApplication {

//	1.添加logback.xml 后加载log日志
	private static final Logger LOG = LoggerFactory.getLogger(ConfigApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ConfigApplication.class);
		Environment env = app.run(args).getEnvironment();
		LOG.info("启动成功！！");
		LOG.info("Config: \thttp://localhost:{}", env.getProperty("server.port"));
	}

}
