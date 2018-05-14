package com.ego.service;

import java.util.List;

import com.ego.bean.EUDataGridResult;
import com.ego.bean.EgoResult;
import com.ego.pojo.TbItem;

public interface IItemService {
	List<TbItem> getItems() throws Exception;
	EUDataGridResult getItemList(int page,int rows) throws Exception;
	void saveItem(TbItem item,String desc)throws Exception;
	void deleteItem(String ids)throws Exception;
	void updateItem(TbItem item, String desc)throws Exception;
	EgoResult queryDesc(Long id);
	EgoResult queryItem(Long id);
	void updateStatus(String ids, int i) throws Exception;
}
