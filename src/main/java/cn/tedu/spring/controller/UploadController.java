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
		 * �����ϴ����ļ��򲻴��ļ�
		 */
		boolean isEmpty=file.isEmpty();
		System.out.println("isEmpty="+isEmpty);
		if(isEmpty) {
			return "error";
		}
		/*
		 * �����ϴ��ļ���С
		 */
		long size=file.getSize();
		System.out.println("size="+size);
		if(size>2*1024*1024) {//�ļ�����2M���ܾ��ϴ�
			return "error";
		}
		/*
		 * �����ϴ��ļ�����
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
		
		//ȷ���ϴ����ļ���~
		String dirPath=request.getServletContext().getRealPath("upload");
		File dir=new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		System.out.println("�ļ���·��="+dir);
		//��ȡԭ�ļ�����
		String originalFilename=file.getOriginalFilename();
		//��ȡ�ļ���---.��չ��
		int beginIndex=originalFilename.lastIndexOf(".");
		String suffix="";
		if(beginIndex!=-1) {//����û����չ�����ļ�
			suffix=originalFilename.substring(beginIndex);
		}
		//�����UUID.��չ��     ---���������˵ı�����ļ���
		//���ߵ�ǰϵͳ���뼶ʱ��System.currentTimeMillis();
		String filename=UUID.randomUUID().toString()+suffix;
		System.out.println("�ļ���="+filename);
		File dest=new File(dir,filename);
		System.out.println("����·��="+dest);
		//ִ�б���
		file.transferTo(dest);
		return null;
	}
}
