package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.Arrays;

/**
 * 打印标签正确时返回类
 * 
 * **/
public class PrintResult{
	 private String FileName;//string 文件名
     private String FileType;//string 文件类型
     private byte[] Bytes;//byte[] 数组 原始的文件字节
     private String Base64Bytes;//string 原始的文件字节经过base64编码后的字符串
     private String MD5;//string 文件的md5值
     
    /************Set && Get************/
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public byte[] getBytes() {
		return Bytes;
	}
	public void setBytes(byte[] bytes) {
		Bytes = bytes;
	}
	public String getBase64Bytes() {
		return Base64Bytes;
	}
	public void setBase64Bytes(String base64Bytes) {
		Base64Bytes = base64Bytes;
	}
	public String getMD5() {
		return MD5;
	}
	public void setMD5(String mD5) {
		MD5 = mD5;
	}
	@Override
	public String toString() {
		return "PrintResult [Base64Bytes=" + Base64Bytes + ", Bytes="
				+ Arrays.toString(Bytes) + ", FileName=" + FileName
				+ ", FileType=" + FileType + ", MD5=" + MD5 + "]";
	}

	
	
	
}