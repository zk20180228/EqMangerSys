package com.zkhv.tongbu;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{  
	  
    @Override  
    protected Object determineCurrentLookupKey() {  
    	return DatabaseContextHolder.getCustomerType();
    }  
  
} 