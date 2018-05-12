package com.chenkangxian.rpc.impl;  
  
import java.lang.reflect.Proxy;  
import java.net.ServerSocket;  
import java.net.Socket;  
  
/** 
 * 
 * @Author: chenkangxian 
 * 
 * @Annotation: 简单的远程调用框架实现 
 * 
 * @Date:2012-5-13 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 * 
 */  
public class RpcFramework {  
      
    /** 
     * 暴露服务 
     *  
     * Author: chenkangxian 
     * 
     * Last Modification Time: 2012-5-15 
     * 
     * @param service 服务实现 
     * @param port 服务端口 
     * @throws Exception 
     */  
    public static void export(final Object service, int port) throws Exception {  
        if (service == null)  
            throw new IllegalArgumentException("service instance == null");  
        if (port <= 0 || port > 65535)  
            throw new IllegalArgumentException("Invalid port " + port);  
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);  
        ServerSocket server = new ServerSocket(port);  
        for(;;) {  
            try {  
                final Socket socket = server.accept();  
                ThreadPoolHelp.getExecutorInstance().execute(new WorkThread(service, socket));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    /** 
     * 引用服务 
     *  
     * Author: chenkangxian 
     * 
     * Last Modification Time: 2012-5-15 
     * 
     * @param <T> 接口泛型 
     * @param interfaceClass 接口类型 
     * @param host 服务器主机名 
     * @param port 服务器端口 
     * @return 远程服务 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {  
          
        if (interfaceClass == null)  
            throw new IllegalArgumentException("Interface class == null");  
        if (! interfaceClass.isInterface())  
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");  
        if (host == null || host.length() == 0)  
            throw new IllegalArgumentException("Host == null!");  
        if (port <= 0 || port > 65535)  
            throw new IllegalArgumentException("Invalid port " + port);  
          
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);  
          
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationProxy(host,port));  
    }  
  
}  