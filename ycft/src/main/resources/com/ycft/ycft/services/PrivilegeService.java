package com.ycft.ycft.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ycft.ycft.mapper.PrivilegeMapper;
import com.ycft.ycft.po.Privilege;

@Service
public class PrivilegeService {
	
	@Autowired
	PrivilegeMapper pm;
	
	private final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	public List<Privilege> testQueryMenuList() {
	    // ԭʼ������
	    List<Privilege> rootMenu = pm.queryMenuList(null);
	    
	    // �鿴���
	    for (Privilege menu : rootMenu) {
	        System.out.println(menu);
	    }
	    // ���Ľ��
	    List<Privilege> menuList = new ArrayList<Privilege>();
	    // ���ҵ����е�һ���˵�
	    for (int i = 0; i < rootMenu.size(); i++) {
	        // һ���˵�û��parentId
	        if (rootMenu.get(i).getpId()==null) {
	            menuList.add(rootMenu.get(i));
	        }
	    }
	    System.out.println(menuList.size());
	    // Ϊһ���˵������Ӳ˵���getChild�ǵݹ���õ�
	    for (Privilege menu : menuList) {
	        menu.setChildMenus(getChild(menu.getId(), rootMenu));
	    }
	    Map<String,Object> jsonMap = new HashMap<String,Object>();
	    jsonMap.put("menu", menuList);
	    System.out.println(gson.toJson(jsonMap));
	    return menuList;
	}

	/**
	 * �ݹ�����Ӳ˵�
	 * 
	 * @param id
	 *            ��ǰ�˵�id
	 * @param rootMenu
	 *            Ҫ���ҵ��б�
	 * @return
	 */
	private List<Privilege> getChild(int id, List<Privilege> rootMenu) {
	    // �Ӳ˵�
	    List<Privilege> childList = new ArrayList<Privilege>();
	    for (Privilege menu : rootMenu) {
	        // �������нڵ㣬�����˵�id�봫������id�Ƚ�
	        if (menu.getpId()!=null) {
	            if (menu.getpId()==id) {
	                childList.add(menu);
	            }
	        }
	    }
	    // ���Ӳ˵����Ӳ˵���ѭ��һ��
	    for (Privilege menu : childList) {// û��url�Ӳ˵������Ӳ˵�
	        if (StringUtils.isBlank(menu.getmUrl())) {
	            // �ݹ�
	            menu.setChildMenus(getChild(menu.getId(), rootMenu));
	        }
	    } // �ݹ��˳�����
	    if (childList.size() == 0) {
	        return null;
	    }
	    return childList;
	}
}
