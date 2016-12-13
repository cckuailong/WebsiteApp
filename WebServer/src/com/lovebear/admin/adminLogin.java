package com.lovebear.admin;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DaoSupport;

import com.lovebear.admindao.EntertainDao;
import com.lovebear.admindao.FinanceDao;
import com.lovebear.admindao.InternationDao;
import com.lovebear.admindao.MilitaryDao;
import com.lovebear.admindao.SocietyDao;
import com.lovebear.admindao.SportDao;
import com.lovebear.admindao.TechDao;
import com.lovebear.admindao.TopDao;
import com.lovebear.admindao.UserInfoDao;
import com.lovebear.dao.UserDao;
import com.lovebear.entity.Data;
import com.lovebear.entity.User;
import com.lovebear.entity.entertain;
import com.lovebear.entity.finance;
import com.lovebear.entity.internation;
import com.lovebear.entity.military;
import com.lovebear.entity.society;
import com.lovebear.entity.sport;
import com.lovebear.entity.tech;
import com.opensymphony.xwork2.ActionSupport;

public class adminLogin extends ActionSupport{

	private String user;
	private String pwd;
	private String isSelected;
	private String uniquekey;
	private String uid;
	private TopDao topDao;
	private List<Data> topdata = new ArrayList<Data>();
	private FinanceDao financeDao;
	private List<finance> financedata = new ArrayList<finance>();
	private EntertainDao entertainDao;
	private List<entertain> entertaindata = new ArrayList<entertain>();
	private SportDao sportDao;
	private List<sport> sportdata = new ArrayList<sport>();
	private MilitaryDao militaryDao;
	private List<military> militarydata = new ArrayList<military>();
	private TechDao techDao;
	private List<tech> techdata = new ArrayList<tech>();
	private InternationDao internationDao;
	private List<internation> internationdata = new ArrayList<internation>();
	private SocietyDao societyDao;
	private List<society> societydata = new ArrayList<society>();
	private UserInfoDao userInfoDao;
	private List<User> userInfodata = new ArrayList<User>();
	

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	public List<User> getUserInfodata() {
		return userInfodata;
	}

	public void setUserInfodata(List<User> userInfodata) {
		this.userInfodata = userInfodata;
	}

	public String getUniquekey() {
		return uniquekey;
	}

	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}
	
	public TopDao getTopDao() {
		return topDao;
	}

	public void setTopDao(TopDao topDao) {
		this.topDao = topDao;
	}

	public List<Data> getTopdata() {
		return topdata;
	}

	public void setTopdata(List<Data> topdata) {
		this.topdata = topdata;
	}
	

	public FinanceDao getFinanceDao() {
		return financeDao;
	}

	public void setFinanceDao(FinanceDao financeDao) {
		this.financeDao = financeDao;
	}

	public List<finance> getFinancedata() {
		return financedata;
	}

	public void setFinancedata(List<finance> financedata) {
		this.financedata = financedata;
	}

	public EntertainDao getEntertainDao() {
		return entertainDao;
	}

	public void setEntertainDao(EntertainDao entertainDao) {
		this.entertainDao = entertainDao;
	}

	public List<entertain> getEntertaindata() {
		return entertaindata;
	}

	public void setEntertaindata(List<entertain> entertaindata) {
		this.entertaindata = entertaindata;
	}

	public SportDao getSportDao() {
		return sportDao;
	}

	public void setSportDao(SportDao sportDao) {
		this.sportDao = sportDao;
	}

	public List<sport> getSportdata() {
		return sportdata;
	}

	public void setSportdata(List<sport> sportdata) {
		this.sportdata = sportdata;
	}

	public MilitaryDao getMilitaryDao() {
		return militaryDao;
	}

	public void setMilitaryDao(MilitaryDao militaryDao) {
		this.militaryDao = militaryDao;
	}

	public List<military> getMilitarydata() {
		return militarydata;
	}

	public void setMilitarydata(List<military> militarydata) {
		this.militarydata = militarydata;
	}

	public TechDao getTechDao() {
		return techDao;
	}

	public void setTechDao(TechDao techDao) {
		this.techDao = techDao;
	}

	public List<tech> getTechdata() {
		return techdata;
	}

	public void setTechdata(List<tech> techdata) {
		this.techdata = techdata;
	}

	public InternationDao getInternationDao() {
		return internationDao;
	}

	public void setInternationDao(InternationDao internationDao) {
		this.internationDao = internationDao;
	}

	public List<internation> getInternationdata() {
		return internationdata;
	}

	public void setInternationdata(List<internation> internationdata) {
		this.internationdata = internationdata;
	}

	public SocietyDao getSocietyDao() {
		return societyDao;
	}

	public void setSocietyDao(SocietyDao societyDao) {
		this.societyDao = societyDao;
	}

	public List<society> getSocietydata() {
		return societydata;
	}

	public void setSocietydata(List<society> societydata) {
		this.societydata = societydata;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	public String adminLogin(){
		if(user.equals("lovebear") && pwd.equals("admin")){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	//top view and delete
	public String topView() {
		
		topdata = topDao.topFindAll();

		return SUCCESS;
	}
	
	public String topDeleteByUniq() {
		topDao.topDelete(uniquekey);
		topdata=topDao.topFindAll();
		return SUCCESS;
	}
	
	public String topDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				topDao.topDelete(s);
			}
		}
		topdata=topDao.topFindAll();
		return SUCCESS;
	}
	//finance view and delete
	public String financeView() {			
		financedata = financeDao.financeFindAll();
		return SUCCESS;
	}
		
	public String financeDeleteByUniq() {
		financeDao.financeDelete(uniquekey);
		financedata=financeDao.financeFindAll();
		return SUCCESS;
	}
	
	public String financeDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				financeDao.financeDelete(s);
			}
		}
		
		financedata=financeDao.financeFindAll();
		return SUCCESS;
	}
	//entertain view and delete
	public String entertainView() {			
		entertaindata = entertainDao.entertainFindAll();
		return SUCCESS;
	}
			
	public String entertainDeleteByUniq() {
		entertainDao.entertainDelete(uniquekey);
		entertaindata=entertainDao.entertainFindAll();
		return SUCCESS;
	}
	
	public String entertainDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				entertainDao.entertainDelete(s);
			}
		}
		
		entertaindata=entertainDao.entertainFindAll();
		return SUCCESS;
	}
	//sport view and delete
	public String sportView() {			
		sportdata = sportDao.sportFindAll();
		return SUCCESS;
	}
			
	public String sportDeleteByUniq() {
		sportDao.sportDelete(uniquekey);
		sportdata=sportDao.sportFindAll();
		return SUCCESS;
	}
	
	public String sportDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				sportDao.sportDelete(s);
			}
		}
		
		sportdata=sportDao.sportFindAll();
		return SUCCESS;
	}
	//military view and delete
	public String militaryView() {			
		militarydata = militaryDao.militaryFindAll();
		return SUCCESS;
	}
			
	public String militaryDeleteByUniq() {
		militaryDao.militaryDelete(uniquekey);
		militarydata=militaryDao.militaryFindAll();
		return SUCCESS;
	}
	
	public String militaryDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				militaryDao.militaryDelete(s);
			}
		}
		
		militarydata=militaryDao.militaryFindAll();
		return SUCCESS;
	}
	//tech view and delete
	public String techView() {			
		techdata = techDao.techFindAll();
		return SUCCESS;
	}
			
	public String techDeleteByUniq() {
		techDao.techDelete(uniquekey);
		techdata=techDao.techFindAll();
		return SUCCESS;
	}
	
	public String techDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				techDao.techDelete(s);
			}
		}
		
		techdata=techDao.techFindAll();
		return SUCCESS;
	}
	//internation view and delete
	public String internationView() {			
		internationdata = internationDao.internationFindAll();
		return SUCCESS;
	}
			
	public String internationDeleteByUniq() {
		internationDao.internationDelete(uniquekey);
		internationdata=internationDao.internationFindAll();
		return SUCCESS;
	}
	
	public String internationDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				internationDao.internationDelete(s);
			}
		}
		
		internationdata=internationDao.internationFindAll();
		return SUCCESS;
	}
	//society view and delete
	public String societyView() {			
		societydata = societyDao.societyFindAll();
		return SUCCESS;
	}
			
	public String societyDeleteByUniq() {
		societyDao.societyDelete(uniquekey);
		societydata=societyDao.societyFindAll();
		return SUCCESS;
	}
	
	public String societyDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				societyDao.societyDelete(s);
			}
		}
		
		societydata=societyDao.societyFindAll();
		return SUCCESS;
	}
	//userInfo view and delete
	public String userInfoView() {			
		userInfodata = userInfoDao.userInfoFindAll();
		return SUCCESS;
	}
			
	public String userInfoDeleteByUid() {
		userInfoDao.userInfoDelete(uid);
		userInfodata=userInfoDao.userInfoFindAll();
		return SUCCESS;
	}
	
	public String userInfoDeleteMore() {
		if(isSelected!=null){
			String[] list=isSelected.split(", ");
			for(String s:list){
				userInfoDao.userInfoDelete(s);
			}
		}
		
		userInfodata=userInfoDao.userInfoFindAll();
		return SUCCESS;
	}
	
	
}
