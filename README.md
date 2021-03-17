# MoocMusic
在慕课网 学习Android  慕课音乐 的一个Demo
项目概述:
###项目仿照网易云音乐App开发，代码使用Java编写，实现开屏页面三秒自动进入、页内动画效果、黑胶唱片播放功能等。同时实现登陆、注册、密码修改及合法性验证等用户管理功能。 
技术细节：
###登录、注册页面用户手机号合法性验证功能使用了AndroidUtilCode中RegexUtils类库来判断手机号的合法性；使用定时器实现三秒过渡动画，使用Realm数据库来存储用户登录信息；
技术难点：
###黑胶唱片播放器播放逻辑的实现，通过Glide的transformation背景高斯模糊效果的实现，自定义动画实现播放暂停；

APP 图标

![](https://github.com/nice98k/MoocMusic/blob/master/APP%E6%88%AA%E5%9B%BE/APP%E5%9B%BE%E6%A0%87.png)
