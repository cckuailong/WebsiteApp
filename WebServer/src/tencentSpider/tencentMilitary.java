package tencentSpider;

import java.util.ArrayList;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import com.lovebear.entity.internation;
import com.lovebear.entity.internationId;
import com.lovebear.entity.military;
import com.lovebear.entity.militaryId;

public class tencentMilitary {

private ArrayList<military> tmList = new ArrayList<military>();
	
	public ArrayList<military> getTmList() {
		return tmList;
	}

	public void setTmList(ArrayList<military> tmList) {
		this.tmList = tmList;
	}

	public void extractDiv(String url){
		try {
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","Q-tpList"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			System.out.println(nodes.size());
            if(nodes!=null) {
                for (int i = 0; i < nodes.size()-1; i++) {
                	military sd=new military(new militaryId());
                    Node textnode = (Node)nodes.elementAt(i);
                    String newhtml = textnode.toHtml();
                    sd.getId().setThumbnailPicS(extractImg(newhtml));
                    sd.getId().setUrl(extractUrl(newhtml));
                    sd.getId().setTitle(extractTitle(newhtml));
                    sd.getId().setAuthorName(extractAuthor(newhtml));
                    sd.getId().setDate("NAN");
                    sd.getId().setSource("腾讯新闻");
                    sd.getId().setType("国际");
                    tmList.add(sd);
                }
            }            
		} catch (ParserException e) {
			e.printStackTrace();
		}

	}
	
	public String extractImg(String url){
		try {
			int start=0,end=0;
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("img"),new HasAttributeFilter("class","picto"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if(nodes.size()!=0) {           	
            	ImageTag node = (ImageTag) nodes.elementAt(0);
                String src = node.getText();
                start=src.indexOf("http");
                end = src.indexOf("0/0");
                src=src.substring(start, end+3);
                return src;
            }
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String extractUrl(String url){
		try {
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("class","pic"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if(nodes.size()!=0) {           	
            	LinkTag node = (LinkTag) nodes.elementAt(0);
                String title = node.getAttribute("href");
                return title;
            }
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String extractTitle(String url){
		try {
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("class","linkto"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if(nodes.size()!=0){           	
            	LinkTag node = (LinkTag) nodes.elementAt(0);
                String title = node.getStringText();
                return title;
            }
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String extractAuthor(String url){
		try {
			Parser parser=new Parser(url);
			parser.setEncoding("utf-8");
			AndFilter filter =    
	                new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("class","from"));
			NodeList nodes = parser.extractAllNodesThatMatch(filter);
			if(nodes.size()!=0){           	
            	Span node = (Span) nodes.elementAt(0);
                String author = node.getStringText();
                return author;
            }
		} catch (ParserException e) {
			e.printStackTrace();
		}
		return null;
	}
}
