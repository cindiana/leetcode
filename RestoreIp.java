package leetcod;

import java.util.ArrayList;
import java.util.List;

public class RestoreIp {
public ArrayList<String> restoreIpAddresses(String s) {
        
	ArrayList<String> ips = new ArrayList<String>();
    if (s==null||s.length()<4) return ips;
    
    ArrayList<ArrayList<String>> candidates = splice(s);
    for (ArrayList<String> ip: candidates) {
        boolean isValid = true;
        for (String number: ip) {
            if (number.length()>1&& number.startsWith("0")) {
                isValid = false;
                break;
            }
            int i = Integer.parseInt(number);
            if (i<0 || i>255) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            StringBuffer sb = new StringBuffer();
            for (int i=0; i<ip.size(); i++) {
                sb.append(ip.get(i));
                if (i!=ip.size()-1) {
                    sb.append(".");
                }
            }
            ips.add(sb.toString());
        }
    }
    return ips;
  }
    
    //splice into 4 parts, each 1-3 digits
    public ArrayList<ArrayList<String>> splice(String s) {
        return doSplice(s, 4);    
    }
    
    public ArrayList<ArrayList<String>> doSplice(String s, int parts) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (parts==1) {
            if (s.length()<4) {
                ArrayList<String> justMe = new ArrayList<String>();
                justMe.add(s);
                result.add(justMe);
                return result;
            }
            return null;
        }
        for (int i=1; i<4&&i<s.length(); i++) {
            String prefix = s.substring(0, i);
            ArrayList<ArrayList<String>> suffix = doSplice(s.substring(i), parts-1);
            if (suffix!=null) {
                for (List<String> r:suffix) {
                    r.add(0, prefix);
                }
                result.addAll(suffix);
            }           
        }
        return result;
    }
    
    public static void main(String[] args) {
    	RestoreIp ip = new RestoreIp();
    	ip.restoreIpAddresses("172162541");
    }
}
