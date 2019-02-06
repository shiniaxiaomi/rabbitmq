package com.lyj;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 自动配置
 * 	1.RabbitAutoConfiguration(Rabbitmq的自动配置类)
 * 	2.有自动配置了连接工厂 CachingConnectionFactory
 * 	3.RabbitProperties 封装了rabbitmq的所有配置
 * 	4.RabbitTemplate: 给rabbitmq发送和接受消息
 * 	5.AmqpAdmin: rabbitmq系统管理功能组件
 * 		AmqpAdmin：用于创建和删除 Queue，Exchange，Binding
 * 	6.@EnableRabbit + @RabbitListener ：监听消息队列中的内容
 */

@EnableRabbit	//开启基于注解的rabbitmq模式
@SpringBootApplication
public class RabbitmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}

