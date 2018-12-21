package com.ztgx.web;

import com.ztgx.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import com.ztgx.vo.*;
import java.util.*;

/**
 * 订阅中心
 */
@RestController
@RequestMapping(value = "/tool", produces = "application/json;charset=UTF-8")
public class ToolController extends AbstractController {


	
	@Autowired
	private ToolService toolService;

	/**
	 * 查询所有表
	 * @param request
	 */
	@RequestMapping(value = "/findAllTables", method = RequestMethod.POST)
	private Result findAllTables(HttpServletRequest request){
		Result result = new Result();
		Map<String, Object> param = parseRequestParam(request);

		List<Map<String,Object>> list = toolService.findAllTables(param);
		result.setData(list);
		return result;
	}

	/**
	 * 查询表字段注释
	 * @param request
	 */
	@RequestMapping(value = "/findTableByTablename", method = RequestMethod.POST)
	private Result findTableByTablename(String tablename,HttpServletRequest request){
		Result result = new Result();
		Map<String, Object> param = parseRequestParam(request);
		param.put("tablename",tablename);
		List<Map<String,Object>> list = toolService.findTableByTablename(param);
		result.setData(list);
		return result;
	}
	/**
	 * 修改表字段注释
	 * @param request
	 */
	@RequestMapping(value = "/updateTableByTablename", method = RequestMethod.POST)
	private Result updateTableByTablename(@RequestBody Map<String,Object> map, HttpServletRequest request){
		Result result = new Result();
		Map<String, Object> param = parseRequestParam(request);
		if(param != null && param.get("tablename") != null && !"".equals(param.get( "tablename" ).toString())){
			String tablename = param.get( "tablename" ).toString();
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for (String key : map.keySet()) {
				Map<String,Object> sqlmap = new HashMap<String,Object>();
				sqlmap.put("executesql",tablename+"."+key+" IS '"+map.get(key)+"'");
				list.add(sqlmap);
			}
			int flag = toolService.batchUpdateComment(list);
			if(flag == 0){
				result.setResult("0");
				result.setMsg("失败");
				return result;
			}
		}else{
			result.setResult("0");
			result.setMsg("失败");
			return result;
		}
		return result;
	}


	/**
	 * 查询单个表信息
	 * @param request
	 */
	@RequestMapping(value = "/findTableCommentByTablename", method = RequestMethod.POST)
	private Result findTableCommentByTablename(HttpServletRequest request){
		Result result = new Result();
		Map<String, Object> param = parseRequestParam(request);
		List<Map<String,Object>> list = toolService.findAllTables(param);
		result.setData(list);
		return result;
	}

	/**
	 * 修改表字段注释
	 * @param request
	 */
	@RequestMapping(value = "/updateTableCommentByTablename", method = RequestMethod.POST)
	private Result updateTableCommentByTablename(@RequestBody Map<String,Object> map, HttpServletRequest request){
		Result result = new Result();
		Map<String, Object> param = parseRequestParam(request);
		if(map != null){
			Map<String,Object> sqlmap = new HashMap<String,Object>();
			for (String key : map.keySet()) {
				sqlmap.put("executesql",key+" IS '"+map.get(key)+"'");
			}
			int flag = toolService.UpdateTableComment(sqlmap);
			if(flag == 0){
				result.setResult("0");
				result.setMsg("失败");
				return result;
			}
		}else{
			result.setResult("0");
			result.setMsg("失败");
			return result;
		}
		return result;
	}
}
