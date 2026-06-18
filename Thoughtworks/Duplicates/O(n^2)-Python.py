x = []
l = []
n = int(input())3
for i in range(n):
  l.append(int(input()))
c = 0
print(len(l))
for i in range(len(l)):
  if l[i] in x:
    c+=1
  else:
    x.append(l[i])
      
print(x," \n",c)
