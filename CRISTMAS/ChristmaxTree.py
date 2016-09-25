import sys
import math

# 문제 2
# 겹치지 않고 몇번이나 살 수 있는 최대 수
def solve2(k, psum):

    print("solve 2")

    # ret[i] = 첫 번째 상자부터 i번째 상자까지 고려했을 때 살 수 있는 최대 횟수
    ret = [ 0 for _ in range(psum.__len__())]

    # prev[s] = psum[] 이 s였던 마지막 위치
    prev = [ -1 for _ in range(psum.__len__())]

    for i in range(psum.__len__()):
        # i 번째 상자를 고려 하지 않는 경우
        if i > 0 :
            ret[i] = ret[i-1]
        else :
            ret[i] = 0

        # psum[i]를 전에도 본 적이 있으면, prev[psum[i]] + 1 부터 여기까지 쭉 사본다
        loc = prev[psum[i]]
        if loc != -1 :
            ret[i] = max(ret[i], ret[loc] + 1)

        # prev[]에 현재 위치를 기록한다.
        prev[psum[i]] = i

    return ret.pop()

# 문제 1
# 한꺼 번에 주문 할 수 있는 방법의 수
def solve1(k, psum):

    print("solve 1")

    MOD = 20091101
    ret = 0
    count = [0 for _ in range(psum.__len__())]
    if __name__ == '__main__':
        for i in range(psum.__len__()):
            count[psum[i]] = count[psum[i]] + 1

    # 두번 이상 본 적이 있다면 이 값 중 두개를 선택하는 방법의 수를 더한다.
    for i in range(k):
        if(count[i] >= 2):
            ret = ret + ((count[i] * (count[i] - 1)) / 2 ) % MOD

    return ret


def partialModSum(list,k):
    ret=[]
    ret.append(list[0])
    # print("###", "list", list)
    for i in range(list.__len__())[1:]:
        # print(i, list[i])
        ret.append((list[i] + ret[i-1]) % k )
    return ret

def rangeSum(psum, a, b):
    if( a<0 or b >= psum.__len__() ):
        print("### [error] out of range")
        exit(-1)
    if( a==0 ):
        return psum[b]
    return psum[b] - psum[a-1]


def partialSum(list):
    ret=[]
    ret.append(list[0])
    # print("###", "list", list)
    for i in range(list.__len__())[1:]:
        # print(i, list[i])
        ret.append(list[i] + ret[i-1] )
    return ret

if __name__ == '__main__':

    # 한 라인을 입력 받는 람다식
    rl = lambda: sys.stdin.readline()
    # 입력받을 라인의 수
    n = int(rl())
    nList = []

    ## n회 반복하며 라인을 입력 받고 출력한다
    for i in range(n):
        print("========")
        n, k = map(int, rl().split())
        nList = map(int, rl().split())
        nList = [int(i) for i in nList]

        modPsum = partialModSum(nList, k)

        print("n, k", n,k)
        print("nList", nList)
        print("modPsum", modPsum)

        ret1 = solve1( k, modPsum)
        print(int(ret1))

        ret2 = solve2( k, modPsum)
        print(ret2)

