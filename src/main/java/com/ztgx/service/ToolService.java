package com.ztgx.service;

import com.ztgx.mapper.ToolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ToolService {
	
	@Autowired
	private ToolMapper toolMapper;

	public List<Map<String, Object>> findAllTables(Map<String, Object> map){
		return toolMapper.findAllTables(map);
	}
	public List<Map<String, Object>> findTableByTablename(Map<String, Object> map){
		return toolMapper.findTableByTablename(map);
	}
	public int batchUpdateComment(List<Map<String, Object>> list){
		int flag = 1;
		try {
			for(int i=0;i<list.size();i++){
				Map map = list.get(i);
				toolMapper.UpdateComment(map);
			}
		}catch (Exception e){
			flag = 0;
		}
		return flag;

	}
	public int UpdateTableComment(Map<String, Object> map){
		int flag = 1;
		try {
			toolMapper.UpdateTableComment(map);
		}catch (Exception e){
			flag = 0;
		}
		return flag;
	}

}
