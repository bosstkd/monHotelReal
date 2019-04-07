package fct;

import java.util.ArrayList;
import java.util.List;


public class str {
	
	
	
public boolean isName(String str){
	boolean ok = true;
	if(str.length() < 1){
		ok = false;
	}else{
		for(int i = 0; i < str.length(); i++){
				try {
					Integer.parseInt(str.substring(i, i+1));
					ok = false;
				} catch (Exception e) {}
		}
	}
	
	return ok;
}



public String searchForm(String recherche){
    String rst = "";
      
   String traduce = recherche.replaceAll("avec", "et");
    traduce = traduce.replaceAll("'", " ");
  
    
    traduce = traduce.replaceAll("trouve", "");
    traduce = traduce.replaceAll("[^A-Za-z0-9 .,]", "");
    traduce = traduce.replaceAll(",", ".");
    traduce = traduce+" ";
    

    rst = traduce;
    return rst;
}


public List<String> searchWordList(String phrase){

    String ph = phrase;
    nbr NBR = new nbr();
    ph = ph.replaceAll("\\.", "");
    List<String> wrdsList = wordsList(ph);
     List<String> strList = new ArrayList<String>();
    for(String word:wrdsList){
        if(!NBR.isInt(word)) {
            if(word.length()>1){
                if(!word.equals("et")){
                        if(!word.equals("pour"))
                            if(!word.equals("avec"))
                                if(!word.equals("est"))
                                    if(!word.equals("du"))
                                        if(!word.equals("en"))
                                            if(!word.equals("non"))
                                                if(!word.equals("oui"))
                                                    if(!word.equals("il"))
                                                        if(!word.equals("elle"))
                                                            if(!word.equals("de"))
                                                                if(!word.equals("ou"))
                                                                        strList.add(word);
                }
            }           
        }
    }
    
    return strList;
}

public List<Double> doubleList(String phrase){
    List<String> strList = wordsList(phrase);
    List<Double> dbList = new ArrayList<Double>();
    nbr NBR = new nbr();
     for(String word:strList){
        if(NBR.isDouble(word)) dbList.add(NBR.toDouble(word));  
    }
     return dbList;
}

public List<Integer> intList(String phrase){
    List<String> strList = wordsList(phrase);
    List<Integer> dbList = new ArrayList<Integer>();
    nbr NBR = new nbr();
     for(String word:strList){
        if(NBR.isInt(word)) dbList.add(NBR.toInt(word));  
    }
     return dbList;
}

public List<String> wordsList(String phrase){
    String getStr = searchForm(phrase);
    List<String> wrdsList = new ArrayList<String>();

    int x = 0;
    while(getStr.contains("  ")) getStr = getStr.replaceAll("  ", " ");
   
    for(int i = 0; i < getStr.length(); i++){
        if((getStr.charAt(i)+"").equals(" ")){
            if(x==0)  
                  wrdsList.add(getStr.substring(0, i));
            else
                  wrdsList.add(getStr.substring(x+1, i));
            x = i;
        }
    }
    
    return wrdsList;
}

public boolean isMail(String str){
	
	boolean ok = true;
	if(str.length() < 9){
		ok = false;
	}else{
		int adr = 0;
		int pnt = 0;
		if(str.contains("@") && str.contains(".")){
				adr = str.indexOf("@");
				pnt = str.indexOf(".");
				if(pnt < adr) ok = false;
		adr = 0;
		pnt = 0;
				for(int i = 0; i < str.length(); i++){
					if(str.charAt(i) == '@') adr ++;
				}
		if(adr > 1) ok = false;			
		String fin = str.substring(str.indexOf("."), str.length());
		if(fin.length() < 3 || fin.length()>5) ok = false;
		}else ok = false;
		if(str.contains(" ")) ok = false;
		if(str.contains("'")) ok = false;
		if(str.contains("/")) ok = false;
		if(str.contains("\""))ok = false;
		if(str.contains("/")) ok = false;
		if(str.contains(",")) ok = false;
		if(str.contains("#")) ok = false;
		if(str.contains("$")) ok = false;
		if(str.contains("&")) ok = false;
		if(str.contains("|")) ok = false;
		if(str.contains("<")) ok = false;
		if(str.contains(">")) ok = false;
		if(str.contains("%")) ok = false;
	}

	return ok;
}

public String nameForm(String str){
	if(!isName(str)){
		str = null;
	}else{
		boolean ok = true;
		str.replaceAll("-", " ");
		str.replaceAll("_", " ");
		
		while(str.substring(0, 1).equals(" ")) {
			try {
				str = str.substring(1, str.length());
			} catch (Exception e) {
				str = null;
				ok = false;
				break;
			}
		}
	if(ok)
		while(str.substring(0, 1).equals("_")) {
			try {
				str = str.substring(1, str.length());
			} catch (Exception e) {
				str = null;
				ok = false;
				break;
			}
		}

	if(ok)
		while(str.contains("  ")) {
			str = str.replaceAll("  ", " ");
		}
	
	if(ok)
		while(str.substring(str.length()-1, str.length()).equals(" "))str = str.substring(0, str.length()-1); 
		
		if(!isName(str)){
			str = null;
			ok = false;
		}
		
		if(ok){
			str = str.substring(0, 1).toUpperCase() + str.substring(1, str.length()).toLowerCase();
			for(int i = 0; i<str.length(); i++){
				if(str.substring(i, i+1).equals(" ")){
					
					str = str.substring(0, i+1) + str.substring(i+1, i+2).toUpperCase() + str.substring(i+2, str.length()).toLowerCase();
				}
			}
		}
		
		
	}
	return str;
}

public String strToBdd(String str){
	if(str.contains("'")){
		while(str.substring(0, 1).equals("'")){
			try {
				str = str.substring(1, str.length());	
			} catch (Exception e) {
				str = null;
				break;
			}
		}
		if(str != null){
			while(str.substring(str.length()-1, str.length()).equals("'")){
				try {
					str = str.substring(0 ,str.length()-1);
				} catch (Exception e) {
					str = null;
					break;
				}
			}
			if(str != null){
				if(str.length() > 0){
										str = str.replaceAll("'", "''");
										while(str.contains("'''")) str = str.replaceAll("'''", "''");
									}else{
										str = null;
									}
			}
			
		}
		
		
	}
	return str;
}

public boolean psw(String str){
	boolean ok = true;
	
	if(str.contains("'")) ok = false;
	if(str.length() < 8) ok = false;
	
	return ok;
}

public String getInt(String nb){
	String str = "";
	boolean neg = false;
	 for(int i = 0; i < nb.length(); i++){
		 if(i == 0 && nb.substring(0,1).equals("-")) neg = true;
		 try {
			   Integer.parseInt(nb.substring(i, i+1));
			   str = str + nb.substring(i, i+1);
		 } catch (Exception e) {}
	 }
	 if(neg) str = "-"+str;
	return str;
}

public String getFloat(String nb){
	String str = "";
	boolean neg = false;
	 for(int i = 0; i < nb.length(); i++){
		 try {
			 if(i == 0 && nb.substring(0,1).equals("-")) neg = true;
			 if(nb.substring(i, i+1).equals(".")){
				 str = str + nb.substring(i, i+1);
			 }else{
				 Integer.parseInt(nb.substring(i, i+1));
			     str = str + nb.substring(i, i+1);
			 }
			   
		 } catch (Exception e) {}
	 }
	 if(neg) str = "-"+str;
	return str;
}

public boolean isEmpty(String str){
	boolean ok = false;
	if(str.equals("") || str == null)ok = true;
	return ok;
}

}
