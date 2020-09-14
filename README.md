清华课程小助手
==================
一款真正懂清华学生的android课程管理APP

**国内CDN高速下载链接**

https://cos.starrah.cn/THUCourseHelper.apk  

此链接会始终保持为最新版本（即Github代码库的最近一次Release的版本）。如您有需要获取老版本，请到Github代码库的Release列表中自行获取。

**github地址**

前端：https://github.com/Starrah/THUCourseHelper

后端：https://github.com/Starrah/THUCourseHelperBackend

## 功能点一览
- 课程表、日程表界面
  - 课程表日程表磁贴显示
  - 点击上方切换当前周查看
  - 点击右下角加号添加新日程
  - 点击磁贴查看日程详细信息
- 日程详情界面
  - 浏览日程详情
  - 点击右上角按钮，编辑日程或删除日程
- 日程编辑界面
  - 编辑日程详情
  - 各种编辑控件（如周选择控件）
  - 设置提醒
- 信息发布界面
  - 查看课程作业（指未过DDL的作业）和期末考试
  - 查询教室资源：可根据教学楼名做关键词检索
  - 查询其他信息：点击即可跳转到信息发布的URL
- 设置界面
  - 登录、同步课程数据
  - 各种设置，如课程表5天7天显示、切换背景、备份恢复数据、常驻通知栏和开关等等。
  - 问题反馈和检查更新
- 桌面小部件和常驻通知栏
  - 开启的方式：桌面小部件，安装成功并打开软件一次后，回到桌面、进入桌面配置（根据手机不同，比如华为是双指向内划）就能在小部件里找到我们的APP了。常驻通知栏则需要您进入软件的设置页面予以打开。
  - 如果您当天有多项日程，那小部件、通知栏的右侧会出现可点击的按钮、可切换查看您今天的各个日程。

## 使用协议
使用本软件，即代表您知悉并同意以下所列各条内容。如果您有任何不同意，请不要使用本软件。您可以就该协议的相关内容在Issue中发出提问。
1. 我们承诺，在任何情况下，**您的密码都不会被发回本软件的后端服务器**。您的密码只会在经HTTPS加密的前提下发送到tsinghua.edu.cn一级域名下的一些站点中，用以完成登录、获取数据以实现功能。
2. 除非您在登录界面选择保存密码，否则您的密码不会存储在您的设备当中；如果您在登录界面选择了保存密码，则您的密码会利用Android系统的加密机制妥善保存在您的设备中，具有一定的安全性，在大多数情况下不会泄露。但您应知悉这并不是万无一失的；例如有恶意软件获取了您系统的root权限的情况，该种加密方式仍有被攻破的可能。
3. 我们为您提供**可选的**将您的日程数据备份到我们的服务器上的服务。如果您使用这一服务，应当知悉并同意以下内容；如果您不同意，请不要使用这一服务。
   - 为了完成这一服务，我们需要获取您登录tsinghua.edu.cn一级域名的一些站点的Cookie信息，并以HTTPS加密方式发送到我们的服务器上。
   - Cookie不同于密码，它只是对您身份的一种临时凭据，且经过一段时间会自动失效。
   - 我们使用您登录tsinghua.edu.cn的Cookie，**会获取您的学号信息**，以确认您的身份、从而为您备份或恢复数据。**您的学号会和您备份的数据一同保存在数据库中，以标志这就是您存储的数据。** 除了学号以外，您的姓名等任何其他的个人信息不会被我们保存。
   - 因个人开发者精力和经费所限，**我们保留随时终止该项服务的权利，可以在不另行通知您的情况下终止该服务**。一旦此情况发生，**则您之前备份在我们服务器上的数据将无法找回，我们对此不承担任何责任**。
4. 您在使用本软件的过程中，我们可能收集一些软件的数据、日志等，并回传到我们的服务器。但这些数据均经过匿名处理，不会包含任何能够确认您的身份的信息，也不会包含您创建的日程的具体内容。
5. 我们承诺，不会因除了国家机关依法要求以外的任何原因，而向任何除了本软件的开发者、维护者以外的个人或机构提供您的任何信息（包括您备份的日程数据、您的学号信息等等）。
6. 我们将尽最大努力保证您的数据的安全，包括在我们的服务器上使用一些安全机制等。**但是，对于我们的服务器遭受外来攻击等原因造成的可能的您的数据泄露，我们不承担任何责任。**
7. 为了方便您获取本软件，我们为您提供基于CDN内容分发的下载服务。**但您不得恶意使用这一服务，不得因正常获取使用本软件以外的原因请求这一服务，不得在一段时间内多次反复使用这一服务下载本软件，不得使用任何脚本请求这一服务**。如果因该服务被滥用造成维护者财务支出过大，我们有权随时终止这一服务。

## 开源协议
MIT License

## 鸣谢
本项目中获取网络学堂课程作业部分，使用了[thu-learn-lib](https://github.com/Harry-Chen/thu-learn-lib)。
拉取课程数据部分，感谢@RikaSugisawa 提供的帮助。

## 基本信息

### 1.开发环境

- Kotlin 1.3.71
- Android最低API级别 26
- AndroidAPI级别 29
- Gradle 5.6.4

### 2.目录结构

/app/src/main/java：代码目录

- ​	activity：日程详情显示和编辑
- ​	data：数据定义和数据库
- ​	fragment：MainActivity的几个Fragment，还有登录界面
- ​	information：信息显示模块
- ​	onlinedata：前后端交互、和学校信息系统交互（含学校数据源CourseDataSource定义）
- ​	picker：周选择器
- ​	remind：提醒模块
- ​	service: 定时任务（刷新小部件、通知栏、刷新作业）等用到的Service、BroadcastReceiver等等
- ​	table：课程/日程表
- ​	utils：其他实用的函数
- ​	widget：小部件和常驻通知栏

/app/src/main/res：资源目录

- ​	layout/calendar_item_edit：详情编辑
- ​	layout/calendar_item_edit：详情显示
- ​	layout/fragment：MainActivity的几个Fragment，还有登录界面
- ​	layout/information：信息显示
- ​	layout/picker：周选择器
- ​	layout/table：课程/日程表
- ​	layout/widget：小部件和常驻通知栏
- ​	menu：下方导航栏
- ​	xml：小部件定义
- ​	drawable：素材
- ​	values：常量和颜色等定义

## 功能介绍和亮点说明

### 1.加载与主界面

在进入主界面之前，我们会显示加载界面几秒钟，此时后台进行数据的加载。

主界面使用BottomNavigationView作为底部导航栏，拥有四个可以互相切换的Fragment

![main](https://github.com/Starrah/THUCourseHelper/raw/master/preview/main.gif)

### 2.课程/日程表

#### 2.1 能横向，纵向滑动的课程表

我们使用两层scrollview来实现这一功能：一方面，上方的日期是一个横向滑动的scrollview；另一方面，下方是一个纵向scrollview套一个横向scrollview。之后，为了让上方的日期和下方的课程同时滑动，我们使用了绑定的方法：当监听到其中一个scrollview滑动的时候，自动将另一个scrollview滑动到对应位置[[1]](https://blog.csdn.net/lixpjita39/article/details/73180546)。

![table](https://github.com/Starrah/THUCourseHelper/raw/master/preview/table.gif)

#### 2.2 根据设置动态设置ui，显示日程

我们在每次onstart时，都会重新设置ui---根据设定的常数和读取的手机屏幕大小，全局设置等更新各个控件大小，来实现切换显示方式等功能，以及适配不同手机。

我们在每次onstart时也会刷新日程显示，具体方法是清空课程显示位置的所有日程时间段view，然后根据数据库读取结果动态放置日程时间段。其中，日程时间段的放置位置由课程数据计算得到，其颜色由其日程做哈希得到，而且每个时间段的Tag都会被设置成对应日程id，便于点击进入详情显示界面。

#### 2.3 边框的显示

日期，时间这些地方的实线边框，我们通过设置背景图为有实线边框的图片实现。

纵向的虚线边框，我们通过设置每个日程放置位置（也就是周一-周日下方的一整个长条）的背景图来实现，这里的背景图是透明，只有虚线右边框的，以显示背景图片，以及保证虚线边框不堆在一起。

横向的虚线边框，我们通过代码动态设置，也就是把一条横虚线放在每个放置位置的对应高度。

同时，为了保证虚线边框和背景图片相适应（比如空白背景需要黑色边框，绿色背景需要白色边框），我们给每张背景图片设置了边框颜色，在onStart设置边框时根据背景图片修改边框颜色。

### 3.详情显示

我们在上方显示整个日程的整体信息，然后在下方通过scrollview来显示每个时间段信息。时间段信息是动态加载到scrollview上的。

![show](https://github.com/Starrah/THUCourseHelper/raw/master/preview/show.jpg)

### 4.详情编辑

#### 4.1 主体结构

由于每个日程有多个时间段，而且时间段可以动态增，删，改，我们采用recyclerview来放置每个时间段，用onBindViewHolder函数来进行动态更新。

那么我们是如何实现根据各种选项动态更新界面的呢？首先，我们在xml里定义了所有可能出现的编辑行，在onBindViewHolder函数里动态根据这个时间段的信息来显示，隐藏编辑行。其次，我们给每个选择器和文本框都绑定了监听事件，一旦监听到这些位置的值发生改变，就立即更新recyclerview的数据，之后recyclerview会根据更新后的数据来更新显示。

![edit](https://github.com/Starrah/THUCourseHelper/raw/master/preview/edit.jpg)

#### 4.2 多级选择器

多级选择器在我们的项目中应用广泛。设置日期和时间，设置大节-小节情况，设置各种选项都需要这种选择器。我们采用了一个开源库PickerView[[2]](https://github.com/Bigkoo/Android-PickerView)，它被广泛应用于招行信用卡的“掌上生活”等商业软件中。

![multiple_picker](https://github.com/Starrah/THUCourseHelper/raw/master/preview/multiple_picker.jpg)

#### 4.3 周选择器

我们在开源项目SimpleDayPicker[[3]](https://github.com/informramiz/SimpleDayPicker)上做了大量修改，完成了周选择器。原项目只支持周一-周日的多选，我们修改之后，支持了根据学期信息修改周选择，以及选择单周，双周，前半学期，全学期等快捷键。

![week_picker](https://github.com/Starrah/THUCourseHelper/raw/master/preview/week_picker.jpg)

### 5.信息显示

后端的数据库中维护一个数据表，表示当前所有能够提供的服务。
其中有些是一段时间之内不会变化的内容，则静态的存储在数据库里或建立静态的Web服务。
另一些是需要从清华大学校园网系统中获取的服务，且具有一定的时效性；这些则通过定时访问校园网获取相关内容，并缓存到后端服务器上，前端在请求时直接从后端服务器请求缓存的内容，以实现无需连接校园网就能获取信息的便捷。

前端通过一个接口，获得后端所能提供的服务的列表，内含每个服务的名称和对应的URL。
前端通过动态创建按钮，每个按钮绑定前往对应URL的intent，实现了信息显示。值得一提的是，对于空教室显示，我们通过监听搜索框与字符串匹配实现了教室搜索功能，这样大大改进了用户体验，因为原来教室有几十个，很难找到自己要的教室。

![information](https://github.com/Starrah/THUCourseHelper/raw/master/preview/information.gif)

### 6.设置界面与登录

#### 6.1 设置界面

我们的设置界面直接使用安卓原生的PreferenceFragmentCompat，扩展和修改非常方便。当然，因为原生界面的限制，我们只能强制在设置界面使用白背景，否则文字直接放在背景上比较难看。

#### 6.2 登录

首先，因为学校选课系统的登录需要验证码，我们的登录界面对于验证码的处理就很重要。我们用一个ImageView来放置验证码，已进入登录界面就发送请求获取验证码图片。我们还给这个ImageView绑定点击事件来点击切换验证码。因为学校选课系统的验证码固定只有数字和大写字母，我们还通过结果转大写实现了验证码不区分大小写功能。

其次，我们设置了一些小功能来让登录更加用户友好，比如长按按钮显示密码，登录后会显示一个加载控件等。

### 7.小部件与常驻通知栏

我们的小部件和常驻通知栏要实现动态更新数据，以及通过上下按钮来切换显示。我们的实现思路如下：首先，我们使用BroadcastReceiver来接收更新小部件/常驻通知栏显示的广播，并且给上/下按钮绑定切换显示日程的广播。其次，为了数据相对持久的存储，我们用静态变量来存储对应的日程数据。最终，//todo：动态更新

![widget](https://github.com/Starrah/THUCourseHelper/raw/master/preview/widget.gif)

### 8.提醒模块

提醒模块主要利用安卓的AlarmManager，这个类可以实现在指定的时间发送一个指定的广播。
通过一个函数setRemindTimerServiceForAll，可以为所有提醒调用AlarmManager、设置定时Intent。这个函数每当进入本APP（LoadingActivity前台加载）、设备重新启动（因为重新启动会导致闹钟清除）都会调用一次，此外每隔1h也会定时调用一次，以保证我们能在指定的时间收到系统发来的Intent。  
  
对于通知栏提醒来说，上述“指定的时间”就是设置的提醒时间。  
而对于闹钟来说，上述“指定的时间”是设置的提醒时间提前2分钟，会在这一时间收到广播然后调用系统的闹钟API，设置一个2分钟后响铃的闹钟。  
  
这一事情在Android10上还有点复杂：因为它[禁止后台应用启动前台Activity](https://developer.android.google.cn/guide/components/activities/background-starts?hl=zh-cn)。而安卓系统提供的设置闹钟API必须通过startActivity来调用。  
为此采用了让收到定时Intent的Receiver创建一个最高优先级的通知（全屏Intent），并绑定一个前台服务，之后就可以在前台服务中启动自己的前台Activity，进而启动设置闹钟的Activity了。几百毫秒后相关服务自动终止、通知也随之消失。

### 9.数据定义

数据定义使用的是Room库。这是一个枯燥而没什么亮点的工程。  
  
值得一提的可能是大量使用kotlin的val属性（计算属性），从而把各种需要计算的工作和数据实体放在一起实现，既有利于数据实体的可维护性，也减轻了前端的工作量、不需要太多的显示转换就可以按合理的方式显示内容。
以及定义Verifiable接口，实现数据内容的合法性验证。
还有在数据库中使用了FTS全文检索，所以可以快速的搜索任何字符串。虽然现在的用武之地不是很明显，但算是对新技术的一种尝试，也为之后比如搜索日程之类的功能留足了空间。

### 10.清华数据获取

在学校数据源方面，进行了良好解耦。抽象类AbstractCourseDataSource可以用于不同的数据源，比如之后实现研究生版本的数据获取或是其他学校的数据获取，只需要实现一个AbstractCourseDataSource的子类就可以了。欢迎大家进行二次开发！

而清华本科的数据源THUCourseDataSource，主体是选课系统。通过Chrome开发者工具、Fiddler、Postman等各种方法，以及在@RikaSugisawa 的巨大帮助下，分析了学校系统的接口结构，请求各个接口，并用正则等工具对接口返回的内容进行处理、把html转换为格式化的CalendarItemData数据。

### 11.后端服务

主要有以下几项：
1. 学期数据和上课时间表数据提供。其中学期数据自带节假日安排，前段根据此数据调整节假日显示。
2. 一些课程比如思政、体育，学分和课时数存在不对应，此时用默认的根据学分推断课时数的策略就会失败。所以后端有一个特判表，前端请求这个表对特殊课程的课时数做一个修正。**如果还有您所参加的其他课程存在自动产生的课时数与真实课程数不对应的话，欢迎您意见反馈给我们（通过上方Issue）！**


### 12.功能亮点

- 登录可一键获取选课数据
- 自动适配学校假期安排
- 能横向，纵向滑动的课程/日程表 
- 课程/日程表边框的显示
- 详情编辑的界面动态更新
- 周选择器
- 通知栏和闹钟两大提醒方式
- 用户友好的登录界面
- 小部件和常驻通知栏
- 信息发布，可获取作业数据和考试数据
