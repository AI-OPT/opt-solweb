package com.ai.opt.sol.core.sandbox.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.opt.sol.core.util.APISearchUtil;
import com.ai.runner.apicollector.vo.APIClassDoc;
import com.ai.runner.apicollector.vo.APIClassFieldDoc;
import com.ai.runner.base.exception.SystemException;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ReflectUtil {

	// 存储参数类型的值是否转换成Map集合
	private static Map<String, Boolean> type2Map = new HashMap<String, Boolean>();

	static {
		type2Map.put(String.class.getName(), false);
		type2Map.put(Long.class.getName(), false);
		type2Map.put(Integer.class.getName(), false);
		type2Map.put(Boolean.class.getName(), false);
		type2Map.put(Double.class.getName(), false);
		type2Map.put(Character.class.getName(), false);
		type2Map.put(Byte.class.getName(), false);
		type2Map.put(Short.class.getName(), false);
		type2Map.put(Timestamp.class.getName(), false);
		type2Map.put("boolean", false);
		type2Map.put("byte", false);
		type2Map.put("char", false);
		type2Map.put("double", false);
		type2Map.put("float", false);
		type2Map.put("int", false);
		type2Map.put("long", false);
		type2Map.put("short", false);
		type2Map.put("void", false);
		type2Map.put(HashMap.class.getName(), true);
		type2Map.put(Map.class.getName(), true);
		type2Map.put(List.class.getName(), true);
		type2Map.put(ArrayList.class.getName(), true);
	}

	/**
	 * 参数类型是否转换成MAP
	 * 
	 * @param pType
	 * @return
	 * @author zhangchao
	 */
	public static boolean checkReflectMap(String pType) {
		return type2Map.containsKey(pType) ? type2Map.get(pType) : true;
	}

	/**
	 * 根据类型转换成值
	 * 
	 * @param pType
	 * @param pValue
	 * @return
	 * @author zhangchao
	 */
	private static Object reflect(String pType, String pValue) {
		boolean transfer = checkReflectMap(pType);
		if (StringUtil.isBlank(pValue))
			return null;
		Object obj = null;
		if (transfer) {
			obj = JSON.parseObject(pValue, Map.class);
		} else {
			obj = pValue;
		}
		return obj;
	}

	/**
	 * 将参数值按照要求映射转换成MAP
	 * 
	 * @param pTypes
	 * @param pValues
	 * @return
	 * @author zhangchao
	 */
	public static Object[] reflects(String[] pTypes, String[] pValues) {
		if (pTypes == null || pTypes.length == 0)
			return null;
		if (pValues == null || pValues.length == 0) {
			throw new SystemException("parameter missing:values length is 0");
		}
		if (pTypes.length != pValues.length) {
			throw new SystemException("parameter missing:values length is " + pValues.length
					+ ",but parameters length is " + pTypes.length);
		}
		Object[] objs = new Object[pTypes.length];
		int index = 0;
		for (String pType : pTypes) {
			String pValue = pValues[index];
			Object v = reflect(pType, pValue);
			objs[index] = v;
			index++;
		}
		return objs;
	}

	/**
	 * 返回数据
	 * 
	 * @param indexId
	 *            从APIDoc中InApiParam中获取的id
	 * @author gaogang
	 * @ApiDocMethod
	 * @ApiCode
	 */
	public static Map<Object, Object> reflectClass(String indexId) {
		Map<Object, Object> map = new HashMap<Object, Object>();

		// 获得APIClassDoc
		APIClassDoc doc = JSONObject.parseObject(APISearchUtil.getAPIClassDetail(indexId), APIClassDoc.class);
		if (null != doc) {
			List<APIClassFieldDoc> flist = doc.getClassFields();

			for (APIClassFieldDoc fdoc : flist) {
				if (fdoc.isCanUnfold()) {
					// 递归遍历（请求subclassfield接口）
					String id = String.valueOf(fdoc.getId());
					// APIClassDoc doc = JSONObject.parseObject(id,
					// APIClassDoc.class);
					// Map<Object,Object> subMap=new HashMap<Object,Object>();
					if (fdoc.getClassName().equals(HashMap.class.getName())) {
						map.put(fdoc.getParamName(), new Object());
					} else if (fdoc.getClassName().equals(Map.class.getName())) {
						map.put(fdoc.getParamName(), new Object());
					} else if (fdoc.getClassName().equals(List.class.getName())) {
						map.put(fdoc.getParamName(), new String[] {});
					} else if (fdoc.getClassName().equals(ArrayList.class.getName())) {
						map.put(fdoc.getParamName(), new String[] {});
					} else {
						map.put(fdoc.getClassName().substring(fdoc.getClassName().lastIndexOf(".") + 1),
								reflectClass(id));
					}

				} else {// 组装数据
					// 根据类型的多种情况

					// if else 和switch的效率
					if (fdoc.getClassName().equals(String.class.getName())) {
						map.put(fdoc.getParamName(), "");
					} else if (fdoc.getClassName().equals(Long.class.getName())) {
						map.put(fdoc.getParamName(), 0L);
					} else if (fdoc.getClassName().equals(Integer.class.getName())) {
						map.put(fdoc.getParamName(), 0);
					} else if (fdoc.getClassName().equals(Boolean.class.getName())) {
						map.put(fdoc.getParamName(), false);
					} else if (fdoc.getClassName().equals(Character.class.getName())) {
						map.put(fdoc.getParamName(), "");
					} else if (fdoc.getClassName().equals(Byte.class.getName())) {
						map.put(fdoc.getParamName(), "");
					} else if (fdoc.getClassName().equals(Short.class.getName())) {
						map.put(fdoc.getParamName(), "");
					} else if (fdoc.getClassName().equals(boolean.class.getName())) {
						map.put(fdoc.getParamName(), false);
					} else if (fdoc.getClassName().equals(byte.class.getName())) {
						map.put(fdoc.getParamName(), "");
					} else if (fdoc.getClassName().equals(double.class.getName())) {
						map.put(fdoc.getParamName(), 0L);
					} else if (fdoc.getClassName().equals(float.class.getName())) {
						map.put(fdoc.getParamName(), 0.0);
					} else if (fdoc.getClassName().equals(int.class.getName())) {
						map.put(fdoc.getParamName(), 0);
					} else if (fdoc.getClassName().equals(long.class.getName())) {
						map.put(fdoc.getParamName(), 0);
					} else if (fdoc.getClassName().equals(short.class.getName())) {
						map.put(fdoc.getParamName(), 0);
					} else if (fdoc.getClassName().equals(Timestamp.class.getName())) {
						map.put(fdoc.getParamName(), DateUtil.getSysDate());
					}

				}
			}
		}

		return map;

	}
}
