package cn.tedu.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@PostMapping("upload.do")
	public String handleUpload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws IllegalStateException, IOException {
		/*
		 * 限制上传空文件或不传文件
		 */
		boolean isEmpty=file.isEmpty();
		System.out.println("isEmpty="+isEmpty);
		if(isEmpty) {
			return "error";
		}
		/*
		 * 限制上传文件大小
		 */
		long size=file.getSize();
		System.out.println("size="+size);
		if(size>2*1024*1024) {//文件大于2M，拒绝上传
			return "error";
		}
		/*
		 * 限制上传文件类型
		 */
		String contentType=file.getContentType();
		System.out.println("fileType="+contentType);
		List<String> types=new ArrayList<String>();
		types.add("image/jpeg");
		types.add("image/png");
		types.add("image/gif");
		if(!types.contains(contentType)) {
			return "error";
		}
		
		//确定上传的文件夹~
		String dirPath=request.getServletContext().getRealPath("upload");
		File dir=new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("文件夹路径="+dir);
		//获取原文件名称
		String originalFilename=file.getOriginalFilename();
		//获取文件的---.扩展名
		int beginIndex=originalFilename.lastIndexOf(".");
		String suffix="";
		if(beginIndex!=-1) {//避免没有扩展名的文件
			suffix=originalFilename.substring(beginIndex);
		}
		//随机的UUID.扩展名     ---》服务器端的保存的文件名
		//或者当前系统毫秒级时间System.currentTimeMillis();
		String filename=UUID.randomUUID().toString()+suffix;
		System.out.println("文件名="+filename);
		File dest=new File(dir,filename);
		System.out.println("整体路径="+dest);
		//执行保存
		file.transferTo(dest);
		return null;
	}
}
