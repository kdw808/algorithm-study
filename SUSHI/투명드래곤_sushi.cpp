#if 1
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Menu {
    int price;
    int preference;
    float cpr;

    bool operator<(const Menu& m) const
    {
        return cpr > m.cpr;
    }
};

int max_preference;

int check_option(Menu* menu, int menu_count, int money, int base_preference)
{
    if (menu_count == 0)
        return 0;
    if (base_preference + money * menu->cpr < max_preference)
        return 0;

    int max_count = money / menu->price;
    max_preference = max(max_preference, base_preference + menu->preference * max_count);

    if (money % menu->price != 0)
    {
        for (int count = max_count; count >= 0; count--)
        {
            if (check_option(menu + 1, menu_count - 1, money - menu->price * count, base_preference + menu->preference * count) == 0)
                break;
        }
    }
    return 1;
}

int main()
{
    int c, n, m;
    for (cin >> c; c--;)
    {
        cin >> n >> m;
        vector<Menu> menulist(n);

        for (int i = 0; i < n; i++)
        {
            cin >> menulist[i].price >> menulist[i].preference;
            menulist[i].cpr = ((float) menulist[i].preference) / menulist[i].price;
        }
        sort(menulist.begin(), menulist.end());

        max_preference = 0;
        check_option(&menulist[0], n, m, 0);
        cout << max_preference << endl;
    }
}




#elif 0  // JM BOOK



#include <iostream>
#include <algorithm>
using namespace std;

#define PRICE_UNIT 100

#define MAX_PRICE 20000
#define WINDOW_SIZE (MAX_PRICE / PRICE_UNIT + 1)

int main()
{
    int c, n, m;
    int price[20];
    int preference[20];
    int total_preference_window[WINDOW_SIZE];

    for (cin >> c; c--;)
    {
        cin >> n >> m;
        m /= PRICE_UNIT;

        for (int dish = 0; dish < n; dish++)
        {
            cin >> price[dish] >> preference[dish];
            price[dish] /= PRICE_UNIT;
        }
        
        total_preference_window[0] = 0;
        for (int budget = 1; budget <= m; budget++)
        {
           int tmp = 0;
           for (int dish = 0; dish < n; dish++)
                if (price[dish] <= budget)
                    tmp = max(tmp, preference[dish] + total_preference_window[(budget - price[dish]) % WINDOW_SIZE]);
            total_preference_window[budget % WINDOW_SIZE] = tmp;
        }
        
        cout << total_preference_window[m % WINDOW_SIZE] << endl;
    }
}





#else   // fastest code (algospot web3p)

#include <stdio.h>

inline int max (int a, int b) { return a > b ? a : b; }
inline int min (int a, int b) { return a < b ? a : b; }

int p[21], v[21], d[401];
int n, b, m;

void init ()
{
	for (int i = 0; i++ < m;)
	    d[i] = 0;
	p[0] = 1;
	v[0] = 0;

	for (int i = 0; i++ < n;) {
		scanf ("%d %d", p+i, v+i);
		p[i] /= 100;
		if (p[0] <= p[i] && v[0] >= v[i])
		    continue;

		if (v[0] * p[i] < p[0] * v[i]) {
		    p[0] = p[i];
		    v[0] = v[i];
		}
		
		for (int j = p[i]; j <= m; j++)
			d[j] = max (d[j], d [j - p[i]] + v[i]);
	}
}

void print (int r = 0)
{
	if (b > m)
	    for (int i = m - p[0]; i <= m; i++)
		    r = max (r, d[i] + (b - i) / p[0] * v[0]);
	else 
	    r = d[b];
	
	printf ("%d\n", r);
}

int main ()
{
	int c;
	scanf ("%d", &c);
	while (c--) {
	    scanf ("%d %d", &n, &b);

		m = min (b /= 100, 400);
		init ();
		print ();
	}
	return 0;
}


#endif