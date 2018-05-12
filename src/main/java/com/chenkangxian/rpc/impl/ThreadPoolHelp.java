/** 
 *  
 */  
package com.chenkangxian.rpc.impl;  
  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
  
/** 
 * @Author: chenkangxian 
 * 
 * @Annotation:  œﬂ≥Ã≥ÿ∞Ô÷˙¿‡ 
 * 
 * @Date:2012-5-15 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 *  
 */  
public class ThreadPoolHelp {  
  
    private static ExecutorService executor ;  
      
    static{  
        executor = Executors.newFixedThreadPool(20);  
    }  
      
    public static ExecutorService getExecutorInstance(){  
        return executor;  
    }  
} 