package com.ego.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ego.bean.EUTreeNode;
import com.ego.bean.EgoResult;
import com.ego.service.ContentCategoryService;

@RestController
@RequestMapping("/content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id",defaultValue="0")Long parentId) throws Exception{
		List<EUTreeNode> list = contentCategoryService.getContentCategoryList(parentId);
		return list;
	}
	@RequestMapping("/create")
	public EgoResult addNode(Long parentId, String name) throws Exception {
		EgoResult result = contentCategoryService.addNode(parentId, name);
		return result;
	}
	@RequestMapping("/update")
	public EgoResult rename(@RequestParam("id")Long id,@RequestParam("name")String name) throws Exception{
		return contentCategoryService.rename(id,name);
	}
	@RequestMapping("/delete")
	public EgoResult delete(@RequestParam("id")Long id) throws Exception{
		return contentCategoryService.deleteById(id);
	}
}
