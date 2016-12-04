package sinaSpider;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.lovebear.entity.internation;
import com.lovebear.entity.internationId;

public class sinaInternation {

private ArrayList<internation> sdList = new ArrayList<internation>();
	
	public ArrayList<internation> getSdList() {
		return sdList;
	}

	public void setSdList(ArrayList<internation> sdList) {
		this.sdList = sdList;
	}

	public void extractDiv(String url){
		try {
			List<String> urlList=new ArrayList<String>();
			List<String> titleList=new ArrayList<String>();
			List<String> DataList=new ArrayList<String>();
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("id","subShowContent1"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
            if(nodes!=null) {
                if(nodes.size()!=0) {
                	
                    Node textnode = (Node)nodes.elementAt(0);
                    String newhtml = textnode.toHtml();
                    
                    urlList=extractUrl(newhtml);
                    titleList=extractTitle(newhtml);
                    DataList=extractDate(newhtml);
                    for(int i=0;i<urlList.size();i++){
                    	internation sd=new internation(new internationId());
	                    sd.getId().setUrl(urlList.get(i));
	                    sd.getId().setTitle(titleList.get(i));
	                    sd.getId().setDate(DataList.get(i));
	                    sd.getId().setAuthorName("新浪新闻");
	                    sd.getId().setThumbnailPicS("NAN");
	                    sd.getId().setSource("新浪新闻");
	                    sd.getId().setType("国际");
	                    sdList.add(sd);
                    }
                    
                }
            }            
		} catch (ParserException e) {
			e.printStackTrace();
		}

	}
	
	public List<String> extractUrl(String url){
		try {
			List<String> list=new ArrayList<String>();
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("target","_blank"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<nodes.size();i++) {           	
            	LinkTag node = (LinkTag) nodes.elementAt(i);
                String title = node.getAttribute("href");
                if(title.startsWith("http://comment")){
                	continue;
                }
                list.add(title);
            }
			return list;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> extractTitle(String url){
		try {
			List<String> list=new ArrayList<String>();
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("target","_blank"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<nodes.size();i++){           	
            	LinkTag node = (LinkTag) nodes.elementAt(i);
                String title = node.getStringText();
                if(title.length()>=20){
                	title=title.substring(4, 19)+"...";
                }
                if(title.startsWith("评论")){
                	continue;
                }
                list.add(title);
                
            }
			return list;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> extractDate(String url){
		try {
			List<String> list=new ArrayList<String>();
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","time"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			for(int i=0;i<nodes.size();i++){           	
            	Div node = (Div) nodes.elementAt(i);
                String date = node.getStringText();
                list.add(date);
                
            }
			return list;
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
}
