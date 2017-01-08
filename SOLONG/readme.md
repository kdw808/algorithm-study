## 트라이(TREE RETRIEVAL, TRIE)

* 일반적인 문자열 알고리즘 O(mlogN)
* 트라이는 O(M), 공간 복잡도는 높음

## 설명

* 많은 양의 텍스트정보를 빠르고 효율적으로 검색하기 위해 그래서 Trie는 사전 혹은 인터넷 자동완성의 retrieval을 효과적으로 할 수 있는 자료구조이다.
* 루트노드는 0, 자식노드로 내려갈때 문자열의 길이는 하나씩 늘어남
* 루트에서 한노드까지내려가는 경로에서 만나는 글자들을 모으면 해당 노드에 대응되는 접두사를 얻을수있다.
* 객체는 자손 노드를 가리키는 포인터목록과 종료 노드 여부값 맴버변수 2개를 가진다.
* 자손 노드를 가리키는 포인터 목록은 입력에 등장할수 있는 모든 문자에 각각 대응되는 원소를 갖는 고정 길이 배열로 구현 (메모리를 엄청나게 낭비하지만 다음 노드를 찾는 과정에서 어떤 탐색도 필요하지 않는다!)
* KMP서치의 경우 <span style="color:  #666666;;"> 접두사(prefix)와 접미사(suffix)를 미리 인덱싱 하여 부분 매칭(검색)에 이용한다. ( O(M+N) )</span>

[http://kwony91.tistory.com/entry/트라이](http://kwony91.tistory.com/entry/%ED%8A%B8%EB%9D%BC%EC%9D%B4)<br>
<br>