# API

## Tips：

* url ： host + uri
  * example :
    + host : http://182.92.121.195:8080/
    + uri : /login
    + url : http://182.92.121.195:8080/login

## 1.User
e


### 1.1 注册

* POST  /registered

* payload:

  ```json
  {
  	"teacherId":14,
  	"userName":"李四",
  	"password":"1234567890qwe",
  	"userAcademy":"信息学院",
  	"token":"fsdf2343sdfsdflj434jl",
  	"userType":1
  }
  ```

* return:

  ```json
  {
      "code": 0,
      "message": "添加成功！"
  }
  ```

  

### 1.2 退出登录

* POST  /exit/{id}

* return 

  ```json
  {
      "code": 0,
      "message": "退出成功！"
  }
  ```

  目前这个方法不成熟，还不用做api



### 1.3 登录

* GET  /login?teacherId=1234354&password=123

* return:

  ```json
  {
      "code": 0,
      "message": "success!",
      "data": {
          "userType": 0,
          "userName": "张三",
          "userId": 2,
          "token": null
      }
  }
  ```



### 1.4 删除指定Id用户（一般用不到，需要request不会做）

* DELETE  /deleteUserById?id=2

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```



### 1.5 获取指定id的用户信息

* GET  /getUserById?id=3

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

  

### 2.2 删除指定id的课程

* DELETE  /deleteCourseById?id=4

* return:

  ```json
  {
      "code": 0,
      "message": "删除成功！"
  }
  ```

  

### 2.3 修改指定id的课程信息

* POST  /updateCourseById?id=1

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

* POST  /getCourseById?id=1

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



###　2.7 更新课程进度

* GET  /updateHours/1?increaseHours=4

* return

  ```json
  {
      "code": 0,
      "message": "修改成功！"
  }
  ```

  