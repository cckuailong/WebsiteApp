package com.lovebear.spider;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import com.lovebear.entity.internation;

public class isSimilar {

	public Vector<String> participle(String str)
	{
		Vector<String> str1 = new Vector<String>();
		try
		{
		    StringReader reader = new StringReader(str);
		    IKSegmenter ik = new IKSegmenter(reader,true); 
		    Lexeme lexeme = null;
		    while((lexeme = ik.next()) != null)
		    	str1.add(lexeme.getLexemeText());
		    if(str1.size() == 0)
		    	return null;
		    
		}
		catch(IOException e1)
		{
			System.out.println();
		}
		return str1;
	}
	
	public double YUZHI = 0.1 ;
	public double getSimilarity(Vector<String> T1, Vector<String> T2) throws Exception
	{
		int size = 0,size2 = 0;
	    if ( T1 != null && ( size = T1.size() ) > 0 && T2 != null && ( size2 = T2.size() ) > 0 ) {
	        
	    	Map<String, double[]> T = new HashMap<String, double[]>();
	        
	        //T1和T2的并集T
	    	String index = null ;
	        for ( int i = 0 ; i < size ; i++ )
	        {
	        	index = T1.get(i);
	            if( index != null)
	            {
	            	double[] c = null;
	                c = new double[2];
	                c[0] = 1;
	                c[1] = YUZHI;
	                T.put( index, c);
	            }
	        }
	 
	        for ( int i = 0; i < size2 ; i++ )
	        {
	        	index = T2.get(i);
	        	if( index != null )
	        	{
	        		double[] c = T.get( index );
	        		if(c != null && c.length == 2)
	        			c[1] = 1;
	        		else
	                {
	                    c = new double[2];
	                    c[0] = YUZHI;
	                    c[1] = 1;
	                    T.put( index , c);
	                }
	            }
	        }
	        Iterator<String> it = T.keySet().iterator();
	        double s1 = 0 , s2 = 0, Ssum = 0;
	        while(it.hasNext())
	        {
	        	double[] c = T.get( it.next() );
	        	Ssum += c[0]*c[1];
	        	s1 += c[0]*c[0];
	        	s2 += c[1]*c[1];
	        }
	        return Ssum / Math.sqrt( s1*s2 );
	    }
	    else
	        throw new Exception("传入参数有问题！");
	}
	
	public boolean isSimilar(String str1, String str2){
		try
		{
			double pro;
			isSimilar sim=new isSimilar();
			pro=sim.getSimilarity(sim.participle(str1),sim.participle(str2));
			if(pro>0.42){
				return true;
			}else{
				return false;
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		};
		return false;
	}

}
