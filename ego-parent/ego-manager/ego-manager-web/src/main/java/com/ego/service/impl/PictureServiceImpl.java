package com.ego.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ego.bean.PictureResult;
import com.ego.service.PictureService;
import com.ego.utils.FastDFSClient;

@Service
public class PictureServiceImpl implements PictureService{
	@Value("${BASE_FASTDFS_PATH}")
	private String BASEPATH;
	@Override
	public PictureResult uploadFile(MultipartFile uploadFile) throws IOException{
		String file = FastDFSClient.uploadFile(uploadFile.getInputStream(), uploadFile.getOriginalFilename());
		PictureResult result = new PictureResult();
		if("".equals(file)){
			result.setError(1);
		}else{
			result.setError(0);
		}
		result.setUrl(BASEPATH+file);
		return result;
	}
}
