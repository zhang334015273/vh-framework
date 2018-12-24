/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明
 * 版本：V1.0
 * 创建日期：2018年05月23日
 * 修改日期：2018年05月23日
 */
package com.viewhigh.framework.web.jpa.dao;


import org.springframework.stereotype.Repository;

import com.viewhigh.framework.jpa.dao.iBaseDao;
import com.viewhigh.framework.web.jpa.bean.DictEntity;



/**
 * @Description 系统字典表dao
 * @author：zhangxiaoming
 * @version：V1.0
 * @since：2018年05月23日
 */
@Repository
public interface iDictDao extends iBaseDao<DictEntity,String>{

}
