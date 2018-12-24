/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年6月20日
 * 修改日期：2018年6月20日
 */
package com.viewhigh.framework.common.code.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.springframework.util.StringUtils;

/**
 * @Description  操作文件工具类
 * @author zhangxm 
 * @version v1.0
 * @since 2018年6月20日
 */
public class FileUtil {
	
	/**
	 * 复制文件夹
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException{
		File source = new File(sourceDir);
		if(!source.exists()){
			//如果源文件不存在 则直接返回
			return;
		}
		
		
		File target = new File(targetDir);
		if(!target.exists()){
			//不存在创建
			target.mkdirs();
		}
		
		 // 获取源文件夹当前下的文件或目录   
        File[] file = (new File(sourceDir)).listFiles();  
        for (int i = 0; i < file.length; i++) {  
            if (file[i].isFile()) {  
                // 源文件   
                File sourceFile=file[i];  
                // 目标文件   
                File targetFile=new  File(new File(targetDir).getAbsolutePath()+File.separator+file[i].getName());  
                copyFile(sourceFile,targetFile);  
            }  
            if (file[i].isDirectory()) {  
                // 准备复制的源文件夹   
                String dir1=sourceDir + File.separator + file[i].getName();  
                // 准备复制的目标文件夹   
                String dir2=targetDir + File.separator + file[i].getName();  
                copyDirectiory(dir1, dir2);  
            }  
        }  
	}
	
	/**
	 * 文件复制
	 * @param sourceFile 源文件
	 * @param targetFile 目标文件
	 */
	public static void copyFile(File sourceFile, File targetFile) throws IOException{
		
		if(targetFile.exists()){
			//目标文件存在 删除
			delete(targetFile);
		}else {
			new File(targetFile.getParent()).mkdirs();
		}
		
		//文件输入流
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream bInput = new BufferedInputStream(input);
		
		//文件 输出流
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream bOutput = new BufferedOutputStream(output);
		
		//缓冲大小 4k
		byte[] b = new byte[1024 * 4];
		
		//进行copy
		int length = 0;
		while( (length = bInput.read(b)) != -1){
			//写length长度 防止 null
			bOutput.write(b,0,length);
		}
		
		//刷新输出流
		bOutput.flush();
		
		//关闭流
		bInput.close();
		input.close();
		bOutput.close();
		output.close();
	}
	
	/**
	 * 根据路径进行文件复制
	 * @param sourcePath 源文件路径
	 * @param targetPath 目标文件路径
	 * @throws IOException
	 */
	public static void copyFile(String sourcePath, String targetPath) throws IOException{
		copyFile(new File(sourcePath), new File(targetPath));
	}
	
	/**
	 * 重命名文件夹
	 * @param sourcePath
	 * @param newPath
	 * @throws IOException
	 */
	public static boolean rename(String sourcePath, String newPath) throws IOException{
		File old = new File(sourcePath);
		if(!old.exists()){
			throw new IOException("源文件夹不存在");
		}
		String rootPath = old.getParent();
		File newFile = new File(rootPath+File.separator+newPath);
		if(old.getAbsolutePath().equals(newFile.getAbsolutePath())){
			//名字一致时  直接返回
			return true;
		}
		if(newFile.exists()){
			//如果重命名的文件夹已经存在 删掉 已经存在的文件
			delete(newFile);
		}
		return old.renameTo(newFile);
	}
	
	/**
	 * 文件夹复制
	 * @param sourceDir 源目录
	 * @param targetDir 目标目录
	 */
	public static void copyDir(String sourceDir, String targetDir) throws IOException{
		File targetFiles = new File(targetDir);
		if(targetFiles.exists()){
			throw new IOException("目标文件夹已经存在");
		}
		
		targetFiles.mkdir();
		
		File[] files = (new File(sourceDir)).listFiles();
		
		for(File file : files){
			if(file.isDirectory()){
				//递归复制文件夹
				copyDir(sourceDir + File.separator + file.getName(), targetDir + File.separator + file.getName());
			}
			if(file.isFile()){
				//复制文件
				copyFile(sourceDir + File.separator + file.getName(), targetDir + File.separator + file.getName());
			}
		}
		
	}
	
	/**
	 * 删除文件或者文件夹
	 * @param file
	 * @return
	 */
	public static boolean delete(File file){
		if(file.isDirectory()){
			File[] childFiles = file.listFiles();
			for(File f : childFiles){
				boolean success = delete(f);
				if(!success){
					//删除失败的话 直接返回
					return false;
				}
			}
		}
		return file.delete();
	}
	
	/**
	 * 根据路径删除文件或者文件夹
	 * @param path
	 * @return
	 */
	public static boolean delete(String path){
		return delete(new File(path));
	}
	
	/**
	 * 压缩文件夹
	 * @param sourcePaths 源文件路径
	 * @param targetPath  目标zip文件路径
	 * @return
	 * @throws IOException
	 */
	public static void compressZip(List<String> sourcePaths, String targetPath) throws IOException{
		List<File> files = new ArrayList<File>();
		for(String path:sourcePaths){
			files.add(new File(path));
		}
		
		compressZip(files, new File(targetPath));
	}
	
	/**
	 *  压缩文件夹
	 * @param sourceFiles
	 * @param targetFile
	 * @throws IOException
	 */
	public static void compressZip(List<File> sourceFiles, File targetFile) throws IOException{
		
		if(targetFile.exists()){
			throw new IOException("目标文件已经存在");
		}
		
		String targetPath = targetFile.getAbsolutePath();
		targetPath = targetPath.substring(0,targetPath.lastIndexOf(File.separator));
		
		File targetDir = new File(targetPath);
		if(!targetDir.exists()){
			targetDir.mkdir();
		}
		
		ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(targetFile));
		BufferedOutputStream bOutput = new BufferedOutputStream(zipOutput);
		compressZip(zipOutput, bOutput, sourceFiles, "");
		bOutput.close();
		zipOutput.close();
		
	}
	
	/**
	 *  解压zip文件至当前目录
	 * @param zipPath zip 路径
	 * @throws IOException
	 */
	public static void decompressZip(String zipPath) throws IOException {
		decompressZip(new File(zipPath), null);
	}
	
	/**
	 * 解压ZIP
	 * @param zipPath zip 路径
 	 * @param targetDir 解压目标目录
	 * @throws IOException
	 */
	public static void decompressZip(String zipPath, String targetDir) throws IOException {
		decompressZip(new File(zipPath), targetDir);
	}
	
	/**
	 * 解压zip、
	 * @param zipFile zip文件
	 * @param targetDir 解压目标目录
	 * @throws IOException
	 */
	public static void decompressZip(File zipFile, String targetDir) throws IOException {
		decompressZip(zipFile, targetDir, null);
	}
	
	
	
	/**
	 *  解压zip   并将文件夹名称替换为newPath
	 * @param zipFile
	 * @param targetDir
	 * @param newPath
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void decompressZip(File zipFile, String targetDir, String newName) throws IOException {
		
		if(StringUtils.isEmpty(targetDir)){
			//默认解压至当前目录
			targetDir = zipFile.getAbsolutePath().substring(0,zipFile.getAbsolutePath().lastIndexOf("."));
		}
		
		if(!targetDir.endsWith(File.separator)){
			targetDir += File.separator;
		}
		
		//校验 zip文件存在
		if(!zipFile.exists()){
			throw new IOException("zip 文件不存在");
		}
		
		//校验目标文件是文件夹且不能为空
		File targetFiles = new File(targetDir);
		if(targetFiles.exists()){
			if(targetFiles.isFile()){
				throw new IOException("目标文件已经存在，且是文件格式");
			}
			
			if(targetFiles.isDirectory()){
				File[] files = targetFiles.listFiles();
				
				if(files!=null && files.length > 0){
					
//					throw new IOException("目标文件夹已经存在，且不为空");
				}
				
			}
		}else{
			targetFiles.mkdirs();
		}
		
		//校验zip 
		String suffix = zipFile.getAbsolutePath().substring(zipFile.getAbsolutePath().lastIndexOf("."));
		if(!suffix.equalsIgnoreCase(".zip")){
			throw new IOException("ZIP文件类型不符");
		}
		
		//遍历zip文件并解压
		ZipFile mZipFile = new ZipFile(zipFile);
		Enumeration entry = mZipFile.entries();
		ZipEntry zipEntry;
		String sourcePath = null;
		while (entry.hasMoreElements()) {
			zipEntry = (ZipEntry) entry.nextElement();
			File outFile = new File(targetDir + zipEntry.getName());
			if(sourcePath == null){
				//第一个文件 即为压缩的总文件夹
				sourcePath = targetDir + zipEntry.getName();
			}
			if(zipEntry.isDirectory()){
				outFile.mkdir();
				continue;
			}else if (!outFile.getParentFile().exists()){
				outFile.getParentFile().mkdir();
			}
			OutputStream outputStream = new FileOutputStream(outFile);
            InputStream inputStream = mZipFile.getInputStream(zipEntry);
            int len;
            byte[] buffer = new byte[8192];
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
			
		}
		
		mZipFile.close();
		
		if(newName != null){
			rename(sourcePath, newName);
		}
		
	}
	
	
	
	/**
	 *  压缩文件夹
	 * @param zipOut
	 * @param out
	 * @param sourceFiles
	 * @param path
	 * @throws IOException
	 */
	private static void compressZip(ZipOutputStream zipOut, BufferedOutputStream out,List<File> sourceFiles,String path) throws IOException{
		
		for(File file : sourceFiles){
			compressZip(zipOut, out, file,path);
		}
		out.flush();
	}
	
	/**
	 * 压缩单个文件
	 * @param zipOut
	 * @param out
	 * @param file
	 * @param filePath
	 * @throws IOException
	 */
	private static void compressZip(ZipOutputStream zipOut, BufferedOutputStream out,File file, String filePath) throws IOException{
		
		if(StringUtils.isEmpty(filePath) && !filePath.endsWith(File.separator)){
			filePath += File.separator;
		}
		
		if(file.isFile()){
			//压缩文件
			zipOut.putNextEntry(new ZipEntry(filePath + file.getName()));
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            int bytesRead;
            while (-1 != (bytesRead = in.read())) {
                out.write(bytesRead);
            }
            
            in.close();
		}
		
		if(file.isDirectory()){
			File[] fileArr = file.listFiles();
			if (null == fileArr || fileArr.length == 0) {
				//空文件夹
                zipOut.putNextEntry(new ZipEntry(filePath + file.getName() + File.separator));
            } else {
            	//递归压缩
            	compressZip(zipOut, out, Arrays.asList(fileArr), filePath + file.getName() + File.separator); 
            }
		}
		
	}
	

}
