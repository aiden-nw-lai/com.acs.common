package acs.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UUID {
	 public static String getUUID(){
	    Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yy-MM-dd-hh-mm-ss-Ms");
        String datetime = ft.format(dNow);
        System.out.println(datetime);
        return datetime;
	
  }

}
