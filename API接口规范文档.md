## API接口规范文档

### 微信小程序相关配置

AppID:wxd114542303074ab3

AppSecret:69f783d47856a0bf57265e725fcdf0a9

### 接口规范

#### 规则

- API 的统一访问地址为：<domain>/api
- 所有API 请求都必须通过HTTPS进行身份验证和发起
- 请求形式为GET和POST
- 数据传输编码为 UTF-8
- 所有数据均为 JSON 格式

#### 鉴权方式

需要验证权限的接口需要客户端http请求提供token,该token应通过登录获得，并保存在本地，作为客户端登录态的证明。

#### 统一返回格式

| 名称      | 描述                                                         |
| --------- | ------------------------------------------------------------ |
| status    | 状态码，标识请求成功与否，如 [1:成功；-1:失败]               |
| errorCode | 错误码，给出明确错误码，更好的应对业务异常；请求成功该值可为空 |
| errorMsg  | 错误消息，与错误码相对应，更具体的描述异常信息               |
| data      | 返回结果，通常是 Bean 对象对应的 JSON 数据, 通常为了应对不同返回值类型，将其声明为泛型类型 |

####  错误对照表

所有 API 使用 **状态码+错误码** 的响应方式来表示错误原因。接口正确统一返回HTTP 状态码为 `2xx` 的正确响应。接口错误则统一返回 HTTP 状态码为 `400` 的错误响应，同时响应内容会返回`错误码（code）`和`错误信息（msg）`

```json
HTTP/1.1 400
Content-Type: application/json

{
  "status":1,
  "errorCode": 8303,
  "errorMsg": "超出请求频率限制",
  "resultBody":[data]
}
```

状态返回码对照表如下：

| 状态返回码 | 说明     |
| :--------- | :------- |
| 2xx        | 响应成功 |
| 400        | 响应失败 |
| 502        | 网关异常 |

当状态返回码为400时，服务器会返回具体的错误码（code）和错误信息（msg），对照表如下：

| 错误码 | 说明           |
| :----- | :------------- |
| 1001   | code为空       |
| 1002   | 请求异常       |
| 1010   | 查询数据不存在 |
| 1017   | 权限不足       |
| 1085   | token错误      |
| ...    | ...            |
| <code> | <msg>          |

### 个人中心API

#### 用户登入

**请求格式**

> GET /api/wx/wx_login

**请求参数**

| 参数         | 类型   | 说明                               |
| ------------ | ------ | ---------------------------------- |
| code         | String | 使用wx.login()获得的用户登录凭证。 |
| rawData      | String | 非敏感的用户信息                   |
| signature    | String | 用户数据签名                       |
| encrypteData | String | 加密数据                           |
| iv           | String | 加密密钥                           |

**请求示例**

```http
GET /user/login
{
	code:"code",
	rawData:[
		{
          "nickName": "Band",
          "gender": 1,
          "language": "zh_CN",
          "city": "Guangzhou",
          "province": "Guangdong",
          "country": "CN",
          "avatarUrl": "http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0"
        }
	],
	signature:"",
	encrypteData:""
}
```

**响应内容**

| 参数     | 类型   | 说明                     |      |
| -------- | ------ | ------------------------ | ---- |
| token    | string | 服务端生成的自定义登录态 |      |
| userInfo | List   | 用户微信账户信息列表     |      |



```json
{
  "data":[
      token:"213123",
      user_info:{
      "openId": "OPENID",
      "nickName": "NICKNAME",
      "gender": GENDER,
      "city": "CITY",
      "province": "PROVINCE",
      "country": "COUNTRY",
      "avatarUrl": "AVATARURL",
      "unionId": "UNIONID",
      "watermark":
        	{
            	"appid":"APPID",
            	"timestamp":TIMESTAMP
        	}
		},
	],  
  "errorCode": "",
  "errorMsg": "",
  "status": 1
    
}
```

------

#### 用户信息查询

**请求说明**

用户每次启动小程序时，检查本地是否有缓存的登录Token。然后用此token调用接口，请求用户数据

**请求格式**

> GET /api/user/user_retrieve

**请求数据类型**:`application/x-www-form-urlencoded`

**请求参数**

| 参数名称      | 参数说明                                            | 请求类型             | 是否必须 | 数据类型 | schema |
| ------------- | --------------------------------------------------- | -------------------- | -------- | -------- | ------ |
| Authorization | Header里名为Authorization，代表客户登录态，用于鉴权 | header（请求头参数） | true     | string   |        |
| user_id       | 用户微信Openid                                      | query                | true     | string   |        |

**请求示例**

```http
GET /user/info_retrieve
-H Autorization "mytoken"
{
	openid:"fa65d73e67f8488994ee8bc98088c348",
}
```



**响应内容**

| 参数 | 类型  | 说明         |      |
| ---- | ----- | ------------ | ---- |
| data | Array | 多条数据集合 |      |

```json
{
  "data": 
    {
      "ciPhonenumber": null,
      "ciPersontype": 1,
      "ciIdCard": null,
      "ciLevel": 1,
      "ciProfile": "https://thirdwx.qlogo.cn/mmopen/vi_32/fTey0lfI4UZiaOicKgdjj38scTWslhVPXfuDicYQ7VnRZ36QrZPeiaVa2XPniaVNZYPyS5lYJy2DVtAgM91zyH95Y0Q/132",
      "ciNkname": "Drew_zhl",
      "ciId": "ocu9t5MdG5DHl7XUx4Z6VyfWugY0"
    },
  "errorCode": "",
  "errorMsg": "",
  "status": 1
}
```

---

#### 用户诊断历史查询

**请求格式**

> GET /user/diagnose_history_retireve

**请求参数**

| 参数   | 类型   | 说明           |
| ------ | ------ | -------------- |
| openid | String | 用户微信openid |

**请求示例**

```http
GET /user/diagnose_history_retrieve
TOKEN:<YOUR_TOKEN>
{
	openid:"fa65d73e67f8488994ee8bc98088c348",
	token:"asd"
}
```



**响应内容**

| 参数             | 类型   | 说明                   |      |
| ---------------- | ------ | ---------------------- | ---- |
| data.id          | String | 诊断流程id             |      |
| data.patient     | String | 病人名称               |      |
| data.datatype    | int    | 诊断数据类型           |      |
| data.status      | int    | 该诊断流程所处于的状态 |      |
| data.doctor      | String | 医生                   |      |
| data.submit_time | date   | 诊断发起日期           |      |

```json
{
	code:0,
	msg:"成功",
	data:{
		list:[
            {
                "id":"a",
                "patient":"zl",
                "datatype":0,
                "status":4,
                "doctor":"zhl",
                "submit_time":"2021-10-10",
            },
            {
               	"id":"b",
                "patient":"zl",
                "datatype":1,
                "status":3,
                "doctor":"zhl",
                "submit_time":"2021-10-11",
            }
        ]
	}
}
```

---

#### 用户诊断记录查询

**请求格式**

> GET /get/user/diagnose_retireve

**请求参数**

| 参数  | 类型   | 说明       |
| ----- | ------ | ---------- |
| id    | String | 诊断记录id |
| token | String | 用户登录态 |

**请求示例**

```http
GET /user/diagnose_history_retrieve
TOKEN:<YOUR_TOKEN>
{
	id:"a",
	token:"asd"
}
```



**响应内容**

| 参数             | 类型   | 说明                   |      |
| ---------------- | ------ | ---------------------- | ---- |
| data.patient     | String | 病人名称               |      |
| data.datatype    | int    | 诊断数据类型           |      |
| data.status      | int    | 该诊断流程所处于的状态 |      |
| data.doctor      | String | 医生                   |      |
| data.opinion     | String | 诊断意见               |      |
| data.submit_time | date   | 诊断发起日期           |      |
| data.result      | Array  | 诊断结果               |      |

```json
{
  "data": [
    {
      "diStatus": null,
      "diSubmittime": "20211128 21:41:040",
      "diType": 2,
      "diAnltime": null,
      "diId": "3189ce37-7db9-4782-b2b8-0bcc3f14ff9c",
      "diDoctorSug": null,
      "diDoctorid": null,
      "diAck": null,
      "diDataAud": null,
      "diDataAct": null,
      "diPatientId": "ocu9t5MdG5DHl7XUx4Z6VyfWugY0",
      "diDitime": null,
      "diPatientNkname": "郑浩龙",
      "diAnlResult": null
    },
    {
      "diStatus": null,
      "diSubmittime": "20211128 21:40:450",
      "diType": 1,
      "diAnltime": null,
      "diId": "d2ef223c-e822-43e8-a35a-c61016c1113c",
      "diDoctorSug": null,
      "diDoctorid": null,
      "diAck": null,
      "diDataAud": null,
      "diDataAct": null,
      "diPatientId": "ocu9t5MdG5DHl7XUx4Z6VyfWugY0",
      "diDitime": null,
      "diPatientNkname": "郑浩龙",
      "diAnlResult": null
    }
  ],
  "errorCode": "",
  "errorMsg": "",
  "status": 1
}
```

---

#### 通知消息推送

本接口是webhook反向API接口，没有http请求。

**推送内容：**

| 字段              | 类型   | 说明                                                         |
| :---------------- | :----- | :----------------------------------------------------------- |
| op                | string | “data_create_message”，固定值                                |
| data              | json   | 推送内容                                                     |
| data.to           | json[] | 被提醒人列表                                                 |
| data.message_type | string | 推送内容类型                                                 |
| data.notify_text  | string | 提醒文字，默认为“有新数据提交，请及时处理”，可在提醒设置中自定义 |
| data.content      | string | 详细内容，有值的表单字段标题和字段值。例如：“单行文本: 123\n多行文本: 1233\n数字: 123\n成员单选: codingmagic1\n部门单选: 研发\n日期时间: 2019-06-13” |
| data.url          | string | 静态消息链接，属于被提醒人的个人消息，登录后可访问           |
| send_time         | string | 推送时间                                                     |

**推送数据样例：**

```json
{
	op: "data_create_message",
	data: {
		to: [
			{
				username: "z",
				name: "小郑"
			}, {
				username: "h",
				name: "小浩"
			}
		],
		entry_name: "论坛消息",
		notify_text: "你有新的消息，请查看",
		content: "大龙回复你的帖子：你的问题很有建树！",
		url: "https://xxx/bbs/{postname}"
	},
	send_time: "2021-10-20T22:41:51.430Z"
}
```

---

### 论坛模块接口

#### 获取论坛帖子列表

> GET /get/bbs/post_list/?{page}

#### 查看单条帖子

> GET /bbs/post_read/?{post_id}

#### 搜索帖子

> GET /bbs/post_search/?{keyword1}&{keyword2}

#### 发布内容

> POST /post/bbs/post_content

#### 删除帖子

> POST /post/bbs/delete_post

#### 发表评论

> POST /post/bbs/post_comment

### 诊断模块接口

## 提交诊断表单

**接口地址**:` POST /api/diagnose/submit_diagnose`

**请求参数**:

|                |          |          |          |          |        |
| :------------- | :------- | :------- | :------- | :------- | :----- |
| 参数名称       | 参数说明 | 请求类型 | 是否必须 | 数据类型 | schema |
| activityUrl    |          |          | false    | string   |        |
| audioUrl       |          |          | false    | string   |        |
| openId         |          |          | true     | string   |        |
| patientComment |          |          | false    | string   |        |
| token          | token    | header   | true     | string   |        |

**请求示例**

```http
{
  "activityUrl": "测试url",
  "audioUrl": "",
  "openId": "ocu9t5MdG5DHl7XUx4Z6VyfWugY0",
  "patientComment": ""
}
```



**响应参数**:


| 参数名称 | 参数说明 | 类型   | schema |
| -------- | -------- | ------ | ------ |
| form_id  | 表单id   | string |        |

**响应示例**:

```javascript
{
  "data": "成功",
  "errorCode": "",
  "errorMsg": "",
  "status": 1
}
```

## 诊断进度查询

**接口地址**:`GET /api/diagnose/process_check`

**请求参数**:

| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型 | schema |
| :------- | :------- | :------- | :------- | :------- | :----- |
| form_id  | form_id  | query    | true     | string   |        |
| token    | token    | header   | true     | string   |        |
| user_id  | user_id  | query    | true     | string   |        |


**请求示例**:


```javascript
GET /api/diagnose/process_check
-H token<YOUR_TOKEN>
{
  	"user_id":"123",
    "form_id": "123"
}
```

**响应参数**:


| 参数名称    | 参数说明           | 类型           |
| ----------- | ------------------ | -------------- |
| anlTime     | 机器分析结束时间   | string         |
| diTime      | 医生诊断时间       | string         |
| doctorName  | 诊断医生名字       | string         |
| doctorSug   | 医生意见           | string         |
| patientName | 病人名字           | string         |
| status      | 该诊断表单目前状态 | integer(int32) |
| submitTime  | 该诊断表单提交时间 | string         |
| type        | 诊断类型           | integer(int32) |

**响应示例**:

```javascript
{
  "data": {
    "patientName": "Drew_zhl",
    "doctorName": "doct",
    "submitTime": "20211129 22:44:250",
    "diTime": "19701002 00:00:000",
    "anlTime": "19701002 00:00:000",
    "doctorSug": null,
    "type": 0,
    "status": 0
  },
  "errorCode": "",
  "errorMsg": "",
  "status": 1
}
```

#### 医生提交诊断意见

> POST /api/diagnose/submit_doctor_opinion

#### 病人医生自动建立对话

> POST /post/diagnose/auto_create_session

#### 获取诊断数据

> GET /get/diagnose/diagnose_result

### 咨询模块接口

#### 获取医生列表

> GET /get/consult/doctor_list?{page}

**接口地址**:`/api/consult/doctor_list`

**请求参数**:


| 参数名称 | 参数说明 | 请求类型 | 是否必须 | 数据类型       | schema |
| -------- | -------- | -------- | -------- | -------------- | ------ |
| page     | page     | query    | true     | integer(int32) |        |

**响应参数**

| 参数名称    | 参数说明 | 类型   | schema |
| ----------- | -------- | ------ | ------ |
| doctor_id   |          | string |        |
| doctor_name |          | string |        |


**响应示例**:
```javascript
{
	"doctor_id": "",
	"doctor_name": ""
}
```


#### 获取资讯历史

> GET /get/consult/history_retrieve

**接口地址**:`/api/consult/history_retrieve`


**请求参数**:


暂无

**响应参数**:


| 参数名称     | 参数说明 | 类型              | schema            |
| ------------ | -------- | ----------------- | ----------------- |
| consult_time |          | string(date-time) | string(date-time) |
| doctor_id    |          | string            |                   |
| doctor_name  |          | string            |                   |
| patient_id   |          | string            |                   |
| patient_name |          | string            |                   |


**响应示例**:
```javascript
{
	"consult_time": "",
	"doctor_id": "",
	"doctor_name": "",
	"patient_id": "",
	"patient_name": ""
}
```

#### 结束咨询

> POST /post/consult/end_consult

#### 发起咨询

> POST /post/consult/submit_consult

### 知识模块接口

#### 获取科普文章列表

> GET /get/info/get_article_list

#### 浏览科普文章详情

> GET /get/info/check_article?{article_id}

#### 文章点赞

> POST /post/info/like_article

#### 文章浏览量更新

> POST /post/info/update_read_num

#### 文章搜索

> GET /get/info/search

### 文件服务接口

#### 文件上传

> POST /post/file/upload

#### 文件下载

> POST /get/file/download

### 通信模块接口

#### 消息发送

> POST /post/websocket/send 

### 接口列表


| 接口功能             | 请求格式                                        |
| -------------------- | ----------------------------------------------- |
| 用户登入             | GET /get/user/login                             |
| 用户登出             | GET /get/user/logout                            |
| 用户信息查询         | GET /get/user/info_retrieve                     |
| 用户诊断历史查询     | GET /get/user/diagnose_history_retireve         |
| 用户诊断记录查询     | GET /get/user/diagnose_retireve                 |
| 消息推送             | Webhook反向API                                  |
| 获取论坛帖子列表     | GET /get/bbs/post_list/?{page}                  |
| 查看单条帖子         | GET /get/bbs/post_read/?{post_id}               |
| 搜索帖子             | GET /get/bbs/post_search/?{keyword1}&{keyword2} |
| 发布内容             | POST /post/bbs/post_content                     |
| 删除帖子             | POST /post/bbs/delete_post                      |
| 发表评论             | POST /post/bbs/post_comment                     |
| 提交诊断表单         | POST /post/diagnose/submit_diagnose             |
| 进度查询             | GET /get/diagnose/progress_check?{diagnose_id}  |
| 医生提交诊断意见     | POST /post/diagnose/submit_doctor_opinion       |
| 病人医生自动建立对话 | POST /post/diagnose/auto_create_session         |
| 获取诊断数据         | GET /get/diagnose/diagnose_result               |
| 获取医生列表         | GET /get/consult/doctor_list?{page}             |
| 获取咨询历史         | GET /get/consult/history_retrieve               |
| 发起咨询             | POST /post/consult/submit_consult               |
| 结束咨询             | POST /post/consult/end_consult                  |
| 获取文章列表         | GET /get/info/get_article_list                  |
| 浏览文章详情         | GET /get/info/check_article?{article_id}        |
| 文章点赞             | POST /post/info/like_article                    |
| 文章浏览量更新       | POST /post/info/update_read_num                 |
| 文件上传             | POST /post/file/upload                          |
| 文件下载             | POST /get/file/download                         |
| 消息发送             | POST /post/websocket/send                       |

