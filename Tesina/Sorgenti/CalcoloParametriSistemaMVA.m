clear,clc,close all
format
%Script per applicare MVA ad una rete chiusa con K=3 e M=2
K = 3
M = 2
l = [3, 1]
m = [5, 2]

[T, N, L] = mva(K, l, m)
x = 1:K;
figure("Name", "Confronto Tempi")
subplot(3,1,1)
hold on;
grid on;
plot(x, T(:,1));
plot(x, T(:,2));
legend("CPU","I/O");
xlabel("K")
ylabel("T")

%figure("Name", "Confronto Numero utenti")
subplot(3,1,2)
hold on;
grid on;
plot(x, N(:,1));
plot(x, N(:,2));
legend("CPU","I/O");
xlabel("K")
ylabel("N")

%figure("Name", "Lambda")
subplot(3,1,3)
hold on;
grid on;
plot(x, L(:,1));
plot(x, L(:,2));
legend("CPU","I/O");
xlabel("K")
ylabel("L")
