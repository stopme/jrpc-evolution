package com.chenkangxian.rpc.impl;  
  
/** 
 * 
 * @Author: chenkangxian 
 * 
 * @Annotation: ����keyȡ���ݷ���ʵ�� 
 * 
 * @Date:2012-5-13 
 * 
 * @Copyright: 2012 chenkangxian, All rights reserved. 
 * 
 */  
public class DataServiceImpl implements DataService {  
  
    public String getData(String key) {  
          
        return "this is the data when key = " + key ;  
    }  
  
}  