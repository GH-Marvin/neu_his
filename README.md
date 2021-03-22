#                           云医院管理项目

### **链接数据库**

```yaml
server:
  port: 8085
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/his?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: 123456
  thymeleaf:
    prefix: http://127.0.0.1:8080/
    suffix: .html

#mybatis-plus:
#  configuration:
#    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```



## 1、登录模块

- 登录成功后会通过cookie存储该用户的id以及姓名信息
- 利用滑块进行人机认证

![登录样式1](https://github.com/GH-Marvin/ImageLab/blob/master/HIS_image/login_image1.png)
![登录样式2](https://github.com/GH-Marvin/ImageLab/blob/master/HIS_image/login_image2.png)





## 2、门诊模块

### (1) 挂号页面
**挂号的病历号id自动生成方法**

- 利用数据库自增主键的唯一性，当新增一个患者时，数据库插入，获取自增主键值
- 通过计算获得类似于600601形式的病历号名
- 然后通过病历号将删除

```java
/**
     * getMaxId() 根据MybatisPlus的select注解获取患者表自增主键
     * map        存储键值对数据，与JSON匹配  HashMap<String, List<ConstantVO>>
     * caseNum    病历号
     *
     * @return ResultVO 结果视图
     */
    @GetMapping("/initConstant")
    public ResultVO initConstant() {
        Map<String, List<ConstantVO>> map = new HashMap<>();
        //map加入初始化的病历号
        Patient patient = new Patient();
        // 获取自增主键值
        int id = patientService.getMaxId();
        // 计算出匹配的病历号
        String caseNum = String.valueOf(id+600601);
        patient.setCaseNumber(caseNum);
        // 插入，让自增键递增
        patientService.insert(patient);
        // 放入map内
        ConstantVO constantVO = new ConstantVO();
        constantVO.setId(0);
        constantVO.setName(caseNum);
        List<ConstantVO> caseNumList = new ArrayList<>();
        caseNumList.add(constantVO);
        map.put("caseNumber",caseNumList);
        // 删除，防止冗余空记录
        patientService.remove(caseNum);
```

- MySQL查询患者表自增主键

```sql
SELECT Auto_increment
FROM information_schema.`TABLES` 
WHERE table_schema = 'his' AND table_name = 'patient';
```



![门诊挂号](https://github.com/GH-Marvin/ImageLab/blob/master/HIS_image/menu1.png)



## 3、医生工作站

###(1) 处方开立页面—选择药品

**动态分页查询药品数据**

- 使用Layui封装的table绑定事件

```js
table.render({
    elem: '#add_medicine',
    id: 'add_medicine',
    url: 'http://localhost:8085/prescribe/initDrugsList',
    defaultToolbar: ['filter'],
    text: { none: '无药品数据' },
    skin: 'line',
    page: {
        limit: 8,
        limits: [5,8,10,15,20]
    },
    size: 'sm',
    toolbar: "#westDiagnosis_toolbar",
    cols: [],
    data: []

});
```

- 它会发送携带limit和page参数的GET请求

```js
Request URL: http://localhost:8085/prescribe/initDrugsList?page=1&limit=8
```

- 后端利用MybatisPlus的IPage类写入该参数，实现分页并封装Drugs类为视图类对象传到前端

```java
	@Override
    public DataVO<DrugsVO> findAll(Integer page , Integer limit) {
        // 使用Layui默认JSON格式规范封装查询结果
        DataVO<DrugsVO> dataVO = new DataVO<>();
        dataVO.setCode(0);
        dataVO.setMsg("");
        // 通过page对象作为分页依据
        // 对原sql语句通过limit来进行分页的效果
        IPage<Drugs> itemsIPage = new Page<>(page , limit);
        IPage<Drugs> result = drugsMapper.selectPage(itemsIPage, null);
        List<Drugs> list = result.getRecords();
        // 封装为视图对象
        List<DrugsVO> drugsVOList = list.stream().map(e -> new DrugsVO(
                e.getDrugsId(),
                e.getDrugsCode(),
                e.getDrugsName(),
                e.getDrugsFormat(),
                e.getDrugsPrice(),
                e.getDrugsUnit(),
                constantItemService.findNameById(e.getDrugsDosageId()),
                constantItemService.findNameById(e.getDrugsTypeId())
        )).collect(Collectors.toList());
        // 通过count来进行查询总条数的限制
        dataVO.setCount(result.getTotal());
        dataVO.setData(drugsVOList);
        return dataVO;
    }
```

![门诊挂号](https://github.com/GH-Marvin/ImageLab/blob/master/HIS_image/menu5.png)
