from math import *

def calc_range(x, y, r):
    center = atan2(y, x)
    dist = 2 * asin(r/16)
    start = center - dist
    if start < -pi:
        start += 2 * pi
    end = center + dist
    if end >= pi:
        end -= 2 * pi
    return (start, end)

def find_least_cover_pt(towerlist):
    pt_list = []
    for start, end in towerlist:
        pt_list.append((start, 1))
        pt_list.append((end, -1))
    pt_list.sort()
    count = 0
    min_pt = 0
    min_count = 100
    for pt, diff in pt_list:
        count += diff
        if count < min_count:
            min_pt = pt
            min_count = count
    return min_pt

def pt_in(pt, (start, end)):
    if start < end:
        return (start <= pt and pt < end)
    else:
        return (pt < end or start <= pt)
    
def find_cover(pt, towerlist):
    return [t for t in towerlist if pt_in(pt, t)]
    
def calculate_from(st, towerlist):
    count = 1
    start, end = st
    t = list(towerlist)
    t.remove(st)
    while 1:
        cover = find_cover(end, t)
        count += 1
        max_end = end
        for s, e in cover:
            if pt_in(s, (start, end)):
                t.remove((s, e))
            if pt_in(max_end, (end, e)):
                max_end = e
        if pt_in(start, (end, max_end)):
            return count
        end = max_end

def run(n):
    towerlist = []
    for i in range(n):
        y, x, r = map(float, raw_input().split())
        if r >= 16:
            for s in range(n - i - 1):
                raw_input()
            return 1
        towerlist.append(calc_range(x, y, r))

    pt = find_least_cover_pt(towerlist)
    start_list = find_cover(pt, towerlist)
    if len(start_list) == 0:
        return 'IMPOSSIBLE'
        
    min_count = 2 * n    
    for s in start_list:
        min_count = min(min_count, calculate_from(s, towerlist))
    return min_count

for c in range(input()):
    print run(input())
