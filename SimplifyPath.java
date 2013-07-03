package leetcod;

import java.util.Stack;

/*
 * 1. fail all. java thinks "." is a regex
 * 2. fail /. trim tail error on loop boundary
 * 3. fail "/a/./b/../../c/", getting rid of all '.' lost me ../
 * 4. fail "/home/../../..". adding a loop removing ../ prefix.
 * 5. fail "/a/./b///../c/../././../d/..//../e/./f/./g/././//.//h///././/..///", tweak /./
 * 6. fail ./. realize need to deal with /../ from right to left.
 * 7. fail "/a/./b/../../c/", #6 is wrong. from left to right, but start from non-prefix /../
 * 8. fail long case, need to change loop condition key inside loop.
 * 9. fail "/home/../../..". need to get rid of heading /../ after each removal since it may introduce new /../
 * 10. fail to handle heading ///../../. Remove them before going into ../ removal loop.
 */
public class SimplifyPath {
	public String simplifyPath(String path) {
		if (path == null || path.length() == 0)
			return path;
		path = path + '/';
		while (path.indexOf("/./") >= 0) {
			path = path.replace("/./", "/");
		}
		while (path.indexOf("//") >= 0) {
			path = path.replaceAll("//", "/");
		}
		while (path.startsWith("/../")) {
			path = path.substring(3);
		}
		int index = path.indexOf("../",2);
		while (index >= 3) {
			int poke = index - 2;
			while (path.charAt(poke) != '/' && path.charAt(poke) != '.'
					&& poke >= 0) {
				poke--;
			}
			if (poke >= 0) {
				path = path.substring(0, poke + 1) + path.substring(index + 3);
				while (path.startsWith("/../")) {
					path = path.substring(3);
				}
			}
			index = path.indexOf("../",2);
		}
		while (path.startsWith("/../")) {
			path = path.substring(3);
		}

		int tail = path.length() - 1;
		while (tail > 0 && path.charAt(tail) == '/') {
			path = path.substring(0, tail);
			tail--;
		}
		return path;
	}
	
	public String simplifyPath_2013(String path) {
        if (path==null || path.length()<2) return path;

        String[] parts = path.split("/");
        Stack<String> dirs = new Stack<String>();
        
        for (String p: parts) {
            if (p.equals(".") || p==null || p.length()==0) {
                continue;
            }
            if (p.equals("..")) {
                if (!dirs.isEmpty())
                    dirs.pop();
            }
            else {
                dirs.push(p);
            }
        }
        
        StringBuffer sb = new StringBuffer();
        if (dirs.isEmpty()) return "/";
        while(!dirs.isEmpty()) {
            sb.insert(0, dirs.pop());           
            sb.insert(0,'/');
        }
                
        return sb.toString();
    }

	public static void main(String[] args) {
		SimplifyPath sp = new SimplifyPath();
		sp.simplifyPath("///../../F/./rVH/jmkyl/wpxS/sRC/cL/NR///tO/.//");
	}
}
