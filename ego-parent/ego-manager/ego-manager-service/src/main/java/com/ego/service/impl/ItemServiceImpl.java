package com.ego.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.bean.IDUtils;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.service.IItemService;
import com.ego.utils.FastDFSClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
@Transactional
public class ItemServiceImpl implements IItemService {
	
	@Resource
	private TbItemMapper mapper;
	@Resource
	private TbItemDescMapper descMapper;
	@Override
	public List<TbItem> getItems() throws Exception {
		TbItemExample example = new TbItemExample();
		List<TbItem> list = mapper.selectByExample(example);
		return list;
	}
	public EUDataGridResult getItemList(int page,int rows)throws Exception{
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page, rows);
		List<TbItem> list = mapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	@Override
	public void saveItem(TbItem item,String desc)throws Exception{
		long id = IDUtils.genItemId();
		Date date = new Date();
		item.setId(id);
		item.setUpdated(date);
		item.setCreated(date);
		item.setStatus((byte)1);
		mapper.insertSelective(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemId(id);
		itemDesc.setItemDesc(desc);
		descMapper.insertSelective(itemDesc);
	}
	@Override
	public void deleteItem(String ids)throws Exception{
		String[] idString = ids.split(",");
		if(idString!=null&&idString.length>0){
			for (String id : idString) {
				Long realId = Long.valueOf(id);
				TbItem item = mapper.selectByPrimaryKey(realId);
				String image = item.getImage();
				String[] images= image.split("/");
				String realImage = "";
				if(images.length>0){
					for(int i = 4; i < images.length; i++){
						realImage += images[i];
						if(i<images.length-1){
							realImage = realImage+"/";
						}
					}
					FastDFSClient.deleteFile(null, realImage);
				}
				mapper.deleteByPrimaryKey(realId);
				descMapper.deleteByPrimaryKey(realId);
			}
		}
	}
	@Override
	public void updateItem(TbItem item, String desc) throws Exception{
		Date date = new Date();
		item.setUpdated(date);
		mapper.updateByPrimaryKeySelective(item);
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setUpdated(date);
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		descMapper.updateByPrimaryKeySelective(itemDesc);
	}
	@Override
	public EgoResult queryDesc(Long id) {
		TbItemDesc desc = descMapper.selectByPrimaryKey(id);
		return EgoResult.ok(desc);
	}
	@Override
	public EgoResult queryItem(Long id) {
		TbItem item = mapper.selectByPrimaryKey(id);
		return EgoResult.ok(item);
	}
	@Override
	public void updateStatus(String ids,int i) throws Exception {
		String[] idString = ids.split(",");
		if (idString != null && idString.length > 0) {
			for (String id : idString) {
				Long realId = Long.valueOf(id);
				TbItem item = new TbItem();
				item.setId(realId);
				item.setStatus((byte)i);
				mapper.updateByPrimaryKeySelective(item);
			}
		}
	}
	

}
