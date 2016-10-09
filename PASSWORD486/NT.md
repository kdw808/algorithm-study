# 알고리즘 스터디
## 정수론(number theory)

## 14.1 도입

- 정수론 : 정수의 성질을 연구대상으로 하는 수학의 한 분야

## 14.2 소수

- **소수(Prime number)** 는 정수론의 가장 중요한 연구 대상 중 하나로, 양의 약수가 1과 자기 자신 두개 뿐인 자연수를 의미한다. 
예) 7
- **합성수(composite number)** 소수의 반대발로, 세 개 이상의 양의 약수를 갖는 자연수를 의미한다.
예) 9
- **1은 소수도 아니고 합성수도 아니다. (주의!)**

### 소수 판별
- 소수에 관한 가장 기초적인 문제
- 이 문제를 풀기 위한 많은 연구가 진행되어 있지만, 대부분 효율적인 방법들은 너무 복잡한 관계로 프로그래밍 대회에는 출제되지 않음.
- 따라서 여기서는 단순한 방법을 사용함
- 합성수 n이 p * q 로 표현 될 때 한 수는 항상 sqrt(n)  이하 ,다른 한 수는 항상 sqrt(n) 이상이라는 점을 이용하면 n-1까지 순회하지 않고 sqrt(n) 까지만 순회하도록 최적화 할 수 있다.
- 2를 예외로 처리하는 이유는 2가 유일한 짝수 소수이기 때문에 2만 예외 처리하면 그 외의 모든 짝수를 합성수 라고 판단 할 수 있기 때문이다.

#### 코드 14.1 O(sqrt(n)) 시간에 동작하는 소수 판별 알고리즘
```java
	// 주어진 자연수 n이 소수인지 확인한다.
	bool isPrime(int n){
		// 예외 처리 : 1과 2는 예외로 처리한다.
		if( n <= 1) return false;
		if( n == 2) return true;
		// 2를 제외한 모든 짝수는 소수가 아니다.
		if( n % 2 == 0) return false;
		// 2를 제외했으니 3이상의 모든 홀수로 나누어보자.
		int sqrtn = int(sqrt(n))
		for(int div = 3;div <= sqrtn; div +=2)
			if(n % div == 0 )
				return false;
			return true;
```

- isPrime()을 최적화할 수 있는 방법에는 여러 가지가 있다.
- 2와 3을 제외한 모든 소수는 6k +-1의 형태를 띈다는 사실을 이용할 수도 있다.

### 소인수 분해
- 소인수 분해 : 한 합성수를 소수의 곱으로 표현하는 방법.

#### 코드 14.2 간단한 소인수 분해 알고리즘
```java
 vector<int> facterSimple(int n){
	 vector<int> ret;
	 int sqrtn = (int)sqrt(n);	
	 for(int div = 2; div <= sqrtn; ++div)
		 while( n % div == 0){
			 n /= div;
			 ret.push_back(n)
		}
	if( n>1 ) ret.push_back(n);
	return ret; 
```

### 에라토스테네스
- **에라토스테네스** (BC 276년 ~ BC 194년)
![에라토스테네스](https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRYKNUtFq88QMJ7EKRQ0JUHhQG02uR7qcxKOMD4rWjXpIbMi-HdyEdt7zK3Cw)
- 에라토스테네스는 고대 그리스의 수학자이자 천문학자
-  지구의 크기를 처음으로 계산해 냈으며, 또 소수를 걸러내는 에라토스테네스의 체를 고안함
- 엘리트지만 2인자로 유명함. 그리스 계의 수학계의 콩라인. 당시 별명 베타.


### 에라토스테네스의 체

- 주어진 수를 N이라 할때, 2~N 목록에서  지워지지 않은 수들을 순회하며 이 수의 배수를 지우기를 반복한다.

	시작|2|3|4|5|6|7|8|9|...
	---|---|---|---|---|---|---|---|---|---|
	2의 배수 지우기|2|3|X|5|X|7|X|9|...
	3의 배수 지우기|2|3|X|5|X|7|X|X|...
- 각 수 m이 소수인지 찬단하기 위해 sqrt(n)까지의 모든 수를 나눠보는 대신, 소수를 찾을 때마다 그 배수들을 지우는 형태로 동작하기 때문에 훨씬 빠르게 수행된다.

> **최적화 방안**
- 지워지지 않은 수를 찾을 때 n이 아니라 sqrt(n) 까지만 찾는다. 
- i의 배수들을 지울 때 2 * i 에서 시작하는 것이 아니라 i * i 에서 시작한다. 2 * i에서 2의 배수 들은 모두 지워졌을 것이고 3 * i 는 3의 배수를 지울 때 이미 지워졌을 테니까.

#### 코드 14.3 에라토스테네스의 체
```java
	int n;
	bool isPrime[MAX_N+1];
	// 가장 단순한 형태의 에라토스테네스의 체의 구현
	// 종료한 뒤 isPrime[i]=i가 소수인가?
	void eratosthenes(){
		memset(isPrime, ture, sizeof(isPrime));
		// 1은 항상 예외 처리
		isPrime[0]=isPrime[1]=false;
		int sqrt=int(sqrt(n))
		for(int i=2; i<sqrtn;++i)
			// 이 수가 아직 채워지지 않았다면
			if(isPrime[i])
				//i의 배수 j들에 대해 isPrime[j]=false로 둔다.
				// i*i 미만의 배수는 이미 지워졌으므로 신경 쓰지 않는다.
				for(int j=i*i; j<=n; j+= i)
					isPrime[j]=false;	
	}
```

### 예제 에라토스테네스의 체를 이용한 빠른 소인수 분해

- 에라스토테네스의 체를 응용해 훨씬 빠르게 소인수 분해를 수행 할 수 있다.
- 최적화의 비결은 체에서 각 숫자가 소수인지 합성수 인지만을 기록하는 것이 아니라, 각 숫자의 가장 작은 소인수를 같이 기록해 두 는 것이다.

#### 코드 14.4 에라토스테네스의 체를 이용한 빠른 소인수 분해
```java
	// minFactor[i]=i 의 가장 작은 소인수(i가 소수인 경우 자기 자신)
	int minFactor[MAX_N]
	// 에라토스테네스의 체를 수행하면서 소인수분해를 위한 정보도 저장한다.
	void eratosthenes2(){
		// 1은 항상 예외 처리
		minFactor[0]=minFactor[1]=-1;
		// 모든 숫자를 처음에는 소수로 표시해 둔다.
		for(int i=2; i<=n; ++i)
			minFactor[i]=i;
		// 에라토스테네스의 채를 수행한다.
		int sqrtn = int(sqrt(n))
		for(int i =2; i<sqrtn; ++i){
			if(minFactor[i]==i){
				for(int j=i*i; j<=n; j+=i)
				// 아직까지 본 적 없는 숫자인 경우 i를 써둔다.
				if(minFactor[j]=j)
					minFactor[j]=i
			}
		}
	}
	// 2 이상의 자연수 n을 소인수 분해하는 결과를 반복한다.
	vector<int> fector(int n){
		vector<int> ret;
		// n이 1이 될 때까지 가장 작은 소인수로 나누기를 반복한다.
		while(n>1){
			ret.push_back(minFactor[n]);
			n /= minFactor[n]
		}
		return ret;
	}
```

출저 : 알고리즘 문제 해결 전략