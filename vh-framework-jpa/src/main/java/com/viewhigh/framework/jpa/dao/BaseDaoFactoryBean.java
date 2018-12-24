/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月22日
 * 修改日期：2018年5月22日
 */
package com.viewhigh.framework.jpa.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.viewhigh.framework.jpa.dao.impl.BaseDaoImpl;


/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月22日
 */
public class BaseDaoFactoryBean<R extends JpaRepository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID>{

	/**
	 * 
	 * 一个构造方法
	 * 
	 * @param repositoryInterface
	 */
	public BaseDaoFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}
	
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseDaoFactory<T, Serializable>(entityManager);
	}
	
	private static class BaseDaoFactory<T, ID extends Serializable> extends JpaRepositoryFactory{

		private final EntityManager entityManager;
		
		public BaseDaoFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}
		
		//设置具体的实现类是BaseRepositoryImpl
        @SuppressWarnings("unchecked")
		@Override
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseDaoImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        //设置具体的实现类的class
        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseDaoImpl.class;
        }
		
	}


}
