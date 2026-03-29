# LeetCodeRecord力扣做题记录

github同步步骤：
1、为这次改动写个备注并封箱 (Commit)

```
单独提交：只把 0049 的改动放入暂存区
git add 0049/
git commit -m "完成第49题：字母异位词分组"
```

```
全部提交
git add .
git commit -m "添加了第1356题的解法"
```

3、推送到 GitHub 服务器 (Push)

```
git push
```

注：

1、在执行这三个必须步骤之前，你可以养成一个好习惯，先敲一下这个命令：

```
git status
```

它就像一个**安检扫描仪**，会用红色字体清清楚楚地列出你今天到底新建了哪些文件、修改了哪些文件。确认没问题后，再去执行 `git add .`，这样心里会非常

2、如果本地和云端不同步，可以先

```
git pull
```

拉取下来

默认main拉取用

```
git clone https://github.com/onlyum/LeetCodeRecord.git
```

分支拉取用

```
git clone -b master https://github.com/onlyum/LeetCodeRecord.git
```

3、用户名和梯子得设置下

```
# 设置用户名
git config --global user.name "onlyum"
# 设置邮箱
git config --global user.email "l442484769@163.com"

git config --global http.proxy http://127.0.0.1:7890
git config --global https.proxy http://127.0.0.1:7890
```

# 栈

## 84.柱状图中的最大矩形

单调栈--（根据高度递增）存储索引

加左右哨兵--置0排除左右边界出栈问题

# 堆

## 215.数组中的第K个最大元素

**方法1：堆（优先队列实现）**

```
//默认小顶堆，堆顶最小
PriorityQueue<Integer> heap = new PriorityQueue<>();

//弹出堆顶
heap.poll()

//入堆
heap.offer();

//看堆顶
heap.peek();
```

**方法2：快速排序**



## 347.前K个高频元素

Java优先队列（PriorityQueue）比较器逻辑

```
PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));
```

我们可以把这行 lambda 表达式拆解来看：

这里的 `a` 和 `b` 是存储在队列里的**元素**（即 `nums` 中的数字）。但是，我们不希望根据数字本身的大小排序，而是根据它们在 `map` 中对应的 **频率（Value）** 排序。

在 Java 的比较器定义中：

- 如果返回 **负数**：认为 `a` 小于 `b`，`a` 应该排在前面。
- 如果返回 **零**：认为 `a` 等于 `b`。
- 如果返回 **正数**：认为 `a` 大于 `b`，`b` 应该排在前面。

所以，`map.get(a) - map.get(b)` 的含义是：

- 如果 `a` 的频率比 `b` 低，结果为负，`a` 会被看作“更小”的元素。
- 因为 `PriorityQueue` 默认是**小顶堆**，它会将“更小”的元素（即频率最低的元素）放在**堆顶**。



遍历HashMap的键值用

```
for(int key:count.keySet()){}
```



## 295.数据流的中位数

维护一个大顶堆（所有都<=中位数）heapBefore和一个小顶堆（所有都>=中位数）heapAfter，从而可以从两个堆堆顶得到中位数

```
heapBefore = new PriorityQueue<>((a,b)-> b-a);//中位数之前，大顶堆
heapAfter = new PriorityQueue<>();//中位数之后，小顶堆
```

新进元素则根据两堆size判断

若放heapBefore里，先进heapAfter排序晒出最小，再入heapBefore；

若放heapAfter里，先进heapBefore排序晒出最大，再入heapAfter。

```
if(heapBefore.size()==heapAfter.size()){//优先放中位数前的大顶堆
    heapAfter.offer(num);
    heapBefore.offer(heapAfter.poll());
}else{//大顶堆多了就放小顶堆，维持平衡
    heapBefore.offer(num);
    heapAfter.offer(heapBefore.poll());
}
```



# 11.盛最多水的容器

## 方法1：双指针

水=短板*距离

从left=0,right=len-1开始，我们left++和right--都是为了尝试取到更多的水，如果短的板不动的话，取到的水永远不会比上次多。

# 15.三数之和

## 方法一：排序 + 双指针

Arrays.sort(nums数组);排序后+双指针找target = -nums[i]的两数之和（注意跳重复数字）

注意：要求“结果集去重”（不能有重复的三元组），所以不能先去重再处理（输入源去重）

# 23.合并K个排序链表

## 方法1：顺序合并（1，2合并，再和3合并如此循环到结束）

先写两两排序链表合并的函数

## 方法2：二分后一一合并（递归）

### 方法3：使用优先队列合并

# 25.K 个一组翻转链表

## 方法1：小段反转再长段拼接（k长度组内反转+和其他k长度组拼接）

# 31.下一个排列

## 方法1：字典序

**字典序的核心逻辑：** 为了让数字变大且增幅最小，我们必须**从后往前**找，把一个“较小的数”换成后面一个“比它大的数中最小的那个”，然后再把后面的部分按升序重新排列。

# 41.缺失的第一个正数

## 方法1：HashMap(原数组中操作实现)

```
//  结果必在[1, n+1]中
//  首先，讨论[1,n]
//  1、所有负值都置为n+1,则全为正整数
//  2、所有[1, n]范围的数对应下标位置的数字置为负
//  3、剩下最左边的非负[1,n]中的数对应位置下标为答案
//  最后，加入n+1
```

# 42.接雨水

## 方法1：动态规划

对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]

leftMax[i] 表示下标 i 及其左边的位置中，height 的最大高度

rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度

### 方法2：单调栈

## 方法3：双指针

双指针`left = 0`, `right = n-1`哪边小，哪边就向中间跳一步

注意if(height[left] < height[right])时leftMax肯定比rightMax更小,所以ans += leftMax - height[left];

# 46.全排列

## 方法1：回溯

利用深度搜索求解回溯问题的通用模板

```
void backtracking(参数) {    
    if (终止条件) {        
        存放结果;        
        return;    
    }    
    for (选择 : 本层集合中的元素) {        
        处理节点;        
        backtracking(路径, 选择列表); // 递归        
        撤销处理; // 回溯    
    } 
}
```



# 49.字母异位词分组

## 方法1：排序后HashMap

key为排序后字符串，value为所有对应字母异位词

```
List<int[]> res = new ArrayList<>();
在 `res.toArray(new int[res.size()][])` 中：
- **第一个 `[]`**：必须填，因为系统需要知道要准备多少个“位置”来放结果。
- **第二个 `[]`**：必须留空，因为结果直接复用 `List` 里的 `int[]` 数组，不需要重新定义每一行的长度。
注意：参数部分：new int[res.size()][]
这是在创建一个新的二维数组作为模板传给 toArray 方法。
```

# 54.螺旋矩阵

## 方法1：迭代（基于四个边界进行）

```
//  基于四个边界进行
int up = 0, down = m-1, right = n-1, left = 0;
//	分上下左右四条边，每条边走完后对应边界++或--
//	内部加结束判断.size()<=m*n，防止内部多走
```

# 56.合并区间

## 方法1：排序后迭代

按左边界排序，然后逐个迭代

# 70.爬楼梯

## 方法1：动态规划（循环替递归）

```
递归dp[i] = dp[i-1]+dp[i-2]有重复
直接循环
```



# 73.矩阵置零

## 方法2：矩阵首行首列标记

```
//  第一行和第一列用来记录，对应的行或列是否需要全0
//  全查询，记录需要置为0的行和列
//  处理第一行和第一列
```

# 74.搜索二维矩阵

| **搜索目标**                      | **中点取向 (mid) 代码公式**  | **循环条件**                                                 | **满足条件时的动作 (保留 mid)** | **不满足时的动作 (排除 mid)**       |                                                            |
| --------------------------------- | ---------------------------- | ------------------------------------------------------------ | ------------------------------- | ----------------------------------- | ---------------------------------------------------------- |
| **第一个满足条件的数 (左边界)**   | `low + (high - low) / 2`     | `while (low < high)`<br />不能加等号，因为low==high情况下死循环 | `high = mid`                    | `low = mid + 1`                     | `L` 和 `H` 总是向左收缩，确保 `mid` 在中间偏左（向下取整） |
| **最后一个满足条件的数 (右边界)** | `low + (high - low + 1) / 2` | `while (low < high)`<br />同样不加等号                       | `low = mid(<=target时)`         | `high = mid - 1`                    | `L` 和 `H` 总是向右收缩，确保 `mid` 在中间偏右（向上取整） |
| **精确查找**一个具体的数          | `low + (high - low) / 2`     | `while (low <= high)`                                        | `return mid`                    | `low = mid + 1` 或 `high = mid - 1` |                                                            |

# 76.最小覆盖子串

```
//	子串和窗口都创建HashMap
//	初始化子串对应的HashMap:(字符，字符出现次数)
//	//只对子串中出现的字符，窗口对应的HashMap对应记录出现次数
//	检查子串中字符出现次数是否被窗口HashMap覆盖，如果覆盖，则记录当前窗口左右位置 + 更新窗口
//  向右滑动，更新窗口的HashMap
```



# 94.中序遍历

## 方法1：递归

左子树——根节点——右子树

# 98.验证二叉搜索树

## 方法1：递归

```java
public boolean isValidBST(TreeNode node, long lower, long upper)
注意：通过传递 `lower` 和 `upper`，递归函数为每个子树设置了一个“允许范围”。
- 当进入左子树时，更新**上限**为当前节点的值。
- 当进入右子树时，更新**下限**为当前节点的值。 
这样，无论树有多深，底层的节点都知道自己不能越过的“红线”。
```

### 方法2：中序遍历



# 101.对称二叉树

## 方法1：递归

```java
我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，p 指针和 q 指针一开始都指向这棵树的根，随后 p 右移时，q 左移，p 左移时，q 右移。每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称。
return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
```

# 102.二叉树的层序遍历

## 方法1：广度优先算法

```java
首先根元素入队
当队列不为空的时候
求当前队列的长度curSize
依次从队列中取curSize个元素
   	用列表存储值并且把左右节点加入队列进行拓展，然后进入下一次迭代
Queue<TreeNode> queue = new LinkedList<>(); // 放入当前层所有的节点
	List<Integer> cur = new ArrayList<>();  // 放入当前层所有节点的值
```

### 层序遍历

```cpp
if(root == null) {
    return res;
}
Queue<TreeNode> queue = new LinkedList<>(); // 放入当前层所有的节点

queue.offer(root);
while(!queue.isEmpty()){
    int curSize = queue.size();	//记录当前层长度
    for(int i=0; i<curSize ; i++){	//遍历该层所有节点
        TreeNode node= queue.poll();	//遍历过的都出列，并把左右节点入列
        if(node.left!=null){
            queue.offer(node.left);
        }
        if(node.right!=null){
            queue.offer(node.right);
        }
    }
}
return res;
```

# 104.二叉树的最大深度

## 方法1：深度优先

每次都返回左右节点中比较大的height。

## 方法2：广度优先



# 108.有序数组转换为二叉搜索树

## 方法一：递归，中序遍历，总是选择中间位置左边的数字作为根节点

```
if(left > right){
    return null;
}
int mid = (left + right)/2;
TreeNode root = new TreeNode(nums[mid]);
root.left = binaryDST(nums, left, mid-1);
root.right = binaryDST(nums, mid+1, right);
return root;
```

# 114.二叉树展开为链表

## 方法1：前序遍历

```
public void preorderTraversal(TreeNode root){
    if(root == null){
        return;
    }
    对root操作;
    preorderTraversal(list, root.left);
    preorderTraversal(list, root.right);
}
```

# 199.二叉树的右视图

## 方法1：层序遍历

取每层最后一个node加入List

# 128.最长连续序列

## 方法1：HashSet	

确保当前数为连续序列的首个数字，再进入最长序列的寻找

# 136.只出现一次的数字

## 方法1：异或

1.任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。
2.任何数和其自身做异或运算，结果是 0，即 a⊕a=0。
3.异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。

# 138.随机链表的复制

## 方法1：HashMap解决

源节点作为键，复制的节点作为值，构建哈希图

# 146.LRU缓存

## 方法1：HashMap + 双向链表

```
class DLinkedNode 
{    
    int key;    
    int value;    
    DLinkedNode prev;   
    DLinkedNode next;    
    DLinkedNode(){}    
    DLinkedNode(int _key, int _value){key = _key;value = _value;}
}
```

# 153.寻找旋转排序数组中的最小值

## 方法1：二分查找

寻找旋转数组的最小值时，**一定要用 mid 去和 right（右边界）进行比较**。因为右边界可以充当一个“标杆”，如果 `mid` 比右边界小，说明右边是平滑的坡，崖底（最小值）在左边；如果 `mid` 比右边界大，说明崖底在右边。这种逻辑可以完美向下兼容未旋转的纯升序数组。

# 160.相交链表

## 方法1：双指针

分别从链表头开始，到底后跳转到另一链表头部，直到相遇或者都为null

# 169.多数元素

## 方法1：**摩尔投票算法 (Boyer-Moore Voting Algorithm)**。

它的核心逻辑非常霸气，可以总结为一句话：**“不同的数同归于尽，最后活下来的就是多数元素。”**

# 226.翻转二叉树

## 方法1：递归

直接递归

# 230.二叉搜索树中第K小的元素

## 方法1：中序遍历（迭代模拟递归）

```
Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
while(root != null || !stack.isEmpty()){
    while(root != null){
        stack.push(root);
        root = root.left;
    }
    root = stack.pop();
    root = root.right;
}
```

# 236.二叉树的最近公共祖先

## 方法1：递归

## 方法2：存储父节点

```
// 1、哈希表存储所有节点的父节点
Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
public void dfs(TreeNode root) {
    if (root.left != null) {
        parent.put(root.left.val, root);
        dfs(root.left);
    }
    if (root.right != null) {
        parent.put(root.right.val, root);
        dfs(root.right);
    }
}
//	2、利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
```



# 239.滑动窗口最大值

## 方法1：优先队列

**“返回负数不换位，返回正数要换位”**（以 `a` 为当前元素，`b` 为比较元素）

```
#优先队列比较器逻辑：一级根据大小降序，二级根据下标降序
PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
    public int compare(int[] pair1, int[] pair2) {
        return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
    }
});
```

### 方法2：单调队列？？

### 方法3：分块+预处理（与稀疏表类似）??

# 240.搜索二维矩阵 II

## 方法3：Z字形查找

```
由于行列均递增，故比较nums[x][y]与target
右上为起点：则x++, y--
左下为起点：则x--, y++
```



# 283.移动零

## 方法1：双指针

left和right都从找到的第一个0元素开始：操作“非零元素前移”，即与0元素交换位置即可

# 437.路径总和 III

### 方法1：递归

## 方法2：前缀和

```java
利用先序遍历二叉树，记录下根节点 root 到当前节点 p 的路径上除当前节点以外所有节点的前缀和，在已保存的路径前缀和中查找是否存在前缀和刚好等于当前节点到根节点的前缀和 curr 减去 targetSum。
Map<Long, Integer> prefix = new HashMap<Long, Integer>();
```

# 438.找到字符串中所有字母异位词

## 方法1：滑动窗口

26字母的数组实现Hash表，不停滚动

### ？？方法2：滑动窗口优化

# 543.二叉树的直径

## 方法1：深度优先

任意一条路径均可以被看作由某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接得到。

假设我们知道对于该节点的左儿子向下遍历经过最多的节点数 L （即以左儿子为根的子树的深度） 和其右儿子向下遍历经过最多的节点数 R （即以右儿子为根的子树的深度），那么以该节点为起点的路径经过节点数的最大值即为 L+R+1 。

转换成求递归左右子树中最大深度的问题。

# 560.和为 K 的子数组

## 方法2：前缀和+HashMap

```
HashMap存储(前缀和:出现次数),若[j,i]求和为k,则pre[i]-pre[j-1]=k,即寻找pre - k是否在HashMap中
```

# 1022.从根到叶的二进制数之和

## 方法1：递归

**后序遍历**的访问顺序为：左子树——右子树——根节点。我们对根节点 root 进行后序遍历：

如果节点是叶子节点，返回它对应的数字 val。

如果节点是非叶子节点，返回它的左子树和右子树对应的结果之和。

## 方法2：迭代

# 1356.根据数字二进制下 1 的数目排序

## 自定义排序函数

```
Collections.sort(list, new Comparator<Integer>(){
    public int compare(Integer x, Integer y){
        if(bitOfNum[x]!=bitOfNum[y]){
            return bitOfNum[x]-bitOfNum[y];
        }else{
            return x-y;
        }
    }
});
# 如下写法
Collections.sort(list, new Comparator<Integer>(){内部写自定义比较函数});
```

## 1、暴力解法

直接求余方法获取1的个数

## 2、递推解法：

我们定义 *bit*[*i*] 为数字 *i* 二进制表示下数字 1 的个数，则可以列出递推式：

*bit*[*i*]=*bit*[*i*>>1]+(*i*&1)

右移相当于整除2，加上奇偶判断i&1异或即可

# 1404.根据数字二进制下 1 的数目排序

## 1、直接模拟

直接求余方法获取1的个数

## 2、遍历计数：（发现在维持末尾为1的情况下，前面的数字其实都没变，计算除2操作+加1再除2操作次数即可）

我们以字符串 1100011100 为例：

一开始最低位为 0，这些 0 的「命运」就是被除二操作删除。我们通过两次除二的操作，得到字符串 **11000111**；

现在的最低位为 1，这些 1 的「命运」就是被加一操作变成 0 再被删除。我们通过一次加一操作将字符串变为 11001000，再进行三次除二操作，得到 **11001**。这一步的操作次数为 4；

现在的最低位为 1，我们通过两次操作（一次加一，一次除二）将字符串变为 **1101**。最低位仍然为 1，我们通过两次操作将字符串变为 111；最低位还是 1（思考一下，为什么最低位总是 1？除了一开始的情况，最低位可能为 0 吗？），我们通过一次加一操作将字符串变为 1000，产生了额外的进位，再通过三次除二操作将字符串变为 **1**。

通过上面的例子，我们可以归纳出每一个字符的「命运」以及需要的步骤数：

只有在一开始的时候，我们才需要考虑字符串最低位为 0 的情况，我们通过若干次操作删去低位的所有 0；

在任意时刻，字符串的最低位均为 1。如果有 k 个 1，那么我们需要 k+1 次操作（1 次加一操作和 k 次除二操作）将所有的 1 变为 0 并删除。并且在这 k+1 次操作后，原本最靠近右侧的那个 0 变为了 1。这也解释了为什么我们不需要考虑最低位为 0 的情况了。







手动实现一个堆（Heap）是理解二叉树、数组索引映射以及“上浮/下沉”逻辑的最佳方式。

## 1. 核心逻辑：数组实现二叉树

堆在逻辑上是一棵**完全二叉树**，但在底层通常用**数组**存储。利用完全二叉树的特性，我们可以通过索引直接找到父子节点：

对于索引为 $i$ 的节点：

- **左孩子**：$2i + 1$
- **右孩子**：$2i + 2$
- **父节点**：$(i - 1) / 2$

------

## 2. 手写小顶堆 (Min-Heap) 详解

我们需要实现两个核心操作：

1. **`offer(val)` (上浮/Swim)**：新元素加在末尾，不断与父节点比较，如果比父节点小就交换，直到位置正确。
2. **`poll()` (下沉/Sink)**：弹出堆顶元素，把数组最后一个元素换到堆顶，然后不断与较小的子节点比较并交换，直到恢复堆序。

#### Java 代码实现：

Java

```
public class MyMinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MyMinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    // 插入元素
    public void offer(int val) {
        if (size == capacity) return; // 简单处理，实际可扩容
        heap[size] = val;
        swim(size); // 执行上浮
        size++;
    }

    // 弹出堆顶
    public int poll() {
        if (size == 0) return -1;
        int res = heap[0];
        // 将最后一个元素移到堆顶
        heap[0] = heap[size - 1];
        size--;
        sink(0); // 执行下沉
        return res;
    }

    // 查看堆顶
    public int peek() {
        return heap[0];
    }

    public int getSize() {
        return size;
    }

    // 上浮逻辑：新元素往上找位置
    private void swim(int k) {
        while (k > 0 && heap[k] < heap[(k - 1) / 2]) {
            swap(k, (k - 1) / 2);
            k = (k - 1) / 2;
        }
    }

    // 下沉逻辑：堆顶元素往下找位置
    private void sink(int k) {
        while (2 * k + 1 < size) {
            int j = 2 * k + 1; // 左孩子索引
            // 找到两个孩子中较小的那个
            if (j + 1 < size && heap[j + 1] < heap[j]) {
                j++;
            }
            // 如果父节点已经比孩子小，停止下沉
            if (heap[k] <= heap[j]) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
```

------

## 3. 如何用这个手写堆解决“第 K 大”问题？

逻辑和使用 `PriorityQueue` 一模一样，只是换成了我们自己的类：

Java

```
public int findKthLargest(int[] nums, int k) {
    MyMinHeap minHeap = new MyMinHeap(k + 1);
    for (int num : nums) {
        minHeap.offer(num);
        if (minHeap.getSize() > k) {
            minHeap.poll();
        }
    }
    return minHeap.peek();
}
```

## 4. 深度思考

- **为什么 `poll` 时要用最后一个元素顶替堆顶？**

  如果直接删掉堆顶，树的结构就断开了。用末尾元素替代能保证它依然是一棵“完全二叉树”，我们只需要通过 `sink` 调整顺序即可。

- **复杂度**：

  - `offer` 和 `poll` 都是 $O(\log K)$，因为树的高度是 $\log K$。
  - 这就是为什么堆在处理大数据时非常高效。

你觉得手动实现之后，对 `PriorityQueue` 的底层运行机制是不是清晰多了？要不要挑战一下把这个堆改成支持**动态扩容**的版本？
