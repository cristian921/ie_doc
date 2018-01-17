function [p30, p21, p12, p03] = GordonNewell(l1, l2, m1, m2)

%calcolo le intensita di traffico q1,q2
q1 = l1/m1;
q2 = l2/m2


%calcolo G
G = q1^3+q1^2*q2+q1*q2^2+q2^3;

%calcolo le probabilita di stare negli stati p30, p21, p12 e p03
p30=q1^3/G;
p21=q1^2*q2/G;
p12=q1*q2^2/G;
p03=q2^3/G;

end
