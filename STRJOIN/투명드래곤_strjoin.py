from heapq import *

for c in range(input()):
    n = input()
    l = map(int, raw_input().split())
    l.sort()

    total = 0
    while len(l) > 1:
        cost = heappop(l) + heappop(l)
        heappush(l, cost)
        total += cost
    print total