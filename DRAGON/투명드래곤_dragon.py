def ch(i):
    if i % 3 < 2:
        return 'FX0YF'[i%6]
    i = i / 3
    while i % 2:
        i /= 2
    return '+0-'[i%4]

for t in range(input()):
    n, p, l = map(int, raw_input().split())
    print ''.join([ch(i) for i in range(p-1, p+l-1)])

