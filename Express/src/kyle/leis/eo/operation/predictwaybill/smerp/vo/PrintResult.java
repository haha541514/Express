package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.Arrays;

/**
 * ��ӡ��ǩ��ȷʱ������
 * 
 * **/
public class PrintResult{
	 private String FileName;//string �ļ���
     private String FileType;//string �ļ�����
     private byte[] Bytes;//byte[] ���� ԭʼ���ļ��ֽ�
     private String Base64Bytes;//string ԭʼ���ļ��ֽھ���base64�������ַ���
     private String MD5;//string �ļ���md5ֵ
     
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