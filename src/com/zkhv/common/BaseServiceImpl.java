package com.zkhv.common;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.zkhv.equipment.dao.EquipmentMapper;
import com.zkhv.log.dao.LogInfoMapper;
import com.zkhv.mkjview.dao.T_OPPatientMapper;
import com.zkhv.mkjview.dao.T_OPRoomMapper;
import com.zkhv.mkjview.dao.T_OPWorkMapper;
import com.zkhv.user.dao.UserMapper;
import com.zkhv.utils.PageUtil;


public class BaseServiceImpl<T> implements BaseService<T>{

	protected  BaseMapper<T> baseMapper;
	
	//下面的实例有SPring创建
	@Autowired
	protected  T_OPPatientMapper t_OPPatientMapper;
	
	@Autowired
	protected  T_OPRoomMapper  t_OPRoomMapper;
	
	@Autowired
	protected  T_OPWorkMapper  t_OPWorkMapper;
	
	//oracle中的
/*	@Autowired
	protected  OR_PatientMapper or_PaORienORMapper;*/
	
/*	@Autowired
	protected  OR_OPRoomMapper  or_OPRoomMapper;*/
	
/*	@Autowired
	protected  OR_OPWorkMapper  or_OPWorkMapper;*/
	
	
	@Autowired
	protected EquipmentMapper equipmentMapper;
	
	@Autowired
	protected UserMapper userMapper;
	
	@Resource
	protected LogInfoMapper logInfoMapper;
	
	
	@PostConstruct//在构造方法后，初化前执行
	private void initBaseMapper() throws Exception{
				//完成以下逻辑，需要对研发本身进行命名与使用规范
				//this关键字指对象本身，这里指的是调用此方法的实现类（子类）
				//返回超类的Class类型
				//获取泛型类型
				ParameterizedType type =(ParameterizedType) this.getClass().getGenericSuperclass();
				//获取第一个参数的class，得到真实类型
				Class clazz = (Class)type.getActualTypeArguments()[0];
				//转化为属性名（相关的Mapper子类的引用名）Supplier  supplierMapper
				String localField = clazz.getSimpleName().substring(0,1).toLowerCase()+clazz.getSimpleName().substring(1)+"Mapper";
				//getDeclaredField:可以使用于包括私有、默认、受保护、公共字段，但不包括继承的字段
				//得到父类中的与泛型一致的字段对象，这个值由spring注入(代理对象)
				Field field=this.getClass().getSuperclass().getDeclaredField(localField);
				//field.get(this)获取当前this的field字段的值。
				//得到父类中baseMapper字段对象
				Field baseField = this.getClass().getSuperclass().getDeclaredField("baseMapper");
				//field.get(this)获取当前this的field字段的值。例如：Supplier对象中，baseMapper所指向的对象为其子类型SupplierMapper对象，子类型对象已被spring实例化于容器中		
				//设置当前对象baseMapper的值为该泛型对应的mapper接口的实现类对象，此对象由Spring注入
				baseField.set(this, field.get(this));		
	}	
	
	
	
	

	public void insert(T entity) {
		
		 baseMapper.insert(entity);
	}


	public void update(T entity) {
		
		 baseMapper.update(entity);
	}


	public void delete(T entity) {
		
		 baseMapper.delete(entity);
	}


	public void deleteList(String[] id) {
		
		 baseMapper.deleteList(id);
	}


	public T select(T entity) {
		
		return baseMapper.select(entity);
	}


	
	public PageUtil<T> selectPageByDyc(PageUtil<T> page) {

		//设置列表集合
		page.setList(baseMapper.selectPageListUseDyc(page));
		//设置中记录数
		page.setTatalRecord(baseMapper.selectPageCountUseDyc(page));
		return page;
	}





}
