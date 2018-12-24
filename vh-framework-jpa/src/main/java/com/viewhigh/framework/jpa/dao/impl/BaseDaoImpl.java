/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.jpa.dao.impl;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.viewhigh.framework.jpa.dao.iBaseDao;


/**
 * @Description
 * @author zhangxm
 * @version v1.0
 * @since 2018年5月22日
 */
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements iBaseDao<T, ID> {

	private final EntityManager entityManager;

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getListBySql(String sql) {
		return entityManager.createNativeQuery(sql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListBySql(String sql, Class<?> clazz) {
		return entityManager.createNativeQuery(sql, clazz).getResultList();
	}
	
	@SuppressWarnings({ "rawtypes" })
	@Override
	public List getListBySql(String sql,List<Object> list,Class<?> clazz) {
		Query query = null;
		if(clazz.equals(Map.class)){
			//map 的 情况需要这样处理 否则报错 no entity：java.util.Map
			query = entityManager.createNativeQuery(sql);
			query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		}else{
			query = entityManager.createNativeQuery(sql, clazz);
		}
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i+1 , list.get(i));
		}
		return query.getResultList();
	}

	@Override
	public List<T> findByEntity(T entity) {
		return findAll(Example.of(entity));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByHql(String hql) {
		return entityManager.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListByHql(String hql, Object... objects) {
		Query query = entityManager.createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getListBySql(String sql, Class<?> clazz, Object... objects) {
		Query query = entityManager.createNativeQuery(sql, clazz);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		return query.getResultList();
	}

	@Override
	public void update(String sql, Object... objects) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public long countBySql(String sql, Object... objects) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i + 1, objects[i]);
		}
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		if (list.get(0) == null) {
			return 0;
		} else {
			return Long.valueOf(list.get(0).toString());
		}
	}
	
	@Override
	public long countBySql(String sql, List<Object> objects) {
		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < objects.size(); i++) {
			query.setParameter(i + 1, objects.get(i));
		}
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		if (list.get(0) == null) {
			return 0;
		} else {
			return Long.valueOf(list.get(0).toString());
		}
	}

	@Override
	public long countBySql(String sql) {
		@SuppressWarnings("rawtypes")
		List list = entityManager.createNativeQuery(sql).getResultList();
		if (list.get(0) == null) {
			return 0;
		} else {
			return Long.valueOf(list.get(0).toString());
		}
	}

	@Override
	public void updateBySelect(T entity) {
		Table table = entity.getClass().getAnnotation(Table.class);
		if(table != null){
			StringBuffer sql = new StringBuffer();
			StringBuffer idSql = new StringBuffer();
			sql.append("update ").append(table.name()).append(" set ");
			
			Field[] fields = entity.getClass().getDeclaredFields();
			Method[] methods = entity.getClass().getMethods();
			List<Object> list = new ArrayList<Object>();
			int count = 0;
			try {
				for(Field field:fields){
					for(Method method:methods){
						if(("get"+field.getName()).toLowerCase().equals(method.getName().toLowerCase())){
							Id id = method.getAnnotation(Id.class);
							Column col = method.getAnnotation(Column.class);
							if(id != null){
								field.setAccessible(true);
								idSql.append(col.name()).append(" = ?").append(count);
								list.add(field.get(entity));
								count ++;
							}else{
								field.setAccessible(true);
								Object obj = field.get(entity);
								if(col != null && obj != null){
									sql.append(col.name()).append(" = ?").append(count).append(" ,");
									list.add(field.get(entity));
									count ++;
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			sql = new StringBuffer(sql.substring(0, sql.length()-1));
			sql.append(" WHERE ").append(idSql.toString());
			
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i , list.get(i));
			}
			query.executeUpdate();
		}
		
	}
	
	/**
	 * 根据属性删除记录
	 */
	@Override
	public void batchDeleteByEntity(T entity) {
		Table table = entity.getClass().getAnnotation(Table.class);
		if(table != null){
			StringBuffer sql = new StringBuffer();
			sql.append("delete from ").append(table.name()).append(" where ");
			
			Field[] fields = entity.getClass().getDeclaredFields();
			Method[] methods = entity.getClass().getMethods();
			List<Object> list = new ArrayList<Object>();
			int count = 0;
			try {
				for(Field field:fields){
					for(Method method:methods){
						if(("get"+field.getName()).toLowerCase().equals(method.getName().toLowerCase())){
							Column col = method.getAnnotation(Column.class);
							field.setAccessible(true);
							Object obj = field.get(entity);
							if(col != null && obj != null){
								sql.append(" ").append(col.name()).append(" = ?").append(count).append(" and");
								list.add(obj);
								count ++;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			sql = new StringBuffer(sql.substring(0, sql.length()-3));
			
			
			Query query = entityManager.createNativeQuery(sql.toString());
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i , list.get(i));
			}
			query.executeUpdate();
		}
	}

}
