# Linux常用命令

### 一. find命令

Linux下的文件搜索命令。

> 比较耗费资源，尽量少用，应该将文件目录规划好，尽量避免搜索。
>
> 如果非要执行搜索，尽量缩小搜索范围。
>
> 如果在生产环境大范围的搜索文件，一定要选择在夜间服务器最空闲的时候进行，避免影响线上服务。

##### 1. 根据文件名称搜索

```shell
find / -name *filename? #根据名称查找，区分大小写
find / -iname *filename? #根据名称查找，不区分大小写
```

> -name 根据名称查找，区分大小写

> -iname 根据名称查找，不区分大小写

> *filename?
>
> *? 星号*是通配符，匹配任意字符
>
> ？也是通配符，只能匹配一个字符

##### 2. 根据文件大小来搜索

```shell
find path -size +204800 #查找path目录下文件大小大于100M的文件。
```

> Linux系统磁盘最小的存储单位是块，每个块512K，也就是0.5M，所以100M = 1M * 100 = 1K * 1024 * 100 = 1块 * 2 * 1024 * 100 = 204800

> 加号+表示文件大小 大于N块

> 减号-表示文件大小 大于N块

##### 3. 根据文件所有者来查找

```shell
find -user #根据文件所有者查找
```

```shell
find -group #根据文件所属的用户组查找
```

##### 4. 根据时间查找

```shell
find -amin #根据访问时间查找 access
```

```shell
find -cmin #根据文件属性修改时间查找 change
```

```shell
find -mmin #根据文件内容修改时间查找 modify
```

```shell
find / -amin -5 #查找5分钟之内被访问过的文件
find / -mmin +30 #查找被修改时间超过30分钟的文件
```

##### 5. 根据文件类型查找 

```shell
find / -type f #f表示文件 d表示文件夹 l表示软连接
```

##### 6. find -a / -o 多条件搜索

```shell
find / -name init -a -type d  #从/目录下查找文件文件名称为init并且类型为文件夹的文件
```

```shell
find / -name init -o -type l  #从/目录下查找文件文件名称为init或者类型为软连接的文件
```

##### 7. 操作符，对查找到的文件做操作

```shell
find / -name init -exec ls -l {} \; #列出查找到的文件的详细信息，无需确认
```

```shell
find / -name init -ok rm {} \; #删除查找到的文件，需要确认
```

> -ok 需要确认 -exec 无需确认
>
> 后面的 {} \;为固定格式

##### 8. 根据i节点查找文件

```shell
find / -inum 321001 #查找i节点为321001的文件
```

> 可以通过 ls -i 查看文件的i节点
>
> ll -i 的结果中，第一列为i节点
>
> 可以通过查找i节点，找到文件的硬链接，因为硬链接的i节点和文件本身是一样的

### 二. 查看文件信息

##### 1. cat / tac

```shell
cat service.sh 查看文件
cat -n service.sh 查看文件，并显示行号
tac service.sh 反向查看文件，从最后一行开始到第一行结束
```

##### 2. more 

翻页：F或空格向下翻页，不能向上翻页

换行：enter

退出：Q或q

> 不能向上翻页
>
> 不能搜索

##### 3. less

翻页：F或空格向下翻页，上下

换行：enter

退出：Q或q

> 可以向上翻页
>
> /abc 搜索abc  按字母n可以查找下一个搜索到的关键字 大写N上一个

##### 4. head

```shell
head -n service.sh #显示前n行，不输入n，默认显示10行
```

##### 5. tail

```shell
tail -n service.sh #查看最后n行, 默认10行
tail -f service.sh #动态显示文件末尾的内容
```

### 三. 用户,用户组和权限

##### 1. 用户管理

```shell
useradd user #添加用户
userdel user # 删除用户
userdel -s user #删除用户,同时删除家目录
passwd user #修改密码
```

##### 2. 用户组管理

```shell
chown user:group file #修改文件的所有者和组
chgrp group file #修改文件所在组
```

##### 3. 权限管理

```shell
chmod 权限 文件 #修改文件权限
```

##### 4. 查看当前所有登录用户

- who

```shell
# who
root     pts/0        2019-10-13 03:40 (192.168.20.1)
```

- w

```shell
# w
14:56:59 up 16:45,  1 user,  load average: 0.00, 0.00, 0.00
USER     TTY      FROM              LOGIN@   IDLE   JCPU   PCPU WHAT
root     pts/0    192.168.20.1     03:40    0.00s  0.06s  0.00s w
```

第一行表示uptime信息,跟uptime命令执行结果是一致的,请查看uptime命令解读

| 字段  | 解释                        | 备注                                             |
| ----- | --------------------------- | ------------------------------------------------ |
| USER  | 用户名                      |                                                  |
| TTY   | 登录方式                    | tty本地登录(localhost); pts远程登录(非localhost) |
| FROM  | 从哪里登录过来              |                                                  |
| LOGIN | 登录了多久                  |                                                  |
| IDLE  | 空闲时间                    | 从执行最后一个命令到现在空闲了多久               |
| JCPU  | 累计占用CPU时间             | 本次登录执行的所有命令累计占用CPU的时间总和      |
| PCPU  | 最后一次执行命令占用CPU时间 |                                                  |
| WHAT  | 最后一次执行的什么命令      |                                                  |

- uptime

```shell
# uptime
 15:10:44 up 16:59,  1 user,  load average: 0.00, 0.00, 0.00
```

| 字段                           | 解释                 | 备注                                 |
| ------------------------------ | -------------------- | ------------------------------------ |
| 15:10:44                       | 当前系统时间         |                                      |
| up 16:59,                      | 本次开机多久没关机了 | 时间越长表示系统稳定性越好           |
| 1 user                         | 有几个用户登录       |                                      |
| load average: 0.00, 0.00, 0.00 | 负载情况             | 过去1分钟,5分钟,15分钟系统的负载情况 |



### 四. 文件权限管理

##### 1. 修改文件权限

```shell
chmod 权限 文件名
```

##### 2. 权限

| 权限    | 数字    |
| ---- | ---- |
| r    | 4    |
| w    | 2    |
| x    | 1    |


| 权限    | 数字    |
| ---- | ---- |
| --x  | 1    |
| -w-  | 2    |
| -wx  | 3    |
| r--  | 4    |
| r-x  | 5    |
| rw-  | 6    |
| rwx  | 7    |

##### 3. 新建文件默认权限

```shell
umask #查看新建文件的默认权限, 默认022, 实际权限转换 777-022=755=rwxr-xr-x,新建文件不具备可执行权限,所以会看到rwxr--r--的默认权限,新建文件夹会是默认的755
umask -S #查看新建文件的默认权限,默认u=rwx,g=r-x,o=r-x
```

### 五. 查看命令帮助信息

##### 1. man

##### 2. which 查看命令位置

##### 3. whatis 查看命令位置,已经命令配置信息位置

##### 4. command --help 

##### 5. aprpos 获取配置文件相关信息

##### 6. info 基本等于man, 仅仅是格式不同

##### 7. help 查看shell内置脚本命令帮助信息

### 六. 文件解压缩

##### 1. zip unzip

##### 2. tar -zxvf  / tar -zcvf

##### 3. bzip2 / bunzip2 / bzip2 -d

##### 4. gzip / gunzip / gzip -d

### 七. 网络

##### 1. ping IP / ping -c 次数 IP

##### 2. ifconfig

##### 3. 给本机用户发送邮件

> 邮件保存地址为/var/spool/mail/用户名

###### 3.1 给在线用户发送邮件

```shell
write user # 给user单个用户发送邮件,用户必须在线才能发送
```

```shell
wall #给所有用户发送邮件,只给在线用户发送邮件,包括自己
```

> ctrl + d 保存发送

###### 3.2 给用户发送邮件,不管在不在线

```shell
mail user # 给user单个用户发送邮件
```

> ctrl + d 保存发送

```shell
mail # 进入邮件系统
h # 查看所有邮件列表,邮件列表中第一列的N表示new,是新邮件(未读)的意思,空格表示已读
2 # 查看邮件编码为2的邮件
d 2 # 删除邮件编码为2的邮件
exit # 退出邮件系统
```

##### 4. 查看登录记录

```shell
last # 可以查看登录用户,登录方式,从哪里登录,登录时间,退出登录时间. 以及本机重启的记录
```

```shell
lastlog # 查看最后一次登录信息
lastlog -u 用户ID # 根据用户ID查看最后一次登录信息
```

##### 5. 路由跟踪

```shell
traceroute www.baidu.com # 查看当前机器到百度服务器要走的路,通不通,到哪里不同都可以查看到
```

##### 6. 查看网络状态

三种常用命令

```shell
netstat -tlun # 查看本机监听的端口
netstat -an # 查看本机所有的网络连接
netstat -rm # 查看本机路由表
```

| -t      | -u      | -l   | -r   | -n                           |
| ------- | ------- | ---- | ---- | ---------------------------- |
| TCP协议 | UDP协议 | 监听 | 路由 | 显示IP地址和端口号(不用域名) |

> LISTEN 表示监听,TCP协议才会有这个状态,UDP协议不会有
>
> establish 表示正在连接

##### 7. 配置网络

```shell
setup # 可以配置IP,子网掩码,网关,DNS等信息
```

> 这个命令是RedHat专用的命令,其它Linux不存在这个命令

### 八. 挂载

Linux的非图形化界面的系统,都不会自动挂载外接硬盘设备,需要手动来进行挂载操作.

Windows系统会自动识别挂载外接硬盘,所以不需要手动操作.

```shell
mount [-t] [iso9660] /dev/sr0 /mnt/cdrom/ # 将外接硬盘/dev/sr0挂载到/mnt/cdrom/下
umount /dev/sr0 # 取消挂载/dev/sr0
```

> /mnt/cdrom 旧版本的挂载目录为/mnt,一般会手动在/mnt下面新建一个名称为cdrom的文件夹,用来挂载外接硬盘. 
>
> /media 新版本的Linux系统一般用/media来做外接硬盘的挂载目录

> Redhat6以上的外接硬盘默认为/dev/sr0

### 九. 关机重启

- shutdown

```shell
shutdown -h now # 立即关机
shutdown -h 20:00 # 今天晚上八点关机
shutdown -r now # 马上重启
shutdown -h 20:00 # 今天晚上八点重启
shutdown -c # 取消定时关机或取消定时重启
```

> 推荐使用shutdown命令进行关机或重启,因为比较安全,系统会自动保存数据

- halt 关机

- poweroff 关机

  > 相当于拔电源,不推荐使用

- init 0 关机

- reboot 重启

- init 6 重启

| init值 | 解释                       | 描述                                                   |
| ------ | -------------------------- | ------------------------------------------------------ |
| 0      | 关机                       |                                                        |
| 1      | 单用户                     | 启动最少的服务运行,类似于Windows中的dos,主要用于做修复 |
| 2      | 不完全多用户,不包含NFS服务 | NFS:NetWorkService, 不可以通过网络服务共享             |
| 3      | 完全多用户                 | 系统默认                                               |
| 4      | 未分配                     |                                                        |
| 5      | 图形化界面                 |                                                        |
| 6      | 重启                       |                                                        |

> 可以通过 /etc/inittab查看或修改init 配置信息

```shell
runlevel # 查看系统运行级别,和之前的运行级别
init 0 # 切换运行级别到0
```

- logout

```shell
logout # 退出登录
```

### 十. grep

##### 1. 文件中查找

```shell
grep hello start.sh # 在start.sh文件中查找包含'hello'的行
```

### 十一. df -h 查看分区