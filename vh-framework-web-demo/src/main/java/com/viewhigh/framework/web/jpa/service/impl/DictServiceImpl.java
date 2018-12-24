/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明
 * 版本：V1.0
 * 创建日期：2018年05月23日
 * 修改日期：2018年05月23日
 */
package com.viewhigh.framework.web.jpa.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viewhigh.framework.jpa.service.impl.BaseServiceImpl;
import com.viewhigh.framework.web.jpa.bean.DictEntity;
import com.viewhigh.framework.web.jpa.dao.iDictDao;
import com.viewhigh.framework.web.jpa.service.iDictService;


/**
 * @Description 系统字典表ServiceImpl
 * @author：zhangxiaoming
 * @version：V1.0
 * @since：2018年05月23日
 */
@Service
public class DictServiceImpl extends BaseServiceImpl<DictEntity, String> implements iDictService {

	@Autowired
	public DictServiceImpl(iDictDao dictDao){
		super(dictDao);
	}


}
