clear,clc
%Script per applicare Gordon-Newell ad una rete chiusa con K=3 e M=2
K=3
M=2
l1=3
l2=1
m1=5
m2=2

%Numero stati della rete
numerostati = nchoosek(M+K-1,M-1)

[p30, p21, p12, p03] = GordonNewell(l1,l2,m1,m2)

N1 = 3*p30+2*p21+p12
N2 = 3*p03+2*p12+p21
