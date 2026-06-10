class Main {
    public static void main(String[] args) {

        int[][] m = {
                        {10,20,30,40},
                        {14,24,34,44},
                        {18,28,38,48},
                        {21,36,54,67} 
            
                    };
        int target = 28;
        int r = 0, c = m.length-1;
        
        while(r<m.length && c>=0)
        {
            if(target == m[r][c])
            {
                System.out.println(target+" find at ["+r+"]["+c+"]");
                break;
            }
            else if(target>m[r][c])
                r++;
            else
                c--;
        }
        if(r== m.length || c==-1)
            System.out.println(target+" not found");
    }
}
