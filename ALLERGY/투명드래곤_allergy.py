# 529ms
def friends_to_bitmask(friendlist, targetlist):
    bit = 0
    for t in targetlist:
        bit |= 1 << friendlist.index(t)
    return bit

def solve(remain_friend, index, count):
    global min_count
    if count >= min_count:
        return min_count
    if remain_friend == 0:
        if count < min_count:
            min_count = count
        return count
        
    solve_with_this = lambda: solve(remain_friend & ~food[index], index+1, count+1)
    solve_without_this = lambda: solve(remain_friend, index+1, count)
    
    if food[index] & remain_friend:
        if ~remain_food[index+1] & remain_friend:
            return solve_with_this()
        else:
            return min(solve_with_this(), solve_without_this())
    else:
        return solve_without_this()
        
for t in range(input()):
    n, m = map(int, raw_input().split())
    friends = raw_input().split()
    food = [raw_input().split()[1:] for i in range(m)]
    food.sort(key = len, reverse = True)
    food = map(lambda f: friends_to_bitmask(friends, f), food)
    remain_food = [reduce(lambda x,y: x | y, food[i:]) for i in range(m)]
    remain_food.append(0)
    min_count = 51
    print solve((1 << n) - 1, 0, 0)
