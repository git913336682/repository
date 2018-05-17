package com.ycft.ycft.services.backstage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ycft.ycft.mapper.UserMapper;
import com.ycft.ycft.po.User;
import com.ycft.ycft.tools.MD5;
import aj.org.objectweb.asm.Label;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@Service(value="BSUserSer")
public class UserSer {

	@Autowired
	private UserMapper um;
	
	public boolean userAjax(String sno,String psd) {
		boolean flag = false;
		psd = MD5.md5Password(psd);
		if (um.bsLogin(sno).getPsd().equals(psd)) {
			flag = true;
		}
		return flag;
	}
	/**
	 * ��Ϣ��ѯ[ȫ����ѯ]
	 * 
	 * @author 
	 * @param wd 
	 * @return List<wddentInfo> ������������Ϣ
	 */
	public List<User> selective(User us){
		
		//ѡ���Բ�ѯ
		List<User> list = um.selective(us);

		return list;
	}
	
	//���ص���ģ��
		public void downloadDemo(HttpServletResponse response , HttpServletRequest request){
			//demo�ļ�������
			String fileName = "demo.xls";
			String filepath = request.getSession().getServletContext().getRealPath("/") + "demo/" +fileName;
			File file = new File(filepath);  
	        InputStream inputStream = null;  
	        OutputStream outputStream = null;  
	        byte[] b= new byte[1024];  
	        int len = 0;  
	        try {  
	            inputStream = new FileInputStream(file);  
	            outputStream = response.getOutputStream();  
	              
	            response.setContentType("application/force-download");  
	            //�ļ����ͽ�ԭ��������
	            String filename = file.getName();  
	            
	            response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));  
	            response.setContentLength( (int) file.length( ) );  
	              
	            while((len = inputStream.read(b)) != -1){  
	                outputStream.write(b, 0, len);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }finally{  
	            if(inputStream != null){  
	                try {  
	                    inputStream.close();  
	                    inputStream = null;  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if(outputStream != null){  
	                try {  
	                    outputStream.close();  
	                    outputStream = null;  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }
			
		}
		
		/**
		 * ������Ϣ
		 * 
		 * @param User
		 * @param request
		 * @param response
		 */
		public boolean exportExcel(User user,HttpServletResponse response){
			
			//������д������
			WritableWorkbook bWorkbook = null;
			OutputStream os = null;
			try {
				//��ѯ����
				List<User> uList =  selective(user);
				if(uList!= null && !uList.isEmpty()){
					response.reset();
					response.setContentType("application/vnd.ms-excel"); //��֤������
					String fileName = "1.xls";
					/* //����һ��ֵ����attachment�����������Ĺؼ����趨�����ֵ��������ͻ�����ʵʵ����ʾ���Ϊ�Ի���������ֵ��� inline����������������������Զ���������֪�����ĳ�����ļ���
					response.addHeader("Content-Disposition","attachment; filename=\""+ new String(fileName.getBytes("gb2312"),"iso8859-1") + "\""); */
					
					response.setHeader("Content-Disposition", "attachment;"+ " filename="+ new String(fileName.getBytes(), "ISO-8859-1"));
					os = response.getOutputStream();
						//����excel����
						bWorkbook = Workbook.createWorkbook(os);
						//ͨ��excel���󴴽�һ��ѡ�����
						WritableSheet sheet = bWorkbook.createSheet("��ˮ��Ϣ", 0);
						for(int i = 0;i < uList.size();i++){
							
							//��ʼ���Ʊ�ͷ
				/*			sheet.addCell(new Label(0, 0, "��ˮ��")); 
			                sheet.addCell(new Label(1, 0, "����"));
			                sheet.addCell(new Label(2, 0, "����"));
			                sheet.addCell(new Label(3, 0, "����Ա"));
			                sheet.addCell(new Label(4, 0, "����ʱ��"));*/
			               
			                //��ʼ������������
			           /*     sheet.addCell(new Label(0, i + 1,  uList.get(i).getWaterQuantity()+"") );
			                sheet.addCell(new Label(1, i + 1,  uList.get(i).getArea()) );
			                sheet.addCell(new Label(2, i + 1,  uList.get(i).getDate()));
			                sheet.addCell(new Label(3, i + 1,  uList.get(i).getOperatePeople()) );
			                sheet.addCell(new Label(4, i + 1,  uList.get(i).getOperateDate()) );*/
			                
						}
							// ����һ����Ԫ����󣬵�һ��Ϊ�У��ڶ���Ϊ�У�������Ϊֵ  
				            //Label label = new Label(0, 2, "test");  
				            // �������õĵ�Ԫ�����ѡ���  
				            //sheet.addCell(label);  
				            // д��Ŀ��·��  
				            bWorkbook.write();
					} 
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}finally {  
					try {
						//�ر�
						if(bWorkbook != null){
							bWorkbook.close();
						}
						if(os != null){
							os.close();
						}
						
					} catch (WriteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		             
		        }
		}
		/**
		 * ����excel�ļ�
		 * @param file Excel�ļ�
		 * @return
		 */
		@SuppressWarnings("deprecation")
		public boolean importExcel(MultipartFile file) throws Exception{
			InputStream input = null;
			try {
				input = file.getInputStream();
		        Workbook bWorkbook = null;  
		        	
		        bWorkbook = Workbook.getWorkbook(input);
		        // ��õ�һ�����������
		        Sheet sheet = bWorkbook.getSheet(0);   
		        	int rows = sheet.getRows();
		        	List<User> uList = new ArrayList<User>();
		            //int columns = sheet.getColumns();
		        	System.out.println("rows==="+rows);
		            for(int i = 1;i < rows;i++){
		            	User user = new User();
		            	//��ˮ��
		            /*	Cell cell = sheet.getCell(0, i);
		                String waterQuantity = cell.getContents();
		                user.setWaterQuantity(Double.parseDouble(waterQuantity));
		                //����
		            	Cell cell1 = sheet.getCell(1, i);
		                String area = cell1.getContents();
		                user.setArea(area);
		                //ʱ��
		            	Cell cell2 = sheet.getCell(2, i);
		                String date = cell2.getContents();
		                user.setDate(date);
		                //������
		            	Cell cell3 = sheet.getCell(3, i);
		                String operatePeople = cell3.getContents();
		                user.setOperatePeople(operatePeople);
		                //����ʱ��
		            	Cell cell4 = sheet.getCell(4, i);
		                String operateDate = cell4.getContents();
		                user.setOperateDate(operateDate);*/
		                //װ��
		                uList.add(user);
		            }
		            return um.batchInsert(uList) >= 1 ? true : false;    
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}finally{
				if(input != null){
					input.close();
				}
			}
		}
		
	
	
}
