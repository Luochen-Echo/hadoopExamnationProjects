# hadoopExamnationProjects
### 一篇伟大的长篇巨著，期末的成绩就靠各位的双手来创造了
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202023-01-06%20145211.png)

使用的框架springboot不需要你们下载打开用idea打开backend文件夹，重载一下pom里面的依赖
需要你们安装的是vue3
安装流程如下：
```
安装@vue/cli
打开Git Bash，执行：

npm i -g @vue/cli
如果执行后面的操作有bug，可能是最新版有问题，可以尝试安装早期版本，比如：npm i -g @vue/cli@4

启动vue自带的图形化项目管理界面
vue ui
```

hadoop期末大作业
实验要求

功能一
2022-2023 学年第二学期 
Hadoop 大数据开发技术期末大作业 
1）功能要求 
1.阅读 2 篇关于 Hadoop 大数据开发的英文文献（不少于 5000 英文
单词）。
2. 将该英文文献上传到 Hadoop 高可用集群中的 HDFS。
3. 调用 MapReduce 对文件中各个单词出现的次数进行统计。
4. 利用 Apache Flume 实时将两篇文章单词统计的结果，合并在一个
文档中，再次自动上传到 HDFS。
5. 设计一个带 web 界面的交互系统。

功能二
（二）系统开发方向
网盘系统的设计和开发：使用Java语言，Spring等JavaEE开发框架，选择MySQL或HDFS文件作为数据存储手段，基于Hadoop开发一个网盘系统，主要包括系统前台和后台两个部分。网盘系统实现的基本功能包括以下几个：
  注册：用户注册账户；
  登录：用户登录个人账户；
上传：从用户本地发送文件到服务器；
查询：查看服务器上存储文件的情况；
下载：用户从服务器上下载指定的文件到用户本地；
删除：用户删除服务器上的文件资源。

代码初步预期
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/hadoop01.png)
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/hadoop02.png)