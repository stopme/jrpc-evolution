/** 
 *  
 */  
package com.chenkangxian.rpc.impl;  
  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.lang.reflect.InvocationHandler;  
import java.lang.reflect.Method;  
import java.net.Socket;  
  
/** 
 * @Author: chenkangxian 
 * 
 * @Annotation: Ö´ÐÐ´úÀí 
 * 
 * @Date:2012-5-15 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 *  
 */  
public class InvocationProxy implements InvocationHandler{  
  
    private String host;  
    private int port;  
  
    public InvocationProxy(String host, int port){  
        this.host = host;  
        this.port = port;  
    }  
  
    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {  
        Socket socket = new Socket(host, port);  
        try {  
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  
            try {  
                output.writeUTF(method.getName());  
                output.writeObject(method.getParameterTypes());  
                output.writeObject(arguments);  
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  
                try {  
                    Object result = input.readObject();  
                    if (result instanceof Throwable) {  
                        throw (Throwable) result;  
                    }  
                    return result;  
                } finally {  
                    input.close();  
                }  
            } finally {  
                output.close();  
            }  
        } finally {  
            socket.close();  
        }  
    }  
} 