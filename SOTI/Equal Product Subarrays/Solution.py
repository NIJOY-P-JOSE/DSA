def EQUAL_PROD_SUBARRAY(A, N):
    target = 1
    ans = -1
    
    for i in range(N):
        target *= A[i]
        
        prod = 1
        c = 0
        flag = True
        
        for j in range(N):
            prod *= A[j]
            
            if prod == target:
                c += 1
                prod = 1
            
            elif prod > target:
                flag = False
                break
        
        if flag and prod == 1 and c >= 2:
            ans = max(ans, c)
    
    return ans
