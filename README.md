# Min_Message
Springboot+Redis+MyBatisPlus+Thymeleaf
此项目为SpringBoot学习总结练手项目，旨在巩固学习知识。
使用MybatisPlus逆向工程生成业务类，在SQL方面使用了两表的联表查询，批量删除等。在实体类中，使用了枚举类型。
在业务方面，自己手动写了登录业务，没有使用安全框架。登录注册业务使用email发送验证码，验证登录。验证码保存在Redis中。
在登陆后，进行了CRUD的一些操作。
此项目只为练手，熟悉SpringBoot框架常用注解与Redis的整合，以及自己部署上线。

