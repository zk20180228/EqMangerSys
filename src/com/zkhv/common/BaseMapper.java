package com.zkhv.common;



import java.util.List;

import com.zkhv.utils.PageUtil;



public interface BaseMapper<T> {
	
	
		//添加单个对象
		public int insert(T t);
		
		//修改单个对象
		public void update(T t);
		
		//删除单个对象
		public int delete(T t);
		
		//根据主键批量删除
		public int deleteList(String[] id);
		
		//查询单个对象
		public T select(T t);
	
		//多条件查询，返回列表集合[动态sql]
		public List<T> selectPageListUseDyc(PageUtil<T> page);
		
		//多条件查询，返回总记录数[动态sql]
		public Integer selectPageCountUseDyc(PageUtil<T> page);
		

}
