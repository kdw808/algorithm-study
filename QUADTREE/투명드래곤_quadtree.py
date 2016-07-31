import sys

rl = sys.stdin.readline
r = sys.stdin.read
w = sys.stdout.write

class quadtree:
    def __init__(self):
        d = r(1)
        if d == 'x':
            self.c = []
            for i in range(4):
                self.c.append(quadtree())
        else:
            self.pr = lambda: w(d)
    def pr(self):
        w('x')
        self.c[2].pr()
        self.c[3].pr()
        self.c[0].pr()
        self.c[1].pr()

for i in range(int(rl())):
    quadtree().pr()
    rl()
    w('\n')
