package com.tlh.action;

import com.opensymphony.xwork2.ActionSupport;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.tlh.model.UploadTokenModel;

public class uploadAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private UploadTokenModel model;
	String ACCESS_KEY = "_V0bLP4GUyxV2YvslWgJiU44c1BytVaDyMdV5odQ";
	String SECRET_KEY = "yMrS_usxKDZ4hVYavSKEBdFp1PdQgNkPYUJ7H1RA";
	  //Ҫ�ϴ��Ŀռ�
	  String bucketname = "reportsystem";
	//��Կ����
	  Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
	  //�����ϴ�����
	  UploadManager uploadManager = new UploadManager();
	  
		public UploadTokenModel getModel() {
			if(model==null){
				model=new UploadTokenModel();
			}
			return model;
		}
	  //���ϴ���ʹ��Ĭ�ϲ��ԣ�ֻ��Ҫ�����ϴ��Ŀռ����Ϳ�����
	  public String getUpToken(){
		  getModel().setUptoken(auth.uploadToken(bucketname));
	      return SUCCESS;
	  }
	  /*
		public void validate() {
			super.validate();
			if (!(this instanceof AccountAction)) {
				UserInfo user = UserUtils.getUserFromSession();
				if (user == null) {
					try {
						Utils.getResponse()
								.sendRedirect(
										Utils.getRequest().getContextPath()
												+ "/login.html");
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					//ֱ�ӷ���
					addFieldError("", "");
				}
			}
		}*/
}
