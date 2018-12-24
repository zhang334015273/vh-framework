/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.jpa.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viewhigh.framework.jpa.dao.iBaseDao;
import com.viewhigh.framework.jpa.service.iBaseService;


/**
 * @Description
 * @author zhangxm
 * @version v1.0
 * @since 2018年5月22日
 */
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = true)
public class BaseServiceImpl<T, ID extends Serializable> implements iBaseService<T, ID> {

	public iBaseDao<T, ID> baseDao;

	// T的实体类
	public Class<T> entityClass;

	@Transactional(readOnly = false)
	@Override
	public void save(T entity) {
		baseDao.saveAndFlush(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public void batchSave(List<T> list) {
		baseDao.save(list);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(T entity) {
		baseDao.save(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(ID id) {
		baseDao.delete(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void batchDeleteByEntity(T entity) {
		
		baseDao.batchDeleteByEntity(entity);
	}

	@Transactional(readOnly = false)
	@Override
	public void batchDelete(List<T> list) {
		baseDao.delete(list);
	}

	@Transactional(readOnly = false)
	@Override
	public void batchDeleteByIds(List<String> list) {
		Table table = entityClass.getAnnotation(Table.class);

		Method[] methods = entityClass.getDeclaredMethods();
		for (Method method : methods) {
			Id id = method.getAnnotation(Id.class);
			if (id != null) {
				Column col = method.getAnnotation(Column.class);
				baseDao.update("delete from " + table.name() + " where " + col.name() + " in (?1)", list);
			}
		}
	}

	@Override
	public T findById(ID id) {
		return baseDao.findOne(id);
	}

	/**
	 * @param baseDao
	 */
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(iBaseDao<T, ID> baseDao) {
		super();
		this.baseDao = baseDao;

		if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		} else {
			entityClass = (Class<T>) ((ParameterizedType) getClass().getSuperclass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}

	}

	@Override
	public List<Object[]> getListBySql(String sql) {
		return baseDao.getListBySql(sql);
	}

	@Override
	public List<T> getListBySql(String sql, Class<?> clazz) {
		return baseDao.getListBySql(sql, clazz);
	}

	@Override
	public List<T> findByEntity(T entity) {
		return baseDao.findByEntity(entity);
	}

	@Override
	public List<T> getListByHql(String hql) {
		return baseDao.getListByHql(hql);
	}
	
	
	@Override
	public List<T> getListByHql(String hql, Object... objects) {
		return baseDao.getListByHql(hql, objects);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(String sql, Object... objects) {
		baseDao.update(sql, objects);
	}

	@Override
	public List<T> getListBySql(String sql, Class<?> clazz, Object... objects) {
		return baseDao.getListBySql(sql, clazz, objects);
	}

	@Override
	public List<T> findAll() {
		return baseDao.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public void updateBySelect(T entity) {
		baseDao.updateBySelect(entity);
	}


}
