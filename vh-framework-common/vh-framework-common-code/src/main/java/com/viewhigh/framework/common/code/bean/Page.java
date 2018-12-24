/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月9日
 * 修改日期：2018年5月9日
 */
package com.viewhigh.framework.common.code.bean;

/**
 * @Description  分页bean
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月9日
 */
public class Page {
	
	
	private int size; //每页的条数
	
	private int curpage; //当前页码
	
	private int pageCounts; //共多少页
	
	private long totalRecs; //共多少条

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the curpage
	 */
	public int getCurpage() {
		return curpage;
	}

	/**
	 * @param curpage the curpage to set
	 */
	public void setCurpage(int curpage) {
		this.curpage = curpage;
	}

	/**
	 * @return the pageCounts
	 */
	public int getPageCounts() {
		return pageCounts;
	}

	/**
	 * @param pageCounts the pageCounts to set
	 */
	public void setPageCounts(int pageCounts) {
		this.pageCounts = pageCounts;
	}

	/**
	 * @return the totalRecs
	 */
	public long getTotalRecs() {
		return totalRecs;
	}

	/**
	 * @param totalRecs the totalRecs to set
	 */
	public void setTotalRecs(long totalRecs) {
		this.totalRecs = totalRecs;
	}

	
	public void calcPageCount(long totalRecs){
		this.totalRecs = totalRecs;
		this.pageCounts = (int) ((int ) totalRecs % size == 0 ? totalRecs / size : totalRecs / size + 1 );
	}
	
}
