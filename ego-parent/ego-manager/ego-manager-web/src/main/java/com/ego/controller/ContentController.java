package com.ego.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.pojo.TbContent;
import com.ego.service.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/query/list")
	public EUDataGridResult getContentList(Long categoryId, Integer page, Integer rows){
		return contentService.getContentList(categoryId, page, rows);
	}
	@RequestMapping("/save")
	public EgoResult saveContent(TbContent content) throws Exception{
		return contentService.saveContent(content);
	}
	@RequestMapping("/delete")
	public EgoResult deleteContent(String ids) throws Exception{
		return contentService.deleteById(ids);
	}
	@RequestMapping("/edit")
	public EgoResult updateContent(TbContent content) throws Exception{
		return contentService.updateContent(content);
	}
}
