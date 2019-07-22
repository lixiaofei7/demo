//package com.pandora.boot.demo.rabbit;
//
//import com.rabbitmq.client.*;
//import java.io.IOException;
//
//public class RabbitMqUtils {
//
//    public static volatile ConnectionFactory connectionFactory = null;
//
//    private final static String EXCHANGE_NAME = "test_exchange_fanout";
//
//    private final static String EXCHANGE_TYPE = "fanout";
//
//    public static ConnectionFactory getConnectionFactory(){
//        if (null == connectionFactory) {
//            synchronized (RabbitMqUtils.class) {
//                if (null == connectionFactory) {
//                    connectionFactory = new ConnectionFactory();
//                    //"localhost", 5672
//                    connectionFactory.setHost("localhost");
//                    connectionFactory.setPort(5672);
//                    connectionFactory.setUsername("guest");
//                    connectionFactory.setPassword("guest");
//                    connectionFactory.setVirtualHost("/");
//                }
//            }
//        }
//        return connectionFactory;
//    }
//
//    /**
//     * 发送消息
//     * @param message
//     */
//    public static void sendMsg(String message){
//        try {
//            //获取连接
//            Connection connection = getConnectionFactory().newConnection();
//            //获取通道
//            Channel channel = connection.createChannel();
//            //声明交换机（分发:发布/订阅模式）
//            channel.exchangeDeclare(EXCHANGE_NAME,EXCHANGE_TYPE);
//            //发送消息
//            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("utf-8"));
//            close(connection,channel);
//            System.out.println("发送消息：("+message+")成功");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 订阅消息
//     * @param consumerKey
//     * @return
//     */
//    public static Channel sub(String consumerKey){
//        Channel channel = null;
//        try {
//            //获取连接
//            Connection connection = getConnectionFactory().newConnection();
//            //获取通道
//            channel = connection.createChannel();
//            //声明交换机（分发:发布/订阅模式）
//            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
//            //声明队列
//            channel.queueDeclare(consumerKey, false, false, false, null);
//            //将队列绑定到交换机
//            channel.queueBind(consumerKey, EXCHANGE_NAME, "");
//            //保证一次只分发一个
//            int prefetchCount = 1;
//            channel.basicQos(prefetchCount);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return channel;
//    }
//
//    /**
//     * 消费消息
//     * @param channel
//     */
//    public static void consumerMsg(Channel channel,String consumerKey){
//        //定义消费者
//        DefaultConsumer consumer = new DefaultConsumer(channel)
//        {
//            //当消息到达时执行回调方法
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
//                                       byte[] body) throws IOException
//            {
//                String message = new String(body, "utf-8");
//                System.out.println(consumerKey+" Receive message：" + message);
//                try
//                {
//                    //消费者休息1s处理业务
//                    Thread.sleep(1000);
//                }
//                catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//                finally
//                {
//                    //手动应答
//                    channel.basicAck(envelope.getDeliveryTag(), false);
//                }
//            }
//        };
//        //设置手动应答
//        boolean autoAck = false;
//        try{
//            //监听队列
//            channel.basicConsume(consumerKey, autoAck, consumer);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 关闭连接
//     * @param connection
//     * @param channel
//     */
//    public static void close(Connection connection,Channel channel){
//        if(null != channel){
//            try {
//                channel.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        if(null != connection){
//            try {
//                connection.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
