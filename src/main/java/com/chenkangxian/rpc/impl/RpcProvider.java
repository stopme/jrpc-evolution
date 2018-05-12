package com.chenkangxian.rpc.impl;  
  
/** 
 * @Author: chenkangxian 
 * 
 * @Annotation: 服务提供者 
 * 
 * @Date:2012-5-13 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 * 
 */  
public class RpcProvider {  
  
    public static void main(String[] args) throws Exception {  
        DataService service = new DataServiceImpl();  
        RpcFramework.export(service, 1234);  
    }  
  
}  