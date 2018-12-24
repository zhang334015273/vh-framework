/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明
 * 版本：V1.0
 * 创建日期：2018年06月05日
 * 修改日期：2018年06月05日
 */
package com.viewhigh.framework.web.jpa.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
/**
 * @Description 系统字典表Entity
 * @author：zhangxiaoming
 * @version：V1.0
 * @since：2018年06月05日
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_saas_dict")
public class DictEntity implements java.io.Serializable{

	private String dictId; //主键
	private String tabName; //表名
	private String tabColumn; //列名
	private Short valueIndex; //列值标识
	private String valueContent; //列值内容

	/*==================== gen setter and getter begin=====================*/
	@Id
	@GeneratedValue(generator = "uuidGenerator")
	@GenericGenerator(name = "uuidGenerator", strategy = "uuid") 
	@Column(name = "dict_id", unique = true, nullable = false, length=40)
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	@Column(name = "tab_name", length=40)
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}

	@Column(name = "tab_column", length=40)
	public String getTabColumn() {
		return tabColumn;
	}
	public void setTabColumn(String tabColumn) {
		this.tabColumn = tabColumn;
	}

	@Column(name = "value_index")
	public Short getValueIndex() {
		return valueIndex;
	}
	public void setValueIndex(Short valueIndex) {
		this.valueIndex = valueIndex;
	}

	@Column(name = "value_content", length=40)
	public String getValueContent() {
		return valueContent;
	}
	public void setValueContent(String valueContent) {
		this.valueContent = valueContent;
	}

	/*==================== gen setter and getter end  =====================*/

}
