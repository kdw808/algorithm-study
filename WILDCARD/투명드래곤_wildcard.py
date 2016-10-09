import sys
gets = lambda: sys.stdin.readline().rstrip()

def match(wildcard, filename):
    chunk = wildcard.split('*')
    chunkpos = [0]
    for i in range(len(chunk) - 1):
        chunkpos.append(chunkpos[i] + len(chunk[i]))
    margin = len(filename) - (chunkpos[-1] + len(chunk[-1]))
    if margin < 0:
        return 0

    cache = [[-1] * (margin+1) for a in range(len(chunk))]    # [idx][shift]
    for i in range(margin):
        cache[-1][i] = 0

    def match_chunk(chunk, pos):
        for i in range(len(chunk)):
            if chunk[i] not in ('?', filename[pos+i]):
                return 0
        return 1
    def check(idx, shift):
        if idx is len(chunk):
            return 1
        if cache[idx][shift] is not -1:
            return cache[idx][shift]
        if match_chunk(chunk[idx], chunkpos[idx] + shift):
            for i in range(shift, margin + 1):
                if check(idx+1, i):
                    cache[idx][shift] = 1
                    return 1
        cache[idx][shift] = 0
        return 0
    
    return check(0, 0)

for c in range(input()):
    matched = []
    wildcard = gets()
    for n in range(input()):
        filename = gets()
        if match(wildcard, filename):
            matched.append(filename)
    matched.sort()
    print '\n'.join(matched)

