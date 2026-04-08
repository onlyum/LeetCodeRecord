# github同步步骤：

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

