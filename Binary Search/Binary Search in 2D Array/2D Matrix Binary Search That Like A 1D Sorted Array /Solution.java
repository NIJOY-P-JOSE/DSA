class Main {
    public static void main(String[] args) {
        
        System.out.println("");
        int[][] m = {
                        {1,2,3,4},
                        {5,6,7,8},
                        {9,10,11,12},
                        {13,14,15,16}
                    };
        int target = 5; 
                    
        int col = m[0].length-1;
        int row = m.length-1;
        if(row==0)
        {
            search(m,target,0,0,col);
            return;
        }
        
        int cmid =col/2;
        int rs = 0, re = row;
        
        
        while(rs<re-1)
        {
            int rmid = rs+(re-rs)/2;
            
            if(m[rmid][cmid] == target)
            {
                System.out.println("["+rmid+"]["+cmid+"]");
                return;
            }
            else if(m[rmid][cmid]>target)
                re = rmid;
            else
                rs = rmid;
        }
        
        if(m[rs][cmid]==target)
        {
            System.out.println("["+rs+"]["+cmid+"]");
            return;
        }
        else if(m[re][cmid]==target)
        { 
            System.out.println("["+re+"]["+cmid+"]");
            return;
        }
        else if(m[rs][cmid]>target)
            search(m,target,rs,0,cmid-1);
        else if(m[rs][cmid]<target && m[re][0]>target)
            search(m,target,rs,cmid+1,col);
        else if(m[re][0]<=target && m[re][cmid]>target)
            search(m,target,re,0,cmid-1);
        else
            search(m,target,re,cmid+1,col);
    }
    
    static void search(int[][] m, int target, int row, int s, int e)
    {
        while(s<=e)
        {
            int mid = s+(e-s)/2;
            
            if(m[row][mid]==target)
            {
                System.out.println("["+row+"]["+mid+"]");
                return;
            }
            else if(m[row][mid]>target)
                e = mid-1;
            else 
                s = mid+1;
        }
        System.out.println("["+-1+"]["+-1+"]");
    }
}
