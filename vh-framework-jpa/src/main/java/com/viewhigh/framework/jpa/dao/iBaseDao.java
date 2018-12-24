/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.jpa.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月22日
 */
@NoRepositoryBean
public interface iBaseDao<T, ID extends Serializable> extends JpaRepository<T, ID>{
	
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
	 * 查询条件封装成list。根据sql 查询list
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getListBySql(String sql,List<Object> objs,Class<?> clazz);
	
	/**
	 * 带条件的根据sql 查询list
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 */
	public List<T> getListBySql(String sql,Class<?> clazz, Object... objects);
	
	/**
	 * 根据hql 查询list
	 * @param hql
	 * @return
	 */
	public List<T> getListByHql(String hql);
	
	/**
	 * 带参数 根据hql 查询list
	 * @param hql
	 * @param objects
	 * @return
	 */
	public List<T> getListByHql(String hql, Object... objects);
	
	
	/**
	 * 根据属性名获取list
	 * @param propertyName
	 * @param clazz
	 * @return
	 */
	public List<T> findByEntity(T entity);
	
	/**
	 * 更新数据使用sql
	 * @param sql
	 * @param clazz
	 * @param objects
	 */
	public void update(String sql, Object... objects);
	
	
	/**
	 * 更新数据使用sql
	 * @param sql
	 * @param clazz
	 * @param objects
	 */
	public void updateBySelect(T entity);
	
	/**
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public long countBySql(String sql,Object... objects);
	
	/**
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public long countBySql(String sql);
	/**
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public long countBySql(String sql,List<Object> objs);
	

	/**
	 * 根据属性删除
	 * @param entity
	 */
	public void batchDeleteByEntity(T entity);
}
