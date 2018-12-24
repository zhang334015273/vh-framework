/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.jpa.service;

import java.io.Serializable;
import java.util.List;


/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月22日
 */
public interface iBaseService<T, ID extends Serializable> {
	
	/**
	 * @Description 新增
	 */
	public void save(T entity);
	
	/**
	 * @Description 批量新增
	 */
	public void batchSave(List<T> list);

	/**
	 * @Description 修改
	 */
	public void update(T entity);
	
	/**
	 * @Description 修改
	 */
	public void updateBySelect(T entity);

	/**
	 * @Description 根据id删除
	 */
	public void deleteById(ID id);
	
	/**
	 * @Description 根据属性删除
	 */
	public void batchDeleteByEntity(T entity);
	
	/**
	 * @Description 批量删除
	 */
	public void batchDelete(List<T> list);
	
	/**
	 * 批量删除
	 * @param list
	 */
	public void batchDeleteByIds(List<String> list);

	/**
	 * @Description 根据id查找
	 */
	public T findById(ID id);
	
	/**
	 * 根据sql 查询list
	 * @param sql
	 * @return
	 */
	public List<Object[]> getListBySql(String sql);
	
	/**
	 * 根据sql 查询list
	 * @param sql
	 * @return
	 */
	public List<T> getListBySql(String sql,Class<?> clazz);
	
	/**
	 * 根据属性名获取list   将属性封装进对象中进行查询
	 * @param propertyName
	 * @param clazz
	 * @return
	 */
	public List<T> findByEntity(T entity);
	
	/**
	 * 根据hql 查询list
	 * @param hql
	 * @return
	 */
	public List<T> getListByHql(String hql);
	
	/**
	 * 根据hql,查询条件 查询list
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> getListByHql(String hql, Object... objects);
	

	/**
	 * 根据sql进行更新
	 * @param sql
	 * @param clazz
	 * @param objects
	 */
	public void update(String sql, Object...objects);
	
	/**
	 * 根据sql进行查询
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 */
	public List<T> getListBySql(String sql,Class<?> clazz, Object...objects);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> findAll();
	
	
}
