function [T, N, L] = mva(K, l, m)

n=length(l);

%Base case
N = zeros(K+1,n);

for i=2:K+1
    T(i,:) = (1+N(i-1,:)).*1./m;
    N(i,:) = (i-1).*(l.*T(i,:))./sum(T(i,:).*l);
    L(i,:) = N(i,:)./T(i,:);
end

 N = N(2:end,:);
 T = T(2:end,:);
 L = L(2:end,:);

end