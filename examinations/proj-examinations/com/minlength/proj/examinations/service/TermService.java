package com.minlength.proj.examinations.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.minlength.platform.core.processor.Processor;
import com.minlength.platform.core.utils.JSONUtil;
import com.minlength.platform.core.web.processor.SouthInterfaceProcessor;
import com.minlength.proj.examinations.vo.Configuration;

public class TermService {
	private Long interval;
	private Long lastUpTime;
	private String sdate;
	private String edate;
	
	@Resource
	private Processor processor;
	
	@Resource
	private Configuration configuration;
	
	@Resource
	private ExaminationService service;
	
	public Long getInterval() {
		return interval;
	}
	public void setInterval(Long interval) {
		this.interval = interval;
	}
	public Long getLastUpTime() {
		return lastUpTime;
	}
	private void setLastUpTime(Long lastUpTime) {
		this.lastUpTime = lastUpTime;
	}
	public String getSdate() {
		return sdate;
	}
	private void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getEdate() {
		return edate;
	}
	private void setEdate(String edate) {
		this.edate = edate;
	}
	public Boolean needUpdate() {
		if (lastUpTime == null || lastUpTime == 0l) {
			return true;
		}
		Long now = new Date().getTime();
		if (now - lastUpTime > getInterval()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void update() {
		String webresource = service.getTerms(null);
		Map<String,Object> listm = JSONUtil.parseJson2Map(webresource);
		List list = (List) listm.get("data");
		for (Object object : list) {
			if(Boolean.parseBoolean(((Map)object).get("iscurrent").toString())) {
				setSdate(((Map)object).get("sdate").toString());
				setEdate(((Map)object).get("edate").toString());
				setLastUpTime(new Date().getTime());
				break;
			}
		}
	}
}
