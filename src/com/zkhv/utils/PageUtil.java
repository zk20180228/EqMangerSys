package com.zkhv.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用于分页的bean
public class PageUtil<T>  implements Serializable{

	private static final long serialVersionUID = 337297181251071639L;
	//当前页
	private int page;
	
	//页大小
	private int rows;
	
	//总记录数
	private int tatalRecord;
	
	//总页数
	//总页数由dataGrid组件自己生成，这里我们不用再计算了
	
	//记录列表
	private List<T> list;
	
	//查询条件Bean
	private T entity;
	
	//查询的起始行
	private int start;
	
	//用于封装返回给dataGrid的map结果
	private Map<String,Object> map =new HashMap<String,Object>();

	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		
		this.rows = rows;
	}
	
	//我们把总记录数放入Map中，不用让外界获取
	/*public int getTatalRecord() {
		return tatalRecord;
	}*/

	public void setTatalRecord(int tatalRecord) {
		//设置总记录数
		map.put("total", tatalRecord);
		this.tatalRecord = tatalRecord;
	}
	
	//已经封装到Map中了，不需要外界获取
	/*public List<T> getList() {
		return list;
	}
	 */
	
	public void setList(List<T> list) {
		map.put("rows", list);
		this.list = list;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public int getStart() {
		return (page-1)*rows;
	}

	public void setStart(int start) {
		//当前页-1乘以记录数就是查询的起始行
		
		this.start = start;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	@Override
	public String toString() {
		return "PageUtil [page=" + page + ", rows=" + rows + ", tatalRecord="
				+ tatalRecord + ", list=" + list + ", entity=" + entity
				+ ", start=" + start + ", map=" + map + "]";
	}
	
	//不需要无用的设置，我们自己已经创建好了
	/*public void setMap(Map<String, Object> map) {
		this.map = map;
	}*/

	
	
	
	
	
}
