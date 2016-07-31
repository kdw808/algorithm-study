def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def solve(n, r, p):
    unit = reduce(gcd, r)
    ratio = [ri / unit for ri in r]
    count = max([(p[i] + ratio[i] - 1) / ratio[i] for i in range(n)])
    count = max(count, r[0] / ratio[0])
    return [ratio[i] * count - p[i] for i in range(n)]

def print_solution(l):
    print ' '.join([str(s) for s in l])

for c in range(input()):
    n = input()
    r = map(int, raw_input().split())
    p = map(int, raw_input().split())    
    print_solution(solve(n, r, p))
