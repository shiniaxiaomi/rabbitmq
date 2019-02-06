package com.lyj;

import com.lyj.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	//使用amqpAdmin创建交换器，队列和绑定规则
	@Test
	public void createExchange(){
		//创建点对点的交换器
//		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));

		//创建队列
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));

		//创建交换器和队列的绑定规则
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,
						"amqpAdmin.exchange","amqp.haha",null));
	}

	/**
	 * 1.单播（点对点）
	 */
	@Test
	public void contextLoads() {
		//message需要自己构造一个；定义消息体内容和消息头
		//rabbitTemplate.send(exchange,routeKey,message);

		//只需要传入要发送的对象，自动序列化发送给rabbitmq（object默认当成消息体）
		//rabbitTemplate.convertAndSend(exchange,routeKey,object);

		Map<String,Object> map=new HashMap<>();
		map.put("msg","这是第一个消息！");
		map.put("data", Arrays.asList("helloWorld",123,true));

		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
	}


	@Test
	public void test02() {
//		rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("西游记","吴承恩"));
		rabbitTemplate.convertAndSend("exchange.direct","atguigu",
				new Book("西游记","吴承恩"));
	}

	//接受数据
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
		System.out.println(o.getClass());
		System.out.println(o);

	}

	/**
	 * 2.广播
	 */
	@Test
	public void sendMsg(){
		//发送给广播模式的交换器，广播模式是不需要路由键的
		rabbitTemplate.convertAndSend("exchange.fanout","",
				new Book("红楼梦","曹雪芹"));
	}

}

