tc = []
for i in range(input()):
    tc.append(input())
max_size = max(tc)

fib = [1] * (max_size + 1)
for i in range(2, max_size + 1):
    fib[i] = fib[i-1] + fib[i-2]
    if fib[i] > 1000000007:
        fib[i] -= 1000000007

for n in tc:
    if n % 2:
        ans = fib[n] - fib[n/2]
    else:
        ans = fib[n] - fib[n/2-1] - fib[n/2]
    while ans < 0:
        ans += 1000000007
    print ans
