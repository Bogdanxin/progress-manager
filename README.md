# API

## Tips：

* url ： host + uri
  * exampl :
    + host : http://182.92.121.195:8080/
    + uri : /login
    + url : http://182.92.121.195:8080/login

## 1.User

**tips：**

* userType :  
  + 0 = 注册用户，只能看到自己课程进展 
  + 1 = 管理用户，能看到所有老师的课程进展和所有学生信息

### 1.1 注册 *

* POST  /registered

* payload:

  ```json
  {
  	"teacherId":14,
  	"userName":"李四",
  	"password":"1234567890qwe",
  	"userAcademy":"信息学院",
  	"token":"",
  	"userType":0
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

* ps：注册都是将用户的userType设置为0，特殊情况再说

### 1.2 退出登录 *修改了

* POST  /exit/{token}

* request中带有token，就是既在路径上有token，也要在request的header中带有token

  例如：

  <img src="C:\Users\公维信\AppData\Roaming\Typora\typora-user-images\image-20200316145116651.png" alt="image-20200316145116651"  />

* return 

  ```json
  {
      "code": 0,
      "message": "退出成功！"
  }
  ```




### 1.3 登录

* GET  /login

* payload：

  ```json
  {
      "teacherId":13434,
      "password":"sdfasdfsdf"
  }
  ```

  

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "userType": 0,
          "userName": "张三",
          "userId": 2,
          "token": "8221ef60a5c84142ae58f8aaed6eca12"
      }
  }
  ```



### 1.4 删除指定Id用户 *

* DELETE  /deleteUserById?id=2

* payload: request的header带有token，判断userType是否=1

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```



### 1.5 获取指定id的用户信息 *

* GET  /getUserById?id=3

* request

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "teacherId": 14,
          "userType": 0,
          "userName": "李四",
          "userId": 3,
          "userAcademy": "信息学院",
          "token": "fsdf2343sdfsdflj434jl"
      }
  }
  ```

  

## 2.课程



###　2.1 添加一个课程

* POST  /addCourse

* payload:

  ```json
  {
  	"courseName":"c语言",
  	"courseIntroduction":"c语言教程",
  	"courseVideoProgress":"已上传3部",
  	"courseHours":43,
  	"courseFinishHours":6,
  	"userId":3,
  	"createTime":"2020-3-4 8:07:33"
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

  

### 2.2 删除指定id的课程 *

* DELETE  /deleteCourseById?id=4

* payload：token

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```

  

### 2.3 修改指定id的课程信息 *

* POST  /updateCourseById?id=1 

* token 这里的userType是0才可以，只能本课程的教师才能修改

* payload:

  ```json
  {
  	"courseName":"高数",
  	"courseHours":47,
  	"userId":4,
  	"courseIntroduction":"高等数学"
  }
  ```

  因为是修改信息，可以有不填的信息

* return:

  ```json
  {
      "code": 0,
      "message": "修改成功!"
  }
  ```

  

### 2.4 获得指定id的课程信息

* GET  /getCourseById?id=1

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "courseIntroduction": "高等数学",
          "courseName": "高数",
          "courseHours": 47,
          "courseVideoProgress": "上传一个视频",
          "createTime": "2020-03-12 18:56:03.0",
          "courseFinishHours": 8,
          "courseId": 1,
          "userId": 4
      }
  }
  ```



### 2.5 获得指定id的课程课时进程

* GET  /getCalculation/{id}

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": "17.02%"
  }
  ```



### 2.6 获得指定id课程的视频进程

* GET /getVideoProgress/{id}

* return

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": "上传一个视频，类似这种信息"
  }
  ```

  * 这个方法有待讨论



### 2.7 更新课时进度

* POST  /updateHours/{id}?increaseHours=4

* token userType只能是0，只能本人更改

* return

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```



### 2.8 更新视频上传进程

* 这个我们要再讨论一下，可以先放一下




## 3. 签到

### 3.1 添加一个某个时间的未签到事件 *

**注意：这里只能添加一个未签到，之后实现多个添加**

后端会处理：如果传入的学生id不存在，报错：“不存在该学生信息，请先添加学生信息”，如果课程信息不存在，报错：“不存在该课程信息，请先添加该课程信息”

* POST  /addAbsence

* payload:

  token和

  ```json
  {
  	"studentId":2018214505,
  	"courseId":2,
  	"createTime":"2020-3-15"
  }
  ```

* return:

  ```json
  {
      "code": 1,
      "message": "不存在该课程信息！请先添加该课程信息！"
  }
  ```



### 3.2 批量添加签到记录 *

* POST  /addAbsences

* payload:

  request + 

  ```json
  [
      {
  		"courseId":14,
  		"studentId":2018214505,
  		"createTime":"2020-3-4"
  	},
      {
  		"courseId":1,
  		"studentId":2018234567,
  		"createTime":"2020-3-5"
  	}
  ]
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "14": "该课程信息不存在，请先添加！"
          },
          {
              "1": "添加成功！",
              "2018234567": "添加成功!"
          }
      ]
  }
  ```

  

### 3.3 删除一个签到记录  *

同时使该记录的学生的未签到记录减一

* DELETE  /deleteAbsence?courseId=1&studentId=2018214505

* token userType = 1

* return：

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```



### 3.4 获取某个时间点的课程签到情况 *

**仔细想有问题的，只能确定某日的，到具体时间就完了**

* GET  /getAbsence?id=1&date=2020-3-15

* token 

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "createTime": "2020-03-15",
          "studentId0": 2018214505,
          "studentId1": 2018242434,
          "absenceStudentNum": 2
      }
  }
  ```

  **这里返回值有该签到的时间、未签到人数和未签到的id，id形式为 student+数字**

### 3.5 获取指定某天的签到 *

**指的是某一天所有的签到**

* GET  /getAbsenceByDate?date=2020-3-15

* return：

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "studentId": 2018214505,
              "courseId": 1
          },
          {
              "studentId": 2018242434,
              "courseId": 1
          }
      ]
  }
  ```

  **这里有问题的是：我暂时没想将相同课程id的信息合并到一起的方法，所以暂时返回json是这样的**

## 4.学生

### 4.1 添加一个学生

* POST  /addStudent

* token

* payload：

  ```json
  {
  	"studentId":2018216585,
  	"studentName":"张思"
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```



### 4.2 删除指定id的学生

* DELETE  /updateStudent?id=2018242434

* token

* payload

  ```json
  {
  	"studentName":"张三",
      "absenceTimes":3
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```

  

### 4.3 获取指定id的学生信息

* GET　/getStudentById?id=2018214505

* token

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "studentId": 2018214505,
          "studentName": "bogdan",
          "absenceTimes": 1
      }
  }
  ```




### 4.4 批量添加学生信息

* POST  /addStudents

* token

* payload:

  ```json
  [
      {
  		"studentId":2018214505,
  		"studentName":"张三"
  	},
  	{
  		"studentId":2018234567,
  		"studentName":"李四"
  	},
  	{
  		"studentId":2019234678,
  		"studentName":"bogdan"
  	}
  ]
  	
  ```

* return：

  这里我会传过来每个学号的相应的添加信息，根据学号对应返回信息好了，下面就是所有的三种情况

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": [
          {
              "2018214505": "该学生已经录入，无需再次记录。"
          },
          {
              "2018234567": "该学生已经录入"
          },
          {
              "2019234678": "录入失败"
          }
      ]
  }
  ```

  