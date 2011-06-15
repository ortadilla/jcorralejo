package es.jcorralejo.contestTuenti;


public class Ejercicio8 {
	
	public static void main(String[] args)
	{
		String str1 = "cgtaattgcgat";
		String str2 = "cgtacagtagc";
		int[][] lcs = new int[str1.length()+1][str2.length()+1];
		int i;
		for(i=0;i<=str1.length();i++)
			lcs[i][0] = 0;
		for(i=0;i<=str2.length();i++)
			lcs[0][i] = 0;
		for(i=1;i<=str1.length();i++)
		{
			for(int j=1;j<=str2.length();j++)
			{
				if(str1.charAt(i-1) == str2.charAt(j-1))
					lcs[i][j]=lcs[i-1][j-1]+1;
				else
					lcs[i][j]=Math.max(lcs[i-1][j], lcs[i][j-1]);
			}

		}
		
		int x = 0, y = 0;
        	while(x < str1.length() && y < str2.length())
		{
            		if (str1.charAt(x) == str2.charAt(y))
			{
    		            	System.out.print(str1.charAt(x));
                		x++;
                		y++;
            		}
            		else if (lcs[x+1][y] >= lcs[x][y+1]) x++;
            		else y++;
        	}
        	System.out.println();
	}



}
