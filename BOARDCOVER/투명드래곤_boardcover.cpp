#include <cstdio>
#include <cstring>

char board[22][22];
int w;
int h;
int remain;

int dist[4][2] = {{22, 23}, {21, 22}, {1, 23}, {1, 22}};

int add_block(char* pos, int type)
{
    if (pos[dist[type][0]] == 0 || pos[dist[type][1]] == 0)
        return 0;
    pos[0] = 0;
    pos[dist[type][0]] = 0;
    pos[dist[type][1]] = 0;
    remain -= 3;
    return 1;
}

void remove_block(char* pos, int type)
{
    pos[0] = 1;
    pos[dist[type][0]] = 1;
    pos[dist[type][1]] = 1;
    remain += 3;
}

int check_count(int sx, int sy)
{
    if (remain == 0)
        return 1;
    
    int count = 0;
    int x = sx;
    int y = sy;
    while (y < h)
    {
        while (x < w)
        {
            if (board[y+1][x+1])
            {
                for (int i = 0; i < 4; i++)
                {
                    if (add_block(&board[y+1][x+1], i))
                    {
                        count += check_count(x+1, y);
                        remove_block(&board[y+1][x+1], i);
                    }
                }
                return count;
            }
            x++;
        }
        x = 0;
        y++;
    }
    return count;
}

int main(void)
{
    int C;
    scanf("%d", &C);
    while (C--)
    {
        memset(board, 0, sizeof(board));
        remain = 0;
        scanf("%d%d", &h, &w);
        for (int y = 0; y < h; y++)
        {
            char buf[30];
            scanf("%30s", buf);
            for (int x = 0; x < w; x++)
            {
                if (buf[x] == '.')
                {
                    board[y+1][x+1] = 1;
                    remain++;
                }
            }
        }
        
        if (remain % 3 != 0)
            printf("0\n");
        else
            printf("%d\n", check_count(0, 0));
    }
}
