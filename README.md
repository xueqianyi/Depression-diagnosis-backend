# Depression-diagnosis-backend
A small program with separated front and back ends, here is the back end part, the framework uses: springMVC+redis+elasticsearch+MySQL
  
  ## Technology-stack
|  功能/接口   |          技术          |
| :----------: | :--------------------: |
|   模糊搜索   | Elastic Search搜索引擎 |
|    数据库    |     phpStudy+MySQL     |
|   接口架构   |       SpringMVC        |
|  浏览量记录  |       redis缓存        |
|  持久层框架  |          JPA           |
| 在线实时通信 | Netty-Socket/Socketio  |
|   数据安全   |      BouncyCastle      |
## Interface overview
|         模块          | 接口数目 |          具体接口          |
| :-------------------: | :------: | :------------------------: |
| 首页（知识/名医模块） |    6     |      首页热门知识接口      |
|                       |          |      首页医生列表接口      |
|                       |          |        医生详情接口        |
|                       |          |      知识分类列表接口      |
|                       |          |        知识详情接口        |
|                       |          |        知识搜索接口        |
|         论坛          |    4     |        论坛发帖接口        |
|                       |          |        论坛列表接口        |
|                       |          |        论坛搜索接口        |
|                       |          |      论坛评论上传接口      |
|  个人（健康信息表）   |    2     |   用户健康信息表保存接口   |
|                       |          | 用户健康信息表是否完善接口 |
|         诊断          |    3     |      诊断文件上传接口      |
|                       |          |   获取某用户诊断历史接口   |
|                       |          |      诊断结果查询接口      |
|         登陆          |    2     |      微信用户登陆接口      |
|                       |          |      用户信息查询接口      |
|        聊天室         |    2     |        发起聊天接口        |
|                       |          |        查询聊天接口        |
|         总计          |    19    |                            |
