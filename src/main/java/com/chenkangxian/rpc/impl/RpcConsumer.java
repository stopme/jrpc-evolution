package com.chenkangxian.rpc.impl;  
  
/** 
 * @Author: chenkangxian 
 * 
 * @Annotation: 服务消费者 
 * 
 * @Date:2012-5-13 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 * 
 */  
public class RpcConsumer {  
      
    public static void main(String[] args) throws Exception {  
        DataService service = RpcFramework.refer(DataService.class, "127.0.0.1", 1234);  
        for (int i = 0; i < Integer.MAX_VALUE; i ++) {  
            String value = service.getData("key_" + i);  
            System.out.println(value);  
            Thread.sleep(1000);  
        }  
    }  
      
}  