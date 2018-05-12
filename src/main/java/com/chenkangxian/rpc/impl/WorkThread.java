/** 
 *  
 */  
package com.chenkangxian.rpc.impl;  
  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.lang.reflect.Method;  
import java.net.Socket;  
  
/** 
 * 
 * @Author: chenkangxian 
 * 
 * @Annotation: 工作线程 
 * 
 * @Date:2012-5-15 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 *  
 */  
public class WorkThread implements Runnable {  
      
    private Object service;  
      
    private Socket socket;  
      
    public WorkThread(Object service,Socket socket){  
        this.service = service;  
        this.socket = socket;  
    }  
  
    @Override  
    public void run() {  
        try {  
            try {  
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  
                try {  
                    String methodName = input.readUTF();  
                    Class<?>[] parameterTypes = (Class<?>[])input.readObject();  
                    Object[] arguments = (Object[])input.readObject();  
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  
                    try {  
                        Method method = service.getClass().getMethod(methodName, parameterTypes);  
                        Object result = method.invoke(service, arguments);  
                        output.writeObject(result);  
                    } catch (Throwable t) {  
                        output.writeObject(t);  
                    } finally {  
                        output.close();  
                    }  
                } finally {  
                    input.close();  
                }  
            } finally {  
                socket.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
} 