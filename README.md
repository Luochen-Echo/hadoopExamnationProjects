# 项目编写指南

#### 6月15完成项目
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/1685706644075.jpg)

<p align="center" />骐骥一跃，不能十步；驽马十驾，功在不舍</p>

#### 写在前面的话：
**1.写项目要勇敢**
<a align="center" href="https://imgse.com/i/pp2KAhQ"><img src="https://img2.doubanio.com/view/photo/l/public/p2229651022.webp" alt="pp2KAhQ.jpg" border="0" /></a>
根据我自身的学习项目的经历，往往都是因为自己没写过项目或者没有****技能比如不会javascript不会html不会flak不会springboot等借口来搪塞过去,实际上往往是需要先开始写才会懂得这些技能，写项目就是首先要不怕不甚求解，遇到什么不会就直接去google(真不建议百度，大部分情况下它反而会阻碍你的进步)，以目标驱动发展，而不是等你学完xxxx全套课程后在开始（学完了黄花菜都凉了），边做边学是最好的方法

**2.写代码是热爱，写到世界充满爱**
![](https://img9.doubanio.com/view/photo/l/public/p2179776895.webp)
要发自内心的想去写一个有意思的东西，而不是重复一些无意义的工作，不要划水开摆，会不会先且不论，但最起码能有所收获，不浪费时间。一起讨论解决问题的办法，共同攻克难题，选择在github上写代码是由于它的平台可以实现协同开发，可以大大加速开发效率，其实它不麻烦，如果以后还要写代码或者从事相关行业，提前了解它没啥坏处

**3.达成成就**
![](https://thumb11.jfcdns.com/2017-11/bce59fe774377d3d.png)
本项目预计使用**springboot**,**vue3**,以及综合了hadoop
用到了java,css,html,javascript,flume,hadoop,mysql,mybats-plus等技术知识，一套全流程，项目实现了**前后端分离**正所谓传统功夫和新时代大数据技术的集大成者，众所周知我和大四的学生开发了一个系统拿来给他做毕设，据我了解他们的毕设也不过如此，这个项目做好了胜过他们的毕设（你们继续在此基础添砖加瓦的话），认真写确实能学到不少东西，我也是刚开始学springboot,遇到问题大家一起解决，共同进步

**4.解决问题的办法：**
**首先谷歌，其次gpt,然后讨论询问小组成员**
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602195743.png)

**5.珍藏多年的武林秘籍**
![](https://picx.zhimg.com/v2-b32f1cbe89988687c50e7ecccd31bbb0_720w.jpg?source=172ae18b)
* [遇事不决先谷歌](https://www.google.com.hk/)
* **MDN**查询前端知识```https://developer.mozilla.org/zh-CN/```
* **freecodecamp**高质量视频教程```https://www.youtube.com/@freecodecamp```
* **stackoverflow**国外高质量提问平台 ```https://stackoverflow.com/```
* **下载找maven依赖的**```https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/3.1.0```
* **Bootstrap**我目前的前端页面都是抄他的```https://v5.bootcss.com/```
* **vue3的中文文档**```https://cn.vuejs.org/guide/introduction.html```
* **chatgpt**属于你的私人教授
* **Copilot**(建议淘宝购买github学生包使用)```https://github.com/features/copilot```
*  [科技强国](https://xftld.org/index.php#/dashboard)

![](https://raw.githubusercontent.com/martine-stdo/my_images/master/1685706611114.jpg)
******
## 正片开始
前端建议使用vscode编辑
建议安装拓展Vue Language Features提供前端代码补全
后端使用idea随各人喜好，可以任意使用你熟悉的编辑器

使用的框架springboot不需要你们下载打开用idea打开backend文件夹，重载一下pom里面的依赖
需要你们安装的是vue3，如果没有mysql的话需要安装mysql
####安装vue3流程如下：
```
安装@vue/cli
打开Git Bash，执行：

npm i -g @vue/cli
如果执行后面的操作有bug，可能是最新版有问题，可以尝试安装早期版本，比如：npm i -g @vue/cli@4

启动vue自带的图形化项目管理界面
vue ui
```
####安装mysql
我在资料文件夹里面放了一个mysql安装包可以用那个安装
安装方法如下：
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602173301.png)

![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602173524.png)
点击向右的箭头将所需的组件加入然后一直下一步就行（建议安装在c盘：一般编程环境或数据库这种我是习惯就默认位置）
#### 1.1 配置环境变量
将```C:\Program Files\MySQL\MySQL Server 8.0\bin```（如果安装到了其他目录，填写相应目录的地址即可）添加到环境变量PATH中，这样就可以在任意目录的终端中执行mysql命令了。

#### 1.2 mysql服务的关闭与启动（默认开机自动启动，如果想手动操作，可以参考如下命令）

关闭：```net stop mysql80```
启动：```net start mysql80```
#### 1.3 mysql的常用操作

连接用户名为root，密码为123456的数据库服务：```mysql -uroot -p123456```
```show databases;```：列出所有数据库
```create database kob;```：创建数据库
```drop database kob;```：删除数据库
```use kob;```：使用数据库kob
```show tables;```：列出当前数据库的所有表
```create table user(id int, username varchar(100));```：创建名称为user的表，表中包含id和username两个属性。
```drop table user;```：删除表
```insert into user values(1, 'yxc');```：在表中插入数据
```select * from user;```：查询表中所有数据
```delete from user where id = 2;```：删除某行数据

安装完成后使用idea的database可视化工具创建如下数据库：
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602175129.png)
一个叫kob的数据库，里面有一张叫user的表
四个列
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602175325.png)
id（设置为主键，并且为自增）,username,password,photo

所有工作完成后前端后端必须都启动才能正常运行代码以及实时监看代码功能
## 前端：
在终端使用vue ui启动脚手架后导入工程文件中的web文件夹
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602175948.png)
正常情况如下所示：
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602175724.png)
之后点击任务，点击运行
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602180217.png)
之后再点击输出就可以看到渲染的前端界面的端口号
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602180435.png)按住ctrl + 鼠标点击即可跳转前端界面
注意
**此时你依然无法快乐的写代码，因为你的后端还没有开启，登录和注册无法从后端数据库中读取用户信息，你还是无法进入前端界面查看功能**
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602180531.png)

## 后端:
用idea打开工程项目的backend文件夹然后点击pom.xml文件重新加载maven文件依赖（没错又是熟悉的感觉，各种爆红😅，这个时候别慌尝试一下clash的tun模式（google了解一下），头铁也可以直接用国内网慢慢下）
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602181308.png)这里面的用户名和密码连接mysql用的根据你自身的情况修改，端口号是后端运行的端口号建议就用我的默认配置
如果你的运气够好，配置没有问题运行后端
![](https://raw.githubusercontent.com/martine-stdo/my_images/master/20230602181534.png)看到如下没有报错那么恭喜你大功告成！！
成功把项目跑起来了（刚出新手村，还有代码等着你们写💁）
**！！！切记要想登录系统不要直接在数据库中加用户名和密码，系统的登录和注册均采用了jwt鉴权机制，数据库中的用户名和密码应该使用我提供的接口也就是前端的登录和注册来填写（聪明的你应该发现了，密码在后端数据库中存的是密文），总之你需要通过前端来登录和注册来实时观看你修改的前端页面**
如果你实在运行不起来可以向小组成员讨论或和roger(nighterdream)讨论



## hadoop期末大作业
实验要求

功能一
2022-2023 学年第二学期 
Hadoop 大数据开发技术期末大作业 
1）功能要求 
1.阅读 2 篇关于 Hadoop 大数据开发的英文文献（不少于 5000 英文
单词）。
1. 将该英文文献上传到 Hadoop 高可用集群中的 HDFS。
2. 调用 MapReduce 对文件中各个单词出现的次数进行统计。
3. 利用 Apache Flume 实时将两篇文章单词统计的结果，合并在一个
文档中，再次自动上传到 HDFS。
1. 设计一个带 web 界面的交互系统。

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