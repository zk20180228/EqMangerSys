package com.zkhv.command.service;

import java.util.List;
import java.util.Map;

public interface SysCommandService {
	
	
	Map<String,String> reLoadCommand();
    List<String> reloadUrl();
}
