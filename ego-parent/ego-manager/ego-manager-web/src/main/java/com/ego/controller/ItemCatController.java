package com.ego.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ego.bean.EUTreeNode;
import com.ego.service.IItemCatService;

@RestController
public class ItemCatController {
	@Resource
	private IItemCatService service;
	
	@RequestMapping("/item/cat/list")
	public List<EUTreeNode> getItemCatList(@RequestParam(value="id",defaultValue="0") Long parentId) throws Exception{
		return service.getItemCatList(parentId);
	}
	
}
