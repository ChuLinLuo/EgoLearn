package com.ego.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.ego.bean.PictureResult;

public interface PictureService {
	PictureResult uploadFile(MultipartFile uploadFile) throws IOException;
}
