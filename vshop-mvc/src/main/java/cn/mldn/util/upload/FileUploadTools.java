package cn.mldn.util.upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
public class FileUploadTools {
	private List<FileItem> items = null; // 保存全部的上传内容
	private Map<String, List<String>> params = new HashMap<String, List<String>>();	// 保存所有的参数
	private Map<String, FileItem> files = new HashMap<String, FileItem>();
	private int maxSize = 3145728; 				// 默认的上传文件大小为3MB，3 * 1024 * 1024
	private String tempDir ;
	public FileUploadTools(HttpServletRequest request, int maxSize,
			String tempDir) throws Exception {	// 传递request对象、最大上传限制、临时保存目录
		DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建磁盘工厂
		if (tempDir != null) { 					// 判断是否需要进行临时上传目录
			this.tempDir = tempDir ;
			factory.setRepository(new File(tempDir)); // 设置临时文件保存目录
		}
		ServletFileUpload upload = new ServletFileUpload(factory); // 创建处理工具
		upload.setHeaderEncoding("UTF-8");	// 设置编码类型
		if (maxSize > 0) {						// 如果给的上传大小限制大于0，则使用新的设置
			this.maxSize = maxSize;
		}
		upload.setFileSizeMax(this.maxSize); 	// 设置最大上传大小为3MB，3 * 1024 * 1024
		try {
			this.items = upload.parseRequest(request);// 接收全部内容
		} catch (FileUploadException e) {
			throw e; 							// 向上抛出异常
		}
		this.init(); 							// 进行初始化操作
	}
	private void init() {						// 初始化参数，区分普通参数或上传文件
		Iterator<FileItem> iter = this.items.iterator();
		while (iter.hasNext()) {				// 依次取出每一个上传项
			FileItem item = iter.next(); 		// 取出每一个上传的文件
			if (item.isFormField()) { 			// 判断是否是普通的文本参数
				String name = item.getFieldName(); // 取得表单的名字
				String value = null ;
				try {
					value = item.getString("UTF-8");	// 要按照UTF-8编码
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 取得表单的内容
				List<String> temp = null; 		// 保存内容
				if (this.params.containsKey(name)) { // 判断内容是否已经存放
					temp = this.params.get(name); // 如果存在则取出
				} else {						// 不存在
					temp = new ArrayList<String>(); // 重新开辟List数组
				}
				temp.add(value);	 			// 向List数组中设置内容
				this.params.put(name, temp); 	// 向Map中增加内容
			} else { 							// 判断是否是file组件
				String fileName = UUID.randomUUID() + "." + item.getName().split("\\.")[1];
				this.files.put(fileName, item); // 保存全部的上传文件
			}
		}
	}
	public String getParameter(String name) { 	// 取得一个参数
		String ret = null; 						// 保存返回内容
		List<String> temp = this.params.get(name); // 从集合中取出内容
		if (temp != null) {						// 判断是否可以根据key取出内容
			ret = temp.get(0); 					// 取出里面的内容
		}
		return ret;
	}
	public String[] getParameterValues(String name) { // 取得一组上传内容
		String ret[] = null; 					// 保存返回内容
		List<String> temp = this.params.get(name); // 根据key取出内容
		if (temp != null) {						// 避免NullPointerException
			ret = temp.toArray(new String[] {});// 将内容变为字符串数组
		}
		return ret; 							// 变为字符串数组
	}
	public Map<String, FileItem> getUploadFiles() {// 取得全部的上传文件
		return this.files; 						// 得到全部的上传文件
	}
	
	public void removeTemp() {
		if (this.files.size() > 0) {
			Set<Map.Entry<String, FileItem>> keys = this.files.entrySet(); // 取得全部的key
			Iterator<Map.Entry<String, FileItem>> iter = keys.iterator(); // 实例化Iterator对象
			while (iter.hasNext()) {			// 循环取出每一个上传文件
				Map.Entry<String, FileItem> fileInfo = iter.next();
				//if (fileInfo.getValue() instanceof DiskFileItem) {
					DiskFileItem disk = (DiskFileItem) fileInfo.getValue() ;
					disk.getStoreLocation().delete() ;
				//}
//				File file = new File(this.tempDir,fileInfo.getValue().get) ;
//				if (file.exists()) {
//					file.delete() ;
//				}
			}
		}
	}

	
	public List<String> saveAll(String saveDir) throws IOException { // 保存全部文件，并返回文件名称，所有异常抛出
		List<String> names = new ArrayList<String>();
		if (this.files.size() > 0) {
			Set<String> keys = this.files.keySet(); // 取得全部的key
			Iterator<String> iter = keys.iterator(); // 实例化Iterator对象
			File saveFile = null; 				// 定义保存的文件
			InputStream input = null; 			// 定义文件的输入流，用于读取源文件
			OutputStream out = null; 			// 定义文件的输出流，用于保存文件
			while (iter.hasNext()) {			// 循环取出每一个上传文件
				String fileName = iter.next();
				FileItem item = this.files.get(fileName); // 依次取出每一个文件
				saveFile = new File(saveDir + fileName); 	// 重新拼凑出新的路径
				names.add(fileName);			// 保存生成后的文件名称
				try {
					input = item.getInputStream(); 			// 取得InputStream
					out = new FileOutputStream(saveFile); 	// 定义输出流保存文件
					int temp = 0;							// 接收每一个字节
					byte data[] = new byte[1024] ;			// 开辟空间分块保存
					while ((temp = input.read(data))!= -1) { // 依次读取内容
						out.write(data,0,temp); 		// 保存内容
					}
				} catch (IOException e) { 		// 捕获异常
					throw e;					// 异常向上抛出
				} finally { 					// 进行最终的关闭操作
					try {
						input.close();			// 关闭输入流
						out.close();			// 关闭输出流
					} catch (IOException e1) {
						throw e1;
					}
				}
			}
		}
		return names;							// 返回生成后的文件名称
	}
}
