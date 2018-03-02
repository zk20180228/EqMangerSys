package com.zkhv.tongbu;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

@Repository(value="dataSourceInterceptor")
@Aspect
@Order(value=1)
public class DataSourceInterceptor {

	
	public void setdataSourceSqlServer(JoinPoint jp){
		DatabaseContextHolder.setCustomerType("dataSourceOne");
	}
	
	public void setdataSourceOracle(JoinPoint jp){
		DatabaseContextHolder.setCustomerType("dataSourceTwo");
	}
}