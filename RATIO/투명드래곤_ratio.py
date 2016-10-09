for t in range(input()):
    N, M = map(int, raw_input().split())
    Z = (M * 100) / N + 1
    if Z >= 100:
        print -1
    else:
        print (Z * N - 100 * M + 99 - Z) / (100 - Z)
        
'''
M / N = Z - 1

(M + x) / (N + x) = Z
M + x = NZ + xZ
(1 - Z)x = NZ - M
x = (NZ - M) / (1 - Z)

100(M + x) / (N + x) = Z
100M + 100x = NZ + xZ
(100 - Z) x = NZ - 100M
x = (NZ - 100M) / (100 - Z)
'''
