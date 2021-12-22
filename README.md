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

  ## UI
  - Personal-Center-page
  <img width="156" alt="image" src="https://user-images.githubusercontent.com/55613486/147069387-0fd7c552-9cc3-4e93-836c-5ff2adcf22df.png">
  - Front-page
  <img width="155" alt="image" src="https://user-images.githubusercontent.com/55613486/147069563-9751a8ae-1906-4f90-bcbb-86562c0eec3a.png">
  - Article-list-page
  <img width="155" alt="image" src="https://user-images.githubusercontent.com/55613486/147069564-cb6f3a30-4a4c-4dc3-aa1f-cbbd8bf350ec.png">
  - Aticle-Search-page
  <img width="156" alt="image" src="https://user-images.githubusercontent.com/55613486/147069712-cf1b88cd-3119-42a0-82c3-51b03d79298d.png">
  - Diagnosis-page
  <img width="154" alt="image" src="https://user-images.githubusercontent.com/55613486/147069742-43f70c03-b89d-4ea0-8c9f-3837180b8c5d.png">
  - Diagnosis-process-page
  <img width="140" alt="image" src="https://user-images.githubusercontent.com/55613486/147069794-281980dc-da8e-41fd-b5be-1f809932c3ed.png">
  - Forum-page
  <img width="154" alt="image" src="https://user-images.githubusercontent.com/55613486/147069845-7cffea4a-829d-48fe-8a30-1220582dc48e.png">
  - Forum-Search-page
  <img width="127" alt="image" src="https://user-images.githubusercontent.com/55613486/147069920-57ba9d30-36bb-46b3-ae03-447abeb754e3.png">
  - Forum-Search-Detail-page
  <img width="128" alt="image" src="https://user-images.githubusercontent.com/55613486/147069958-097c56c2-2a97-483c-b985-405579359388.png">
  - Send-Post-page
  <img width="154" alt="image" src="https://user-images.githubusercontent.com/55613486/147070005-504eb860-d573-489a-9554-8a93015a9626.png">

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
