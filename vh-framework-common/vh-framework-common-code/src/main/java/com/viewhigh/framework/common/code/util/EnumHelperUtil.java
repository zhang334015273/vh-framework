/**
 * 版权所属：东软望海科技有限公司
 * 作者：赵鹏程
 * 版本：V1.0
 * 创建日期：2018年5月24日
 * 修改日期：2018年5月24日
 */
package com.viewhigh.framework.common.code.util;

import java.lang.reflect.Method;

/**
 * 此帮助类严格限定为有code和info的枚举类
 * 
 * @Description 枚举帮助类 根据不同的枚举code 返回不同的枚举名
 * @author zhaopc
 * @version v1.0
 * @since 2018年5月24日
 */
public class EnumHelperUtil {
	/**
	 * 使用枚举类型对应的typeCode获取枚举类型 T
	 * 
	 * @param clazz
	 *            枚举类的class
	 * @param getTypeCodeMethodName
	 *            传入的typeCode的get方法
	 * @param typeCode
	 *            传入的typeCode值，这个方法为Integer类型
	 * @return
	 * @throws Exception
	 */
	public static <T extends Enum<T>> T getByIntegerTypeCode(Class<T> clazz, String getTypeCodeMethodName,
			Integer typeCode) throws Exception {
		T result = null;
		T[] arr = clazz.getEnumConstants();
		Method targetMethod = clazz.getDeclaredMethod(getTypeCodeMethodName);
		Integer typeCodeVal = null;
		for (T entity : arr) {
			typeCodeVal = Integer.valueOf(targetMethod.invoke(entity).toString());
			if (typeCodeVal.equals(typeCode)) {
				result = entity;
				break;
			}
		}
		return result;
	}

	/**
	 * 使用枚举类型对应的typeName获取枚举类型 T
	 * 
	 * @param clazz
	 *            枚举类的class
	 * @param getTypeNameMethodName
	 *            传入的info的get方法
	 * @param typeName
	 *            传入的info值，这个方法为String类型
	 * @return
	 */
	public static <T extends Enum<T>> T getByStringTypeName(Class<T> clazz, String getTypeNameMethodName,
			String typeName) throws Exception {
		T result = null;
		T[] arr = clazz.getEnumConstants();
		Method targetMethod = clazz.getDeclaredMethod(getTypeNameMethodName);
		String typeNameVal = null;
		for (T entity : arr) {
			typeNameVal = targetMethod.invoke(entity).toString();
			if (typeNameVal.contentEquals(typeName)) {
				result = entity;
				break;
			}
		}
		return result;
	}
}
