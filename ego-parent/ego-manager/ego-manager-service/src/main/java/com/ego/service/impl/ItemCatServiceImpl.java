package com.ego.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ego.bean.EUTreeNode;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
import com.ego.service.IItemCatService;

@Service
@Transactional
public class ItemCatServiceImpl implements IItemCatService {
	@Resource
	private TbItemCatMapper catMapper;
	@Override
	public List<EUTreeNode> getItemCatList(long parentId) throws Exception {
		TbItemCatExample example = new TbItemCatExample();
		example.or().andParentIdEqualTo(parentId);
		List<TbItemCat> catList = catMapper.selectByExample(example);
		List<EUTreeNode> list = new ArrayList<>();
		for (TbItemCat c : catList) {
			EUTreeNode node = new EUTreeNode();
			node.setId(c.getId());
			node.setText(c.getName());
			node.setState(c.getIsParent()?"closed":"open");
			list.add(node);
		}
		return list;
	}

}
