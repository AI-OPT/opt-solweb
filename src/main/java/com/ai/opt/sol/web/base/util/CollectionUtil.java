package com.ai.opt.sol.web.base.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CollectionUtil {
    
    public static boolean isEmpty(Collection collection) {
        if (null == collection) {
            return true;
        } else {
            return collection.isEmpty();
        }
    }
    
    public static boolean isEmpty(Object[] objects){
        return (objects==null || objects.length==0)?true:false;
    }
    
    /**
     * 数组转换为List
     * @param arr
     * @return
     */
    public static List arrayToList(Object[] arr) { 
            List list = new ArrayList(); 
            if (arr == null) return list; 
            list = Arrays.asList(arr); 
            return list; 
    }

}
