package com.lyj.service;

import com.lyj.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2019/2/6.
 */

@Service
public class BookService {

    //监听rabbitmq中的指定队列，接受book消息
    @RabbitListener(queues = "atguigu.news")
    public void recieve(Book book){
        System.out.println(book);
    }

    //在参数列表接受Message，可以获取到消息的头信息
    @RabbitListener(queues = "atguigu")
    public void recieve02(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }



}
