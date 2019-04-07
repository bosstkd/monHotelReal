package fct;

import java.awt.Font;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jdesktop.swingx.JXDatePicker;

public class dt extends JXDatePicker{
	
	
	
public dt(){
	setDate(new Date());
	setFont(new Font("Consolas", Font.PLAIN, 11));
}

public  Calendar dateToCalendar(Date date){ 
  Calendar cal = Calendar.getInstance();
  cal.setTime(date);
  return cal;
}
	
public  Date[] getDaysBetween(Date dtInferieur, Date dtSuperieur){
     dt sdt = new dt();
    
     String dt1 = sdt.form_Insr(dtInferieur);
     String dt2 = sdt.form_Insr(dtSuperieur);
     
     Date dd1 = sdt.strToDate(dt1, "yyyy-MM-dd");
     Date dd2 = sdt.strToDate(dt2, "yyyy-MM-dd");

     
     
     int nbNuitee = (int) sdt.nuitee(dd1, dd2);
     
     Date[] days = new Date[nbNuitee+1];
     
     int i = 0;
     Long ld = dd1.getTime();
     while (ld < dd2.getTime()){
        ld = sdt.addDay(dd1, i);
         days[i] = sdt.longToDate(ld);
         i++;
     }
     
    return days;
}	

public  Date getFirstDayOfMonth(Date dts){
         dt sdt = new dt();

    String str = sdt.form_Aff(dts);
    str = str.substring(2, str.length());
    str = "01"+str;
    return sdt.strToDate(str, "dd/MM/yyyy");
}

public  Date getLastDayOfMonth(Date dts){
      dt sdt = new dt();
    String str = sdt.form_Aff(dts);
    Date dt1 = sdt.strToDate(str, "dd/MM/yyyy");
    Date dt2 = null;
    String month = sdt.Mois(dt1);
    String mth = "";
    boolean bl = true;
    while(bl){
        mth = month;
        dt2 = dt1;
        dt1 = new Date(sdt.addDay(dt1, 1));
        month = sdt.Mois(dt1);
        if(!mth.equals(month)){
        	bl = false;
        	break;
        }
    }
    return dt2;
}

public  String[] getMonthsBetween2Date(Date dtInferieur, Date dtSuperieur){
      dt sdt = new dt();
	    String str[] = new String[12];
	    Date dtInf = dtInferieur;
	    int i = 1;
	    String dts = sdt.form_Aff(dtInf);
	    str[0] =  dts.substring(3, dts.length());
	    while(dtInf.getTime() <= dtSuperieur.getTime()){
	    	if(!(dts.substring(3, dts.length()).equals(sdt.form_Aff(dtInf).substring(3, sdt.form_Aff(dtInf).length())))){
	    		dts = sdt.form_Aff(dtInf);
	    		str[i] = dts.substring(3, dts.length());
	    		i++;
	    	}
	       dtInf = new Date(sdt.addDay(dtInf, 1));
	    }
	    int x = 0;
	    for(String st:str) if(st!=null)x++;
	    String go[] = new String [x];
	    x = 0;
	    for(String st:str)
	    	   if(st!=null) {
	    		   go[x] = st;
	    		   x++;
	    	   }
	    return go;
	}


public  Date TSampToDate(Timestamp ts){
		Date dts = new Date(ts.getTime());
		return dts;
}	

public  String TSampToDateStr(Timestamp ts){
	Date dts = new Date(ts.getTime());
	String dt = form_Aff(dts);
	return dt;
}	

public  Timestamp dateToTStamp(Date dt){
	Timestamp ts = new Timestamp(dt.getTime());
	return ts;
}

public  String TSampToHour(Timestamp ts){
	Date dts = new Date(ts.getTime());
	String dt = new SimpleDateFormat("HH:mm:ss").format(dts);
	return dt;
}	
	
public  String form_Aff(Date dt){
	String dts = new SimpleDateFormat("dd/MM/yyyy").format(dt);
	return dts;
}
	
public  String form_Insr(Date dt){
	String dts = new SimpleDateFormat("yyyy-MM-dd").format(dt);
	return dts;
}

public  String getTime(Date dt){
	String dts = new SimpleDateFormat("HH:mm:ss").format(dt);
	return dts;
}


public  String Jour(Date dt){
	String dts = new SimpleDateFormat("dd").format(dt);
	return dts;
}

public  String Mois(Date dt){
	String dts = new SimpleDateFormat("MM").format(dt);
	return dts;
}

public  String Annee(Date dt){
	String dts = new SimpleDateFormat("yyyy").format(dt);
	return dts;
}

public  long nuitee (Date dt_a, Date dt_d){
	   long nb_nuit = (long) (dt_d.getTime() - dt_a.getTime()) / (1000 * 60 * 60 * 24); 
	   return nb_nuit;
}
	
public  boolean superieur(Date dt_1, Date dt_2){
	long dif = (long) dt_2.getTime() - (long) dt_1.getTime();
            return dif > 0;
}

public  boolean superieurORE(Date dt_1, Date dt_2){
	long dif = (long) dt_2.getTime() - (long) dt_1.getTime();
            return dif >= 0;
}

public  Date strToDate(String str_dt, String formDate){
	Date dt = new Date();
	try {
		dt = new SimpleDateFormat(formDate).parse(str_dt);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	return dt;
}

public  boolean dateIntoPeriode(Date dt_a, Date dt_d, Date dtANew, Date dtDNew){
	
boolean ok = false;
	
	long dt_a_l = dt_a.getTime();
	long dt_d_l = dt_d.getTime();
	long dt_an_l = dtANew.getTime();
	long dt_dn_l = dtDNew.getTime();

	if(dt_dn_l < dt_an_l && !ok) {
		ok = true;	
	}
	if(dt_an_l == dt_a_l && !ok) {
		ok = true;
	}
	if((dt_an_l < dt_d_l && dt_an_l >= dt_a_l) && !ok) {
		ok = true;
	}

	 if((dt_an_l < dt_a_l && dt_dn_l >= dt_d_l) && !ok){
		 ok = true;
	 }
	
return ok;
}

public  boolean isInPeriode(Date dt_a, Date dt_d, Date dt_test){
	boolean ok = false;
	long dt1 = dt_a.getTime();
	long dt2 = dt_d.getTime();
	long dt3 = dt_test.getTime();
	
	if(dt3 >= dt1 && dt3 < dt2 ) ok = true;
	
	return ok;
}

public  boolean isValidDateRes(Date dt_a, Date dt_d, Date dt_r){
	
	boolean ok = true;
	
	long dt1 = dt_a.getTime();
	long dt2 = dt_d.getTime();
	long dt3 = dt_r.getTime()-(60*60*24*1000);
	
        
	if(dt2 <= dt1) ok = false;
	if(dt3 > dt1) ok = false;
	
	return ok;
}


public  boolean isValidDateRes(Date dt_a, Date dt_r){
	
	boolean ok = true;
	long dt1 = dt_a.getTime();
	long dt3 = dt_r.getTime()-(60*60*24*1000);
	if(dt3 > dt1) ok = false;
	return ok;
}

public  long addYear(Date dtAct, int yearNbr){
	long newDate = 0;
	long nb = 31536000000L;
	newDate = dtAct.getTime() + nb*yearNbr;
	return newDate;
}

public  long addDay(Date dtAct, int dayNbr){
	long newDate = 0;
	long nb = 60*60*24*1000;
		newDate = dtAct.getTime() + nb*dayNbr;
	return newDate;
}


public  Date longToDate(long nb){
	Date dte = new Date(nb);
	return dte;
}

public  double nbrYearsBetween(Date dtSup, Date dtInf){
	double dif = dtSup.getTime() - dtInf.getTime();
	dif = dif/31536000000L;
	return dif;
}

}
