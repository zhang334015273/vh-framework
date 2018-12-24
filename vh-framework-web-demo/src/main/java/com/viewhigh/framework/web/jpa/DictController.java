/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年7月23日
 * 修改日期：2018年7月23日
 */
package com.viewhigh.framework.web.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.viewhigh.framework.web.jpa.bean.DictEntity;
import com.viewhigh.framework.web.jpa.service.iDictService;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年7月23日
 */
@RestController
@RequestMapping(value = "dict")
public class DictController {
	
	@Autowired
	private iDictService dictService;
	
	@RequestMapping(value = "all")
	public String all(){
		List<DictEntity> dict = dictService.findAll();
		return JSONObject.toJSONString(dict);
	}

}
