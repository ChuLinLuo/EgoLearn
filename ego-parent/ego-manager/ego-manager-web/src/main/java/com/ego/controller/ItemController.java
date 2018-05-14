package com.ego.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.bean.PictureResult;
import com.ego.pojo.TbItem;
import com.ego.service.IItemService;
import com.ego.service.PictureService;

@RestController
public class ItemController {
	@Resource
	private IItemService service;
	@Resource
	private PictureService pictureService;
	@RequestMapping("/item/list")
	public EUDataGridResult getItemList(Integer page,Integer rows){
		EUDataGridResult result = null;
		try {
			result = service.getItemList(page, rows);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping("/pic/upload")
	public PictureResult uploadFile(@RequestParam("uploadFile")MultipartFile uploadFile) throws IOException{
		return pictureService.uploadFile(uploadFile);
	}
	@RequestMapping("/item/save")
	public EgoResult saveItem(TbItem item,String desc){
		try {
			service.saveItem(item, desc);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(304, "添加失败");
		}
		return EgoResult.ok();
	}
	@RequestMapping("/rest/item/delete")
	public EgoResult deleteItem(String ids){
		try {
			service.deleteItem(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(304, "删除失败");
		}
		return EgoResult.ok();
	}
	@RequestMapping("/rest/item/update")
	public EgoResult updateItem(TbItem item,String desc){
		try {
			service.updateItem(item, desc);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(304, "修改失败");
		}
		return EgoResult.ok();
	}
	@RequestMapping("/rest/item/query/item/desc/{id}")
	public EgoResult queryDesc(@PathVariable(value="id")Long id){
		return service.queryDesc(id);
	}
	@RequestMapping("/rest/item/param/item/query/{id}")
	public EgoResult queryItem(@PathVariable(value="id")Long id){
		return service.queryItem(id);
	}
	@RequestMapping("/rest/item/instock")
	public EgoResult instock(String ids){
		try {
			service.updateStatus(ids,2);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(304, "下架失败");
		}
		return EgoResult.ok();
	}
	@RequestMapping("/rest/item/reshelf")
	public EgoResult reshelf(String ids){
		try {
			service.updateStatus(ids,1);
		} catch (Exception e) {
			e.printStackTrace();
			return EgoResult.build(304, "上架失败");
		}
		return EgoResult.ok();
	}
}
