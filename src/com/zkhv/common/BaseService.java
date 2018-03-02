package com.zkhv.common;

import com.zkhv.utils.PageUtil;



public interface BaseService<T> {
	
		//添加单个对象
		public void insert (T entity);
		
		//修改单个对象
		public void update(T entity);
		 
		//删除单个对象
		public void delete(T entity);
		
		//通过主键（数组）批量删除数据
		public void deleteList(String [] pks);
		
		
		//查询单个对象
		public T select(T entity);
		
	/*	//通过关键字分页查询
		public Page<T> selectPage(Page<T> page); */
		
		
		//通过多条件分页查询
		public PageUtil<T> selectPageByDyc(PageUtil<T> page);

}
